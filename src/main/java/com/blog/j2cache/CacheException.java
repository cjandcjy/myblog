package com.blog.j2cache;

public class CacheException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 324344231231L;
	
	private  CacheErrorCode errorCode;
	
	public CacheException(String s){
		super(s);
	}
	
	public CacheException(String s,Exception e){
		super(s,e);
	}
	
	public CacheException(CacheErrorCode errorCode){
		this.errorCode = errorCode;
	}
	
	public CacheException(Throwable e) {
		super(e);
	}
	
	public CacheErrorCode getErrorCode(){
		return this.errorCode;
	}

}
