package testCom;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

	static Write write=null;
	static reMsg remsg=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket serversocket =null;
		try {
			System.out.println("대기중..");
			serversocket=new ServerSocket();  //소켓 생성
			serversocket.bind(new InetSocketAddress("14.38.226.175",9000));  //bind 생성
			System.out.println("서버 소켓이 만들어졌습니다.");
			
			while(true){
				System.out.println("기다리는중...");
				Socket socket=serversocket.accept(); // accept 생성
				
				InetSocketAddress isa=(InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[연결을 수락함]"+isa.toString());
				
				write=new Write(socket);
				write.start();
				remsg=new reMsg(socket);
				remsg.start();
			}
			
		} catch (Exception e) {}
		if(!serversocket.isClosed())
		{
			try{
				serversocket.close();
			}catch(IOException e1){}
		}
	}
	
	public static class Write extends Thread{
		Socket socket=null;
		 
		 OutputStream os=null;
		 Writer writer=null;
		 BufferedWriter bw=null;
		
		 public Write(Socket socket)
			{
				this.socket=socket;
				try {
					os=socket.getOutputStream();
					writer=new OutputStreamWriter(os);
					bw=new BufferedWriter(writer);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		 public void run()
			{
			 	
			 	
				try{
					while(true){
						
						//String message=null;
						//if(br.readLine()!=null)
						
						//System.out.println("Client Receive message: "+message);
						 Scanner message=new Scanner(System.in);
						 System.out.println("Send Client: ");
						 String input=message.next();
						 bw.write(input+"\n");
						 bw.flush();
						 
						//bw.write("asd");
						//bw.newLine();
						//bw.flush();
						//System.out.println("[데이터 보내기 성공]");	
						
						
					}
				}catch(Throwable e1){
				} 
				finally{
					try {
						//br.close();		
						bw.close();
						//reader.close();
						//is.close();
						socket.close();
					} catch (IOException e) {e.printStackTrace();}
				}
	}
	}
	
	public static class reMsg extends Thread{
		Socket socket=null;
		 InputStream is=null;
		 Reader reader=null;
		 BufferedReader br=null;
		 String Msg=null;
		 
		 List<List<String>> ret = new ArrayList<List<String>>();
		 BufferedReader csv = null;
		 String line = "";
		 
		 public reMsg(Socket socket){
			 this.socket=socket;
		 
		 try{
			 is=socket.getInputStream();
				reader=new InputStreamReader(is);
				br=new BufferedReader(reader);
				
				csv = Files.newBufferedReader(Paths.get("C:\\Users\\42796\\Desktop\\student_utf8.csv"));
				Charset.forName("UTF-8");
				
				
		 }catch(IOException e){}
	}
		 public void run(){
			 while(socket!=null && socket.isConnected()){
			 try{
			 
				 Msg=br.readLine();
				 
				while((/*line = */br.readLine()) != null) {
						//CSV 1행을 저장하는 리스트
						List<String> tmpList = new ArrayList<String>();
						String array[] = line.split(",");
						//배열에서 리스트 반환
						tmpList = Arrays.asList(array);
						//System.out.println(tmpList);
						//if(Msg.equals(tmpList)) {
						if(tmpList.contains(Msg) == true) {
							System.out.println("주문 메뉴 : " + Msg);
							ret.add(tmpList); 
						}
						else
							ret.add(tmpList); 
					}
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
				//System.out.println("다시 입력해 주세요.");
			}finally {
				try {
					if(csv != null) {
						csv.close();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
}