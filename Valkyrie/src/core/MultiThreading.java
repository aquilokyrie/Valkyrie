package core;

import assist.Logger;

/**
 * 多线程模块，每处理一个请求，都要创建一个新的线程
 * @author 春平
 */
public class MultiThreading implements Runnable {
	
	private Communication community;		//链接对象
	private String message;					//从浏览器接收的完整请求字符串
	private static int index = 0;			//当前已经创建的线程数量

	/**
	 * 建立处理请求的多线程构造器
	 * @param com 建立连接的时候生成的链接对象
	 */
	public MultiThreading(Communication com) {	//构造方法，传入
		this.community = com;
		index++;
		//System.out.println("已构造"+index+"号线程处理前一个请求");
	}

	/**
	 * 处理某个请求的方法
	 */
	@Override
	public void run() {		
		this.message = this.community.GetData();			//读取链接发送的内容		
		if(!message.isEmpty())
			Logger.info("HTTP请求字符串:" + this.message);
		//为每个连接请求创建一个request对象和一个response对象
		HttpRequest request = new HttpRequest(this.message,this.community);		
		HttpResponse response = new HttpResponse(request);
		//if(!response.Response()) response.Response();		//如果一次失败,则尝试重新处理一次
		response.Response();
		this.community.coSocketClose();
	}
}
