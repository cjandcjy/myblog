package com.blog.j2cache;

import java.util.List;


public interface ICache {
	
	public Object get(Object key) throws CacheException;
	
	public void put(Object key,Object value) throws CacheException;
	
	public void Update(Object key,Object value) throws CacheException;
	
	public List<Object> keys() throws CacheException;
	
	public void evict(Object key) throws CacheException;
	
	public void batchEvice(List<Object> keys) throws CacheException;
	
	public void clear() throws CacheException;
	
	public void destroy() throws CacheException;

}
