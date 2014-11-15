package assist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * ��ValkyrieFileWriter��Ϊ�˸��ӷ�������ļ���д������
 * ʹ����Ҫд����ļ���������ĳ�ʼ��������writeLine��д��һ������
 * ���Ҫ���˵���close���ر��ļ�
*/

public class ValkyrieFileWriter {
	private File file;								//������������ļ���
	private FileOutputStream fos;
	private OutputStreamWriter osr;

	/**
	 * �������ڲ������ļ��������ļ���
	 * @return ���ڲ������ļ��������ļ���
	 */
	public String getfilename(){					
		return file.getAbsolutePath();
	}
	
	/**
	 * ������Ҫд����ļ��Ķ���
	 * @param file Ŀ���ļ���java.io.File��Ķ���
	 */
	public ValkyrieFileWriter(File file){			//���캯������Ҫд����ļ����������г�ʼ��
		this.file = file;	
		try{
			this.fos = new FileOutputStream(file.getAbsolutePath(),true);
			this.osr = new OutputStreamWriter(fos);
		}catch(Exception e){
			Logger.info("��ʼ��д�ļ���"+file+"ʧ��");
		}
	}
	
	/**
	 * ���ļ���ĩβд��һ�����ݲ��Զ�����
	 * @param message ��Ҫд���ļ�ĩβ���ַ���
	 * @return �������Ƿ�ɹ��Ĳ���ֵ
	 * */
	public boolean writeLine(String message){		
		char ch = 0;
		try{
			for(int i=0;i<message.length();i++){
				ch = message.charAt(i);
				this.osr.write(ch);
			}
			this.osr.write("\r");
			this.osr.write("\n");
			this.osr.flush();
		}catch(IOException e){
			Logger.info("д�ļ�"+file+"����");
			return false;
		}			
		return true;
	}	
	
	/**
	 * �ر��ļ�
	 * @return ���ر��ļ��Ƿ�ɹ��Ĳ���ֵ
	 */
	public boolean close(){							//�رողŲ��������ļ�
		try{
			osr.close(); fos.close();
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
