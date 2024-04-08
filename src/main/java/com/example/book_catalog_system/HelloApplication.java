package com.example.book_catalog_system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.json.JsonValue;

public class HelloApplication extends Application {

    TextArea tunay = new TextArea();
    @Override
    public void start (Stage stage) throws Exception {

        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);

        MenuBar menu = new MenuBar();
        Menu mFile = new Menu("File");
        Menu mHelp = new Menu("Help");
        MenuItem mNewFile = new MenuItem("New");
        mNewFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        mNewFile.setOnAction(e -> newFile(stage));

        MenuItem mOpenFile = new MenuItem("Open");
        mOpenFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        mOpenFile.setOnAction(e -> {
            try {
                openFile(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        MenuItem mSaveFile = new MenuItem("Save");
        mSaveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        MenuItem mQuitFile = new MenuItem("Quit");
        mQuitFile.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        MenuItem hAbout = new MenuItem("About");


        mFile.getItems().addAll(mNewFile, mOpenFile, mSaveFile, mQuitFile); // add menuitems to file menu
        mHelp.getItems().addAll(hAbout);

        menu.getMenus().addAll(mFile, mHelp); // add menus to menubar

        VBox.setVgrow(tunay, Priority.ALWAYS);

        mainLayout.getChildren().addAll(menu, tunay);
        Scene scene = new Scene(mainLayout, 500, 500);
        stage.setTitle("Para-Text");
        stage.setScene(scene);
        stage.show();
    }

    private void saveFile (Stage stage){
        FileChooser fc = new FileChooser();
        fc.setTitle("Select file to save!");
        File f = fc.showSaveDialog(stage); // more to do here…
    }
    private void openFile (Stage stage) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select file to open!");
        File f = fc.showOpenDialog(stage); // more to do here…
        if (f != null) {
            tunay.setText(Files.readString(f.toPath()));
        } else {
            System.out.println("It's null");
        }
    }

    private void newFile (Stage stage){
        FileChooser fc = new FileChooser();
        fc.setTitle("Select file to save!");
        File f = fc.showSaveDialog(stage);
    }

    public static void main (String[]args){
        launch();
    }
}