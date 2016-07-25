package com.blog.j2cache;

import java.util.Properties;

public interface ICacheProvider {
	
   public ICache buildCache(String region,boolean autoCreat,CacheExpiredListener listener) throws CacheException;
   
   public void init(Properties prop) throws CacheException;
   
   public void stop() throws CacheException;
}
