package tde.gui;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {
	 private TreeView treeView1;
	 private TreeView treeView2;
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		MenuBar bar = new MenuBar();
		VBox right = new VBox(190);
		VBox top = new VBox();
		VBox left = new VBox();
		VBox center = new VBox();
		/*************************/
		Menu menuFile = new Menu ("Datei");					//Die MenuBar wird oben erschaffen. Es feheln nur noch die EventListener
		Menu menuCata = new Menu ("Katalog");
		MenuItem load = new MenuItem ("Laden");
		MenuItem save = new MenuItem ("Speichern");
		
		bar.getMenus().addAll(menuFile, menuCata);
		menuFile.getItems().addAll(load, save);
		top.getChildren().add(bar);
		top.setAlignment(Pos.TOP_LEFT);
		pane.setTop(top);
		/*************************/
		Button btnApp = new Button ("Ready");				//2 Buttons werden rechts erschaffen. Es fehlen nur die EventListener
		Button btnTest = new Button ("Ready");
		
		right.getChildren().addAll(btnApp, btnTest);
		right.setAlignment(Pos.BASELINE_RIGHT);
		pane.setRight(right);
		/*************************/
		TreeItem<String> root1 = new TreeItem<String>("C:\\");	//2 Explorer werden erschaffen. Leider wird nichst angeziegt -> muss ge?ndert werden
		treeView1 = new TreeView<String>(root1);
		TreeItem<String> root2 = new TreeItem<String>("C:\\");
		treeView2= new TreeView<String>(root2);
		
		left.getChildren().addAll(treeView1, treeView2);
		left.setAlignment(Pos.TOP_LEFT);
		pane.setLeft(left);
		/*************************/
		TextArea text1 = new TextArea();					//2 Textfelder werden erschaffen. Vielleicht spacing machen um klare Grenzen aufzuzeigen?
		text1.setMinSize(380, 400);
		TextArea text2 = new TextArea();
		text2.setMinSize(380, 400);
		
		center.getChildren().addAll(text1, text2);
		center.setAlignment(Pos.TOP_CENTER);
		pane.setCenter(center);
		
		stage.setTitle("TDDT");
		stage.setMaximized(true);							//Maybe setCenter oder so
		stage.setHeight(500);
		stage.setWidth(700);
		stage.setScene(scene);
		stage.show();
			
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
