package org.example;

public class Book {
    private String id;
    private String title;
    private boolean available;

    public Book(String id, String title, boolean available) {
        this.id = id;
        this.title = title;
        this.available = available;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }
}
