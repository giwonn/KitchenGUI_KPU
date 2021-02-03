package kiosk.page.confirm;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import kiosk.page.ImageEdit;
import kiosk.page.order.CartPanel;
import kiosk.page.order.MenuButton;
import kiosk.page.order.MenuTablePanel;
import kiosk.page.order.SelectedMenu;

public class OrderList extends JLabel {
	
	Container orderlist;
	private static int PANEL_WIDTH = MenuTablePanel.TABLE_WIDTH / 2;
	private static int PANEL_HEIGHT = MenuTablePanel.TABLE_HEIGHT / 9;

	private final String MENU_NAME;
	private final String IMG_PATH;
	private final int PRICE;
	private final int K_CAL;
	
		OrderList(String menuName, String imgPath, int price, int kCal) {
			this.MENU_NAME = menuName;
			this.IMG_PATH = imgPath;
			this.PRICE = price;
			this.K_CAL = kCal;

			
			this.setText("<html><center>" + menuName + "<br>" + "°¡°Ý £Ü " + "<font color='red'>" + price + "</font><br>" + "</center></html>");
			this.setIcon(ImageEdit.getResizeIcon(imgPath, PANEL_WIDTH, PANEL_HEIGHT));
			this.setHorizontalTextPosition(SwingConstants.RIGHT);
			this.setVerticalTextPosition(SwingConstants.CENTER);
			
			this.setBackground(Color.WHITE);
	}
	
		void addMenu(OrderList... orderlist) {
			if (orderlist.length != 0) {
				for (OrderList order : orderlist) {
	//				MENU_BUTTON_LIST.add(order);
					this.add(order);
				}
			}
		}
}
