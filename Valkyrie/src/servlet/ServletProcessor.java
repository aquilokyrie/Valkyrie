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
 * @author 春平
 * 处理servlet请求的处理器
 * 调度类
 */
public class ServletProcessor {
	
	private ServletRequests request;					//本处理器所解决的请求者
	private ServletResposes respose;					//本处理器所将要调用的回答者
	
	private String uri;									//http请求希望得到的目标uri
	private String servletName;							//HTTP所请求的servlet资源的类名
	private URLClassLoader classLoader;					//类载入器对象
	private File classPath;								//web工作目录下的servlet存储仓库（repository），
														//类载入器从中查找servlet		
	private URL[] urls;									//http请求的servlet的存储的位置
	private URLStreamHandler streamHandler;				//

	/**
	 * 构造函数,初始化动态资源处理器
	 * @param hr HTTP请求的对象
	 * @param hp HTTP响应的对象
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
			Logger.info("构造URL失败");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			Logger.info("未找到指定的类文件路径");
			e.printStackTrace();
			return false;
		}		
		Class<?> myClass = null;
		try{						
			myClass = this.classLoader.loadClass(this.servletName);
			Logger.info("所请求Servlet类类名:" + myClass.toString());
		}catch(Exception e){
			Logger.info("所请求的类未找到");
			//e.printStackTrace();
			return false;
		}		
		//创建一个已经 载入的servlet实例，并调用其service方法
		Servlet servlet = null;
		try{
			servlet = (Servlet)myClass.newInstance();
//			String errorMessage = "HTTP/1.1 200 ok ";
//			this.respose.getCom().getOutput().write(errorMessage.getBytes());
			servlet.service(request, respose);
			Logger.info("容器已开启"+myClass.toString()+"的服务");
		}catch(Exception e){
			//e.printStackTrace();
			Logger.info("Servlet已找到,但加载失败");
			return false;
		}catch(Throwable e){
			Logger.info("Servlet已找到,但加载失败");
			return false;
		}		
		return true;
	}

}
























