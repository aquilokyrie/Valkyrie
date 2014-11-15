package core;


/**
 * �����ʾһ��HTTP�����ܹ���ԭʼ�����ַ�����ɸȡ������Ϣ
 * ��װһ�������������Ϣ���Ա�֤ϵͳ������ģ���ܹ������ֱ��ʹ����Щ��Ϣ
 */


//import java.io.*;


public class HttpRequest {
	
	
	private String uri;
	private String message;
	private Communication com;
	
	
	public HttpRequest(String message,Communication c){		//���캯��������Ϊ���յ��������������
		this.message = message;
		this.com = c;
		this.parseUri();				//��������ͬʱ����������Ϣ�г�ȡUri
		//uri = "/SHUTDOWN";
	}
	
	
	private boolean parseUri(){			//�ڲ����ַ������ҳ���һ���͵ڶ����ո񣬽�ȡ�м��ַ���
		int index1,index2;
		index1 = this.message.indexOf(' ');
		if(index1 != -1){
			index2 = this.message.indexOf(' ', index1 + 1);
			if(index2 > index1) {
				this.uri = this.message.substring(index1+1, index2);
				return true;
			}
		}
		return false;
	}
	
	
	public String getUri(){									//Ϊuri�ṩ�ӿ�
		return this.uri;
	}
	public Communication getCom(){
		return this.com;
	}
	public String getMessage(){
		return this.message;
	}
}
