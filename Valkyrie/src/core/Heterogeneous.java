package core;

import assist.Logger;

import com.amd.aparapi.Kernel;

/**
 * ��������ض��˿�,���յ��µ�http����ʱ�����µ��߳�
 * ������ͼ������GPU��
 * @author ��ƽ
 *
 */
public class Heterogeneous extends Kernel{
	
	private Communication community;		//���Ӷ���

	/**
	 * ������������Ķ��̹߳�����
	 * @param com �������ӵ�ʱ�����ɵ����Ӷ���
	 */
	public Heterogeneous(Communication com) {	
		this.community = com;
	}

	/**
	 * ����ĳ������ķ���
	 * ����Ӧ�ü��ص�GPU��ʹ��
	 * �÷����еĴ���Ԥ�ڽ�Ҫ��GPU������
	 */
	@Override
	public void run() {		
		community.Listen();
		while(true){			
			Communication c = new Communication();		//����һ���µ�communication���󲢼�������������֮�󱻷��䵽
														//�µ��߳�
			//Logger.info("���߳����ڼ���");
			c.SetCoSocket(community.Accept(),community.output);
			//��ͼʹ��GPU����һ���µ��̣߳����߳��ڲ��������󣬱�����ղŵ�socket
			MultiThreading mm = new MultiThreading(c);	
			Thread thr = new Thread(mm);
			//Logger.info("����ģʽ:"+this.getExecutionMode());
		 	thr.start();
		}		
	}
}
