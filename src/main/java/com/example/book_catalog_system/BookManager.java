package com.example.book_catalog_system;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;



public class BookManager{

    private TreeMap<Long, Book> BookList = new TreeMap<>();

    private ObservableList<Book> searchResult;
    //- filterResult: A list to store the result of the latest filter query by the user.
    private ObservableList<Book> filterResult;

    public TreeMap<Long, Book> getBookList() {
        return BookList;
    }

    public ObservableList<Book> OgetBookList() {

        ArrayList<String> valueList = new ArrayList<String>();
        for (Book value : getBookList().values()) {
            valueList.add(value.getTitle());
        }

        return FXCollections.observableList((List<Book>) getBookList().values());
    }

    public void setBookList(TreeMap<Long, Book> bookList) {
        BookList = bookList;
    }

    public ObservableList<Book> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(ObservableList<Book> searchResult) {
        this.searchResult = searchResult;
    }

    public ObservableList<Book> getFilterResult() {
        return filterResult;
    }

    public void setFilterResult(ObservableList<Book> filterResult) {
        this.filterResult = filterResult;
    }

    // allBooks

    // The returned books;


    private static ObservableList<String> tags;

    public JsonDataManager getDataManager() {
        return dataManager;
    }

    private JsonDataManager dataManager;

    public BookManager() {
        JsonDataManager dataManager = new JsonDataManager();
    }

    public ObservableList<String> getTags() {
        return tags;
    }

    public void setTags(ObservableList<String> tags) {
        BookManager.tags = tags;
    }

    /* at least client needs to give isbn,title,author,tag
 When we create a book object the create book class automatically put the book in the BookList
 scanner may be used in this function ı need to discuss with my team.
 isFile
 */
    public void createBook(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, List<String> tags, String rating, String cover) {


        Book book = new Book(isbn, title, subtitle, author, translator, publisher, date, edition, tags, rating, cover);
        JsonDataManager.saveBookToJson(book);
        BookList.put(Long.parseLong(isbn), book);
        for(String tag : book.getTags()){
            if(!tags.contains(tag)){
                tags.add(tag);
            }
        }
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
                    ObservableList<String> cnvrt = FXCollections.observableList(Collections.singletonList((String) value));
                    bookToUpdate.setTags(cnvrt);
                    break;
                case "rating":
                    bookToUpdate.setRating((String) value);
                    break;
                case "cover":
                    bookToUpdate.setCover((String) value);
                    break;


            }
        }
        JsonDataManager.saveBookToJson(bookToUpdate);
    }


    // Deletion of book ---> delete from the list, do we delete from programs memory too ?
    // and return the deleted book;
    public void deleteBook(String isbn) {
        Book bookToRemove = BookList.get(Long.parseLong(isbn));

        // Check if the book exists
        if (bookToRemove == null) {
            // Book with the given ISBN does not exist
            throw new IllegalArgumentException("Book with ISBN '" + isbn + "' does not exist.");
        } else {
            // Remove the book from the TreeMap
            Book removedBook = BookList.remove(Long.parseLong(isbn));
            JsonDataManager.deleteJson(bookToRemove.getIsbn());

        }
    }

    public List<String> listingTags(){
        List<String> allTags = new ArrayList<>();
        for (Book book : BookList.values()) {
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
    public ObservableList<Book> filterByTag(ObservableList<String> tags) {
        filterResult.removeAll();
        for (Book book : BookList.values()) {
            if(book.getTags().containsAll(tags)) {
                filterResult.add(book);
            }
        }
        return filterResult;
    }
}