package org.example;

import java.util.List;

public class BookController {
    private final BookRepository repository;
    private final NotificationService notificationService;

    public BookController(BookRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    // Task 1: Cek ketersediaan pada controller
    public boolean checkAvailability(String id) {
        return repository.findById(id)
                .map(Book::isAvailable)
                .orElse(false);
    }

    // Task 3: Alur peminjaman buku
    public void borrowBook(String id, String username) {
        // Urutan 1: getAllBooks
        List<Book> books = repository.findAll();

        // Cari buku berdasarkan ID
        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.isAvailable()) {
            // Urutan 2: Peminjaman (Hanya jika tersedia)
            repository.peminjaman(book.getTitle(), username);
            
            // Urutan 3: Send Notification
            notificationService.sendNotification(username, book.getTitle());
        }
    }
}
