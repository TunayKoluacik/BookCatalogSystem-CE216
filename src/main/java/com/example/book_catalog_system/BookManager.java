package com.example.book_catalog_system;

import java.util.List;
import java.util.TreeMap;

public class BookManager {
    // allBooks
TreeMap<String,Book> BookList;
// The returned books;
List searchResult;
//- filterResult: A list to store the result of the latest filter query by the user.
List filterResult;


public void createBook(){


}



    public class Book {
        private String isbn, title, subtitle, author, translator, publisher, date, edition, tag, rating, cover;
        private boolean isFile, isDeleted;

        public Book(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, String tag, String rating, String cover, boolean isFile, boolean isDeleted) {
            setIsbn(isbn);
            setTitle(title);
            setSubtitle(subtitle);
            setAuthor(author);
            setTranslator(translator);
            setPublisher(publisher);
            setDate(date);
            setEdition(edition);
            setTag(tag);
            setRating(rating);
            setCover(cover);
            setFile(isFile);
            setDeleted(isDeleted);
        }

        ////ISBN, title, author, category, description, and
        ////price.


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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
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

        public boolean isFile() {
            return isFile;
        }

        public void setFile(boolean file) {
            isFile = file;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}


