package core;

import java.io.File;

import assist.Logger;

import assist.ValkyrieFileReader;
import assist.ValkyrieFileWriter;

/**
 * 读取，处理和保存配置文件的类
 */
public class Config {			
	
	private static String WEB_ROOT;			//存储设置的web服务器控制的文件系统根目录
	private static int port;				//服务器监听的端口号	
	private static int MAXLOG;				//日志缓冲区中允许的最大日志条数
	
	private final static String defaultWEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";			//默认存储设置的web服务器控制的文件系统根目录
	private final static int defaultPort = 2000;				//服务器监听的默认端口号	
	private final static int defaultMAXLOG = 100;				//日志缓冲区中允许的最大日志条数
	
	public static boolean SetConfig(){		//初始化配置信息，失败则返回false
		File configFile = new File(System.getProperty("user.dir") + File.separator + "config.txt");
		if(!configFile.exists()){			//文件不存在，说明第一次启动，设置默认的配置信息
			Logger.info("配置文件不存在,将根据默认配置创建新配置文件");
			ValkyrieFileWriter fw = new ValkyrieFileWriter(configFile);
			String str = "webroot " + defaultWEB_ROOT;				
			fw.writeLine(str);
			str = "port " + defaultPort;
			fw.writeLine(str);
			str = "MAXLOG " + defaultMAXLOG;
			fw.writeLine(str);
			fw.close();			
		}		
		//创建文件之后，总是从文件中读取配置信息
		ValkyrieFileReader fr = new ValkyrieFileReader(configFile);
		fr.read();
		fr.close();
		String next = new String();
		while((next = fr.getNextWord())!=""){
			switch(next){
			case "webroot":
				Config.WEB_ROOT = fr.getNextWord();
				break;
			case "port":
				Config.port = Integer.parseInt(fr.getNextWord());
				break;
			case "MAXLOG":
				Config.MAXLOG = Integer.parseInt(fr.getNextWord());
				break;
			default: 
				break;
			}
		}		
		Logger.info("读取到配置:webroot目录="+Config.GetWebroot());
		Logger.info("读取到配置:HTTP协议监听端口="+Config.GetPort());		
		Logger.info("读取到配置:缓冲区最大日志数量="+Config.getMAXLOG());
		return true;
	}
	
	
	public static String GetWebroot(){						//获取文件系统根目录
		return Config.WEB_ROOT;
	}
	public static boolean SetWebroot(String newRoot){		//设置文件系统根目录
		Config.WEB_ROOT = newRoot;
		return true;
	}
	public static int GetPort(){							//获取监听的默认端口号
		return Config.port;
	}
	public static boolean SetPort(int newPort){				//设置监听的默认端口号
		Config.port = newPort;
		return true;
	}


	public static int getMAXLOG() {
		return MAXLOG;
	}


	public static void setMAXLOG(int mAXLOG) {
		MAXLOG = mAXLOG;
	}
	
	
}






