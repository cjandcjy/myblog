package com.blog.j2cache.ehcache;

import java.util.Properties;

import com.blog.j2cache.CacheException;
import com.blog.j2cache.CacheExpiredListener;
import com.blog.j2cache.ICache;
import com.blog.j2cache.ICacheProvider;

public class EhCacheProvider implements ICacheProvider {

	

	@Override
	public void init(Properties prop) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public EhCache buildCache(String region, boolean autoCreat, CacheExpiredListener listener) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

}
