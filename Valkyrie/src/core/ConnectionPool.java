package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import assist.Logger;

public class ConnectionPool {
	
	private static Set<Communication> cons;
	
	/**
	 * 初始化连接池
	 * @param initialCapacity 连接池初始化时默认连接数量
	 * @param loadFactor 连接池增长因子
	 */
	public static void initPool(int initialCapacity, float loadFactor){
		cons = new HashSet<Communication>(initialCapacity,loadFactor);
		for(int i=0;i<=initialCapacity;i++){
			Communication c = new Communication();	
			cons.add(c);
		}
	}
	
	/**
	 * 创建默认设置的连接池,
	 */
	public static void initPool(){
		initPool(100,0.75f);
	}
	
	/**
	 * 获得一个未在使用中的tcp连接对象,如果连接池中没有可用的连接,将返回空对象
	 * @return 处于未使用状态的连接
	 */
	public static Communication getACon(){
		Communication temp = null;
		for(Iterator<Communication> it = cons.iterator();it.hasNext();){
			temp = it.next();
			//System.out.println(temp.isAlive());
			if(!temp.isAlive()){
				return temp;
			}				
			else{
				continue;
			}
		}
		return null;
	}
	
}
