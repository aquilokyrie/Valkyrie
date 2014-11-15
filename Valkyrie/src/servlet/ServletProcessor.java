package servlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

import assist.Logger;
import core.Config;
import core.HttpRequest;
import core.HttpResponse;

/** 
 * @author ��ƽ
 * ����servlet����Ĵ�����
 * ������
 */
public class ServletProcessor {
	
	private ServletRequests request;					//���������������������
	private ServletResposes respose;					//������������Ҫ���õĻش���
	
	private String uri;									//http����ϣ���õ���Ŀ��uri
	private String servletName;							//HTTP�������servlet��Դ������
	private URLClassLoader classLoader;					//������������
	private File classPath;								//web����Ŀ¼�µ�servlet�洢�ֿ⣨repository����
														//�����������в���servlet		
	private URL[] urls;									//http�����servlet�Ĵ洢��λ��
	private URLStreamHandler streamHandler;				//

	/**
	 * ���캯��,��ʼ����̬��Դ������
	 * @param hr HTTP����Ķ���
	 * @param hp HTTP��Ӧ�Ķ���
	 */
	public ServletProcessor(HttpRequest hr,HttpResponse hp) {
		this.request = new ServletRequests(hr.getMessage(),hr.getCom());
		this.respose = new ServletResposes(hp.getRequest());
		this.uri = this.request.getUri();
		this.servletName = this.uri.substring(uri.lastIndexOf("/")+1);
		this.classLoader = null;
		this.urls = new URL[1];
		this.streamHandler = null;
		this.classPath = new File(Config.GetWebroot()+File.separator+"servlets"+File.separator);	
	}
	
	public boolean process(){		
		try {
			String repository = (
					new URL("file",
							null,
							this.classPath.getCanonicalPath() + File.separator
							)
					).toString();
			urls[0] = new URL(null,repository,this.streamHandler);		
			this.classLoader = new URLClassLoader(urls);
		} catch (MalformedURLException e) {
			Logger.info("����URLʧ��");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			Logger.info("δ�ҵ�ָ�������ļ�·��");
			e.printStackTrace();
			return false;
		}		
		Class<?> myClass = null;
		try{						
			myClass = this.classLoader.loadClass(this.servletName);
			Logger.info("������Servlet������:" + myClass.toString());
		}catch(Exception e){
			Logger.info("���������δ�ҵ�");
			//e.printStackTrace();
			return false;
		}		
		//����һ���Ѿ� �����servletʵ������������service����
		Servlet servlet = null;
		try{
			servlet = (Servlet)myClass.newInstance();
//			String errorMessage = "HTTP/1.1 200 ok ";
//			this.respose.getCom().getOutput().write(errorMessage.getBytes());
			servlet.service(request, respose);
			Logger.info("�����ѿ���"+myClass.toString()+"�ķ���");
		}catch(Exception e){
			//e.printStackTrace();
			Logger.info("Servlet���ҵ�,������ʧ��");
			return false;
		}catch(Throwable e){
			Logger.info("Servlet���ҵ�,������ʧ��");
			return false;
		}		
		return true;
	}

}
























