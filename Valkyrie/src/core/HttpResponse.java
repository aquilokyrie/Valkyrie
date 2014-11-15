package core;

import servlet.ServletProcessor;
//import java.io.*;
/**
 * ����httpӦ��Ĵ�����,�������������
 * @author ��ƽ
 *
 */
public class HttpResponse {				//http��Ӧ
	
		
	private String uri;					//http����ϣ���õ��ľ�̬��Դ
	private Communication com;			//����ͨ�ŵĶ���
	private StaticResourceProcessor srp;//����̬����Ķ���
	private HttpRequest httpRequest;	
	
	public HttpResponse(HttpRequest request) {		//���캯����Ϊ��һ��HTTP��������������
						
		this.uri = request.getUri();		
		this.com = request.getCom();
		this.httpRequest = request;
		
	}
	
	/**
	 * ��http������д���,�ڼ��ж���������Դ������
	 * @return
	 */
	public boolean Response(){									//��Ҫ���ܣ�ʵ�ʴ�������ĺ���
		//����Ϊ�ж϶���̬��Դ����ʱ����
		if(uri == null){
			return false;
		}else{			
			if(!this.uri.startsWith("/servlets/")){					//����̬��Դ
				//System.out.println("��һ������̬��Դ");
				this.srp = new StaticResourceProcessor(this.httpRequest);
				this.srp.process();				
			}else{													//����servlet����
				//System.out.println("��һ������̬��Դ");
				ServletProcessor sp = new ServletProcessor(this.httpRequest,this);
				sp.process();
			}
			return true;
		}
	}
	
	public Communication getCom(){
		return this.com;
	}
	public HttpRequest getRequest(){
		return this.httpRequest;
	}
	
	
}
