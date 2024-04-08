package com.example.book_catalog_system;

import java.util.List;
import java.util.TreeMap;

public class BookManager {
    // allBooks
TreeMap<String,Book> BookList = new TreeMap<>();
// The returned books;
List searchResult;
//- filterResult: A list to store the result of the latest filter query by the user.
List filterResult;

// at least client needs to give isbn,title,author,tag
// When we create a book object the create book class automatically put the book in the BookList
    // scanner may be used in this function ı need to discuss with my team.
    public Book createBook(Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Parameters must be provided in pairs (key, value).");
        }

        String isbn = null;
        String title = null;
        String subtitle = null;
        String author = null;
        String translator = null;
        String publisher = null;
        String date = null;
        String edition = null;
        String tag = null;
        String rating = null; // Default rating
        String cover = null;

        for (int i = 0; i < params.length; i += 2) {
            String key = (String) params[i];
            Object value = params[i + 1];
            switch (key.toLowerCase()) {
                case "isbn":
                    isbn = (String) value;
                    break;
                case "title":
                    title = (String) value;
                    break;
                case "subtitle":
                    subtitle = (String) value;
                    break;
                case "author":
                    author = (String) value;
                    break;
                case "translator":
                    translator = (String) value;
                    break;
                case "publisher":
                    publisher = (String) value;
                    break;
                case "date":
                    date = (String) value;
                    break;
                case "edition":
                    edition = (String) value;
                    break;
                case "tag":
                    tag = (String) value;
                    break;
                case "rating":
                    rating = (String) value;
                    break;
                case "cover":
                    cover = (String) value;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown parameter: " + key);
            }
        }

        if (isbn == null || title == null || author == null || tag == null) {
            throw new IllegalArgumentException("At least ISBN, title, author, and tag must be provided.");
        }

        Book book = new Book(isbn, title, subtitle, author, translator, publisher, date, edition, tag, rating, cover);
        BookList.put(title,book);
return book;
    }


}



       class Book {
        private String isbn, title, subtitle, author, translator, publisher, date, edition, tag, rating, cover;
        private boolean isFile, isDeleted;

        //constructor without parameters isFile and İsDeleted

        public Book(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, String tag, String rating, String cover) {
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
        }
// constructor with full attributes
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


