package com.example.book_catalog_system;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.List;

public class JsonDataManager {

    // Method to save a list of books to a JSON file
    // TODO : WRITE TO A JSON FİLE

    // TODO : REMOVE JSON FILE
    public static void saveBooksToJson(List<BookManager.Book> books, String filename) {
        JSONArray jsonArray = new JSONArray();
        for (BookManager.Book book : books) {
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
            jsonArray.put(jsonObject);
        }
        try (Writer writer = new FileWriter(filename)) {
            jsonArray.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to append a single book to an existing JSON file
    public static void appendBookToJson(BookManager.Book book, String filename) {
        try (Reader reader = new FileReader(filename)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
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
            jsonArray.put(jsonObject);
            try (Writer writer = new FileWriter(filename)) {
                jsonArray.write(writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read books from a JSON file
    public static BookManager.Book readBooksFromJson(String filename) {
        //TODO: Get the book's ISBN and add ".json" to the end of it. This is your filename.
        try (Reader reader = new FileReader(filename)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            JSONObject jsonObject = jsonArray.getJSONObject(0); // Assuming there's only one book in the JSON array
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
        }
        return null;
    }

    // Sample usage
    public static void main(String[] args) {
        // Sample usage
        List<BookManager.Book> books = List.of(
                new BookManager.Book("1234567890", "Book Title", "Subtitle", "Author",
                        "Translator", "Publisher", "2024-04-10", "First Edition",
                        "Tag", "Rating", "Cover Image URL")
        );

        // Save books to JSON file
        saveBooksToJson(books, "aras.json");

        // Append a book to the JSON file
        appendBookToJson(new BookManager.Book("0987654321", "Another Book", "", "Author 2",
                "", "Publisher 2", "2024-04-11", "Second Edition",
                "Tag", "Rating", "Cover Image URL"), "aras.json");

        // Read books from JSON file
        //BookManager.Book book = readBooksFromJson("books.json");
        System.out.println(books.get(0));
    }
}
