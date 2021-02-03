package kiosk.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class Role : 프로그램에서 공통적으로 사용되고 있는 가이드 패널이다.(핸드폰번호를위한클래스)
 * 가이드패널은 BorderLayout형식을 가지고 있으며
 * 상단 타이틀(TitleLabel)바, 중간 그리드레이아웃 형식의 아이템 패널로 구성되어 있다.
 */
public class KioskGuidePanel2 extends JComponent {
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	
	private final JPanel GUIDE_PANEL = new JPanel();
	private final JPanel ITEM_PANEL = new JPanel();
	
	public KioskGuidePanel2() {
		initGuidePanel(null);
		initItemPanel2(0, 0);
	}

	private void initGuidePanel(String title){
		GUIDE_PANEL.setLayout(new BorderLayout());
		GUIDE_PANEL.setBackground(BACKGROUND_COLOR);
		if (title != null)
			GUIDE_PANEL.add(new TitleLabel(title, JLabel.CENTER), BorderLayout.NORTH);
		
		GUIDE_PANEL.add(ITEM_PANEL, BorderLayout.CENTER);
	}
	private void initItemPanel2(final int row, final int col){
		ITEM_PANEL.setLayout(new FlowLayout());
	}
	
	public void addItem(Component... comps){
		for(Component comp : comps) ITEM_PANEL.add(comp);
	}
	
	public void setTitleColor(Color color){
		if(GUIDE_PANEL.getComponentCount() == 2)
			GUIDE_PANEL.getComponent(0).setForeground(color);
	}
	
	public JComponent getPanel(){
		return GUIDE_PANEL;
	}
}