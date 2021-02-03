package testCom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Fileserver {
   static Write write=null;
   static reMsg remsg=null;   
   public static void main(String[] args) {
	  
      ServerSocket serversocket =null;
      try {
         System.out.println("대기중..");
         serversocket=new ServerSocket();  //소켓 생성
         InetAddress local = InetAddress.getLocalHost();
         String ip = local.getHostAddress();
         serversocket.bind(new InetSocketAddress(ip,9001));  //bind 생성
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
      if(!serversocket.isClosed()) {
         try{
            serversocket.close();
         }catch(IOException e1){}
      }
   }
   
   public static class Write extends Thread {
      Socket socket=null;
      BufferedReader br_csv = null;
       OutputStream os=null;
       Writer writer=null;
       BufferedWriter bw=null;
      
       public Write(Socket socket) {
            this.socket=socket;
            try {
               os=socket.getOutputStream();
               writer=new OutputStreamWriter(os);
               bw=new BufferedWriter(writer);
               
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
       public void run() {
            try{
               while(true){
                   Scanner message=new Scanner(System.in);
                   System.out.print("Send Client(기능-추가,삭제): ");
                   String input=message.nextLine();
                  
                   bw.write(input+"\n");
                   bw.flush();
               }
            }catch(Throwable e1){} 
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
         
       public reMsg(Socket socket){
          this.socket=socket;
          try{
        	  is=socket.getInputStream();
        	  reader=new InputStreamReader(is);
        	  br=new BufferedReader(reader);
          }catch(IOException e){}
       }
       
       public void run(){
          while(socket!=null && socket.isConnected()){
        	try{
             Msg=br.readLine();
             System.out.println(Msg);
        	}catch(Exception e){}
          }
       }
   }
}