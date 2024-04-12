package com.example.book_catalog_system;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;



public class BookManager {
    // allBooks
    TreeMap<Long, Book> BookList = new TreeMap<>();
    // The returned books;
    ObservableList searchResult;
    //- filterResult: A list to store the result of the latest filter query by the user.
    ObservableList filterResult;

    private static List<String> tags;

    BookManager() {

    }

    public List getTags() {
        return tags;
    }

    public void setTags(ObservableList tags) {
        this.tags = tags;
    }

    /* at least client needs to give isbn,title,author,tag
 When we create a book object the create book class automatically put the book in the BookList
 scanner may be used in this function ı need to discuss with my team.
 isFile
 */
    public Book createBook(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, List<String> tags, String rating, String cover) {


        Book book = new Book(isbn, title, subtitle, author, translator, publisher, date, edition, tags, rating, cover);
        JsonDataManager.saveBookToJson(book);
        BookList.put(Long.parseLong(isbn), book);
        return book;
    }

    //client is going to give the book isbn that needed to be  edited. And then the attribute that is going to change and then the new value
    // ex:
    public void editBook(String isbn,String attribute, Object... params) {
        Book bookToUpdate = null;

        bookToUpdate = BookList.get(Long.parseLong(isbn));

        if (bookToUpdate == null) {
            throw new IllegalArgumentException("Book with ISBN '" + isbn + "' does not exist.");
        }

        for (int i = 0; i < params.length; i++) {

            Object value = params[i];
            switch (attribute.toLowerCase()) {
                case "isbn":
                    bookToUpdate.setIsbn((String) value);
                    break;
                case "title":
                    bookToUpdate.setTitle((String) value);
                    break;
                case "subtitle":
                    bookToUpdate.setSubtitle((String) value);
                    break;
                case "author":
                    bookToUpdate.setAuthor((String) value);
                    break;
                case "translator":
                    bookToUpdate.setTranslator((String) value);
                    break;
                case "publisher":
                    bookToUpdate.setPublisher((String) value);
                    break;
                case "date":
                    bookToUpdate.setDate((String) value);
                    break;
                case "edition":
                    bookToUpdate.setEdition((String) value);
                    break;
                case "tag":
                    bookToUpdate.setTags(Collections.singletonList((String) value));
                    break;
                case "rating":
                    bookToUpdate.setRating((String) value);
                    break;
                case "cover":
                    bookToUpdate.setCover((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Attribute: " + attribute);
            }
        }
        JsonDataManager.saveBookToJson(bookToUpdate);
    }


    // Deletion of book ---> delete from the list, do we delete from programs memory too ?
    // and return the deleted book;
    public Book deleteBook(String isbn) {
        Book bookToRemove = BookList.get(Long.parseLong(isbn));

        // Check if the book exists
        if (bookToRemove == null) {
            // Book with the given ISBN does not exist
            throw new IllegalArgumentException("Book with ISBN '" + isbn + "' does not exist.");
        } else {
            // Remove the book from the TreeMap
            Book removedBook = BookList.remove(Long.parseLong(isbn));
            JsonDataManager.deleteJson(bookToRemove.getIsbn());
            return removedBook;

        }
    }

    public List<String> listingTags(List<BookManager.Book> books){
        List<String> allTags = new ArrayList<>();
        for (BookManager.Book book : books) {
            List<String> tags = book.getTags();
            for (String tag : tags) {
                if (!allTags.contains(tag)) {
                    allTags.add(tag);
                }
            }
        }
        return allTags;
    }
    //
    public List<BookManager.Book> filterByTag(List<String> tags) {
        filterResult = (ObservableList) new ArrayList<>();
        for (BookManager.Book book : BookList.values()) {
            if(book.getTags().containsAll(tags)) {
                filterResult.add(book);
            }
        }
        return filterResult;
    }

    static class Book {
        private String isbn, title, subtitle, author, translator, publisher, date, edition, rating, cover;
        private boolean isFile, isDeleted;
        private List<String> tags;

        //constructor without parameters isFile and İsDeleted

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
            setCover(cover);
        }

        // constructor with full attributes
        public Book(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, List<String> tags, String rating, String cover, boolean isFile, boolean isDeleted) {
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
            setCover(cover);
            setFile(isFile);
            setDeleted(isDeleted);
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