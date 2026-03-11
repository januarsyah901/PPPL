Tugas 

Implementasikan mockito, identifikasi stub dan mock yang bisa digunakan dalam kasus Berikut. Silahkan menambahkan fungsi atau class yang dibutuhkan. 

1. Tambahkan fungsi untuk cek ketersediaan buku pada controller dan model.. Cek ketersediaan buku berdasarkan id. Buatlah test method untuk method cek ketersediaan pada controller. 


2. Tambahkan class model notificationService yang akan mengirimkan notifikasi apabila buku berhasil dipinjam atau dikembalikan. 


3. Meminjam buku (id buku). Buat 2 test scenario: 


* Apabila tidak sedang dipinjam maka fungsi peminjaman buku akan dipanggil. 


* Apabila buku sedang dipinjam, lakukan verifikasi bahwa fungsi peminjaman buku tidak dipanggil. 





Urutan proses yang diharapkan (eksplor penggunaan fungsi InOrder untuk memastikan urutan pemanggilannya sesuai): 

1. 
`getAllBooks()` 


2. 
`Peminjaman(title, username)` 


3. 
`sendNotification(username, title)` 