package com.example.book_catalog_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PresentationLayer extends Application {


    public static BookManager bookmanager = new BookManager();
    ListView<Book> tunay = new ListView<>();
    @Override
    public void start (Stage stage) throws Exception {

        VBox mainLayout = new VBox();

        HBox searchBar = new HBox(10);

        Text sTitle = new Text("Search Bar ");
        TextField sField = new TextField("Write Here...");
        Button sButton = new Button("Search");
        sButton.setOnAction(e ->{
            //TODO
        });

        searchBar.setAlignment(Pos.CENTER);
        searchBar.getChildren().addAll(sTitle, sField, sButton);
        HBox.setHgrow(sField, Priority.ALWAYS);
        searchBar.setPadding(new Insets(10));

        HBox tagBar = new HBox(10);
        tagBar.setPadding(new Insets(10));
        tagBar.setAlignment(Pos.CENTER_LEFT);

        Text tTitle = new Text("Tags: ");
        SplitMenuButton split = new SplitMenuButton();

        //TODO seçili tag gösterme


        List<String> tags = bookmanager.listingTags();
        tags.forEach(tag -> {
            CheckMenuItem checkMenuItem = new CheckMenuItem(tag);
            split.getItems().add(checkMenuItem);
        });

        Button tButton = new Button("Filter");
        tButton.setOnAction(e -> {
            //TODO filter method goes here
        });

        Button stButton = new Button("Search and Filter");
        tagBar.getChildren().addAll(tTitle, split, tButton, stButton);

        HBox footerBar = new HBox(10);
        Button fShow = new Button("Show Details");
        fShow.setOnAction(e -> {
            //TODO detail screen
        });
        Button fEdit = new Button("Edit");
        fEdit.setOnAction(e-> {
            //TODO editing book funciton
        });
        Button fDelete = new Button("Delete");
        fDelete.setOnAction(e-> {
            //TODO deleting book funciton
        });

        footerBar.getChildren().addAll(fShow, fEdit, fDelete);
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
                GUIcreateBook();
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

    private void GUIcreateBook() throws IOException {
        Stage createStage = new Stage();

        VBox vertical = new VBox( 10);

        double wid = 65;

        HBox isbn = new HBox(10);
        Label lIsbn = new Label("ISBN: ");
        lIsbn.setAlignment(Pos.CENTER_LEFT);
        lIsbn.setMinWidth(wid);
        TextField tIsbn = new TextField();
        tIsbn.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tIsbn, Priority.ALWAYS);
        isbn.getChildren().addAll(lIsbn, tIsbn);

        HBox title = new HBox(10);
        Label lTitle = new Label("Title: ");
        lTitle.setAlignment(Pos.CENTER_LEFT);
        lTitle.setMinWidth(wid);
        TextField tTitle = new TextField();
        tTitle.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tTitle, Priority.ALWAYS);
        title.getChildren().addAll(lTitle, tTitle);

        HBox subtitle = new HBox(10);
        Label lSubtitle = new Label("Subtitle: ");
        lSubtitle.setAlignment(Pos.CENTER_LEFT);
        lSubtitle.setMinWidth(wid);
        TextField tSubtitle = new TextField();
        tSubtitle.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tSubtitle, Priority.ALWAYS);
        subtitle.getChildren().addAll(lSubtitle, tSubtitle);

        HBox author = new HBox(10);
        Label lAuthor = new Label("Author: ");
        lAuthor.setAlignment(Pos.CENTER_LEFT);
        lAuthor.setMinWidth(wid);
        TextField tAuthor = new TextField();
        tAuthor.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tAuthor, Priority.ALWAYS);
        author.getChildren().addAll(lAuthor, tAuthor);

        HBox translator = new HBox(10);
        Label lTranslator = new Label("Translator: ");
        lTranslator.setAlignment(Pos.CENTER_LEFT);
        lTranslator.setMinWidth(wid);
        TextField tTranslator = new TextField();
        tTranslator.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tTranslator, Priority.ALWAYS);
        translator.getChildren().addAll(lTranslator, tTranslator);

        HBox publisher = new HBox(10);
        Label lPublisher = new Label("Publisher: ");
        lPublisher.setAlignment(Pos.CENTER_LEFT);
        lPublisher.setMinWidth(wid);
        TextField tPublisher = new TextField();
        tPublisher.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tPublisher, Priority.ALWAYS);
        publisher.getChildren().addAll(lPublisher, tPublisher);

        HBox date = new HBox(10);
        Label lDate = new Label("Date: ");
        lDate.setAlignment(Pos.CENTER_LEFT);
        lDate.setMinWidth(wid);
        TextField tDate = new TextField();
        tDate.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tDate, Priority.ALWAYS);
        date.getChildren().addAll(lDate, tDate);

        HBox edition = new HBox(10);
        Label lEdition = new Label("Edition: ");
        lEdition.setAlignment(Pos.CENTER_LEFT);
        lEdition.setMinWidth(wid);
        TextField tEdition = new TextField();
        tEdition.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tEdition, Priority.ALWAYS);
        edition.getChildren().addAll(lEdition, tEdition);

        HBox tags = new HBox(10);
        Label lTags = new Label("Tags: ");
        lTags.setAlignment(Pos.CENTER_LEFT);
        lTags.setMinWidth(wid);
        TextField tTags = new TextField();
        tTags.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tTags, Priority.ALWAYS);
        tags.getChildren().addAll(lTags, tTags);

        HBox rating = new HBox(10);
        Label lRating = new Label("Rating: ");
        lRating.setAlignment(Pos.CENTER_LEFT);
        lRating.setMinWidth(wid);
        TextField tRating = new TextField();
        tRating.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tRating, Priority.ALWAYS);
        rating.getChildren().addAll(lRating, tRating);

        HBox cover = new HBox(10);
        Label lCover = new Label("Cover: ");
        lCover.setAlignment(Pos.CENTER_LEFT);
        lCover.setMinWidth(wid);
        TextField tCover = new TextField();
        tCover.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(tCover, Priority.ALWAYS);
        cover.getChildren().addAll(lCover, tCover);


        vertical.setAlignment(Pos.TOP_CENTER);
        vertical.getChildren().addAll(isbn, title, subtitle, author, translator, publisher, date, edition, tags, rating, cover);
        vertical.setPadding(new Insets(10));

        Scene createScene = new Scene(vertical, 300, 500);
        createStage.setScene(createScene);
        createStage.showAndWait();
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

        File dir = new File("");

        bookmanager.listingTags();
        launch();
    }
}