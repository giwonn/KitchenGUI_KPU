package kiosk.page.welcome;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kiosk.Client;
import kiosk.JTextFieldLimit;
import kiosk.MainFrame;
import kiosk.page.ImageTextPanel;
import kiosk.page.KioskGuidePanel;
import kiosk.page.KioskGuidePanel2;
import kiosk.page.KioskPage;
import kiosk.page.eatplace.EatPlacePage;

/**
 * Class Role : 인사 페이지
 */
public class WelcomePage extends KioskPage {

	private final KioskGuidePanel WATING_PANEL = new KioskGuidePanel(2, 0);
	
	private final ImageTextPanel WELCOME_IMG_TEXT_PANEL = new ImageTextPanel(new ImageIcon("image/bg_info3.jpg"),
			"주문하시려면 화면을 터치하세요");
	
	private final KioskGuidePanel2 NUMBER_PANEL = new KioskGuidePanel2();
	private final KioskGuidePanel2 NUMBER_PANEL2 = new KioskGuidePanel2();
	
	private final int MIDDLE_PANEL_WIDTH = KioskPage.PAGE_WIDTH * 4 / 5;
	private final int MIDDLE_PANEL_HEIGHT = KioskPage.PAGE_HEIGHT * 2 / 5;
	
	Font font = new Font("arian", Font.BOLD, 35);
	JLabel label, label2;
	JTextField Text, Text2;
	
	public WelcomePage() {
		initWelcomePage();
		initWaitingPanel();
		initWelcomeImgTextPanel();
	//	initwaitnumber();
		setListener();
	}

	private void initWelcomePage() {
		this.showBackgroundImg(false);
		this.showBackButton(false);
	}
	
	private void initWaitingPanel() {
		/************대기인원***************************/
		label = new JLabel("대기 인원: "); label.setFont(font);
		Text = new JTextField();
		Text.setText("1234");
		Text.setFont(font);
      
		NUMBER_PANEL.addItem(label, Text);
		Component numberadd = NUMBER_PANEL.getPanel();
		numberadd.setSize(610,200);
       // numberadd.setLocation((KioskPage.PAGE_WIDTH - PANEL_WIDTH) / 4, (KioskPage.PAGE_HEIGHT * 1 / 3 + 55));
        //this.add(numberadd);
        /***********대기시간****************************/
        label2 = new JLabel("대기 시간: "); label2.setFont(font);
		Text2 = new JTextField();
		Text2.setText("5678");
		Text2.setFont(font);
      
		NUMBER_PANEL2.addItem(label2, Text2);
		Component numberadd2 = NUMBER_PANEL2.getPanel();
		numberadd2.setSize(610,200);
        //numberadd2.setLocation((KioskPage.PAGE_WIDTH - PANEL_WIDTH) / 4, (KioskPage.PAGE_HEIGHT * 1 / 3 + 55));
        // this.add(numberadd);
		WATING_PANEL.addItem(numberadd, numberadd2); 
		
		Component WaitingPanel = WATING_PANEL.getPanel();
		WaitingPanel.setSize(KioskPage.PAGE_WIDTH * 2/3 , KioskPage.PAGE_HEIGHT * 1/5);
		WaitingPanel.setLocation(200, 400);
		this.add(WaitingPanel);
	}
	
	private void initWelcomeImgTextPanel() {
		Component welcomeImgTextComp = WELCOME_IMG_TEXT_PANEL.getPanel();
		welcomeImgTextComp.setSize(KioskPage.PAGE_WIDTH, KioskPage.PAGE_HEIGHT);
		welcomeImgTextComp.setLocation(0, 0);
		
		this.add(welcomeImgTextComp);
	}

	private void setListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.attachPanel(new EatPlacePage());	// 화면 클릭시에 장소선택으로 화면 전환
			}
		});
	}
}