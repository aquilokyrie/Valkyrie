package core;

import java.io.File;

import assist.Logger;

import assist.ValkyrieFileReader;
import assist.ValkyrieFileWriter;

/**
 * ��ȡ������ͱ��������ļ�����
 */
public class Config {			
	
	private static String WEB_ROOT;			//�洢���õ�web���������Ƶ��ļ�ϵͳ��Ŀ¼
	private static int port;				//�����������Ķ˿ں�	
	private static int MAXLOG;				//��־������������������־����
	
	private final static String defaultWEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";			//Ĭ�ϴ洢���õ�web���������Ƶ��ļ�ϵͳ��Ŀ¼
	private final static int defaultPort = 2000;				//������������Ĭ�϶˿ں�	
	private final static int defaultMAXLOG = 100;				//��־������������������־����
	
	public static boolean SetConfig(){		//��ʼ��������Ϣ��ʧ���򷵻�false
		File configFile = new File(System.getProperty("user.dir") + File.separator + "config.txt");
		if(!configFile.exists()){			//�ļ������ڣ�˵����һ������������Ĭ�ϵ�������Ϣ
			Logger.info("�����ļ�������,������Ĭ�����ô����������ļ�");
			ValkyrieFileWriter fw = new ValkyrieFileWriter(configFile);
			String str = "webroot " + defaultWEB_ROOT;				
			fw.writeLine(str);
			str = "port " + defaultPort;
			fw.writeLine(str);
			str = "MAXLOG " + defaultMAXLOG;
			fw.writeLine(str);
			fw.close();			
		}		
		//�����ļ�֮�����Ǵ��ļ��ж�ȡ������Ϣ
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
		Logger.info("��ȡ������:webrootĿ¼="+Config.GetWebroot());
		Logger.info("��ȡ������:HTTPЭ������˿�="+Config.GetPort());		
		Logger.info("��ȡ������:�����������־����="+Config.getMAXLOG());
		return true;
	}
	
	
	public static String GetWebroot(){						//��ȡ�ļ�ϵͳ��Ŀ¼
		return Config.WEB_ROOT;
	}
	public static boolean SetWebroot(String newRoot){		//�����ļ�ϵͳ��Ŀ¼
		Config.WEB_ROOT = newRoot;
		return true;
	}
	public static int GetPort(){							//��ȡ������Ĭ�϶˿ں�
		return Config.port;
	}
	public static boolean SetPort(int newPort){				//���ü�����Ĭ�϶˿ں�
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






