package com.blog.j2cache;

public interface CacheChannel {
	
   public  void onDeleteCacheKey(String region,Object key);
   
   public void onDeleteCache(String region);
}
