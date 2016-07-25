package com.blog.j2cache;

public interface CacheExpiredListener{
	
	public void notifyCacheExpired(String region,Object key);

}
