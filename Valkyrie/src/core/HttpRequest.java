package core;


/**
 * 该类表示一个HTTP请求，能够从原始链接字符串中筛取有用信息
 * 封装一个请求的所有信息，以保证系统的其他模块能够方便地直接使用这些信息
 */


//import java.io.*;


public class HttpRequest {
	
	
	private String uri;
	private String message;
	private Communication com;
	
	
	public HttpRequest(String message,Communication c){		//构造函数，参数为接收到的请求的输入流
		this.message = message;
		this.com = c;
		this.parseUri();				//构造对象的同时，从请求信息中抽取Uri
		//uri = "/SHUTDOWN";
	}
	
	
	private boolean parseUri(){			//在参数字符串中找出第一个和第二个空格，截取中间字符串
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
	
	
	public String getUri(){									//为uri提供接口
		return this.uri;
	}
	public Communication getCom(){
		return this.com;
	}
	public String getMessage(){
		return this.message;
	}
}
