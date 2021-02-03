package kiosk.page.confirm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kiosk.JTextFieldLimit;
import kiosk.page.KioskGuidePanel;
import kiosk.page.KioskGuidePanel2;
import kiosk.page.KioskPage;

public class MileagePanel extends JPanel {
	private final int MIDDLE_PANEL_WIDTH = KioskPage.PAGE_WIDTH * 4 / 5;
	private final int MIDDLE_PANEL_HEIGHT = KioskPage.PAGE_HEIGHT * 2 / 5;
	BorderLayout border = new BorderLayout(50, 50);
	JPanel panel = new JPanel(border);
	JLabel mileage = new JLabel("마일리지 적립 하시겠습니까?");
	ConfirmButton confirmbutton = new ConfirmButton("확인");
	JLabel label, hyphen1, hyphen2;
	JTextField top = new JTextField(3);
	JTextField middle = new JTextField(3);
	JTextField bottom = new JTextField(3);
	Font font = new Font("arian", Font.BOLD, 45);
	private final KioskGuidePanel2 NUMBER_PANEL = new KioskGuidePanel2();
	private final KioskGuidePanel KEYPAD_PANEL = new KioskGuidePanel(0, 3);
	final int PANEL_WIDTH = MIDDLE_PANEL_WIDTH  / 2;
	
	public static String number[] = new String[3];

	Phonenumber numberpage = new Phonenumber(true);
	
	MileagePanel() {
		askpage();
		
		confirmbutton.addActionListener((args)-> {
			
			panel.removeAll();
			numberpage();
			initkeypad();
			
			revalidate();
			repaint();
		});
		
		this.add(panel);
	}
	
	private void askpage() {
		panel.add(mileage, border.NORTH);
		mileage.setFont(new Font("arian", Font.BOLD, 30));
		panel.add(confirmbutton,border.SOUTH);
		confirmbutton.setFont(new Font("arian", Font.BOLD, 30));
		panel.setBackground(Color.WHITE);
		
		
	}
	
	private void numberpage() {
		label = new JLabel("핸드폰 번호: "); label.setFont(font);
		label.setBackground(Color.WHITE);
		top.setFont(font); middle.setFont(font); bottom.setFont(font);
		top.setDocument(new JTextFieldLimit(3)); middle.setDocument(new JTextFieldLimit(4)); bottom.setDocument(new JTextFieldLimit(4));	// 번호칸 글자제한
        top.setBackground(Color.WHITE);
		hyphen1 = new JLabel("-"); hyphen2 = new JLabel("-");	// 하이푼
      
		NUMBER_PANEL.addItem(top, hyphen1, middle, hyphen2, bottom);
		Component numberadd = NUMBER_PANEL.getPanel();
		numberadd.setBackground(Color.WHITE);
		
		panel.setBackground(Color.WHITE);
		panel.add(label, border.NORTH);
		panel.add(numberadd, border.CENTER);
	}
	// 키패드 추가
			private void initkeypad() {
				
		        ActionListener add = new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	if(bottom.getText().length() == 4)
		            		return;
		            	else if(middle.getText().length() == 4)
		            		bottom.setText(bottom.getText()+e.getActionCommand());
		            	else if(top.getText().length() == 3)
		            		middle.setText(middle.getText()+e.getActionCommand());
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
		        Font Buttonfont = new Font("arian", Font.BOLD, 45);
		        PhoneButton bt1 = new PhoneButton("1"); bt1.addActionListener(add); bt1.setFont(Buttonfont);
		        PhoneButton bt2 = new PhoneButton("2"); bt2.addActionListener(add); bt2.setFont(Buttonfont); 
		        PhoneButton bt3 = new PhoneButton("3"); bt3.addActionListener(add); bt3.setFont(Buttonfont); 
		        PhoneButton bt4 = new PhoneButton("4"); bt4.addActionListener(add); bt4.setFont(Buttonfont); 
		        PhoneButton bt5 = new PhoneButton("5"); bt5.addActionListener(add); bt5.setFont(Buttonfont);
		        PhoneButton bt6 = new PhoneButton("6"); bt6.addActionListener(add); bt6.setFont(Buttonfont);
		        PhoneButton bt7 = new PhoneButton("7"); bt7.addActionListener(add); bt7.setFont(Buttonfont);
		        PhoneButton bt8 = new PhoneButton("8"); bt8.addActionListener(add); bt8.setFont(Buttonfont);
		        PhoneButton bt9 = new PhoneButton("9"); bt9.addActionListener(add); bt9.setFont(Buttonfont);
		        PhoneButton btnull = new PhoneButton("clear"); btnull.addActionListener(clear);  btnull.setFont(Buttonfont);
		        PhoneButton bt0 = new PhoneButton("0"); bt0.addActionListener(add);  bt0.setFont(Buttonfont);
		        PhoneButton btback = new PhoneButton("back"); btback.addActionListener(back);  btback.setFont(Buttonfont);
				
				
				KEYPAD_PANEL.addItem(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btnull, bt0, btback);
				Component keypad = KEYPAD_PANEL.getPanel();
				keypad.setSize(MIDDLE_PANEL_WIDTH , MIDDLE_PANEL_HEIGHT *1/3);

				panel.add(keypad, border.SOUTH);
			}
			public static String getnumber() {
				if(number[0].equals("") || number[1].equals("") || number[2].equals(""))
					return "0";
				else
					return (number[0] + number[1] + number[2]);
			}
}
