package com.blog.j2cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MultiCache implements ICache{
	
	private static final Logger LOG = LoggerFactory.getLogger(MultiCache.class);
	
	private ICache firstcache;
	private ICache secondcache;
	
	public MultiCache(ICache first,ICache second) {
		this.firstcache = first;
		this.secondcache = second;
	}
	

	@Override
	public Object get(Object key) throws CacheException {
		if(key==null){
			return null;
		}
		Object result = firstcache.get(key);
		if(result==null){
			result = secondcache.get(key);
			firstcache.put(key, result);
		}
		return result;
	}

	@Override
	public void put(Object key, Object value) throws CacheException {
		if(key==null||value==null){
			LOG.error("you've transfered null key or null value when doing put process.");
			throw new CacheException(CacheErrorCode.Cache_Error_NullParameter);
		}       
		firstcache.put(key, value);
		secondcache.put(key, value);
	}

	@Override
	public void Update(Object key, Object value) throws CacheException {
		if(key==null){
			LOG.error("you've transfered null key");
			throw new CacheException(CacheErrorCode.Cache_Error_Nullkey);
		}
		if(value==null){
			firstcache.evict(key);
			secondcache.evict(key);
		}else{
			firstcache.Update(key, value);
			secondcache.Update(key, value);
		}
		
	}

	@Override
	public List<Object> keys() throws CacheException {
		List<Object> list = firstcache.keys();
		return list;
	}

	@Override
	public void evict(Object key) throws CacheException {
		if(key==null){
			LOG.error("null key");
			throw new CacheException(CacheErrorCode.Cache_Error_Nullkey);
		}
		firstcache.evict(key);
		secondcache.evict(key);
	}

	@Override
	public void batchEvice(List<Object> keys) throws CacheException {
		if(keys==null||keys.size()==0){
			LOG.error("there is no element in list.");
			throw new CacheException(CacheErrorCode.Cache_Error_Nullkey);
		}
		firstcache.batchEvice(keys);
		secondcache.batchEvice(keys);
	}

	@Override
	public void clear() throws CacheException{
		firstcache.clear();
		secondcache.clear();
	}

	@Override
	public void destroy() throws CacheException{
		firstcache.destroy();
		secondcache.destroy();
	}

}
