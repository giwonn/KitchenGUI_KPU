package testCom;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
static Socket socket=null;
static Write write=null;
static Recieve recieve=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 try{
	 socket=new Socket();
	 InetAddress local = InetAddress.getLocalHost();
	 String ip = local.getHostAddress();
	 socket.connect(new InetSocketAddress(ip,9001));
	 System.out.println("Server 연결 성공");
	 write=new Write(socket);
	 write.start();
	 recieve=new Recieve(socket);
	 recieve.start();
 }catch(Exception e){System.out.println(e.getMessage());}
	}

	public static class Write extends Thread{
		Socket socket=null;
		 OutputStream os=null;
		 Writer writer=null;
		 BufferedWriter bw=null;
		 public Write(Socket socket)
		 {
			 this.socket=socket;
			 try{
				 os=socket.getOutputStream();
					writer=new OutputStreamWriter(os);
					bw=new BufferedWriter(writer);
			 }catch(Exception e){System.out.println(e.getMessage());}
		 }
		 public void run()
		 {
			 try{
				 while(true)
				 {
					 Scanner message=new Scanner(System.in);
					 System.out.println("Send Server: ");
					 
					 String input=message.nextLine();
					 bw.write(input+"\n");
					 bw.flush();
				 }
			 }catch(Exception e){System.out.println(e.getMessage());}
		 }
	}
	public static class Recieve extends Thread{
		Socket socket=null;
		InputStream is=null;
		 Reader reader=null;
		 BufferedReader br=null;
		 String Msg=null;
		 public Recieve(Socket socket)
		 {
			 this.socket=socket;
			 try{
				 is=socket.getInputStream();
					reader=new InputStreamReader(is);
					br=new BufferedReader(reader);
					System.out.println(br.readLine());
			 }catch(Exception e){System.out.println(e.getMessage());}
		 }
		 public void run()
		 {
			 try{
			 while(true)
			 {
				 Msg=br.readLine();
				 System.out.println("Receive Server: "+Msg);
			 }
		 }catch(Exception e){System.out.println(e.getMessage());}
	}
	}
}
