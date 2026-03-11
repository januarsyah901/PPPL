package org.example;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(String id);
    List<Book> findAll();
    // Fungsi peminjaman yang akan dipanggil jika buku tersedia
    void peminjaman(String title, String username);
}
