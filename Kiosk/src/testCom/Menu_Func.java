package testCom;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu_Func {
   static int n = 0;
   static String path = "E:\\Menu.csv";
   public static Info_Menu[] info = new Info_Menu[100];
}

/************조리대기메뉴 추가************/
class Add {					// 메뉴 추가 메소드
   BufferedReader br = null;
   public Add() {}
   public void add() throws IOException {
	  br = Files.newBufferedReader(Paths.get(Menu_Func.path));
      String line = "";
      Scanner add = new Scanner(System.in);
      System.out.print("어떤 메뉴를 추가하시겠습니까?: ");
      String addmenu = add.nextLine();
      while((line = br.readLine()) != null) {	//엑셀파일을 끝까지 읽음
          String array[] = line.split(",");			// ,가 있으면 배열을 나눔
          if(addmenu.equals(array[0])) {
            Menu_Func.info[Menu_Func.n] = new Info_Menu();	//n번째 메뉴 배열을 만듬
            Menu_Func.info[Menu_Func.n].setMenu(addmenu);	//그 배열에 신청된 메뉴를 넣어줌
            System.out.println((Menu_Func.n+1) + "번에 '" + addmenu + "'이 추가되었습니다.");
            System.out.print("대기중인 주문 : ");
            for(int i=0; i<Menu_Func.n+1; i++)	//첫번째 대기메뉴부터 마지막 대기메뉴까지 출력
               System.out.print((i+1) + "." + Menu_Func.info[i].getMenu() + "  ");	
            System.out.println();
            Menu_Func.n++;		//다음 메뉴를 받기위해 n을 증가시켜줌
          }
      }
   }
}

/************조리대기메뉴 삭제************/
class Remove {
   public Remove() {
   }
   public void remove() {
	   Scanner input = new Scanner(System.in);
      while(true) {
         System.out.print("몇번을 삭제하시겠습니까?: ");
         int del = input.nextInt();		//삭제할 번호 del을 입력받음
         if(del > Menu_Func.n || del <= 0)	//del이 현재 주문대기개수(=n)보다 크거나 작을경우 실행
            System.out.println("잘못된 주문번호입니다. 다시 입력해주세요.");
         else if(del <= Menu_Func.n && del > 0) {
            for(int i=del; i<Menu_Func.n; i++)
            	Menu_Func.info[i-1] = Menu_Func.info[i];
            Menu_Func.n--;
            break;
         }
      }
      System.out.print("대기중인 주문 : ");
      for(int i=0; i<Menu_Func.n; i++)
            System.out.print((i+1) + "." + Menu_Func.info[i].getMenu() + "  ");
      System.out.println();  
   }
}

/************조리대기메뉴, 주문들어온메뉴 출력************/
class Printmenu {
	String Msg;
	BufferedReader br = null;
	
	public Printmenu(String Msg) {
		this.Msg = Msg;
	}
	
	public void printmenu() throws IOException {
		br = Files.newBufferedReader(Paths.get(Menu_Func.path));	//메인클래스에 선언된 경로를 불러온다.
        Charset.forName("UTF-8");
        String line = "";
        while ((line = br.readLine()) != null) {
                 String array[] = line.split(",");
                 if(Msg.equals(array[0])) { //엑셀의 메뉴와 입력값이 같은지 문자열비교(=주문한 메뉴가 메뉴판에 있을때 실행)
                    System.out.println();
                    System.out.println("---------들어온 주문----------");
                    System.out.println("메뉴 : " + array[0]);
                    System.out.println("가격 : " + array[1] + "원");
                    System.out.println("--------------------------");
                    System.out.print("대기중인 주문 : ");
                    Menu_Func.info[Menu_Func.n] = new Info_Menu();
                    Menu_Func.info[Menu_Func.n].setMenu(array[0]);
                    for(int i=0; i<(Menu_Func.n+1); i++)
                       System.out.print((i+1) + "." + Menu_Func.info[i].getMenu() + "  ");
                    System.out.println();
                    System.out.print("Send Client(기능->추가,삭제): ");
                    Menu_Func.n++;
                 }
	}
	}
}
