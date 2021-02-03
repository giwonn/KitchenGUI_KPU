package kiosk.page.order.MenuPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kiosk.page.ImageTextButton;
import kiosk.page.KioskPage;
import kiosk.page.confirm.ConfirmButton;
import kiosk.page.order.MenuButton;
import kiosk.page.order.MenuTablePanel;


public class DrinkPanel extends JPanel {
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
	DrinkPage2 page2 = new DrinkPage2();
	DrinkPage3 page3 = new DrinkPage3();
	
	//JButton prev_button = new JButton("이전");
	ImageTextButton prev_button1 = new ImageTextButton();
	ImageTextButton next_button1 = new ImageTextButton();
	ImageTextButton prev_button2 = new ImageTextButton();
	ImageTextButton next_button2 = new ImageTextButton();
	ImageTextButton prev_button3 = new ImageTextButton();
	ImageTextButton next_button3 = new ImageTextButton();
	JPanel drinkpanel=new JPanel();
	BorderLayout border = new BorderLayout();
	
	public DrinkPanel(){
		
		prev_button1.setResizedImg(new ImageIcon("image/button/prev_button_NO.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		prev_button1.setBorderPainted(false);
		prev_button1.setFocusPainted(false);
		next_button1.setResizedImg(new ImageIcon("image/button/next_button.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		next_button1.setBorderPainted(false);
		next_button1.setFocusPainted(false);
		prev_button2.setResizedImg(new ImageIcon("image/button/prev_button.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		prev_button2.setBorderPainted(false);
		prev_button2.setFocusPainted(false);
		next_button2.setResizedImg(new ImageIcon("image/button/next_button.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		next_button2.setBorderPainted(false);
		next_button2.setFocusPainted(false);
		prev_button3.setResizedImg(new ImageIcon("image/button/prev_button.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		prev_button3.setBorderPainted(false);
		prev_button3.setFocusPainted(false);
		next_button3.setResizedImg(new ImageIcon("image/button/next_button_NO.png"), BUTTON_WIDTH, BUTTON_HEIGHT);
		next_button3.setBorderPainted(false);
		next_button3.setFocusPainted(false);
		
		/*************button이 들어있는 패널**************/
		page1_buttonpanel.add(prev_button1, button_border1.WEST);
		page1_buttonpanel.add(next_button1, button_border1.EAST);
		page1_buttonpanel.setBackground(Color.WHITE);
		
		page2_buttonpanel.add(prev_button2, button_border2.WEST);
		page2_buttonpanel.add(next_button2, button_border2.EAST);
		page2_buttonpanel.setBackground(Color.WHITE);
		
		page3_buttonpanel.add(prev_button3, button_border2.WEST);
		page3_buttonpanel.add(next_button3, button_border2.EAST);
		page3_buttonpanel.setBackground(Color.WHITE);
		/********************************************/
		
		/*************패널 초기화면**********************/
		drinkpanel.setLayout(border);
		make_page1();
		drinkpanel.add(page1, border.CENTER);
		drinkpanel.add(page1_buttonpanel, border.SOUTH);
		this.setBackground(Color.WHITE);
		/********************************************/
		this.add(drinkpanel);
		
		
		prev_button1.addActionListener((args)-> {
			
			if(cnt == 1) {}
			
			revalidate();
			repaint();
		});
		next_button1.addActionListener((args)-> {

			if(cnt == 1) {
				drinkpanel.removeAll();
				drinkpanel.add(page2, border.CENTER);
				drinkpanel.add(page2_buttonpanel, border.SOUTH);
				cnt++;
			}
			
			revalidate();
			repaint();
		});
		
		prev_button2.addActionListener((args)-> {
			
			if(cnt == 2) {
				drinkpanel.removeAll();
				drinkpanel.add(page1, border.CENTER);
				drinkpanel.add(page1_buttonpanel, border.SOUTH);
				cnt--;
			}
			
			revalidate();
			repaint();
		});
		next_button2.addActionListener((args)-> {
			
			if(cnt == 2) {
				drinkpanel.removeAll();
				drinkpanel.add(page3, border.CENTER);
				drinkpanel.add(page3_buttonpanel, border.SOUTH);
				cnt++;
			}
			
			revalidate();
			repaint();
		});
		prev_button3.addActionListener((args)-> {
			

			if(cnt == 3) {
				drinkpanel.removeAll();
				drinkpanel.add(page2, border.CENTER);
				drinkpanel.add(page2_buttonpanel, border.SOUTH);
				cnt--;
			}
			
			revalidate();
			repaint();
		});
		next_button3.addActionListener((args)-> {
			
			if(cnt == 3) {}
			
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
	 			if(array[0].equals("(음료&차)")) {
					drinkTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
					count++;
					if(count == 12)
		 				break;
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}

class DrinkPage2 extends JPanel {
	
	JPanel page2 = new JPanel();
	MenuTablePanel drinkTable = new MenuTablePanel(0, 4);
	
	public DrinkPage2(){

		this.setBackground(Color.WHITE);
		make_page2();
		
		this.add(page2);
	}
	
	private void make_page2() {
		
		int count = 0;
		page2.setBackground(Color.WHITE);
		page2.add(drinkTable);
		
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get("Menu.csv"));
			String line = "";
	 		while((line = br.readLine()) != null) {
	 			String array[] = line.split(",");
	 			if(array[0].equals("(음료&차)")) {
	 				if(count < 13)
	 					count++;
	 				
					if(count >= 13) {
						drinkTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
						count++;
						if(count == 25)
			 				break;
					}
					
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}

class DrinkPage3 extends JPanel {
	
	JPanel page3 = new JPanel();
	MenuTablePanel drinkTable = new MenuTablePanel(0, 4);
	
	public DrinkPage3(){

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
	 			if(array[0].equals("(음료&차)")) {
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
