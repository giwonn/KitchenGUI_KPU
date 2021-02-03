package kiosk.page.welcome;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class OrderButton extends JButton {

	public OrderButton(String text) {
		super(text);
		this.setBackground(Color.ORANGE);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				OrderButton.this.setBackground(Color.ORANGE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				OrderButton.this.setBackground(Color.ORANGE);
			}
		});
	}
}