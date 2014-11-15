package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import assist.Logger;

/**
 * �̳߳�,�����趨��ʼ�̳߳����߳�����,
 * ���̳߳ؿ��Ը�����Ҫ����,�����趨��������
 * @author ��ƽ
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
	 * ����һ���̳߳�,�����趨��ʼ����
	 * @param initialCapacity �̳߳��г�ʼ�߳�����
	 * @param loadFactor ��������
	 */
	public static void InitThreadPool(int initialCapacity, float loadFactor){
		threads = new HashSet<HttpThread>(initialCapacity,loadFactor);
		for(int i=0;i<=initialCapacity;i++){
			HttpThread mm = new HttpThread(null);	
			threads.add(mm);
		}
		timer.schedule(maintain, 0,1000);		//���ö�ʱά���̳߳صĶ�ʱ��
	}
	
	/**
	 * ����Ĭ�����õ��̳߳�,
	 */
	public static void InitThreadPool(){
		InitThreadPool(60,0.75f);
	}
	
	/**
	 * ���һ��δ���е��߳�,����̳߳���û�п��õ��߳�,�����ؿն���
	 * @return ���ھ���״̬���߳�
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
	
	//ά���̳߳��е��߳�,���������̲߳������½��߳�
	private static void flushState(){
//		Logger.info("ά���̳߳�");
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
