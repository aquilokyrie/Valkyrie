package core;

import com.amd.aparapi.Kernel.EXECUTION_MODE;
import com.amd.aparapi.Range;

/**
 * ��װ�ļ���������
 * @author ��ƽ
 *
 */
public class Valkyrie {	
	public static void main(String args[]){		
		Config.SetConfig();									//���������ļ����г�ʼ��		
		Communication com = new Communication();			//��ʼ������������
		Heterogeneous h = new Heterogeneous(com);
		h.setExecutionMode(EXECUTION_MODE.JTP);
		Range range = Range.create(1); 
		h.execute(range);
	}
}
