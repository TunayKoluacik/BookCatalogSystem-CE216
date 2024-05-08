package com.example.book_catalog_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Book{
    private String isbn, title, subtitle, author, translator, publisher, date, edition, rating, coverPath;

    private Image cover;

    private ObservableList<String> tags;


    public Book(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, List<String> tags, String rating, String cover) {
        setIsbn(isbn);
        setTitle(title);
        setSubtitle(subtitle);
        setAuthor(author);
        setTranslator(translator);
        setPublisher(publisher);
        setDate(date);
        setEdition(edition);
        ObservableList<String> cnvrt = FXCollections.observableList(tags);
        setTags(cnvrt);
        setRating(rating);
        setCoverPath(cover);
    }



    public Book(){
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public ObservableList<String> getTags() {
        return tags;
    }

    public void setTags(ObservableList<String> tags) {
        this.tags = tags;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Image getCover() {
        return cover;
    }
    public void setCover(Image cover) {
        this.cover = cover;
    }

    public void setCoverPath(String s){
        if (isFile(s)){
            try {
                if (!s.equals("def.png")){ //TODO: Change the condition to check the image folders for already existing images
                    FileInputStream inputFile = new FileInputStream(s);
                    BufferedImage image = ImageIO.read(inputFile);

                    File outputFile = new File(JsonDataManager.dir + isbn + ".jpg");
                    ImageIO.write(image, "jpg", outputFile);
                    this.coverPath = outputFile.getPath();
                    System.out.println(outputFile);
                    inputFile.close();
                }

                System.out.println("already exists");


            } catch (IOException e) {
                cover = new Image("file:def.png");
            }
        }else{
            setCoverPath("def.png");
        }
    }

    private boolean isFile(String s) {
        try{
            FileInputStream fis = new FileInputStream(s);
            cover = new Image(fis);
            fis.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getCoverPath() {
        return coverPath;
    }
}
