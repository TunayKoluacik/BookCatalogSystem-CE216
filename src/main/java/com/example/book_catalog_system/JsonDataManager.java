package com.example.book_catalog_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class JsonDataManager {

    // Saves the information of the book which is passed as a parameter on to a json file.

    static File dir = new File("./jsonFiles");

    public JsonDataManager() {
        BootingUp();
    }

    public static void BootingUp(){
        if (!dir.exists()) {
            // Create the directory
            boolean result = dir.mkdir();

            if(result) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Failed to create directory");
            }
        } else {
            System.out.println("Directory already exists");
        }
    }

    public static void saveBookToJson(Book book) {
        String filename=  book.getIsbn();
        filename = dir +"/"+ filename + ".json";

        try (Writer writer = new FileWriter(filename)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("isbn", book.getIsbn());
            jsonObject.put("title", book.getTitle());
            jsonObject.put("subtitle", book.getSubtitle());
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("translator", book.getTranslator());
            jsonObject.put("publisher", book.getPublisher());
            jsonObject.put("date", book.getDate());
            jsonObject.put("edition", book.getEdition());
            jsonObject.put("tags", book.getTags());
            jsonObject.put("rating", book.getRating());
            jsonObject.put("cover", book.getCover());

            writer.write(jsonObject.toString());

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("There is no such file. Save action failed.");

        }
    }
    //Simply deletes the given file
    public static void deleteJson(String filename){
        filename = dir +"/"+ filename + ".json";
        File myObj = new File(filename);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    // Reads attributes from a json and returns a book object with said attributes.
    public static Book readBooksFromJson(String filename) {
        try (Reader reader = new FileReader(filename)) {
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            Book book = new Book();
            book.setAuthor(jsonObject.getString("author"));
            book.setIsbn(jsonObject.getString("isbn"));
            book.setTitle(jsonObject.getString("title"));
            book.setSubtitle(jsonObject.getString("subtitle"));
            book.setTranslator(jsonObject.getString("translator"));
            book.setPublisher(jsonObject.getString("publisher"));
            book.setDate(jsonObject.getString("date"));
            book.setEdition(jsonObject.getString("edition"));
            JSONArray tagsArray = jsonObject.getJSONArray("tags");
            List<String> tags = new ArrayList<>();
            for (int i = 0; i < tagsArray.length(); i++) {
                tags.add(tagsArray.getString(i));
            }
            ObservableList<String> cnvrt = FXCollections.observableList(tags);
            book.setTags(cnvrt);
            book.setRating(jsonObject.getString("rating"));
            book.setCoverPath(jsonObject.getString("cover"));
            return book;
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("There is no such file. Read action failed.");
        }
        return null;
    }

    // Export a single book to a JSON file
//    public static void exportBookToJson(Book book) {
//
//        String filename=  book.getIsbn();
//        filename+= ".json";
//
//        try (Writer writer = new FileWriter(filename)) {
//            JSONObject jsonObject = createJSONObject(book);
//            writer.write(jsonObject.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Failed export from json" + filename);
//        }
//    }
//
//    // Import a single book from a JSON file
//    public static Book importBookFromJson(String filename) {
//
//        try (Reader reader = new FileReader(filename)) {
//            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
//            return createBookFromJSONObject(jsonObject);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Failed import from book" + filename);
//        }
//        return null;
//    }

    // creates a json with the book object
    private static JSONObject createJSONObject(Book book) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isbn", book.getIsbn());
        jsonObject.put("title", book.getTitle());
        jsonObject.put("subtitle", book.getSubtitle());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("translator", book.getTranslator());
        jsonObject.put("publisher", book.getPublisher());
        jsonObject.put("date", book.getDate());
        jsonObject.put("edition", book.getEdition());
        jsonObject.put("tags", new JSONArray(book.getTags()));
        jsonObject.put("rating", book.getRating());
        jsonObject.put("cover", book.getCover());
        return jsonObject;
    }

    // CREATES A BOOK OBJECT WITH THE .JSON FILE
    private static Book createBookFromJSONObject(JSONObject jsonObject) {
        Book book = new Book();
        book.setIsbn(jsonObject.getString("isbn"));
        book.setTitle(jsonObject.getString("title"));
        book.setSubtitle(jsonObject.optString("subtitle", ""));
        book.setAuthor(jsonObject.getString("author"));
        book.setTranslator(jsonObject.optString("translator", ""));
        book.setPublisher(jsonObject.optString("publisher", ""));
        book.setDate(jsonObject.optString("date", ""));
        book.setEdition(jsonObject.optString("edition", ""));
        List<String> tags = new ArrayList<>();
        JSONArray tagsArray = jsonObject.getJSONArray("tags");
        for (int i = 0; i < tagsArray.length(); i++) {
            tags.add(tagsArray.getString(i));
        }
        ObservableList<String> cnvrt = FXCollections.observableList(tags);
        book.setTags(cnvrt);
        book.setRating(jsonObject.optString("rating", ""));
        book.setCoverPath(jsonObject.optString("cover", ""));
        return book;
    }

    //lots of unorganized tests......
    public static void main(String[] args) {
        //initializing a book to use in the functions
        Book aras = new Book("1234567890", "zo", "Subtitle", "Author",
                "Translator", "Publisher", "za", "First Edition",
                List.of("Tag1"), "Rating", "Cover Image URL");


        // save test
        //  saveBookToJson(aras);
        BookManager bookmanager = new BookManager();
        bookmanager.getBookList().put(1234567890L, aras);
        bookmanager.editBook("1234567890","author","Tunay");
        // create test
        bookmanager.createBook("1234545", "ege", "akın", "A1",
                "Translator1", "Publisher", "za", "First Edition", List.of("Tag1", "Tag2"), "2", "Cover Image URL");
        bookmanager.createBook("1234545641", "gizem", "akcay", "A2",
                "Translator1", "Publisher", "za", "Second Edition",
                List.of("Tag2", "Tag3"), "3", "Cover Image URL");
        bookmanager.createBook("1234545642", "aras", "firat", "A1",
                "Translator2", "Publisher", "za", "Fifth Edition",
                List.of("Tag1", "Tag3"), "4", "Cover Image URL");

        //bookmanager.deleteBook("1234545640");
        //
        // read test
        Book book = readBooksFromJson("1234567890.json");

        if (book != null) createJSONObject(book);

        createBookFromJSONObject(createJSONObject(aras));
        // reads correctly as seen
        TreeMap<Long, Book> test = bookmanager.getBookList();

        System.out.println(test.get(Long.parseLong(aras.getIsbn())).getAuthor());
        if (book != null) System.out.println(book.getTitle());
        System.out.println(bookmanager.listingTags());

        System.out.println(dir.getAbsolutePath());
        BootingUp();
    }
}