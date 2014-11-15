package core;

import com.amd.aparapi.Kernel.EXECUTION_MODE;
import com.amd.aparapi.Range;

/**
 * 包装文件的主函数
 * @author 春平
 *
 */
public class Valkyrie {	
	public static void main(String args[]){		
		Config.SetConfig();									//根据配置文件进行初始化		
		Communication com = new Communication();			//开始监听链接请求
		Heterogeneous h = new Heterogeneous(com);
		h.setExecutionMode(EXECUTION_MODE.JTP);
		Range range = Range.create(1); 
		h.execute(range);
	}
}
