package kiosk.page.confirm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kiosk.page.eatplace.EatPlace;
import kiosk.page.order.CartPanel;
import kiosk.page.order.MenuButton;
import kiosk.page.order.SelectedMenu;

public class OrderTotalDataPanel extends JPanel {
	
	private final EatPlace eatPlace;
	public static ArrayList<OrderData> orderDataArray;
	
	String header[] = { "메뉴", "수량", "가격" };
	
	OrderTotalDataPanel(EatPlace eatPlace, ArrayList<OrderData> orderDataArray) {
		this.eatPlace = eatPlace;
		
		this.orderDataArray = orderDataArray;
		
		
		initPanel();
	}
	
	// 패널 초기화
	private void initPanel() {
		this.setLayout(new BorderLayout());
//		this.add(new JScrollPane(new JList<Object>(orderDataArray.toArray())), BorderLayout.CENTER);
		this.add(createTotalListPanel(), BorderLayout.SOUTH);
		this.add(CartPanel.CartTable);
	}
	
	// factory 공장, 만들어 낸다.
	private JPanel createTotalListPanel() {
		Font font = new Font("arian", Font.BOLD, 30);
		JPanel totalListPanel = new JPanel();
		totalListPanel.setLayout(new GridLayout(3, 2));
		JLabel numLabel = new JLabel("주문수량 : ", JLabel.CENTER);
		JLabel priceLabel = new JLabel("주문금액 : ", JLabel.CENTER);
		
		JLabel kcalLabel = new JLabel("총 칼로리 : ", JLabel.CENTER);
		JLabel num = new JLabel( SelectedMenu.orderAmount + " EA", JLabel.CENTER);
		JLabel price = new JLabel(SelectedMenu.orderPrice + " 원", JLabel.CENTER);
		JLabel kcal = new JLabel(SelectedMenu.totalKCal + " Kcal", JLabel.CENTER);
		numLabel.setFont(font);		num.setFont(font);
		priceLabel.setFont(font);	price.setFont(font);
		kcalLabel.setFont(font);	kcal.setFont(font);
		totalListPanel.add(numLabel);	totalListPanel.add(num);
		totalListPanel.add(priceLabel);	totalListPanel.add(price);
		totalListPanel.add(kcalLabel);	totalListPanel.add(kcal);
		
		return totalListPanel;
	}
	public static int menunum() {
		return orderDataArray.size();
	}

}