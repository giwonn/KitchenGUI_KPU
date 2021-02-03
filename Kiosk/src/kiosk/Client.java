package kiosk;

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

import kiosk.page.thank.ThankPage;
import kiosk.page.welcome.WelcomePage2;

/**
 * Class Role : 프로그램 실행을 담당한다.
 */
public  class Client {
	static Socket socket=null;
	static OutputStream os=null;
	static Writer writer=null;
	static Recieve recieve=null;
	public static BufferedWriter bw=null;
	public static BufferedWriter br=null;
	private static String time="0";
	 static Timerr timerr;
	    public static void main(String[] args) {
	    	try{
	        	   InetAddress local = InetAddress.getLocalHost();
	        	   String ip = local.getHostAddress();
	               socket=new Socket();
//	               socket.connect(new InetSocketAddress(ip,9001));
	               socket.connect(new InetSocketAddress("192.168.1.100",9001));
	               System.out.println("Server 연결 성공");
	               os=socket.getOutputStream();
	               writer=new OutputStreamWriter(os);
	               bw=new BufferedWriter(writer);
	               bw.write("0000" + "\n");
	  	           bw.flush();
	  	           recieve=new Recieve(socket);
	  	           recieve.start();
	  	         
	            }catch(Exception e){System.out.println(e.getMessage());}
	           finally{
	        	   MainFrame.showScreen();
	           }
	    }
	    
	    public static class Recieve extends Thread {
			Socket socket=null;
			InputStream is=null;
			Reader reader=null;
			BufferedReader br=null;
			String Msg=null;
			
			 public Recieve(Socket socket) {
				 this.socket=socket;
				 try {
					
					 is=socket.getInputStream();
						reader=new InputStreamReader(is);
						br=new BufferedReader(reader);
				 }catch(Exception e){System.out.println(e.getMessage());}
			 }
			 public void run() {
				 try {
				
					 while(true) {
						 
						 Msg=br.readLine();
						 System.out.println("Receive Server: "+ Msg);
						 // Msg == 대기인원.대기시간.1or0(1일때주문번호추가, 0일땐 아무것도안함)
						 String arr[] = Msg.split(",");
						 if(!time.equals(arr[1])){
							 if(!(timerr==null))
							 timerr.interrupt();
						 WelcomePage2.WaitingLine = Integer.parseInt(arr[0]);
						 WelcomePage2.WaitingTime = Integer.parseInt(arr[1]);
						 WelcomePage2.WaitNum.setText(WelcomePage2.WaitingLine + " 명");
						 WelcomePage2.WaitTime.setText(WelcomePage2.WaitingTime + " 분");
						// WelcomePage2.Time();
						
						timerr=new Timerr(arr[1]);
						 timerr.start();
						 }
						 time=arr[1];
						 if(arr[2].equals("1"))
							 ThankPage.OrderNum++;
					 }
				 }catch(Exception e){System.out.println(e.getMessage());}
			 }
		}
	    
	    
	    public static class Timerr extends Thread {
	        long time = 0;
	        long time1 = 0;
	        int minutes = 0;
	        int seconds = 0;
	        int settime=0;
	        public Timerr(String msg) {
	            time = 0;
	            time1 = System.currentTimeMillis();
	            minutes = Integer.parseInt(msg);
	        }

	        public void run() {
	            try {
	                while (!(timerr.isInterrupted())) {
	                    time = System.currentTimeMillis();
	                    long millitime = time - time1;
	                    int m = (int) (millitime / 1000.0 / 60.0);
	                    int s = (int) (millitime % (1000.0 * 60) / 1000.0);
	                    int ms = (int) (millitime % 1000 / 10.0);
	                    if (minutes > 0) {
	                        if (seconds > 0) {
	                            seconds -= 1;
	                        } else {
	                            seconds += 60;
	                            seconds -= 1;
	                            minutes -= 1;
	                            settime=minutes+1;
	                            
	                            WelcomePage2.WaitTime.setText(String.valueOf(settime) + " 분");
	                        }

	                    } else if (minutes == 0) {
	                        if (seconds > 0) {
	                            seconds -= 1;
	                        } else {
	                            break;
	                        }

	                    }

	                    String tt = String.format("%02d : %02d : %02d", minutes, seconds, ms);
	                 
	                    Thread.sleep(1000);
	                }
	            }catch (InterruptedException e){e.getMessage();}
	            finally{
	  //             System.out.println("dead");
	            }
	        }
	    }
}

