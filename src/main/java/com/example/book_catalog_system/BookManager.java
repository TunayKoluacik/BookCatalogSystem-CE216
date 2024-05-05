package com.example.book_catalog_system;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;


public class BookManager{

    private TreeMap<Long, Book> BookList = new TreeMap<>();

    private ObservableList<Book> searchResult = FXCollections.observableArrayList();
    //- filterResult: A list to store the result of the latest filter query by the user.
    private ObservableList<Book> filterResult = FXCollections.observableArrayList();

    public TreeMap<Long, Book> getBookList() {
        return BookList;
    }

    public ObservableList<Book> OgetBookList() {
        return FXCollections.observableList(new ArrayList<>(getBookList().values()));
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


    private static ObservableList<String> totalTags = FXCollections.observableArrayList();

    public JsonDataManager getDataManager() {
        return dataManager;
    }

    private JsonDataManager dataManager;

    public BookManager() {
        dataManager = new JsonDataManager();
    }

    public ObservableList<String> getTags() {
        return totalTags;
    }

    public void setTags(ObservableList<String> tags) {
        BookManager.totalTags = tags;
    }

    public ObservableList<Book> SearchBook(String keyword) {
        searchResult.clear(); // Clear previous search results
        String lowerCaseKeyword = keyword.toLowerCase(); // Prepare the keyword to avoid repeated conversions

        for (Book book : BookList.values()) {
            // Directly check each attribute to see if it contains the keyword
            if (book.getIsbn().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getTitle().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getSubtitle().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getAuthor().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getTranslator().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getPublisher().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getDate().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getEdition().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getRating().toLowerCase().contains(lowerCaseKeyword) ||
                    book.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(lowerCaseKeyword))) {
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public void createBook(String isbn, String title, String subtitle, String author, String translator, String publisher, String date, String edition, List<String> tags, String rating, String cover) {





        try {
            FileInputStream inputFile = new FileInputStream(cover);
            BufferedImage image = ImageIO.read(inputFile);

            File outputFile = new File(JsonDataManager.dir + isbn + ".jpg");
            ImageIO.write(image, "jpg", outputFile);
            System.out.println(outputFile);
        } catch (IOException e) {
            cover = "null";
        }




        Book book = new Book(isbn, title, subtitle, author, translator, publisher, date, edition, tags, rating, cover);
        JsonDataManager.saveBookToJson(book);
        BookList.put(Long.parseLong(isbn), book);
        for(String tag : book.getTags()){
            if(!totalTags.contains(tag)){
                totalTags.add(tag);
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

        for (Object value : params) {

            switch (attribute.toLowerCase()) {
                case "isbn":
                    String deleteISBN = bookToUpdate.getIsbn();
                    bookToUpdate.setIsbn((String) value);
                    JsonDataManager.deleteJson(deleteISBN);
                    BookList.remove(Long.parseLong(deleteISBN));
                    BookList.put(Long.parseLong(bookToUpdate.getIsbn()), bookToUpdate);
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
                    ObservableList<String> cnvrt = FXCollections.observableList(List.of(value.toString().replace(" ", "").replace("[", "").replace("]", "").split(",")));
                    for (String tag : bookToUpdate.getTags()) {
                        if (!totalTags.contains(tag)) {
                            totalTags.add(tag);
                        }
                    }
                    bookToUpdate.setTags(cnvrt);
                    break;
                case "rating":
                    bookToUpdate.setRating((String) value);
                    break;
                case "cover":
                    bookToUpdate.setCoverPath((String) value);
                    break;


            }
        }
        JsonDataManager.saveBookToJson(bookToUpdate);
    }


    // Deletion of book ---> delete from the list, do we delete from programs memory too ?
    // and return the deleted book;

    public List<String> listingTags(){
        for (Book book : BookList.values()) {
            List<String> tags = book.getTags();
            for (String tag : tags) {
                if (!totalTags.contains(tag)) {
                    totalTags.add(tag);
                }
            }
        }
        return totalTags;
    }
    //
    public ObservableList<Book> filterByTag(List<String> tags) {
        filterResult.clear();
        for (Book book : BookList.values()) {
            if(new HashSet<>(book.getTags()).containsAll(tags)) {
                filterResult.add(book);
            }
        }
        return filterResult;
    }

    public void deleteBook(String isbn) {
        Book bookToRemove = BookList.get(Long.parseLong(isbn));

        // Check if the book exists
        if (bookToRemove == null) {
            // Book with the given ISBN does not exist
            throw new IllegalArgumentException("Book with ISBN '" + isbn + "' does not exist.");
        } else {
            // Remove the book from the TreeMap

            JsonDataManager.deleteJson(isbn);
            BookList.remove(Long.parseLong(isbn));

        }
    }
}

