package assist;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import core.Config;

/**
 * 产生日志的工具类
 * 功能:在控制台输出日志信息/生成日志文件
 * @author 春平
 *
 */
public class Logger {
	private static Date timeNow;
	private static List<String> tempLogs = new ArrayList<String>();
	/**
	 * 向控制台以特定格式输出日志信息
	 * 该信息会被临时保存在内存中,每当日志条数达到一定量时,输出到文件中
	 * @param message 希望在控制台打印的字符串
	 */
	public static void info(String message){
		timeNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS aa");   
	    String temp_str=sdf.format(timeNow);  
	    temp_str = "INFO:[" + temp_str + "] " + message;
		System.out.println(temp_str);
		logAdd(temp_str);
	}
	
	//增加了一条日志信息,如果缓冲区中的日志条数过多,将会把日志写入外存中
	private static void logAdd(String newLog){
		tempLogs.add(newLog);
		if(tempLogs.size() >= Config.getMAXLOG()){
			flushLogs();
		}
	}
	
	/**
	 * 强制刷新日志缓冲区的日志信息到硬盘中
	 */
	public static void flushLogs(){
		//因为在遍历集合时不能修改该集合,所以在遍历以写入文件之前,应该先制作缓冲区的副本
		List<String> temp = new ArrayList<String>();
		temp.addAll(tempLogs);
		tempLogs.clear();
		tempLogs = new ArrayList<String>();		//清空缓冲区		
		
		timeNow = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String temp_str=format.format(timeNow);
		File logFile = new File(System.getProperty("user.dir") + File.separator + "logs" + File.separator + "log" + temp_str + ".log");
		//System.out.println(logFile);
		ValkyrieFileWriter writer = new ValkyrieFileWriter(logFile);
		for(Iterator<String> it = temp.iterator();it.hasNext(); ){
			writer.writeLine(it.next());
		}
		
		//打印刷新日志,同时把该条日志信息也写入文件
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS aa");   
	    temp_str=format.format(timeNow);  
	    temp_str = "INFO:[" + temp_str + "] " + "将缓冲区中的日志填写到硬盘";
		System.out.println(temp_str);
		writer.writeLine(temp_str);
		writer.close();				
	}
}
