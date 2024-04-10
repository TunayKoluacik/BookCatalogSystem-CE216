package com.example.book_catalog_system;





    /*
    3.3.1 JsonDataManager Class
    The JsonDataManager class provides methods for reading from and writing to
    JSON files using the object model API.
    Attributes
    - address: The path for the JSON files’ folder
    Methods
    - readJsonFile(String filePath)
    - writeJsonFile(String filePath, JsonObject jsonObject)
     */
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import javax.json.stream.JsonParsingException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JsonDataManager {

    // Method to save a list of books to a JSON file
    // Method to save a list of books to a JSON file
    public static void saveBooksToJson(List<BookManager.Book> books, String filename) {
        try (Writer writer = new FileWriter(filename)) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (BookManager.Book book : books) {
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                        .add("isbn", book.getIsbn())
                        .add("title", book.getTitle())
                        .add("subtitle", book.getSubtitle())
                        .add("author", book.getAuthor())
                        .add("translator", book.getTranslator())
                        .add("publisher", book.getPublisher())
                        .add("date", book.getDate())
                        .add("edition", book.getEdition())
                        .add("tag", book.getTag())
                        .add("rating", book.getRating())
                        .add("cover", book.getCover());
                jsonArrayBuilder.add(jsonObjectBuilder);
            }

            JsonArray jsonArray = jsonArrayBuilder.build();

            // Write JSON array to the file
            try (JsonWriter jsonWriter = Json.createWriter(writer)) {
                jsonWriter.writeArray(jsonArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to append a single book to an existing JSON file
    public static void appendBookToJson(BookManager.Book book, String filename) {
        try (InputStream inputStream = new FileInputStream(filename);
             JsonReader reader = Json.createReader(inputStream);
             OutputStream outputStream = new FileOutputStream(filename);
             JsonWriter writer = Json.createWriter(outputStream)) {

            JsonArray jsonArray;
            try {
                jsonArray = reader.readArray();
            } catch (JsonParsingException e) {
                // If file is empty or invalid, create a new array
                jsonArray = Json.createArrayBuilder().build();
            }

            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add("isbn", book.getIsbn())
                    .add("title", book.getTitle())
                    .add("subtitle", book.getSubtitle())
                    .add("author", book.getAuthor())
                    .add("translator", book.getTranslator())
                    .add("publisher", book.getPublisher())
                    .add("date", book.getDate())
                    .add("edition", book.getEdition())
                    .add("tag", book.getTag())
                    .add("rating", book.getRating())
                    .add("cover", book.getCover());

            jsonArray.add(jsonObjectBuilder.build());

            writer.writeArray(jsonArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read books from a JSON file
    public static void readBooksFromJson(String filename) throws IOException {

        try {
            InputStream fis = new FileInputStream(filename);

            JsonReader jsonReader = Json.createReader(fis);

            JsonObject jsonObject = jsonReader.readObject();

            jsonReader.close();
            fis.close();

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


        }catch (Exception e){

        }




    }

    // Sample usage
    public static void main(String[] args) throws IOException {


        // Read books from JSON file
       readBooksFromJson("aras.json");
        System.out.println("a");
    }
}
