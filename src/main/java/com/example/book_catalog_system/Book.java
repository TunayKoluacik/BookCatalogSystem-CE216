package com.example.book_catalog_system;

import java.io.File;
import java.util.List;

public class Book {
    private String isbn, title, subtitle, author, translator, publisher, date, edition, rating, cover;

    private List<String> tags;


    public Book(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, List<String> tags, String rating, String cover) {
        setIsbn(isbn);
        setTitle(title);
        setSubtitle(subtitle);
        setAuthor(author);
        setTranslator(translator);
        setPublisher(publisher);
        setDate(date);
        setEdition(edition);
        setTags(tags);
        setRating(rating);
        File f = new File(cover);
        //TODO check for a bug
        if (f == null) {
            setCover("null");
        } else setCover(cover);
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
