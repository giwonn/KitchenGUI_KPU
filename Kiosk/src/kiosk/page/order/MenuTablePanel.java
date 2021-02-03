package kiosk.page.order;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kiosk.page.KioskPage;

/**
 * Class Role : 한 면에 보이는 메뉴들을 붙일수있는 테이블이다.
 */
public class MenuTablePanel extends JPanel {
	
	private final ArrayList<MenuButton> MENU_BUTTON_LIST = new ArrayList<>();

	public static final int TABLE_WIDTH = KioskPage.PAGE_WIDTH;
	public static final int TABLE_HEIGHT = KioskPage.PAGE_HEIGHT;

	public MenuTablePanel(int menuRows, int menuCols) {
		// 초기화
		GridLayout gridlayout = new GridLayout(menuRows, menuCols);
		this.setLayout(gridlayout);
		
		// 사이즈 & 컬러 
		this.setSize(TABLE_WIDTH-150, TABLE_HEIGHT);
		this.setBackground(Color.WHITE);
		
	}
	
	// 메뉴등록
	private void registerMenu() {
		for (MenuButton menuBtn : MENU_BUTTON_LIST) {
			this.add(menuBtn);
		}
	}

	// 메뉴 추가
	public void addMenu(MenuButton... menuButtons) {
		if (menuButtons.length != 0) {
			for (MenuButton menuBtn : menuButtons) {
				MENU_BUTTON_LIST.add(menuBtn);
				this.add(menuBtn);
			}
		}
	}
}