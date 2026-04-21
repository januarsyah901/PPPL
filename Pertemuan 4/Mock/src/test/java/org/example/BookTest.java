package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookTest {

    @Mock
    private BookRepository bookRepository; // Ini akan menjadi Stub (untuk return data) & Mock (untuk verifikasi)

    @Mock
    private NotificationService notificationService; // Ini adalah Mock (untuk verifikasi interaksi)

    @InjectMocks
    private BookController bookController;

    @Test
    void testCheckAvailability_Success() {
        // Stubbing: Mengatur agar findById mengembalikan buku yang tersedia
        Book book = new Book("B01", "Clean Code", true);
        when(bookRepository.findById("B01")).thenReturn(Optional.of(book));

        boolean available = bookController.checkAvailability("B01");

        assertTrue(available);
        verify(bookRepository).findById("B01");
    }

    @Test
    void testBorrowBook_WhenAvailable_ShouldCallPeminjamanAndNotify() {
        // Arrange
        String id = "B01";
        String username = "bangjan";
        Book book = new Book(id, "Clean Code", true);
        
        // Stubbing
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        // Act
        bookController.borrowBook(id, username);

        // Assert & Verify Order
        InOrder inOrder = inOrder(bookRepository, notificationService);
        
        inOrder.verify(bookRepository).findAll(); // Urutan 1
        inOrder.verify(bookRepository).peminjaman("Clean Code", username); // Urutan 2
        inOrder.verify(notificationService).sendNotification(username, "Clean Code"); // Urutan 3
    }

    @Test
    void testBorrowBook_WhenNotAvailable_ShouldNotCallPeminjaman() {
        // Arrange
        String id = "B02";
        String username = "bangjan";
        Book book = new Book(id, "Design Patterns", false); // Sedang dipinjam
        
        // Stubbing
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        // Act
        bookController.borrowBook(id, username);

        // Verify
        verify(bookRepository).findAll();
        // Verifikasi bahwa fungsi peminjaman TIDAK dipanggil karena buku tidak tersedia
        verify(bookRepository, never()).peminjaman(anyString(), anyString());
        verify(notificationService, never()).sendNotification(anyString(), anyString());
    }
}
