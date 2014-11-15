package assist;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import core.Config;

/**
 * ������־�Ĺ�����
 * ����:�ڿ���̨�����־��Ϣ/������־�ļ�
 * @author ��ƽ
 *
 */
public class Logger {
	private static Date timeNow;
	private static List<String> tempLogs = new ArrayList<String>();
	/**
	 * �����̨���ض���ʽ�����־��Ϣ
	 * ����Ϣ�ᱻ��ʱ�������ڴ���,ÿ����־�����ﵽһ����ʱ,������ļ���
	 * @param message ϣ���ڿ���̨��ӡ���ַ���
	 */
	public static void info(String message){
		timeNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS aa");   
	    String temp_str=sdf.format(timeNow);  
	    temp_str = "INFO:[" + temp_str + "] " + message;
		System.out.println(temp_str);
		logAdd(temp_str);
	}
	
	//������һ����־��Ϣ,����������е���־��������,�������־д�������
	private static void logAdd(String newLog){
		tempLogs.add(newLog);
		if(tempLogs.size() >= Config.getMAXLOG()){
			flushLogs();
		}
	}
	
	/**
	 * ǿ��ˢ����־����������־��Ϣ��Ӳ����
	 */
	public static void flushLogs(){
		//��Ϊ�ڱ�������ʱ�����޸ĸü���,�����ڱ�����д���ļ�֮ǰ,Ӧ���������������ĸ���
		List<String> temp = new ArrayList<String>();
		temp.addAll(tempLogs);
		tempLogs.clear();
		tempLogs = new ArrayList<String>();		//��ջ�����		
		
		timeNow = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String temp_str=format.format(timeNow);
		File logFile = new File(System.getProperty("user.dir") + File.separator + "logs" + File.separator + "log" + temp_str + ".log");
		//System.out.println(logFile);
		ValkyrieFileWriter writer = new ValkyrieFileWriter(logFile);
		for(Iterator<String> it = temp.iterator();it.hasNext(); ){
			writer.writeLine(it.next());
		}
		
		//��ӡˢ����־,ͬʱ�Ѹ�����־��ϢҲд���ļ�
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS aa");   
	    temp_str=format.format(timeNow);  
	    temp_str = "INFO:[" + temp_str + "] " + "���������е���־��д��Ӳ��";
		System.out.println(temp_str);
		writer.writeLine(temp_str);
		writer.close();				
	}
}
