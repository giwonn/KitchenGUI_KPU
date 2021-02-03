package kiosk.page.confirm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import kiosk.page.eatplace.EatPlace;
import kiosk.page.order.OrderPlace;

public class ChoosePlace extends JPanel {
	
	LineBorder lineborder = new LineBorder(Color.BLACK, 1);
	private static EatPlace eatplace;
	JPanel panel;
	BorderLayout border = new BorderLayout();
	JLabel label = new JLabel("포장선택");
	JRadioButton takeout = new JRadioButton("예");
	JRadioButton takein = new JRadioButton("아니오");
	ButtonGroup take_group = new ButtonGroup();
	
	BorderLayout placeborder = new BorderLayout(0, 40);
	JPanel PlacePanel = new JPanel(placeborder);
	Font font = new Font("arian", Font.BOLD, 50);
	
	ChoosePlace() {
		panel = new JPanel(border);
		PlacePanel();
		
		this.add(panel);
		this.setBorder(lineborder);
	//	panel.setBorder(lineborder);
	}
	
	private void PlacePanel() {
		takeout.setFont(font); takeout.setBackground(Color.WHITE);
		takeout.addItemListener(new MyItemListener());
		takein.addItemListener(new MyItemListener());
		takein.setFont(font);  takein.setBackground(Color.WHITE);
		// 버튼을 하나의 그룹으로 묶어줌
		take_group.add(takeout); take_group.add(takein);
		PlacePanel.setBackground(Color.WHITE);
		PlacePanel.add(takeout, placeborder.CENTER); PlacePanel.add(takein, placeborder.SOUTH);
		panel.add(PlacePanel, border.SOUTH);
		
	}
	
	class MyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.DESELECTED) {
				return;
			}
			if(takeout.isSelected()) {
				OrderPlace.getInstance().setEatPlace(EatPlace.포장);	// 주문장소 선택페이지로 넘길 글자
				setPlace(EatPlace.포장);
			}
			if(takein.isSelected()) {
				OrderPlace.getInstance().setEatPlace(EatPlace.매장식사);
				setPlace(EatPlace.매장식사);
			}
		}
	}
	
	public void setPlace(EatPlace eatplace) {		// eatplace 저장
		this.eatplace = eatplace;
	}
	public static EatPlace getPlace() {			// eatplace 출력
		return eatplace;
	}
	public static int sendserver() {
		if(getPlace() == EatPlace.매장식사)
			return 0;
		else if(getPlace() == EatPlace.포장)
			return 1;
		else
			return -1;
	}
}
