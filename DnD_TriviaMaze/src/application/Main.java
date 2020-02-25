package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * Author: Bryan Wilson
 * 
 * This class is only for testing purposes ONLY. This file should NEVER arrive on the master branch.
 */
 
public class Main extends Application {
	private static Label mStatus;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Say 'Hello World'");
        btn.setOnAction(actionEvent -> setStatus("Hello World"));
        
        BorderPane root = new BorderPane();
        root.setCenter(btn);
        btn.prefWidthProperty().bind(primaryStage.widthProperty().divide(2)) ;

        // add the menus
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Everything is Copacetic");
        ToolBar toolBar = new ToolBar(mStatus);
        root.setBottom(toolBar);
        
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Practice Template");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static void onAbout() {
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
    	alert.setTitle("About");
    	alert.setHeaderText("Code Goblins Practice Template");
    	alert.showAndWait();
    }
    
    private static MenuBar buildMenuBar() {
    	
		// build a menu bar
		MenuBar menuBar = new MenuBar();

		// File menu with just a quit item for now
		Menu fileMenu = new Menu("_File");
		MenuItem quitMenuItem = new MenuItem("_Quit");
		quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
		quitMenuItem.setOnAction(actionEvent -> Platform.exit());
		fileMenu.getItems().add(quitMenuItem);

		// Help menu with just an about item for now
		Menu helpMenu = new Menu("_Help");
		MenuItem aboutMenuItem = new MenuItem("_About");
		aboutMenuItem.setOnAction(actionEvent -> onAbout());
		helpMenu.getItems().add(aboutMenuItem);
		menuBar.getMenus().addAll(fileMenu, helpMenu);

		return menuBar;
    }
    
    private static void setStatus(String str) {
    	mStatus.setText(str);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
