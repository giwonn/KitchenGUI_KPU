package kitchen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kitchen.model.DataList;
import kitchen.model.ReceiveOrder;
import kitchen.view.KitchenOverviewController;
import kitchen.view.NoOptionDialogController;
import kitchen.view.OrderAddDialogController;
import kitchen.view.OrderEditDialogController;
import kitchen.view.OrderOptionDialogController;
import kitchen.view.TempOptionDialogController;

public class MainApp extends Application {
	public static ObservableList<DataList> orderData = FXCollections.observableArrayList();
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane KitchenOverview;
    
    static Socket socket=null;
	static OutputStream os=null;
	static Writer writer=null;
	public static BufferedWriter bw=null;
	static InputStream is=null;
	static Reader reader=null;
	public static int ordernum = 1;
	
	public static BufferedReader br=null;
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("주방 GUI");

        initRootLayout();
        showOrderOverview();
        
        primaryStage.setOnCloseRequest(event->System.exit(0));
    }
    
    public boolean showOrderEditDialog(DataList selectedOrder) {
    	try {
    		// fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/OrderEditDialog.fxml"));
    		
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// 다이얼로그 스테이지를 만든다.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("메뉴 수정");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// order를 컨트롤러에 설정한다.
    		OrderEditDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		controller.setOrder(selectedOrder);
    		// 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
    		dialogStage.showAndWait();
    		
    		return controller.isOkClicked();
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean showOrderAddDialog(DataList selectedOrder) {
    	
    	try {
    		// fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/OrderAddDialog.fxml"));
    		
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// 다이얼로그 스테이지를 만든다.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("주문 추가");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// order를 컨트롤러에 설정한다.
    		OrderAddDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
 //   		controller.setOrder(selectedOrder);
    		// 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
    		dialogStage.showAndWait();
    		return controller.isOkClicked();
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean showAllOptionDialog() {
    	try {
    		// fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/OrderOptionDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// 다이얼로그 스테이지를 만든다.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("옵션 선택");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// order를 컨트롤러에 설정한다.
    		OrderOptionDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		// 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
    		dialogStage.showAndWait();
    		OrderAddDialogController.Size = controller.getSize();
    		OrderAddDialogController.Temp = controller.getTemp();
    		OrderAddDialogController.Menunum = Integer.parseInt(controller.getMenunum());
    		OrderEditDialogController.Size = controller.getSize();
    		OrderEditDialogController.Temp = controller.getTemp();
    		OrderEditDialogController.Menunum = Integer.parseInt(controller.getMenunum());
    		
    		return controller.isOkClicked();
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean showTempOptionDialog() {
    	try {
    		
    		// fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/TempOptionDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// 다이얼로그 스테이지를 만든다.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("옵션 선택");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		// order를 컨트롤러에 설정한다.
    		TempOptionDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		// 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
    		dialogStage.showAndWait();
    		OrderAddDialogController.Size = controller.getSize();
    		OrderAddDialogController.Temp = controller.getTemp();
    		OrderAddDialogController.Menunum = Integer.parseInt(controller.getMenunum());
    		OrderEditDialogController.Size = controller.getSize();
    		OrderEditDialogController.Temp = controller.getTemp();
    		OrderEditDialogController.Menunum = Integer.parseInt(controller.getMenunum());
    		return controller.isOkClicked();
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean showNoOptionDialog() {
    	try {
    		
    		// fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/NoOptionDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// 다이얼로그 스테이지를 만든다.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("옵션 선택");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		// order를 컨트롤러에 설정한다.
    		NoOptionDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		// 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
    		dialogStage.showAndWait();
    		OrderAddDialogController.Size = controller.getSize();
    		OrderAddDialogController.Temp = controller.getTemp();
    		OrderAddDialogController.Menunum = Integer.parseInt(controller.getMenunum());
    		OrderEditDialogController.Size = controller.getSize();
    		OrderEditDialogController.Temp = controller.getTemp();
    		OrderEditDialogController.Menunum = Integer.parseInt(controller.getMenunum());
    		return controller.isOkClicked();
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }


    /**
     * 상위 레이아웃을 초기화한다.
     */
    public void initRootLayout() {
        try {
            // fxml 파일에서 상위 레이아웃을 가져온다.
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // 상위 레이아웃을 포함하는 scene을 보여준다.
        	Scene scene = new Scene(rootLayout);
        	primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 상위 레이아웃 안에 연락처 요약(person overview)을 보여준다.
     */
    public void showOrderOverview() {
        try {
            // 연락처 요약을 가져온다.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/KitchenOverview.fxml"));
            AnchorPane kitchenOverview = (AnchorPane) loader.load();

            // 연락처 요약을 상위 레이아웃 가운데로 설정한다.
            rootLayout.setCenter(kitchenOverview);
            
            // 메인 애플리케이션이 컨트롤러를 이용할 수 있게 한다.
            KitchenOverviewController controller = loader.getController();
             controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 생성자
     */
    public MainApp() {}

    /**
     * 연락처에 대한 observable 리스트를 반환한다.
     * @return
     */
   public ObservableList<DataList> getDataList() {
        return orderData;
    }
    
    /**
     * 메인 스테이지를 반환한다.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
    	Receive recieve=null;
    	  
         try {
        	 InetAddress local = InetAddress.getLocalHost();
        	   String ip = local.getHostAddress();
               socket=new Socket();
               socket.connect(new InetSocketAddress(ip,9001));
//             socket.connect(new InetSocketAddress("192.168.1.100",9001));
			os=socket.getOutputStream();
			 System.out.println("Server 연결 성공");
	         os=socket.getOutputStream();
	         writer=new OutputStreamWriter(os);
	         recieve=new Receive(socket);
	    	 recieve.start();
	         bw=new BufferedWriter(writer);
	         bw.write("0001"+"\n");
	         bw.flush();
		} catch (IOException e) {System.out.println(e.getMessage());}
        launch(args);
    }
    public static class Receive extends Thread{
    	public static ArrayList<DataList> dataarray=new ArrayList<DataList>();
    	ReceiveOrder receiveorder=new ReceiveOrder();
		Socket socket=null;
		InputStream is=null;
		 Reader reader=null;
		 BufferedReader br=null;
		 String Msg=null;

		 public Receive(Socket socket)
		 {
			 this.socket=socket;
			 try{
				 is=socket.getInputStream();
					reader=new InputStreamReader(is);
					br=new BufferedReader(reader);
			 }catch(Exception e){System.out.println(e.getMessage());}
		 }
		 public void run()
		 {
			 try{
			 while(true)
			 {
				Msg=br.readLine();
				System.out.println("Receive Server: "+ Msg);
			 	dataarray.add(receiveorder.receive_data(Msg));
			 	orderData.add(dataarray.get(dataarray.size()-1));
			 }
		 }catch(Exception e){}
	}
	}
    
}