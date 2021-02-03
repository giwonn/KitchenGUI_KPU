package kiosk.page.confirm;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ConfirmButton extends JButton {

	public ConfirmButton(String text) {
		super(text);
		this.setBackground(Color.WHITE);
		
		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				ConfirmButton.this.setBackground(Color.ORANGE);
//			}

//			@Override
//			public void mouseExited(MouseEvent e) {
//				ConfirmButton.this.setBackground(Color.WHITE);
//			}
		});
	}
	public void setBackGround(Color color) {
		this.setBackground(color);
	}
}