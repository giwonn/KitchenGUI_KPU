package kiosk.page.confirm;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kiosk.JTextFieldLimit;
import kiosk.MainFrame;
import kiosk.page.KioskGuidePanel;
import kiosk.page.KioskGuidePanel2;
import kiosk.page.KioskPage;
import kiosk.page.order.OrderPage;
import kiosk.page.thank.ThankPage;

public class Phonenumber extends KioskPage {
	Font font = new Font("arian", Font.BOLD, 35);	// 폰트 설정
	
	/* 중간패널 사이즈 */
	private final int MIDDLE_PANEL_WIDTH = KioskPage.PAGE_WIDTH * 4 / 5;
	private final int MIDDLE_PANEL_HEIGHT = KioskPage.PAGE_HEIGHT * 2 / 5;
	
	// 상단 패널(번호입력칸)
	private final KioskGuidePanel2 NUMBER_PANEL = new KioskGuidePanel2();
	private final KioskGuidePanel KEYPAD_PANEL = new KioskGuidePanel(0, 3);
	
	private static String number[] = new String[3];
	JLabel label, hyphen1, hyphen2;
	JTextField top, middle, bottom;
	
	
	// 하단 패널
		private final KioskGuidePanel YES_NO_SELECT_PANEL = new KioskGuidePanel(0, 2);
		private final ConfirmButton NO_BUTTON = new ConfirmButton("취소"); 
		private final ConfirmButton YES_BUTTON = new ConfirmButton("확인");
	
	public Phonenumber(boolean show) {
		initNumberAdd(show);
		initphonenumberpage();
		initkeypad();
		initYesNoSelectPanel();
		setListener();
	}
	
	private void initphonenumberpage() {
		this.setBackgroundImg("image/background.png");
		this.showBackgroundImg(true);
		this.showBackButton(false);
	}

	// 핸드폰번호 입력칸 추가
		private void initNumberAdd(boolean show) {
			final int PANEL_WIDTH = MIDDLE_PANEL_WIDTH  / 2;
			
			label = new JLabel("핸드폰 번호: "); label.setFont(font);
			top = new JTextField(3); middle = new JTextField(4); bottom = new JTextField(4);	// 각각 top = 010, middle = 1234, bottom=5678
			top.setFont(font); middle.setFont(font); bottom.setFont(font);
			
	        top.setDocument(new JTextFieldLimit(3)); middle.setDocument(new JTextFieldLimit(4)); bottom.setDocument(new JTextFieldLimit(4));	// 번호칸 글자제한
	        hyphen1 = new JLabel("-"); hyphen2 = new JLabel("-");	// 하이푼
	      
			NUMBER_PANEL.addItem(label, top, hyphen1, middle, hyphen2, bottom);
			Component numberadd = NUMBER_PANEL.getPanel();
			numberadd.setSize(610,60);
	        numberadd.setLocation((KioskPage.PAGE_WIDTH - PANEL_WIDTH) / 4, (KioskPage.PAGE_HEIGHT * 1 / 3 + 55));
	        
	        if(show == true)
	        	this.add(numberadd);
	        else {
	        	number[0] = "";
	        	number[1] = "";
	        	number[2] = "";
	        }
	        
	        this.add(numberadd);     
		}
		public static void initPhoneNumber() {
			for(int i=0;i<number.length;i++)
				number[i] = "";
		}
		
	// 키패드 추가
		private void initkeypad() {
			
	        ActionListener add = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	
	            	if(bottom.getText().length() == 4) {
	            		return;
	            	}
	            	else if(middle.getText().length() == 4) {
	            		bottom.setText(bottom.getText()+e.getActionCommand());
	            	}
	            	else if(top.getText().length() == 3) {
	            		middle.setText(middle.getText()+e.getActionCommand());
	            	}
	            	else
	            		top.setText(top.getText()+e.getActionCommand());
	            }
	        };
	        ActionListener back = new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	if(bottom.getText().length() > 0) {
		        		String bottom_temp = bottom.getText();
		        		bottom_temp = bottom_temp.substring(0,bottom_temp.length()-1);
		        		bottom.setText(bottom_temp);
		        	}
		        	else if(middle.getText().length() > 0) {
		        		String middle_temp = middle.getText();
		        		middle_temp = middle_temp.substring(0,middle_temp.length()-1);
		        		middle.setText(middle_temp);
		        	}
		        	else if(top.getText().length() > 0) {
		        	String top_temp = top.getText();
		            top_temp = top_temp.substring(0,top_temp.length()-1);
		            top.setText(top_temp);
		        	}
		        	else
		        		return;
		        }
	        };
	        ActionListener clear = new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            top.setText("");
		            middle.setText("");
		            bottom.setText("");
		        }
	        };
	        Font Buttonfont = new Font("arian", Font.BOLD, 40);
	        ConfirmButton bt1 = new ConfirmButton("1"); bt1.addActionListener(add); bt1.setFont(Buttonfont);
	        ConfirmButton bt2 = new ConfirmButton("2"); bt2.addActionListener(add); bt2.setFont(Buttonfont);
	        ConfirmButton bt3 = new ConfirmButton("3"); bt3.addActionListener(add); bt3.setFont(Buttonfont);
	        ConfirmButton bt4 = new ConfirmButton("4"); bt4.addActionListener(add); bt4.setFont(Buttonfont);
	        ConfirmButton bt5 = new ConfirmButton("5"); bt5.addActionListener(add); bt5.setFont(Buttonfont);
	        ConfirmButton bt6 = new ConfirmButton("6"); bt6.addActionListener(add); bt6.setFont(Buttonfont);
	        ConfirmButton bt7 = new ConfirmButton("7"); bt7.addActionListener(add); bt7.setFont(Buttonfont);
	        ConfirmButton bt8 = new ConfirmButton("8"); bt8.addActionListener(add); bt8.setFont(Buttonfont);
	        ConfirmButton bt9 = new ConfirmButton("9"); bt9.addActionListener(add); bt9.setFont(Buttonfont);
	        ConfirmButton btnull = new ConfirmButton("clear"); btnull.addActionListener(clear);  btnull.setFont(Buttonfont);
	        ConfirmButton bt0 = new ConfirmButton("0"); bt0.addActionListener(add);  bt0.setFont(Buttonfont);
	        ConfirmButton btback = new ConfirmButton("back"); btback.addActionListener(back);  btback.setFont(Buttonfont);
			
			
			KEYPAD_PANEL.addItem(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btnull, bt0, btback);
			Component keypad = KEYPAD_PANEL.getPanel();
			keypad.setSize(PAGE_WIDTH, PAGE_HEIGHT * 1/3);
			keypad.setLocation(0, PAGE_HEIGHT * 1/2);
			
			this.add(keypad);
		}
		
		private void initYesNoSelectPanel() {
			NO_BUTTON.setFont(font);
			YES_BUTTON.setFont(font);
			YES_NO_SELECT_PANEL.addItem(NO_BUTTON, YES_BUTTON);
			
			final int PANEL_WIDTH = MIDDLE_PANEL_WIDTH  / 2;
			Component yesNoSelectComp = YES_NO_SELECT_PANEL.getPanel();
			yesNoSelectComp.setSize(PANEL_WIDTH, MIDDLE_PANEL_HEIGHT / 5);
			yesNoSelectComp.setLocation((KioskPage.PAGE_WIDTH - PANEL_WIDTH) / 2, KioskPage.PAGE_HEIGHT * 8 / 9);
			
			this.add(yesNoSelectComp);
		}
		
		// set 설정하는 느낌
		private void setListener() {
			this.NO_BUTTON.addActionListener((args)-> {
				MainFrame.attachPanel(new ConfirmPage(false));
				}); // 한줄짜리는 { } 지울수 있음.
			
			this.YES_BUTTON.addActionListener((args) -> {
				if(top.getText().equals("010") == false)
					return;
				else if(top.getText().length() == 3 && middle.getText().length() == 4 && bottom.getText().length() == 4) {
					number[0] = top.getText().replace("010","8210");// 핸드폰번호 담아놓음
			        number[1] = middle.getText();
			        number[2] = bottom.getText();
			        MainFrame.attachPanel(new ThankPage());
				}
				else
					return;
			});
		}
		
		public static String getnumber() {
			if(number[0].equals("") || number[1].equals("") || number[2].equals(""))
				return "0";
			else
				return (number[0] + number[1] + number[2]);
		}
	
}
