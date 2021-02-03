package kiosk.page.order.MenuPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kiosk.page.ImageTextButton;
import kiosk.page.confirm.Phonenumber;
import kiosk.page.order.MenuButton;
import kiosk.page.order.MenuTablePanel;

public class BagelPanel extends JPanel{
	int cnt=1;
	int BUTTON_WIDTH = 100;
	int BUTTON_HEIGHT = 100;
	
	BorderLayout button_border1 = new BorderLayout();
	BorderLayout button_border2 = new BorderLayout();
	JPanel page1_buttonpanel = new JPanel(button_border1);
	JPanel page2_buttonpanel = new JPanel(button_border2);
	
	JPanel page1 = new JPanel();
	DessertPage2 page2 = new DessertPage2();
	DessertPage3 page3 = new DessertPage3();
	
	ImageTextButton prev_button = new ImageTextButton();
	ImageTextButton next_button = new ImageTextButton();
	ImageTextButton prev_button_NO = new ImageTextButton();
	ImageTextButton next_button_NO = new ImageTextButton();
	
	JPanel bagelpanel=new JPanel();
	BorderLayout border = new BorderLayout();
	
	public BagelPanel() {
		
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
		/********************************************/
		
		
		/*************패널 초기화면**********************/
		bagelpanel.setLayout(border);
		make_page1();
		bagelpanel.add(page1, border.CENTER);
		bagelpanel.add(page1_buttonpanel, border.SOUTH);
		this.setBackground(Color.WHITE);
		/********************************************/
		
		this.add(bagelpanel);
		
		
		prev_button.addActionListener((args)-> {
			
			if(cnt == 1) {}
			if(cnt == 2) {
				bagelpanel.removeAll();
				bagelpanel.add(page1, border.CENTER);
				bagelpanel.add(page1_buttonpanel, border.SOUTH);
				cnt--;
			}
			revalidate();
			repaint();
		});
		
		next_button.addActionListener((args)-> {

			if(cnt == 2) {}
			if(cnt == 1) {
				bagelpanel.removeAll();
				bagelpanel.add(page2, border.CENTER);
				bagelpanel.add(page2_buttonpanel, border.SOUTH);
				cnt++;
			}
			
			revalidate();
			repaint();
		});
	}
	
	private void make_page1() {
		MenuTablePanel bagelTable = new MenuTablePanel(0, 4);
		int count = 0;
		page1.setBackground(Color.WHITE);
		page1.add(bagelTable);
		
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get("Menu.csv"));
			String line = "";
	 		while((line = br.readLine()) != null) {
	 			String array[] = line.split(",");
	 			if(array[0].equals("(베이글)")) {
					bagelTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
					count++;
					if(count == 12) // 12개로 설정해야함
		 				break;
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}

class BagelPage2 extends JPanel {
	
	JPanel page2 = new JPanel();
	MenuTablePanel BagelTable = new MenuTablePanel(0, 4);
	
	public BagelPage2(){

		this.setBackground(Color.WHITE);
		make_page2();
		
		this.add(page2);
	}
	
	private void make_page2() {
		
		int count = 0;
		page2.setBackground(Color.WHITE);
		page2.add(BagelTable);
		
		BufferedReader br;
		try {
			br = Files.newBufferedReader(Paths.get("Menu.csv"));
			String line = "";
	 		while((line = br.readLine()) != null) {
	 			String array[] = line.split(",");
	 			if(array[0].equals("(베이글)")) {
	 				if(count < 13)
	 					count++;
	 				
					if(count >= 13) {
						BagelTable.addMenu(new MenuButton(Integer.parseInt(array[1]), array[2], array[3], Integer.parseInt(array[4]), Integer.parseInt(array[5])));
						count++;
						if(count == 25)
			 				break;
					}
					
	 			}
	 		}
		} catch (IOException e) {e.printStackTrace();}
	}
}

class BagelPage3 extends JPanel {
	
	public BagelPage3(){

		this.setBackground(Color.WHITE);
		JButton b1=new JButton("3페이지");
		
		this.add(b1);
	}
}
