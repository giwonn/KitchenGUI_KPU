package kiosk.page.order.MenuPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import kiosk.page.ImageTextButton;
import kiosk.page.order.MenuButton;
import kiosk.page.order.MenuTablePanel;

public class DessertPanel extends JPanel {
	int cnt=1;
	int BUTTON_WIDTH = 100;
	int BUTTON_HEIGHT = 100;
	
	BorderLayout button_border1 = new BorderLayout();
	BorderLayout button_border2 = new BorderLayout();
	BorderLayout button_border3 = new BorderLayout();
	JPanel page1_buttonpanel = new JPanel(button_border1);
	JPanel page2_buttonpanel = new JPanel(button_border2);
	JPanel page3_buttonpanel = new JPanel(button_border3);
	
	JPanel page1 = new JPanel();
	DessertPage2 page2 = new DessertPage2();
	DessertPage3 page3 = new DessertPage3();
	
	JButton button = new JButton("이전");
	ImageTextButton prev_button = new ImageTextButton();
	ImageTextButton next_button = new ImageTextButton();
	ImageTextButton prev_button_NO = new ImageTextButton();
	ImageTextButton next_button_NO = new ImageTextButton();
	
	JPanel drinkpanel=new JPanel();
	BorderLayout border = new BorderLayout();
	
	public DessertPanel(){
		
		prev_button.setResizedImg(new ImageIcon("image/button/prev_button.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		prev_button.setBorderPainted(false);
		prev_button.setFocusPainted(false);
		next_button.setResizedImg(new ImageIcon("image/button/next_button.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		next_button.setBorderPainted(false);
		next_button.setFocusPainted(false);
		prev_button_NO.setResizedImg(new ImageIcon("image/button/prev_button_NO.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		prev_button_NO.setBorderPainted(false);
		prev_button_NO.setFocusPainted(false);
		next_button_NO.setResizedImg(new ImageIcon("image/button/next_button_NO.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		next_button_NO.setBorderPainted(false);
		next_button_NO.setFocusPainted(false);
		
		/*************button이 들어있는 패널**************/
		page1_buttonpanel.add(prev_button_NO, button_border1.WEST);
		page1_buttonpanel.add(next_button, button_border1.EAST);
		page1_buttonpanel.setBackground(Color.WHITE);
		
		page2_buttonpanel.add(prev_button, button_border2.WEST);
		page2_buttonpanel.add(next_button_NO, button_border2.EAST);
		page2_buttonpanel.setBackground(Color.WHITE);
		
//		page3_buttonpanel.add(prev_button, button_border3.WEST);
//		page3_buttonpanel.add(next_button_NO, button_border3.EAST);
//		page3_buttonpanel.setBackground(Color.WHITE);
//		page1_buttonpanel.add(button, button_border1.EAST);
		/********************************************/
		
		
		/*************패널 초기화면**********************/
		drinkpanel.setLayout(border);
		make_page1();
		drinkpanel.add(page1, border.CENTER);
		drinkpanel.add(page1_buttonpanel, border.SOUTH);
		this.setBackground(Color.WHITE);
		/********************************************/
		
		this.add(drinkpanel);
		
		
		prev_button.addActionListener((args)-> {
			
			if(cnt == 1) {}
			if(cnt == 2) {
				drinkpanel.removeAll();
				drinkpanel.add(page1, border.CENTER);
				drinkpanel.add(page1_buttonpanel, border.SOUTH);
				cnt--;
			}
/*			if(cnt == 3) {
				drinkpanel.removeAll();
				drinkpanel.add(page2, border.CENTER);
				drinkpanel.add(buttonpanel, border.SOUTH);
				cnt--;
			}	
*/			
			revalidate();
			repaint();
		});
		
		next_button.addActionListener((args)-> {
			
/*			if(cnt == 3) {}
			if(cnt == 2) {
				drinkpanel.removeAll();
				drinkpanel.add(page3, border.CENTER);
				drinkpanel.add(buttonpanel, border.SOUTH);
				cnt++;
			}
*/
			if(cnt == 2) {}
			if(cnt == 1) {
				drinkpanel.removeAll();
				drinkpanel.add(page2, border.CENTER);
				drinkpanel.add(page2_buttonpanel, border.SOUTH);
				cnt++;
			}
			
			revalidate();
			repaint();
		});
	}
	
	private void make_page1() {
		MenuTablePanel drinkTable = new MenuTablePanel(0, 4);
		int count = 0;
		page1.setBackground(Color.WHITE);
		page1.add(drinkTable);
		
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get("Menu.csv"));
			String line = "";
	 		while((line = br.readLine()) != null) {
	 			String array[] = line.split(",");
	 			if(array[0].equals("(디저트)")) {
					drinkTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
					count++;
					if(count == 12) // 12개로 설정해야함
		 				break;
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}

class DessertPage2 extends JPanel {
	
	JPanel page2 = new JPanel();
	MenuTablePanel DessertTable = new MenuTablePanel(0, 4);
	
	public DessertPage2(){

		this.setBackground(Color.WHITE);
		make_page2();
		
		this.add(page2);
	}
	
	private void make_page2() {
		
		int count = 0;
		page2.setBackground(Color.WHITE);
		page2.add(DessertTable);
		
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get("Menu.csv"));
			String line = "";
	 		while((line = br.readLine()) != null) {
	 			String array[] = line.split(",");
	 			if(array[0].equals("(디저트)")) {
	 				if(count < 13)
	 					count++;
	 				
					if(count >= 13) {
						DessertTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
						count++;
						if(count == 25)
			 				break;
					}
					
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}

class DessertPage3 extends JPanel {
	
	JPanel page3 = new JPanel();
	MenuTablePanel drinkTable = new MenuTablePanel(0, 4);
	
	public DessertPage3(){

		this.setBackground(Color.WHITE);
		make_page3();
		
		this.add(page3);
	}
	
	private void make_page3() {
		
		int count = 0;
		page3.setBackground(Color.WHITE);
		page3.add(drinkTable);
		
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get("Menu.csv"));
			String line = "";
	 		while((line = br.readLine()) != null) {
	 			String array[] = line.split(",");
	 			if(array[0].equals("(디저트)")) {
	 				if(count < 25)
	 					count++;
	 				
					if(count >= 25) {
						drinkTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
						count++;
						if(count == 37)
			 				break;
					}
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}
