package core;

import assist.Logger;

import com.amd.aparapi.Kernel;

/**
 * 负责监听特定端口,接收到新的http请求时创建新的线程
 * 本类试图运行在GPU中
 * @author 春平
 *
 */
public class Heterogeneous extends Kernel{
	
	private Communication community;		//链接对象

	/**
	 * 建立处理请求的多线程构造器
	 * @param com 建立连接的时候生成的链接对象
	 */
	public Heterogeneous(Communication com) {	
		this.community = com;
	}

	/**
	 * 处理某个请求的方法
	 * 这里应该加载到GPU中使用
	 * 该方法中的代码预期将要在GPU中运行
	 */
	@Override
	public void run() {		
		community.Listen();
		while(true){			
			Communication c = new Communication();		//创建一个新的communication对象并监听并接受请求，之后被分配到
														//新的线程
			//Logger.info("主线程正在监听");
			c.SetCoSocket(community.Accept(),community.output);
			//试图使用GPU创建一个新的线程，在线程内部接受请求，被分配刚才的socket
			MultiThreading mm = new MultiThreading(c);	
			Thread thr = new Thread(mm);
			//Logger.info("运行模式:"+this.getExecutionMode());
		 	thr.start();
		}		
	}
}
