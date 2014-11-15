package core;

import assist.Logger;

/*
 * ����ר�Ŵ���̬��Դ
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
			Logger.info("���;�̬��Դ"+uri+"ʧ��");
			//e.printStackTrace();
			return false;
		}
		return true;
	}

}
