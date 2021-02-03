package kiosk.page.confirm;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class PhoneButton extends JButton {

	public PhoneButton(String text) {
		super(text);
		this.setBackground(Color.WHITE);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PhoneButton.this.setBackground(Color.ORANGE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				PhoneButton.this.setBackground(Color.WHITE);
			}
		});
	}
}