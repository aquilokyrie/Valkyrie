package core;

import assist.Logger;

/**
 * ���߳�ģ�飬ÿ����һ�����󣬶�Ҫ����һ���µ��߳�
 * @author ��ƽ
 */
public class HttpThread implements Runnable {
	
	private Communication community;		//���Ӷ���
	private String message;					//����������յ����������ַ���
	private Thread thr;

	/**
	 * ������������Ķ��̹߳�����
	 * @param com �������ӵ�ʱ�����ɵ����Ӷ���
	 */
	public HttpThread(Communication com) {	//���췽��������
		this.community = com;
		thr = new Thread(this);
	}

	/**
	 * ����ĳ������ķ���
	 */
	@Override
	public void run() {		
		this.message = this.community.GetData();			//��ȡ���ӷ��͵�����		
		if(!message.isEmpty())
			Logger.info("HTTP�����ַ���:" + this.message);
		//Ϊÿ���������󴴽�һ��request�����һ��response����
		HttpRequest request = new HttpRequest(this.message,this.community);		
		HttpResponse response = new HttpResponse(request);
		//if(!response.Response()) response.Response();		//���һ��ʧ��,�������´���һ��
		response.Response();
		this.community.coSocketClose();
	}
	
	public void start(){		
		thr.start();
	}
	
	public boolean isAlive(){
		return thr.isAlive();
	}
	
	public Thread.State getState(){
		return thr.getState();
	}

	public Communication getCom() {
		return community;
	}

	public void setCom(Communication community) {
		this.community = community;
	}
}
