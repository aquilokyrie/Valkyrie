package assist;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 为了其他模块读取文件的方便，创建了此类
 * 先用File对象来构造实例
 * 然后调用read方法读取文件，close方法关闭文件，此时文件信息全部在一个缓冲数组message中
 * 然后使用getNextWord可以逐个读取缓冲区中的单词
 */

public class ValkyrieFileReader {
	private File file;								//本对象的完整文件名
	private FileInputStream fis;
	private InputStreamReader isr;
	private char[] message;							//读取文件的缓冲区
	private int index;								//当前操作的缓冲区位置的索引
	private int end;								//当前字符缓冲区的有效长度
	
	/**
	 * 根据File对象构造实例
	 * @param file 待读取的文件对象
	 */
	public ValkyrieFileReader(File file){			//构造函数，想要读取的文件对象
		//System.out.println("构造读文件类");
		this.file = file;
		message = new char[4048];
		for(int m=0;m<4048;m++) message[m]=' ';
		this.index = 0;
		try{
			this.fis = new FileInputStream(this.file.getAbsolutePath());
			this.isr = new InputStreamReader(fis);
		}catch(Exception e){
			System.out.println("读文件类构造出错");
			e.printStackTrace();
		}		
	}
	
	/**
	 * 关闭文件
	 * @return 表达关闭文件是否成功的布尔值
	 */
	public boolean close(){							//关闭文件并释放资源
		try{
			this.fis.close();
			this.isr.close();
		}catch(Exception e){
			System.out.println("关闭文件出错");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * //将文件的所有内容读到缓冲数组
	 * @return 表达操作是否成功的布尔值
	 */
	public boolean read(){							
		//System.out.println("准备读取");
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
			System.out.println("读取文件时出错");
			e.printStackTrace();
			return false;
		}
		//System.out.println(message.toString());
		this.end = i;
		return true;
	}
	
	/**
	 * 从缓冲字符数组中读取一个单词，并保存读取的位置
	 * @return 读取到的单词 ,如果没有更多单词返回空
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
