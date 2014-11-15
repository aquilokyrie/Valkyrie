package assist;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Ϊ������ģ���ȡ�ļ��ķ��㣬�����˴���
 * ����File����������ʵ��
 * Ȼ�����read������ȡ�ļ���close�����ر��ļ�����ʱ�ļ���Ϣȫ����һ����������message��
 * Ȼ��ʹ��getNextWord���������ȡ�������еĵ���
 */

public class ValkyrieFileReader {
	private File file;								//������������ļ���
	private FileInputStream fis;
	private InputStreamReader isr;
	private char[] message;							//��ȡ�ļ��Ļ�����
	private int index;								//��ǰ�����Ļ�����λ�õ�����
	private int end;								//��ǰ�ַ�����������Ч����
	
	/**
	 * ����File������ʵ��
	 * @param file ����ȡ���ļ�����
	 */
	public ValkyrieFileReader(File file){			//���캯������Ҫ��ȡ���ļ�����
		//System.out.println("������ļ���");
		this.file = file;
		message = new char[4048];
		for(int m=0;m<4048;m++) message[m]=' ';
		this.index = 0;
		try{
			this.fis = new FileInputStream(this.file.getAbsolutePath());
			this.isr = new InputStreamReader(fis);
		}catch(Exception e){
			System.out.println("���ļ��๹�����");
			e.printStackTrace();
		}		
	}
	
	/**
	 * �ر��ļ�
	 * @return ���ر��ļ��Ƿ�ɹ��Ĳ���ֵ
	 */
	public boolean close(){							//�ر��ļ����ͷ���Դ
		try{
			this.fis.close();
			this.isr.close();
		}catch(Exception e){
			System.out.println("�ر��ļ�����");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * //���ļ����������ݶ�����������
	 * @return �������Ƿ�ɹ��Ĳ���ֵ
	 */
	public boolean read(){							
		//System.out.println("׼����ȡ");
		int i = 0;
		int ch = 0;
		try{
			ch = isr.read();
			while(ch != -1){
				//System.out.print(ch);
				message[i] = (char)ch;
				ch = isr.read();
				i++;
			}
		}catch(Exception e){
			System.out.println("��ȡ�ļ�ʱ����");
			e.printStackTrace();
			return false;
		}
		//System.out.println(message.toString());
		this.end = i;
		return true;
	}
	
	/**
	 * �ӻ����ַ������ж�ȡһ�����ʣ��������ȡ��λ��
	 * @return ��ȡ���ĵ��� ,���û�и��൥�ʷ��ؿ�
	 */
	public String getNextWord(){					
		if(this.index>=this.end) return "";
		int begin = this.index;
		char ch = this.message[this.index];
		while(ch!=' ' && ch!='\r' && ch!='\n'){
			this.index++;
			ch =this.message[this.index];
		}
		char[] word = new char[this.index-begin];
		for(int i=0;i<word.length;i++){
			word[i] = this.message[begin+i];
		}
		while(ch==' ' || ch=='\r' || ch=='\n'){
			if(this.index>this.end) break;
			this.index++;
			ch = this.message[this.index];
		}
		String lalala = new String(word);
		return lalala;
	}
}
