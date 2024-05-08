package com.example.book_catalog_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class JsonDataManager {

    // Saves the information of the book which is passed as a parameter on to a json file.

    static File dir = new File("./jsonFiles");

    public JsonDataManager() {

        BootingUp();

    }

    public static void BootingUp() {
        if (!dir.exists()) {
            // Create the directory
            boolean result = dir.mkdir();

            if (result) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Failed to create directory");
            }
        } else {
            System.out.println("Directory already exists");
        }

    }

    public static void saveBookToJson(Book book) {
        String filename = book.getIsbn();
        filename = dir + "/" + filename + ".json";

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
    public static void deleteJson(String filename) {
        filename = dir + "/" + filename + ".json";
        File myObj = new File(filename);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    BookManager bookmanager;

    public static void addBooksOnStart(String folderName) {
        File folder = new File(folderName);
        BookManager bookmanager = PresentationLayer.bookmanager;

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".json")) {
                        try {

                            Book book = readBooksFromJson(file.getPath());
                            JsonDataManager.saveBookToJson(book);
                            bookmanager.getBookList().put(Long.parseLong(book.getIsbn()), book);
                            for (String tag : book.getTags()) {
                                if (!bookmanager.getTags().contains(tag)) {
                                    bookmanager.getTags().add(tag);
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("error importing " + file.getName());

                        }
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist or is not accessible.");
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

    public static void zipJsonFilesFolder(List<Book> selectedBooks, String zipFilePath) {
        try {
            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            for (Book selectedBook : selectedBooks) {
                String title = selectedBook.getTitle();
                if (title != null && !title.isEmpty()) {
                    JSONObject jsonObject = createJSONObject(selectedBook);
                    byte[] bytes = jsonObject.toString().getBytes();
                    ZipEntry zipEntry = new ZipEntry(title + ".json");
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(bytes, 0, bytes.length);
                } else {
                    System.out.println("Title is null or empty for book: " + selectedBook.getTitle());
                }
            }

            zipOut.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void importJson(String jsonFilePath) {
        File jsonFile = new File(jsonFilePath);
        BookManager bookManager = PresentationLayer.bookmanager;

        if (jsonFile.exists() && jsonFile.isFile() && jsonFile.getName().endsWith(".json")) {
            try {
                Book importedBook = readBooksFromJson(jsonFile.getPath());
                if (importedBook != null) {
                    saveBookToJson(importedBook); // puts the file to our libraries folder
                    bookManager.getBookList().put(Long.parseLong(importedBook.getIsbn()), importedBook); // bizim json listemize ekliyor
                    System.out.println("Imported: " + importedBook.getTitle());
                } else {
                    System.out.println("Failed to import: " + jsonFile.getName());
                }
            } catch (Exception e) {
                System.out.println("Error importing: " + jsonFile.getName());
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid JSON file: " + jsonFile.getName());
        }
    }

}