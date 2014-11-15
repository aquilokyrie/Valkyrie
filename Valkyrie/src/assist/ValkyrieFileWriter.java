package assist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 类ValkyrieFileWriter，为了更加方便地向文件中写入数据
 * 使用想要写入的文件名进行类的初始化，调用writeLine来写入一行数据
 * 最后不要忘了调用close来关闭文件
*/

public class ValkyrieFileWriter {
	private File file;								//本对象的完整文件名
	private FileOutputStream fos;
	private OutputStreamWriter osr;

	/**
	 * 返回正在操作的文件的完整文件名
	 * @return 正在操作的文件的完整文件名
	 */
	public String getfilename(){					
		return file.getAbsolutePath();
	}
	
	/**
	 * 构造想要写入的文件的对象
	 * @param file 目标文件的java.io.File类的对象
	 */
	public ValkyrieFileWriter(File file){			//构造函数，想要写入的文件对象来进行初始化
		this.file = file;	
		try{
			this.fos = new FileOutputStream(file.getAbsolutePath(),true);
			this.osr = new OutputStreamWriter(fos);
		}catch(Exception e){
			Logger.info("初始化写文件类"+file+"失败");
		}
	}
	
	/**
	 * 向文件的末尾写入一行数据并自动换行
	 * @param message 想要写入文件末尾的字符串
	 * @return 表达操作是否成功的布尔值
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
			Logger.info("写文件"+file+"出错");
			return false;
		}			
		return true;
	}	
	
	/**
	 * 关闭文件
	 * @return 表达关闭文件是否成功的布尔值
	 */
	public boolean close(){							//关闭刚才操作过的文件
		try{
			osr.close(); fos.close();
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
