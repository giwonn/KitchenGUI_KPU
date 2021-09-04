# KitchenGUI_KPU
 대학교 졸업작품(2017/06~2018/06)

## Skills
Java, Java Swing, JavaFx

## GUI
- Kiosk 는 **Java Swing**<br>
- 주방에서 사용하는 프로그램은 **JavaFX**를 사용함
<br><br>

Kiosk는
https://github.com/inthej/McDonaldKiosk
위의 코드를 바탕으로 제작하였고

주방 스크린은 처음부터 직접 제작하였습니다.
<br><br>

통신방식은 TCP/IP를 사용하였고, 진동벨도 만들었으나 현재 레포지토리에는 Kiosk 관련 코드만 존재합니다.
진동벨은 블루투스를 통하여 통신하였습니다.

PCB기판은 직접 제작하였습니다.

## 프로젝트 구조
<details>
 <summary>📦Kiosk</summary>
 
   ┣ 📂image  
   ┣ 📂src  
   ┃ ┣ 📂kiosk  
   ┃ ┃ ┣ 📜Client.java  [프로그램 실행을 담당함]  
   ┃ ┃ ┣ 📜Display.java  [디스플레이 화면(=스크린 및 윈도우 사이즈)의 정보를 제공함]  
   ┃ ┃ ┣ 📜JTextFieldLimit.java  [JText의 글자수를 제한함]  
   ┃ ┃ ┣ 📜MainFrame.java  [새로운 데이터가 들어오면 프레임(페이지)를 갱신해줌]  
   ┃ ┃ ┣ 📂page  
   ┃ ┃ ┃ ┣ 📂confirm  [확인(주문,장소 등등) 페이지]  
   ┃ ┃ ┃ ┃ ┣ 📜ChoosePlace.java  
   ┃ ┃ ┃ ┃ ┣ 📜ConfirmButton.java  
   ┃ ┃ ┃ ┃ ┣ 📜ConfirmPage.java  
   ┃ ┃ ┃ ┃ ┣ 📜MileagePanel.java  
   ┃ ┃ ┃ ┃ ┣ 📜OrderData.java  
   ┃ ┃ ┃ ┃ ┣ 📜OrderList.java  
   ┃ ┃ ┃ ┃ ┣ 📜OrderTotalDataPanel.java  
   ┃ ┃ ┃ ┃ ┣ 📜PhoneButton.java  
   ┃ ┃ ┃ ┃ ┗ 📜Phonenumber.java  
   ┃ ┃ ┃ ┣ 📂eatplace  [포장/식사 선택 페이지]  
   ┃ ┃ ┃ ┃ ┣ 📜EatPlace.java  
   ┃ ┃ ┃ ┃ ┗ 📜EatPlacePage.java  
   ┃ ┃ ┃ ┣ 📂order  [메뉴 주문 페이지]  
   ┃ ┃ ┃ ┃ ┣ 📂MenuPanel  [메뉴 보여주는 패널]  
   ┃ ┃ ┃ ┃ ┃ ┣ 📜BagelPanel.java  
   ┃ ┃ ┃ ┃ ┃ ┣ 📜DessertPanel.java  
   ┃ ┃ ┃ ┃ ┃ ┗ 📜DrinkPanel.java  
   ┃ ┃ ┃ ┃ ┣ 📜CartMenu.java  [장바구니에 담기는 메뉴를 관리함]  
   ┃ ┃ ┃ ┃ ┣ 📜CartPanel.java  
   ┃ ┃ ┃ ┃ ┣ 📜CoffeeOptionPanel.java  
   ┃ ┃ ┃ ┃ ┣ 📜MenuButton.java  
   ┃ ┃ ┃ ┃ ┣ 📜MenuTab.java  
   ┃ ┃ ┃ ┃ ┣ 📜MenuTablePanel.java  
   ┃ ┃ ┃ ┃ ┣ 📜OrderPage.java  [주문 리스트 확인 페이지]  
   ┃ ┃ ┃ ┃ ┣ 📜OrderPlace.java  
   ┃ ┃ ┃ ┃ ┗ 📜SelectedMenu.java  
   ┃ ┃ ┃ ┣ 📂payment  [결제 페이지]  
   ┃ ┃ ┃ ┃ ┗ 📂place  
   ┃ ┃ ┃ ┃ ┃ ┣ 📜AlarmSelect.java  
   ┃ ┃ ┃ ┃ ┃ ┗ 📜AlarmSelectPage.java  
   ┃ ┃ ┃ ┣ 📂thank  [결제 후 마지막 페이지]  
   ┃ ┃ ┃ ┃ ┗ 📜ThankPage.java  
   ┃ ┃ ┃ ┣ 📂welcome  [첫 페이지]  
   ┃ ┃ ┃ ┃ ┣ 📜OrderButton.java  
   ┃ ┃ ┃ ┃ ┣ 📜WelcomePage.java  
   ┃ ┃ ┃ ┃ ┗ 📜WelcomePage2.java  
   ┃ ┃ ┃ ┣ 📜BackButton.java  
   ┃ ┃ ┃ ┣ 📜ImageEdit.java  
   ┃ ┃ ┃ ┣ 📜ImageTextButton.java  
   ┃ ┃ ┃ ┣ 📜ImageTextPanel.java  
   ┃ ┃ ┃ ┣ 📜KioskGuidePanel.java  
   ┃ ┃ ┃ ┣ 📜KioskGuidePanel2.java  
   ┃ ┃ ┃ ┣ 📜KioskPage.java  
   ┃ ┃ ┃ ┗ 📜TitleLabel.java  
   ┃ ┗ 📂testCom  [서버, 소켓 등 관리 폴더]  
   ┃ ┃ ┣ 📜Client.java  
   ┃ ┃ ┣ 📜Excel_IO.java  
   ┃ ┃ ┣ 📜Fileserver.java  
   ┃ ┃ ┣ 📜ImageEdit.java  
   ┃ ┃ ┣ 📜Info_Menu.java  
   ┃ ┃ ┣ 📜Menu_Func.java  
   ┃ ┃ ┣ 📜Server.java  
   ┃ ┃ ┗ 📜test.java  
   ┗ 📜Menu.csv  [메뉴 담아놓은 csv 파일]  
</details>


<details>
 <summary>📦KitchenGUI</summary>
 
   ┣ 📂bin  
   ┃ ┗ 📂kitchen  
   ┃ ┃ ┗ 📂view  
   ┃ ┃ ┃ ┣ 📜KitchenOverview.fxml  
   ┃ ┃ ┃ ┣ 📜NoOptionDialog.fxml  
   ┃ ┃ ┃ ┣ 📜OrderAddDialog.fxml  
   ┃ ┃ ┃ ┣ 📜OrderEditDialog.fxml  
   ┃ ┃ ┃ ┣ 📜OrderOptionDialog.fxml  
   ┃ ┃ ┃ ┣ 📜RootLayout.fxml  
   ┃ ┃ ┃ ┗ 📜TempOptionDialog.fxml  
   ┗ 📂src  
   ┃ ┗ 📂kitchen  
   ┃ ┃ ┗ 📜MainApp.java  

</detail
