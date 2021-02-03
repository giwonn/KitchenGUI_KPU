package kiosk.page.order;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import kiosk.page.KioskPage;
import kiosk.page.confirm.ConfirmButton;
import kiosk.page.confirm.OrderData;

public class CoffeeOptionPanel extends JDialog{

	JLabel TempLabel = new JLabel("Ice/Hot : ");
	JLabel SizeLabel = new JLabel("Size : ");
	
	private String size="asd";
	private String temp="weq";
	
	String TEMP_ICE = "Ice";
	String TEMP_HOT = "Hot";
	String SIZE_REGULAR = "Regular";
	String SIZE_LARGE = "Large";
	JLabel choose_temp = new JLabel("");
	JLabel choose_size = new JLabel("");
	
	ConfirmButton Ice = new ConfirmButton("Ice");
	ConfirmButton Hot = new ConfirmButton("Hot");
	ConfirmButton Regular = new ConfirmButton("Regular");
	ConfirmButton Large = new ConfirmButton("Large");
	ConfirmButton OK = new ConfirmButton("확인");
	ConfirmButton Cancel = new ConfirmButton("취소");
	
	String[] MenuArray; 
	int binary;
	String menuName;
	int price;
	int kCal;
	int menunum;
	int i;
	CoffeeOptionPanel(int i,int binary, String menuName, int price, int kCal) {
		display(i);
		setListener();
		this.binary = binary;
		this.menuName = menuName;
		this.price = price;
		this.kCal = kCal;
	}

	public void display(int i) {
		setLayout(null);
		
		int Button_width = KioskPage.PAGE_HEIGHT * 1/7;
		int Button_height = KioskPage.PAGE_HEIGHT * 1/10;
		int ice_xpos = KioskPage.PAGE_WIDTH * 2/5;
		int ice_ypos = KioskPage.PAGE_HEIGHT * 1/30;
		int blank = KioskPage.PAGE_WIDTH * 1/30;
		int Frame_xpos = 0;
		int Frame_ypos = KioskPage.PAGE_HEIGHT * 1/3;
		int Frame_width = KioskPage.PAGE_WIDTH;
		int Frame_height = KioskPage.PAGE_HEIGHT * 3/7;
		Font font = new Font("arian", Font.BOLD, 35);
		Font Button_font = new Font("arian", Font.BOLD, 25);
		if(i == 2) {
			TempLabel.setFont(font);
			TempLabel.setLocation(blank,0);
			TempLabel.setSize(Button_width, Button_height);
			this.add(TempLabel);
			choose_temp.setFont(font);
			choose_temp.setLocation(Button_width, 0);
			choose_temp.setSize(Button_width, Button_height);
			this.add(choose_temp);
			// Ice 버튼
			Ice.setFont(Button_font);
			Ice.setLocation(ice_xpos,  ice_ypos);
			Ice.setSize(Button_width, Button_height);
			this.add(Ice);
		
			// Hot 버튼
			Hot.setFont(Button_font);
			Hot.setLocation(Button_width + ice_xpos + blank, ice_ypos);
			Hot.setSize(Button_width, Button_height);
			this.add(Hot);
			
			SizeLabel.setFont(font);
			SizeLabel.setLocation(blank, Button_height);
			SizeLabel.setSize(Button_width, Button_height);
			this.add(SizeLabel);
			choose_size.setFont(font);
			choose_size.setLocation(Button_width, Button_height);
			choose_size.setSize(Button_width, Button_height);
			this.add(choose_size);
			// Small 버튼
			Regular.setFont(Button_font);
			Regular.setLocation(ice_xpos, ice_ypos + Button_height + blank);
			Regular.setSize(Button_width, Button_height);
			this.add(Regular);	
			// Large 버튼
			Large.setFont(Button_font);
			Large.setLocation(Button_width + ice_xpos + blank, ice_ypos + Button_height + blank);
			Large.setSize(Button_width, Button_height);
			this.add(Large);
		}
		if(i == 1) {
			SizeLabel.setFont(font);
			SizeLabel.setLocation(blank, Button_height);
			SizeLabel.setSize(Button_width, Button_height);
			this.add(SizeLabel);
			choose_size.setFont(font);
			choose_size.setLocation(Button_width + blank, Button_height);
			choose_size.setSize(Button_width, Button_height);
			this.add(choose_size);
			// Small 버튼
			Regular.setFont(Button_font);
			Regular.setLocation(ice_xpos, ice_ypos + Button_height + blank);
			Regular.setSize(Button_width, Button_height);
			this.add(Regular);	
			// Large 버튼
			Large.setFont(Button_font);
			Large.setLocation(Button_width + ice_xpos + blank, ice_ypos + Button_height + blank);
			Large.setSize(Button_width, Button_height);
			
			choose_temp.setText("0");
			this.add(Large);
		}
		if(i == 0){
			choose_size.setText("0");
			choose_temp.setText("0");
		}
		
		Cancel.setFont(Button_font);
		Cancel.setLocation(Button_width * 4/3 + ice_xpos - Button_width * 2/3, ice_ypos * 2 + Button_height * 2 + blank);
		Cancel.setSize(Button_width * 2/3, Button_height * 2/3);
		this.add(Cancel);
		OK.setFont(Button_font);
		OK.setLocation(Button_width * 4/3 + ice_xpos + blank, ice_ypos * 2 + Button_height * 2 + blank);
		OK.setSize(Button_width * 2/3, Button_height * 2/3);
		this.add(OK);

		this.setLocation(Frame_xpos, Frame_ypos);
		this.setSize(Frame_width, Frame_height);
		
		setUndecorated(true);
		
		if(i == 2 || i == 1)
			setVisible(true);
		else {
			setVisible(false);
		}
	}
	
	private void setListener() {
		this.Ice.addActionListener((args)-> {
			choose_temp.setText(TEMP_ICE);
		});
		this.Hot.addActionListener((args)-> {
			choose_temp.setText(TEMP_HOT);
		});
		this.Regular.addActionListener((args)-> {
			choose_size.setText(SIZE_REGULAR);
		});
		this.Large.addActionListener((args)-> {
			choose_size.setText(SIZE_LARGE);
		});
		this.Cancel.addActionListener((args)-> {
			choose_size.setText("");
			choose_temp.setText("");
			dispose();
			return;
		});
		
		this.OK.addActionListener((args)-> {
			addMenu();
			});
	}
	
	public void addMenu() {
		// size, temp 안골랐을때
					if(choose_temp.getText().equals("") || choose_size.getText().equals("")) {OK.setBackground(Color.yellow);}  
					//장바구니 비어있을때
					else if(MenuButton.info.size() == 0 ) {	
						size = choose_size.getText().toString();
						temp = choose_temp.getText().toString();
						i = -1;
						if(choose_size.getText().toString().equals("Large"))
							price += 500;
						MenuButton.info.add(new OrderData(binary, menuName, price, kCal, size, temp));	// info리스트 끝에 데이터 추가
						CartPanel.SELECTED_MENU.add(this.toString());
					    CartPanel.J_LIST.setListData(CartPanel.SELECTED_MENU);
					    SelectedMenu.addOrder(price, 1, kCal);
					    // JScrollPane의 바를 최 하단으로맞춤
					    CartPanel.scroll.getVerticalScrollBar().setValue(CartPanel.scroll.getVerticalScrollBar().getMaximum());	
						CartPanel.cartTotalData.setText("<html>" + SelectedMenu.orderAmount + " EA"+ "<br>" + SelectedMenu.orderPrice
								+ " 원" + "<br>" + SelectedMenu.totalKCal + "Kcal" + "</html>");
						setList();
					    dispose();
					}
					else {
						// 누른 temp, size를 장바구니에 넣기위해 넣어줌
						size = choose_size.getText().toString();
						temp = choose_temp.getText().toString();
						
						for(int i=0; i<MenuButton.info.size(); i++) {
							this.i = i;
							// 누른 메뉴가 이미 장바구니에 있을때
							if(menuName.equals(MenuButton.info.get(i).getMenu()) &&
							   size.equals(MenuButton.info.get(i).getSize()) && 
							   temp.equals(MenuButton.info.get(i).getTemp())) {
								MenuButton.info.get(i).addAmount();
								if(choose_size.getText().toString().equals("Large"))
									price += 500;
								SelectedMenu.addOrder(price, 1, kCal);
								CartPanel.SELECTED_MENU.remove(i);
								CartPanel.SELECTED_MENU.add(i, this.toString());
								CartPanel.J_LIST.setListData(CartPanel.SELECTED_MENU);
								CartPanel.cartTotalData.setText("<html>" + SelectedMenu.orderAmount + " EA"+ "<br>" + SelectedMenu.orderPrice
										+ " 원" + "<br>" + SelectedMenu.totalKCal + "Kcal" + "</html>");
								setList();
								dispose();
								break;
							}
							// 장바구니에 없을때
							else if(i == MenuButton.info.size()-1) {
								this.i = -1;
								MenuButton.info.add(new OrderData(binary, menuName, price, kCal, size, temp));	// info리스트 끝에 데이터 추가
								CartPanel.SELECTED_MENU.add(this.toString());
							    CartPanel.J_LIST.setListData(CartPanel.SELECTED_MENU);
							    if(choose_size.getText().toString().equals("Large"))
									price += 500;
							    SelectedMenu.addOrder(price, 1, kCal);
							    // JScrollPane의 바를 최 하단으로맞춤
							    CartPanel.scroll.getVerticalScrollBar().setValue(CartPanel.scroll.getVerticalScrollBar().getMaximum());		// JScrollPane의 바를 최 하단으로맞춤
							    CartPanel.cartTotalData.setText("<html>" + SelectedMenu.orderAmount + " EA"+ "<br>" + SelectedMenu.orderPrice
										+ " 원" + "<br>" + SelectedMenu.totalKCal + "Kcal" + "</html>");
								setList();
							    dispose();
								break;
							}
						}
					}
	}
	// 장바구니에 메뉴를 추가시켜준다.
	public void setList() {
		
		if(size.equals("0"))
			size = "";
		else if(size.equals("Regular"))
			size = "R";
		else
			size = "L";
		if(temp.equals("0"))
			temp = "";
		else if(temp.equals("Hot"))
			temp = "Hot";
		else
			temp = "Cold";
		
		MenuArray = new String[3];
		if(size.equals("") && temp.equals(""))
			MenuArray[0] = menuName;
		else if(size.equals("") && !(temp.equals("")))
			MenuArray[0] = menuName+"("+size+")";
		else if(!(size.equals("")) && temp.equals(""))
			MenuArray[0] = menuName+"("+temp+")";
		else
			MenuArray[0] = menuName+"("+size+")"+"("+temp+")";
		
		MenuArray[1] = String.valueOf(menunum);
		MenuArray[2] = String.valueOf(price);
		// 장바구니에 아무것도 없을때
		if(i == -1) {
			CartPanel.CartMenu.addRow(MenuArray);
		}
		else {
			for(int i=0; i<CartPanel.CartMenu.getRowCount(); i++) {
				// 고른 메뉴가 장바구니에 있다면 장바구니 목록(개수,가격)을 업데이트 해준다.
				if(CartPanel.CartMenu.getValueAt(i, 0).equals(MenuArray[0]))  {
					CartPanel.CartMenu.setValueAt(MenuArray[1], i, 1);
					CartPanel.CartMenu.setValueAt(MenuArray[2], i, 2);
				}
			}
		}
	}
	@Override
	public String toString() {
		//장바구니가 비어있을때
		menunum = 1;
		if(i == -1) {
			
			if(size.equals("0") && temp.equals("0")) {
				String MenuFormat=String.format("%s", menuName);
				String PriceFormat=String.format("                        1개 " + "  %s원", price);
				String Menu=String.format("%s" + "%s" , MenuFormat, PriceFormat);
				return Menu;
			}
			else if(temp.equals("0")) {
				String MenuFormat=String.format("%s" +"("+"%s"+")", menuName, size);
				String PriceFormat=String.format("                        1개 " + "  %s원", price);
				String Menu=String.format("%s" + "%s" , MenuFormat, PriceFormat);
				return Menu;
			}
			else {
				String MenuFormat=String.format("%s" +"("+"%s"+")"+"("+"%s"+")", menuName, size, temp);
				String PriceFormat=String.format("                        1개 " + "  %s원", price);
				String Menu=String.format("%s" + "%s" , MenuFormat, PriceFormat);
				return Menu;
			}
		}
		
		else {
			menunum = MenuButton.info.get(i).getAmount();
			price = MenuButton.info.get(i).getPrice();
			if(size.equals("0") && temp.equals("0")) {
				String MenuFormat=String.format("%s", menuName);
				String PriceFormat=String.format("                        %s개 " + "  %s원", MenuButton.info.get(i).getAmount(), MenuButton.info.get(i).getPrice());
				String Menu=String.format("%s" + "%s" , MenuFormat, PriceFormat);
				return Menu;
			}
			else if(temp.equals("0")) {
				String MenuFormat=String.format("%s" +"("+"%s"+")", menuName, size);
				String PriceFormat=String.format("                        %s개 " + "  %s원", MenuButton.info.get(i).getAmount(), MenuButton.info.get(i).getPrice());
				String Menu=String.format("%s" + "%s" , MenuFormat, PriceFormat);
				return Menu;
			}
			else {
				String MenuFormat=String.format("%s" +"("+"%s"+")"+"("+"%s"+")", menuName, size, temp);
				String PriceFormat=String.format("                        %s개 " + "  %s원", MenuButton.info.get(i).getAmount(), MenuButton.info.get(i).getPrice());
				String Menu=String.format("%s" + "%s" , MenuFormat, PriceFormat);
				return Menu;
			}
		}
	}
	public String getsize() {
		return size;
	}
	public String gettemp() {
		return temp;
	}
	
}
