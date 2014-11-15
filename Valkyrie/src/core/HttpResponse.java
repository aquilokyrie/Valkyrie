package core;

import servlet.ServletProcessor;
//import java.io.*;
/**
 * 返回http应答的处理器,会鉴别请求类型
 * @author 春平
 *
 */
public class HttpResponse {				//http响应
	
		
	private String uri;					//http请求希望得到的静态资源
	private Communication com;			//用于通信的对象
	private StaticResourceProcessor srp;//处理静态请求的对象
	private HttpRequest httpRequest;	
	
	public HttpResponse(HttpRequest request) {		//构造函数，为哪一个HTTP请求对象而建立？
						
		this.uri = request.getUri();		
		this.com = request.getCom();
		this.httpRequest = request;
		
	}
	
	/**
	 * 对http请求进行处理,期间判断了请求资源的类型
	 * @return
	 */
	public boolean Response(){									//主要功能，实际处理请求的函数
		//以下为判断动静态资源的临时方法
		if(uri == null){
			return false;
		}else{			
			if(!this.uri.startsWith("/servlets/")){					//处理静态资源
				//System.out.println("上一个请求静态资源");
				this.srp = new StaticResourceProcessor(this.httpRequest);
				this.srp.process();				
			}else{													//处理servlet请求
				//System.out.println("上一个请求动态资源");
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
