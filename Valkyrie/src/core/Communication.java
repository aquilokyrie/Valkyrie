package core;

import java.net.*;
import java.io.*;

import assist.Logger;

/**
 * 本类在程序中专门用于处理网络连接，同时封装了ServerSocket和Socket两个类，使其用法更加直观
 * 本类暂时没有构造函数
 * 
 */
public class Communication {
	
	
	private ServerSocket serverSocket;					//监听使用的ServerSocket
	private Socket coSocket;							//传递消息使用的socket
	private static final int BUFFER_SIZE = 1024;		//发送的数据块的默认大小
	OutputStream output;								//发送文件时使用的输出流对象
	
	public Communication(){
		
	}
	
	public Boolean Listen(){								//监听函数
		try{
			serverSocket = new ServerSocket(Config.GetPort());
			Logger.info("服务器Socket启动");	
			Logger.info("服务器启动监听"+serverSocket);
		}catch(IOException e){			
			Logger.info("服务器Socket启动失败,系统即将退出");
			//e.printStackTrace();
			System.exit(0);
		}
		return true;
	}
	
	
	public Boolean Connect(String addr,int port){						//客户端连接服务器的函数
		try{
			InetAddress serverAddress = InetAddress.getByName(addr);		
			Logger.info("addr="+addr);
			coSocket = new Socket(serverAddress,port);		//连接服务器
		}catch(UnknownHostException e){
			Logger.info("没有找到相应ip地址的主机");			
			//e.printStackTrace();
			return false;
		}catch(IOException e){
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public Socket Accept(){								//接收连接	
		try{			
			coSocket = serverSocket.accept();
			Logger.info("收到连接"+coSocket);
		}catch(IOException e){
			Logger.info("accept链接请求时出现错误");
			//e.printStackTrace();
			return null;
		}
		try {
			this.output = this.coSocket.getOutputStream();
		} catch (Exception e) {
			Logger.info("获得连接的输出流时出错");
			//e.printStackTrace();
		}
		return this.coSocket;
	}
	
	public String GetData(){						//获得一个字符串
		String message = "";
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(coSocket.getInputStream()));
				message = in.readLine().trim();
			}catch(Exception e){
				Logger.info("读取HTTP请求信息错误,连接将关闭");
				return "";
			}
		return message;
	}
	
	
	public Boolean SendString(String message){					//发送字符串到对方
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(coSocket.getOutputStream())),true);
			out.println(message);
		}catch(IOException e){			
			Logger.info("发送信息错误");
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean SendStaicResource(String uri) throws IOException	{
		byte[] bytes = new byte[BUFFER_SIZE];		//用于存储被发送的数据
		FileInputStream files = null;
		File file = new File(Config.GetWebroot() , uri);//(路径文件夹,文件名)
		try{			
			Logger.info("上一个连接请求文件："+uri);
			if(file.exists()){
				files = new FileInputStream(file);
				int  ch = files.read(bytes,0,BUFFER_SIZE);			//将文件信息读入bytes数组中,每块的大小为buffer_size
				int p = 1;
				while(ch!=-1){
					output.write(bytes, 0, ch);
					ch = files.read(bytes, 0, BUFFER_SIZE);
					//Logger.info("已发送"+p+"块数据");
					p++;
				}
				Logger.info("文件" + uri + "发送完毕");
			}else{													//文件不存在
				Logger.info("文件"+uri+"未找到,连接将关闭");
//				String errorMessage = "HTTP/1.1 404 文件未找到\r\n" +
//										"Content-Type:text/html\r\n" +
//										"Content-Length:23\r\n" +
//										"\r\n" +
//										"<h1>文件未找到</h1>";
				String errorMessage = "HTTP/1.1 500 file not found@@@";
				this.SendString(errorMessage);
//				output.write(errorMessage.getBytes());
			}
		}catch(Exception e){
			Logger.info("文件"+uri+"打开失败");
			Logger.info(e.toString());						//文件打开失败
			return false;
		}finally{
			if(files != null) files.close();						//勿忘关闭文件
		}
		return true;
	}
	
	
	public Boolean coSocketClose(){		
		try{
			coSocket.close();		//关闭处理socket（关闭连接）
		}catch(IOException e){
			Logger.info("交换数据用socket关闭异常");
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public Boolean serverSocketClose(){		
		try{
			serverSocket.close();		//关闭服务器socket（关闭监听）
		}catch(IOException e){
			Logger.info("服务器socket关闭异常");
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
