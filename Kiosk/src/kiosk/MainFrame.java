package kiosk;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;

import javax.swing.JFrame;

import kiosk.page.KioskPage;
import kiosk.page.welcome.WelcomePage;
import kiosk.page.welcome.WelcomePage2;

/**
 * Class Role : 프로그램에서 유일하게 사용되는 프레임이다.
 * 
 * 기능-
 * 1. 입력된 Page를 프레임에 붙인다.(갱신하다.)
 * 2. 화면을 보여준다.
 */
public final class MainFrame extends JFrame {
	static BufferedWriter bw=null;
	/* 프레임 사이즈 */
//	public static final int FRAME_WIDTH = Display.SCREEN_WIDTH / 2;
	public static final int FRAME_WIDTH = Display.SCREEN_WIDTH + 10;
	
//	public static final int FRAME_HEIGHT = Display.WINDOWS_HEIGHT;
	public static final int FRAME_HEIGHT = Display.SCREEN_HEIGHT + 40;
	/* 싱글톤 */
	private static final MainFrame UNIQUE_INSTANCE = new MainFrame(bw);
	private static final String TITLE = "ORDER HERE!";

	private MainFrame(BufferedWriter bw) {
		super(TITLE);
		initMainFrame();
		MainFrame.bw = bw;
	}

	// 프레임 환경설정
	private void initMainFrame() {
		this.setLayout(null);
		this.setTitle(TITLE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		this.setLocation((Display.SCREEN_WIDTH - FRAME_WIDTH) / 2, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.add(new WelcomePage());
		this.add(new WelcomePage2());
	}

	// 입력된 KioskPage를 프레임에 붙인다.
	public static void attachPanel(KioskPage page) {
		// 컨테이너 삭제
		UNIQUE_INSTANCE.getContentPane().removeAll();

		// 패널 추가
		UNIQUE_INSTANCE.getContentPane().add(page);

		// 갱신
		UNIQUE_INSTANCE.revalidate();
		UNIQUE_INSTANCE.repaint();
	}

	// 화면을 보여준다.
	static void showScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		UNIQUE_INSTANCE.setUndecorated(true);
	//	gd.setFullScreenWindow(UNIQUE_INSTANCE);
		
		UNIQUE_INSTANCE.setVisible(true);
	}
}