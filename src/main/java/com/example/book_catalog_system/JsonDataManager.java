package com.example.book_catalog_system;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;


public class JsonDataManager {

    // Saves the information of the book which is passed as a parameter on to a json file.
    public static void saveBookToJson(BookManager.Book book) {
      String filename=  book.getIsbn();
      filename+= ".json";

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
            jsonObject.put("tag", book.getTag());
            jsonObject.put("rating", book.getRating());
            jsonObject.put("cover", book.getCover());

            writer.write(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is no such file. Save action failed.");

        }
    }
    //Simply deletes the given file
    public static void deleteFile(String filename){
        File myObj = new File(filename);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    // Reads attributes from a json and returns a book object with said attributes.
    public static BookManager.Book readBooksFromJson(String filename) {
        try (Reader reader = new FileReader(filename)) {
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            BookManager.Book book = new BookManager.Book();
            book.setAuthor(jsonObject.getString("author"));
            book.setIsbn(jsonObject.getString("isbn"));
            book.setTitle(jsonObject.getString("title"));
            book.setSubtitle(jsonObject.getString("subtitle"));
            book.setTranslator(jsonObject.getString("translator"));
            book.setPublisher(jsonObject.getString("publisher"));
            book.setDate(jsonObject.getString("date"));
            book.setEdition(jsonObject.getString("edition"));
            book.setTag(jsonObject.getString("tag"));
            book.setRating(jsonObject.getString("rating"));
            book.setCover(jsonObject.getString("cover"));
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is no such file. Read action failed.");
        }
        return null;
    }


    public static void main(String[] args) {
        //initializing a book to use in the functions
        BookManager.Book aras = new BookManager.Book("1234567890", "zo", "Subtitle", "Author",
                "Translator", "Publisher", "za", "First Edition",
                "Tag", "Rating", "Cover Image URL");


        // save test
      //  saveBookToJson(aras);
         BookManager bookmanager = new BookManager();
        bookmanager.BookList.put(1234567890L, aras);
        bookmanager.editBook("1234567890","author","");
        // read test
        BookManager.Book book = readBooksFromJson("1234567890.json");
        // reads correctly as seen
       System.out.println(book.getAuthor());
        System.out.println(book.getTitle());
    }
}
