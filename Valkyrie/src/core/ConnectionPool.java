package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import assist.Logger;

public class ConnectionPool {
	
	private static Set<Communication> cons;
	
	/**
	 * ��ʼ�����ӳ�
	 * @param initialCapacity ���ӳس�ʼ��ʱĬ����������
	 * @param loadFactor ���ӳ���������
	 */
	public static void initPool(int initialCapacity, float loadFactor){
		cons = new HashSet<Communication>(initialCapacity,loadFactor);
		for(int i=0;i<=initialCapacity;i++){
			Communication c = new Communication();	
			cons.add(c);
		}
	}
	
	/**
	 * ����Ĭ�����õ����ӳ�,
	 */
	public static void initPool(){
		initPool(100,0.75f);
	}
	
	/**
	 * ���һ��δ��ʹ���е�tcp���Ӷ���,������ӳ���û�п��õ�����,�����ؿն���
	 * @return ����δʹ��״̬������
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
