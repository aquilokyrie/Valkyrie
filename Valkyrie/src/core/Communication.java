package core;

import java.net.*;
import java.io.*;

import assist.Logger;

/**
 * �����ڳ�����ר�����ڴ����������ӣ�ͬʱ��װ��ServerSocket��Socket�����࣬ʹ���÷�����ֱ��
 * ������ʱû�й��캯��
 * 
 */
public class Communication {
	
	
	private ServerSocket serverSocket;					//����ʹ�õ�ServerSocket
	private Socket coSocket;							//������Ϣʹ�õ�socket
	private static final int BUFFER_SIZE = 1024;		//���͵����ݿ��Ĭ�ϴ�С
	OutputStream output;								//�����ļ�ʱʹ�õ����������
	
	public Communication(){
		
	}
	
	public Boolean Listen(){								//��������
		try{
			serverSocket = new ServerSocket(Config.GetPort());
			Logger.info("������Socket����");	
			Logger.info("��������������"+serverSocket);
		}catch(IOException e){			
			Logger.info("������Socket����ʧ��,ϵͳ�����˳�");
			//e.printStackTrace();
			System.exit(0);
		}
		return true;
	}
	
	
	public Boolean Connect(String addr,int port){						//�ͻ������ӷ������ĺ���
		try{
			InetAddress serverAddress = InetAddress.getByName(addr);		
			Logger.info("addr="+addr);
			coSocket = new Socket(serverAddress,port);		//���ӷ�����
		}catch(UnknownHostException e){
			Logger.info("û���ҵ���Ӧip��ַ������");			
			//e.printStackTrace();
			return false;
		}catch(IOException e){
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public Socket Accept(){								//��������	
		try{			
			coSocket = serverSocket.accept();
			Logger.info("�յ�����"+coSocket);
		}catch(IOException e){
			Logger.info("accept��������ʱ���ִ���");
			//e.printStackTrace();
			return null;
		}
		try {
			this.output = this.coSocket.getOutputStream();
		} catch (Exception e) {
			Logger.info("������ӵ������ʱ����");
			//e.printStackTrace();
		}
		return this.coSocket;
	}
	
	public String GetData(){						//���һ���ַ���
		String message = "";
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(coSocket.getInputStream()));
				message = in.readLine().trim();
			}catch(Exception e){
				Logger.info("��ȡHTTP������Ϣ����,���ӽ��ر�");
				return "";
			}
		return message;
	}
	
	
	public Boolean SendString(String message){					//�����ַ������Է�
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(coSocket.getOutputStream())),true);
			out.println(message);
		}catch(IOException e){			
			Logger.info("������Ϣ����");
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean SendStaicResource(String uri) throws IOException	{
		byte[] bytes = new byte[BUFFER_SIZE];		//���ڴ洢�����͵�����
		FileInputStream files = null;
		File file = new File(Config.GetWebroot() , uri);//(·���ļ���,�ļ���)
		try{			
			Logger.info("��һ�����������ļ���"+uri);
			if(file.exists()){
				files = new FileInputStream(file);
				int  ch = files.read(bytes,0,BUFFER_SIZE);			//���ļ���Ϣ����bytes������,ÿ��Ĵ�СΪbuffer_size
				int p = 1;
				while(ch!=-1){
					output.write(bytes, 0, ch);
					ch = files.read(bytes, 0, BUFFER_SIZE);
					//Logger.info("�ѷ���"+p+"������");
					p++;
				}
				Logger.info("�ļ�" + uri + "�������");
			}else{													//�ļ�������
				Logger.info("�ļ�"+uri+"δ�ҵ�,���ӽ��ر�");
//				String errorMessage = "HTTP/1.1 404 �ļ�δ�ҵ�\r\n" +
//										"Content-Type:text/html\r\n" +
//										"Content-Length:23\r\n" +
//										"\r\n" +
//										"<h1>�ļ�δ�ҵ�</h1>";
				String errorMessage = "HTTP/1.1 500 file not found@@@";
				this.SendString(errorMessage);
//				output.write(errorMessage.getBytes());
			}
		}catch(Exception e){
			Logger.info("�ļ�"+uri+"��ʧ��");
			Logger.info(e.toString());						//�ļ���ʧ��
			return false;
		}finally{
			if(files != null) files.close();						//�����ر��ļ�
		}
		return true;
	}
	
	
	public Boolean coSocketClose(){		
		try{
			coSocket.close();		//�رմ���socket���ر����ӣ�
		}catch(IOException e){
			Logger.info("����������socket�ر��쳣");
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public Boolean serverSocketClose(){		
		try{
			serverSocket.close();		//�رշ�����socket���رռ�����
		}catch(IOException e){
			Logger.info("������socket�ر��쳣");
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void SetCoSocket(Socket s,OutputStream os){
		this.coSocket = s;
		this.output = os;
	}
	public OutputStream getOutput(){
		return this.output;
	}
	
}
