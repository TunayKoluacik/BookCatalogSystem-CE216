package com.example.book_catalog_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class PresentationLayer extends Application {


    public static BookManager bookmanager = new BookManager();
    ListView<BookManager.Book> tunay = new ListView<>();
    @Override
    public void start (Stage stage) throws Exception {

        VBox mainLayout = new VBox();

        HBox searchBar = new HBox(10);

        Text sTitle = new Text("Search Bar ");
        TextField sField = new TextField("Write Here...");
        Button sButton = new Button("Search");

        searchBar.setAlignment(Pos.CENTER);
        searchBar.getChildren().addAll(sTitle, sField, sButton);
        HBox.setHgrow(sField, Priority.ALWAYS);
        searchBar.setPadding(new Insets(10));

        HBox tagBar = new HBox(10);
        tagBar.setPadding(new Insets(10));
        tagBar.setAlignment(Pos.CENTER_LEFT);

        Text tTitle = new Text("Tags: ");
        SplitMenuButton split = new SplitMenuButton();


        List<String> tags = bookmanager.listingTags();
        tags.forEach(tag -> {
            CheckMenuItem checkMenuItem = new CheckMenuItem(tag);
            split.getItems().add(checkMenuItem);
        });

        Button tButton = new Button("Filter");
        Button stButton = new Button("Search and Filter");
        tagBar.getChildren().addAll(tTitle, split, tButton, stButton);

        HBox footerBar = new HBox(10);
        Button fEdit = new Button("Edit");
        Button fDelete = new Button("Delete");

        footerBar.getChildren().addAll(fEdit, fDelete);
        footerBar.setAlignment(Pos.CENTER_RIGHT);
        footerBar.setPadding(new Insets(10));

        mainLayout.setAlignment(Pos.TOP_CENTER);

        MenuBar menu = new MenuBar();
        Menu mFile = new Menu("File");


        Menu mHelp = new Menu("Help");
        //TODO: Help Menu


        MenuItem mCreate = new MenuItem("Create");
        mCreate.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        mCreate.setOnAction(e -> {
            try {
                openFile(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        MenuItem mImport = new MenuItem("Import");
        mImport.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        mImport.setOnAction(e -> {
            //TODO: Write "not available popup or tooltip"
        });

        MenuItem mExport = new MenuItem("Export");
        mExport.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));

        MenuItem mAbout = new MenuItem("About");
        MenuItem mManuel = new MenuItem("Manuel");


        mFile.getItems().addAll(mCreate, mImport, mExport); // add menuitems to file menu
        mHelp.getItems().addAll(mAbout, mManuel);

        menu.getMenus().addAll(mFile, mHelp); // add menus to menubar

        VBox.setVgrow(tunay, Priority.ALWAYS);

        mainLayout.getChildren().addAll(menu, searchBar, tagBar, tunay, footerBar);
        Scene tst = new Scene(mainLayout, 600, 600);
        stage.setTitle("Book Catalog System: Group 9");
        stage.setScene(tst);
        stage.show();



    }

    private void openFile(Stage stage) throws IOException {
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(new HBox(4, new Label("Second window"))));
        secondStage.show();
    }

    public static void main (String[]args){

        bookmanager.createBook("1234545640", "ege", "akın", "A1",
                "Translator1", "Publisher", "za", "First Edition", List.of("Tag1", "Tag2"), "2", "Cover Image URL");
        bookmanager.createBook("1234545641", "gizem", "akcay", "A2",
                "Translator1", "Publisher", "za", "Second Edition",
                List.of("Tag2", "Tag3"), "3", "Cover Image URL");
        bookmanager.createBook("1234545642", "aras", "firat", "A1",
                "Translator2", "Publisher", "za", "Fifth Edition",
                List.of("Tag1", "Tag3"), "4", "Cover Image URL");

        bookmanager.listingTags();
        launch();
    }
}