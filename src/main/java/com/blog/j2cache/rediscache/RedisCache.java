package com.blog.j2cache.rediscache;

import java.util.List;

import com.blog.j2cache.CacheException;
import com.blog.j2cache.ICache;

public class RedisCache implements ICache {

	@Override
	public Object get(Object key) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Object key, Object value) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Object key, Object value) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> keys() throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evict(Object key) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchEvice(List<Object> keys) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() throws CacheException {
		// TODO Auto-generated method stub
		
	}

}
