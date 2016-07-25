package com.blog.j2cache;

import java.net.URL;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Authored by CJ 
 */
@SuppressWarnings("unused")
public class JGroupCacheChannel extends ReceiverAdapter implements CacheExpiredListener,CacheChannel{

	private static final Logger LOG = LoggerFactory.getLogger(JGroupCacheChannel.class);
	
	private static final String CONFIG_FILE = "jgroup.xml";
	
	private static JGroupCacheChannel instance;
	
	private  JChannel jchannel;
	
	private  String name;
	
	
	private JGroupCacheChannel(String name){
		LOG.info("initializing jgroupchannel instance...");
		this.name = name;
		try {
			
			CacheManager.init(this);//初始化CacheManager
			
			URL xml = JGroupCacheChannel.class.getClassLoader().getResource(CONFIG_FILE);		
			long start = System.currentTimeMillis();
			jchannel = new JChannel(xml);
			jchannel.setReceiver(this);
			jchannel.connect(this.name);
			LOG.info("connected to group : "+this.name+",using time:"+(System.currentTimeMillis()-start)+"s");
		} catch (Exception e) {
            throw new CacheException(e);
		}
	}
				
	private static class InstanceHolder{
		private static JGroupCacheChannel Instance = new JGroupCacheChannel("default");
	}
	
	public static JGroupCacheChannel getInstance(){
		return InstanceHolder.Instance;
	}
	
	/*
	 * 发送删除命令
	 */
	public void sendEvictCmd(String region,Object key){
		
	}
	
	/*
	 * 发送清除cache命令
	 */
	public void sendClearCmd(String region){
		
	}
		
	@Override
	public void receive(Message msg) {
		
	}
	
	@Override
    public void viewAccepted(View view) {
		
	}
	
	@Override
	public void notifyCacheExpired(String region,Object key) {
		
	}

	@Override
	public  void onDeleteCacheKey(String region, Object key) {
		
	}

	@Override
	public void onDeleteCache(String region) {
		
	}
    
}
