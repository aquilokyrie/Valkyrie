package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import assist.Logger;

/**
 * 线程池,可以设定初始线程池中线程数量,
 * 在线程池可以根据需要增长,可以设定增长因子
 * @author 春平
 *
 */
public class ThreadPool {
	
	private static Set<HttpThread> threads;
	private static Timer timer = new Timer(true);   	  
	private static TimerTask maintain = new TimerTask() {   
		public void run() {   
			flushState();
		}   
	}; 
	
	/**
	 * 构造一个线程池,可以设定初始容量
	 * @param initialCapacity 线程池中初始线程数量
	 * @param loadFactor 增长因子
	 */
	public static void InitThreadPool(int initialCapacity, float loadFactor){
		threads = new HashSet<HttpThread>(initialCapacity,loadFactor);
		for(int i=0;i<=initialCapacity;i++){
			HttpThread mm = new HttpThread(null);	
			threads.add(mm);
		}
		timer.schedule(maintain, 0,1000);		//设置定时维护线程池的定时器
	}
	
	/**
	 * 创建默认设置的线程池,
	 */
	public static void InitThreadPool(){
		InitThreadPool(60,0.75f);
	}
	
	/**
	 * 获得一个未运行的线程,如果线程池中没有可用的线程,将返回空对象
	 * @return 处于就绪状态的线程
	 */
	public static HttpThread getThread(Communication c){
		HttpThread temp = null;
		for(Iterator<HttpThread> it = threads.iterator();it.hasNext();){
			temp = it.next();
			if(temp.getState() == Thread.State.NEW){
				temp.setCom(c);
				return temp;
			}				
			else{
				continue;
			}
		}
		return null;
	}
	
	//维护线程池中的线程,销毁死亡线程并补充新建线程
	private static void flushState(){
//		Logger.info("维护线程池");
		Set<HttpThread> tempToRemove = new HashSet<HttpThread>();
		Set<HttpThread> tempToAdd = new HashSet<HttpThread>();
		HttpThread temp = null;
		for(Iterator<HttpThread> it = threads.iterator();it.hasNext();){
			temp = it.next();
			if(temp.getState() == Thread.State.TERMINATED){
				tempToRemove.add(temp);
				HttpThread mm = new HttpThread(null);	
				tempToAdd.add(mm);
			}
		}
		threads.removeAll(tempToRemove);
		threads.addAll(tempToAdd);
	}
}
