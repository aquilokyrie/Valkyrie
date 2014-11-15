package core;

import assist.Logger;

/**
 * ���߳�ģ�飬ÿ����һ�����󣬶�Ҫ����һ���µ��߳�
 * @author ��ƽ
 */
public class MultiThreading implements Runnable {
	
	private Communication community;		//���Ӷ���
	private String message;					//����������յ����������ַ���
	private static int index = 0;			//��ǰ�Ѿ��������߳�����

	/**
	 * ������������Ķ��̹߳�����
	 * @param com �������ӵ�ʱ�����ɵ����Ӷ���
	 */
	public MultiThreading(Communication com) {	//���췽��������
		this.community = com;
		index++;
		//System.out.println("�ѹ���"+index+"���̴߳���ǰһ������");
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
}
