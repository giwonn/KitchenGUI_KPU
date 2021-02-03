package kiosk.page.order;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kiosk.MainFrame;
import kiosk.page.KioskPage;
import kiosk.page.confirm.ConfirmPage;
import kiosk.page.eatplace.EatPlacePage;

/** 
 * Class Role : 음식 주문 화면에서 선택한 메뉴들을 보여주는 페이지다.
 */
public class CartPanel extends JPanel {

	private final JPanel TOP_PANEL = new JPanel(); // (검은색 바) 출력
	Font font = new Font("arian", Font.BOLD, 25);
	private Border border = BorderFactory.createTitledBorder("선택메뉴");
	public static JScrollPane scroll = new JScrollPane();	// 선택메뉴목록 박스창 출력
	public static final JList<String> J_LIST = new JList<>();
	public static final Vector<String> SELECTED_MENU = new Vector<>();
	public static DefaultTableModel CartMenu = new DefaultTableModel(new String[] {"메뉴", "수량" ,"금액"}, 0);
	public static JTable CartTable = new JTable(CartMenu);
	
	DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	
	
//	public static JLabel cartTotalData = new JLabel("<html>" + SelectedMenu.orderAmount + "<br>"
//			+ SelectedMenu.orderPrice + "<br>" + SelectedMenu.totalKCal + "</html>", JLabel.CENTER);
	public static JLabel cartTotalData = new JLabel("<html>" + SelectedMenu.orderAmount + " EA" + "<br>" + SelectedMenu.orderPrice + " 원" + "<br>"
				+ SelectedMenu.totalKCal + " Kcal" + "</html>", JLabel.CENTER);
	
	private final JPanel BOTTOM_PANEL = new JPanel();	// 총 주문정보, 주문취소, 주문진행 버튼 출력

	public CartPanel() {
		// 초기화
		this.setLayout(null);
		TOP_PANEL.setLayout(new GridLayout(0, 2));	// 메뉴, 가격만 표기하기 때문에 (0,2)로 설정
		BOTTOM_PANEL.setLayout(new GridLayout(0, 4));
		CartTable.setFont(font);
		CartTable.getTableHeader().setFont(font);

		// 사이즈
		TOP_PANEL.setSize(KioskPage.PAGE_WIDTH, OrderPage.BOTTOM_HEIGHT / 8);
		BOTTOM_PANEL.setSize(KioskPage.PAGE_WIDTH, OrderPage.BOTTOM_HEIGHT / 4);
		J_LIST.setSize(KioskPage.PAGE_WIDTH, OrderPage.BOTTOM_HEIGHT / 2 + OrderPage.BOTTOM_HEIGHT / 4 / 2);
		scroll.setSize(KioskPage.PAGE_WIDTH - 12, OrderPage.BOTTOM_HEIGHT / 2 + OrderPage.BOTTOM_HEIGHT / 4 / 2);
		CartTable.setSize(KioskPage.PAGE_WIDTH, OrderPage.BOTTOM_HEIGHT / 2 + OrderPage.BOTTOM_HEIGHT / 3 / 2);
		
		// 위치
		TOP_PANEL.setLocation(0, 0);
		BOTTOM_PANEL.setLocation(0, OrderPage.BOTTOM_HEIGHT * 3 / 4);
		J_LIST.setLocation(0,OrderPage.BOTTOM_HEIGHT / 8);
		scroll.setLocation(0, OrderPage.BOTTOM_HEIGHT / 8 - 50);
		CartTable.setLocation(0, OrderPage.BOTTOM_HEIGHT / 30 );
		
		// 컬러
		TOP_PANEL.setOpaque(true);
		TOP_PANEL.setBackground(Color.BLACK);

		// 하위컴포넌트
		JLabel menuLabel = new JLabel("메뉴", JLabel.CENTER);
//		JLabel amountLabel = new JLabel("수량", JLabel.CENTER);
		JLabel priceLabel = new JLabel("가격", JLabel.CENTER);
		JLabel cartTotal = new JLabel("<html>주문수량<br>주문금액<br>총 칼로리</html>", JLabel.CENTER);
		cartTotal.setFont(new Font("arian", Font.BOLD, 25));
		JButton cancleButton = new JButton("주문취소"); cancleButton.setFont(new Font("arian", Font.BOLD, 30));
		JButton paymentButton = new JButton("주문진행"); paymentButton.setFont(new Font("arian", Font.BOLD, 30));
		cartTotalData.setFont(new Font("arian", Font.BOLD, 25));
		// 하위컴포넌트 컬러
		menuLabel.setForeground(Color.WHITE);
//		amountLabel.setForeground(Color.WHITE);
		priceLabel.setForeground(Color.WHITE);
		cancleButton.setBackground(Color.GRAY);
		paymentButton.setBackground(Color.ORANGE);

		// 패널 추가
		TOP_PANEL.add(menuLabel);
//		TOP_PANEL.add(amountLabel);
		TOP_PANEL.add(priceLabel);

		BOTTOM_PANEL.add(cartTotal);
		BOTTOM_PANEL.add(cartTotalData);
		BOTTOM_PANEL.add(cancleButton);
		BOTTOM_PANEL.add(paymentButton);

		BOTTOM_PANEL.setOpaque(true);
		BOTTOM_PANEL.setBackground(Color.WHITE);
		
		CartTable.getColumnModel().getColumn(0).setPreferredWidth(MainFrame.FRAME_WIDTH / 3);
		CartTable.setRowHeight(30);
		
		// 기타 설정
		//		scroll.setViewportView(J_LIST);
				scroll.setViewportView(CartTable);
				scroll.setBorder(border); // 경계 설정
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로바정책
		//		J_LIST.setListData(SELECTED_MENU);
		
//		this.add(TOP_PANEL);
		this.add(BOTTOM_PANEL);
		this.add(scroll);
//		this.add(CartTable);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = CartPanel.CartTable.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(center);

		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectedMenu.init();
				initCart();
			}
		});

		paymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MenuButton.info.isEmpty() == true)
					return;

				MainFrame.attachPanel(new ConfirmPage(false));
			}
		});		
	}

	public static void initCart() {
		SELECTED_MENU.clear();
		J_LIST.setListData(SELECTED_MENU);
		cartTotalData.setText("<html>" + SelectedMenu.orderAmount + " EA" + "<br>" + SelectedMenu.orderPrice + " 원" + "<br>"
				+ SelectedMenu.totalKCal + " Kcal" + "</html>");
		MenuButton.info.clear();
		CartMenu.setNumRows(0);
	}
	
}