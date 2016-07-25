package com.blog.j2cache;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blog.j2cache.ehcache.EhCacheProvider;
import com.blog.j2cache.rediscache.RedisCacheProvider;
import com.blog.util.StringUtil;

/*
 * Authored by CJ
 */
@SuppressWarnings("rawtypes")

public class CacheManager{
	       	
	private static ICacheProvider L1_provider;
	
	private static ICacheProvider L2_provider;
	
	private static final String CONFIG_FILE = "jgroup.file";
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheManager.class);
	
	private static JGroupCacheChannel listener;
	
	private static boolean isInitialized = false;
	
	private static final int LEVEL_1 = 1;
	private static final int LEVEL_2 = 2;
	
		
	/*
	 * 初始化manager
	 */
	public static void init(JGroupCacheChannel listener){
		LOG.info("initializing  CacheManager....");
		CacheManager.listener = listener;
		Properties prop = new Properties();
		try {
			prop.load(CacheManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
		} catch (IOException e) {
			throw new CacheException("load "+CONFIG_FILE+" file failed.");
		}
		L1_provider = getinitProvider(prop.getProperty("cache.L1.provider_class"));
		L1_provider.init(prop);
		L2_provider = getinitProvider(prop.getProperty("cache.L2.provider_class"));
		L2_provider.init(prop);
		isInitialized = true;
		LOG.info("初始化成功.");
	}
	
	private static ICacheProvider getinitProvider(String str){
		if("ehcache".equals(str)){
			return new EhCacheProvider();
		}else if("redis".equals(str)){
			return new RedisCacheProvider();
		}else{
			throw new CacheException("no cache provider named by :" +str);
		}
	}
	
    /*
     *获取缓存中的值
     */
    public static Object get(String region,Object key){
    	if(isInitialized==false){
    		throw new CacheException(CacheErrorCode.Cache_Error_Not_Initialized);
    	} 
    	Object result = null;
    	if(StringUtil.isEmpty(region)||key==null){
    		throw new CacheException(CacheErrorCode.Cache_Error_NullParameter);
    	}
    	result = CacheHelper.get(LEVEL_1, region, key);
    	if(result!=null){
    	   LOG.info("从一级缓存中取值,region:"+region+",key:"+key+",value:"+result);
           return result;
    	}else{
    	   result = CacheHelper.get(LEVEL_2, region, key);
    	   if(result!=null){
        	   LOG.info("从二级缓存中取值,region:"+region+",key:"+key+",value:"+result);
               CacheHelper.set(LEVEL_1, region, key, result); //填充一级缓存
    	   }
    	}
        return result;	         
    }
    
    /*
     * 写入缓存
     * 
     */
    public static void set(String region,Object key,Object value){
    	if(isInitialized==false){
    		throw new CacheException(CacheErrorCode.Cache_Error_Not_Initialized);
    	}  
    }
    
    /*
     * 删除缓存
     */
    public static void evict(String region,Object key){
    	CacheHelper.evict(LEVEL_1, region, key);   //删除一级缓存
    	CacheHelper.evict(LEVEL_2, region, key);  //删除二级缓存
    	listener.sendEvictCmd(region, key);  //发送组播，通知同组其他节点删除cache
    }
    
    /*
     * 批量删除缓存
     */
    public static void batchEvict(String region,List<Object> keys){
    	CacheHelper.batchEvict(LEVEL_1, region, keys);   //删除一级缓存
    	CacheHelper.batchEvict(LEVEL_2, region, keys);  //删除二级缓存
    	listener.sendEvictCmd(region, keys);  //发送组播，通知同组其他节点删除cache
    }
    /*
     * 清除缓存
     */
    public static void clear(String region){
    	CacheHelper.clear(LEVEL_1, region);
    	CacheHelper.clear(LEVEL_2, region);
    	listener.sendClearCmd(region);
    }
    
    /*
     * 获取keys集合
     */
    public List keys(String region){
    	return CacheHelper.keys(LEVEL_1, region);
    }
    
    /*
     * 辅助工具 
     */
	private static class CacheHelper{
    	
    	static ICache getCache(int level,String region,boolean autoCreat){
    		return (level==1?L1_provider:L2_provider).buildCache(region, autoCreat, listener);
    	}
    	
    	static Object get(int level,String region,Object key){
    		ICache cache = getCache(level, region, false);
    		if(cache!=null){
    			return cache.get(key);
    		}else{
    			return null;
    		}
    	}
    	
    	static void set(int level,String region,Object key,Object value){
    		if(StringUtil.isNotEmpty(region)&&key!=null&&value!=null){
				ICache cache = getCache(level, region,true);
				if(cache!=null){
		    		cache.put(key, value);
				}
    		}
    	}
    	
    	static void evict(int level,String region,Object key){
    		if(StringUtil.isNotEmpty(region)&&key!=null){
				ICache cache = getCache(level, region,false);
				if(cache!=null){
		    		cache.evict(key);
				}
    		}
    	}
    	
    	static void batchEvict(int level,String region,List<Object> keys){
    		if(StringUtil.isNotEmpty(region)&&keys!=null&&keys.size()>0){
				ICache cache = getCache(level, region,false);
				if(cache!=null){
		    		cache.batchEvice(keys);
				}
    		}
    	}
    	
    	static void clear(int level,String region){
    		if(StringUtil.isNotEmpty(region)){
    			ICache cache = getCache(level, region, false);
    			if(cache!=null){
    				cache.clear();
    			}
    		}
    	}
    	
    	static List keys(int level,String region){
    			ICache cache = getCache(level, region, false);
    			if(cache!=null){
    				return cache.keys();
    			}else{
    				return null;
    			}
		}   	
    }
}
