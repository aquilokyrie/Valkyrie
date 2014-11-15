package core;

import assist.Logger;

/*
 * 本类专门处理静态资源
 */



public class StaticResourceProcessor {

	private Communication com;
	private String uri;
	
	
	public StaticResourceProcessor(HttpRequest r) {
		this.com = r.getCom();
		this.uri = r.getUri();
	}
	
	public boolean process(){
		try{
			this.com.SendStaicResource(this.uri);
		}catch(Exception e){
			Logger.info("发送静态资源"+uri+"失败");
			//e.printStackTrace();
			return false;
		}
		return true;
	}

}
