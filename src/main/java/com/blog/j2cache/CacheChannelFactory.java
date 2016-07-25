package com.blog.j2cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheChannelFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheChannelFactory.class);
	
	private static final String CONFIG_FILE = "jcache.properties";
	
	private static CacheChannel cacheChannel;
	
	private static Properties prop;
	
	static{
		LOG.info("CacheChannelFactory initialize start....");
		try {
			prop = getConfig();
			String channel_type = prop.getProperty("cache.broadcast");
		    if("jgroup".equals(channel_type)){
		       cacheChannel = JGroupCacheChannel.getInstance();
			   LOG.info("CacheChannel initialize successful....");
		    }else{
		    	throw new CacheException("channel_type error.");
		    }		    			    
		} catch (IOException e) {
            throw new CacheException("unable to load config file.",e);
		}
	}
	
	public static CacheChannel getChannel(){
		return cacheChannel;
	}
	
	@SuppressWarnings("unused")
	public static Properties getConfig() throws IOException{
		LOG.info("load channel config...");
		
		File file = new File(CacheChannelFactory.class.getClassLoader().getResource(CONFIG_FILE).getPath());
		FileInputStream ins = new FileInputStream(file);
		if(ins==null){
			throw new CacheException("can not find channel config file!!!");
		}
		Properties properties = new Properties();
		properties.load(ins);
		return properties;
	}

}
