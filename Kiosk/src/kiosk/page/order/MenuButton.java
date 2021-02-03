package kiosk.page.order;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import kiosk.Client;
import kiosk.page.ImageEdit;
import kiosk.page.confirm.OrderData;

/**
 * Class Role : 메뉴들은 각각의 버튼으로 구성되어있다. 
 * 해당 메뉴버튼을 담당하는 클래스이다.
 */
public class MenuButton extends JButton {

	public static ArrayList<OrderData> info = new ArrayList<>();
	
	
	/* 버튼의 크기 */
	private static int BUTTON_WIDTH = MenuTablePanel.TABLE_WIDTH / 5;
	private static int BUTTON_HEIGHT = MenuTablePanel.TABLE_HEIGHT / 9;
	
	public MenuButton(int binary, String menuName, String imgPath, int price, int kCal) {
		
		this.setText("<html><center>" + menuName + "<br>" + "가격 ￦ " + "<font color='red'>" + price + "</font><br>" + kCal + "Kcal</center></html>");
		this.setIcon(ImageEdit.getResizeIcon(imgPath, BUTTON_WIDTH, BUTTON_HEIGHT));
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		this.setBackground(Color.WHITE);
		this.setBorderPainted(false);

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					if(Integer.parseInt(String.valueOf(binary), 2) < 25) {
						CoffeeOptionPanel all_option = new CoffeeOptionPanel(2,binary,menuName,price,kCal);
					} else if(Integer.parseInt(String.valueOf(binary), 2) < 41) {
						CoffeeOptionPanel size_only = new CoffeeOptionPanel(1, binary,menuName,price,kCal);
					} else {
						CoffeeOptionPanel nothing = new CoffeeOptionPanel(0, binary,menuName,price,kCal);
						nothing.choose_size.setText("0");
						nothing.choose_temp.setText("0");
						nothing.addMenu();
					}
					
			}
		});
	}
}