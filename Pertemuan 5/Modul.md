
-----

# BAB VI SKENARIO PENGUJIAN DAN PELAPORAN BUG

### 5.3 Tujuan Pembelajaran

c. Mahasiswa mampu memahami definisi, fungsi, dan cara pembuatan skenario pengujian serta pelaporan bug
d. Mahasiswa mampu membuat skenario pengujian serta pelaporan bug

### 5.4 Dasar Teori

Mock dan stub merupakan bagian dari kategori fake object, yaitu objek tiruan yang digunakan dalam pengujian untuk menggantikan komponen asli. Stub adalah kelas tiruan yang telah diprogram sebelumnya untuk mengembalikan nilai tertentu sesuai kebutuhan pengujian. Stub disuntikkan (di-inject) ke dalam kelas yang sedang diuji agar pengembang memiliki kendali penuh terhadap data masukan yang diterima sistem. Dengan demikian, berbagai kondisi dapat direkonstruksi secara terkontrol, misalnya mensimulasikan tingkat radiasi tertentu melalui sensor tiruan.

Sementara itu, mock adalah kelas tiruan yang tidak hanya dapat memberikan respons seperti stub, tetapi juga dapat diperiksa setelah proses pengujian selesai untuk mengetahui bagaimana interaksinya dengan kelas yang diuji. Misalnya, kita dapat mengecek apakah suatu metode dipanggil, berapa kali dipanggil, atau dengan parameter apa metode tersebut dijalankan. Dalam praktiknya, karena mock juga dapat diprogram untuk mengembalikan nilai tertentu seperti stub, maka mock dapat dianggap sebagai bentuk yang lebih lengkap (superset) dari stub. Perbedaannya, stub hanya berfungsi menyediakan nilai balik, sedangkan mock memungkinkan verifikasi interaksi.

### 5.5

#### I. SKENARIO PENGUJIAN

Kasus uji adalah seperangkat data input, pra-kondisi, dan pasca kondisi eksekusi yang dikembangkan untuk melakukan verifikasi terhadap kesesuaian program dengan spesifikasi kebutuhan tertentu. Secara umum terdapat beberapa informasi yang diperlukan dari tiap kasus uji, antara lain:

1.  **Fitur:** Informasi mengenai fitur atau fungsionalitas yang akan diuji. Use case ID juga dapat digunakan sehingga lebih mudah dalam melakukan tracing.
2.  **Test Case ID:** Kode kasus pengujian
3.  **Deskripsi:** Informasi yang menjelaskan scenario yang akan diuji
4.  **Tipe:** Kasus uji dibagi menjadi 2 tipe: positif dan negatif.
    a. Skenario positif adalah skenario yang dibuat untuk menguji perilaku sistem ketika kondisi normal.
    b. Skenario negative adalah scenario yang dibuat untuk menguji perilaku sistem ketika kondisi tidak normal atau terdapat kesalahan.

Pada kasus fitur pendaftaran. Contoh kasus uji yang termasuk skenario positif adalah memasukkan data pendaftaran dengan benar sesui aturan dan lengkap. Contoh skenario negatif adalah alamat email tidak valid, password tidak sesuai ketentuan, atau data tidak lengkap.

5.  **Pre-Condition:** Kondisi prasyarat sebelum pengujian pada kasus uji tersebut dilakukan. Contoh: User sudah berada pada halaman utama.
6.  **Langkah Pengujian/Test Step:** langkah-langkah yang dilakukan saat pengujian
7.  **Data Pengujian/Test Data:** Informasi ini diperlukan apabila dalam pengujian terdapat data yang harus dimasukkan dalam sistem. Contoh pada kasus pendaftaran adalah data nama, email, dan password.
8.  **Expected Result:** Hasil diharapkan dari fitur yang sedang diuji.
9.  **Actual Result:** Hasil sebenarnya yang terjadi ketika pengujian dilakukan
10. **Test Status:** Informasi status dari hasil pengujian. Beberapa status pengujian yang mungkin terjadi:
    a. **Pass:** Fitur yang diuji berjalan dengan semestinya atau lolos pengujian
    b. **Fail:** Fitur yang diuji tidak berjalan dengan semestinya atau terdapat bug
    c. **Blocked:** Skenario tidak dapat diuji karena terdampak bug yang muncul pada fitur lain.

Format penulisan kasus uji dapat berbeda-beda tergantung dari kebutuhan perusahaan. Umumnya scenario pengujian dituliskan dalam bentuk tabular. Pembuatan kasus uji dapat memanfaatkan tools sederhana seperti excel. Selain itu terdapat pula test case management tools berbayar seperti QASE.

Berikut adalah contoh scenario pengujian untuk kasus uji pendaftaran:

**Tabel 1. Contoh Test Case**

| Fitur | ID | Description | Tipe | Pre-Condition | Test Step | Test Data | Expected Result | Actual Result | Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| UC-01 | TC-01 | User melakukan pendaftaran dengan data yang sesuai dan lengkap | Positif | User sudah masuk ke halaman registrasi | 1. User memasukkan data nama, email, dan password yang benar<br>2. User memilih tombol "register" | Nama: Divi Putri<br>Email: divi.putri@gmail.com<br>Password: KataKunci123\! | 1. Sistem menampilkan konfirmasi pendaftaran berhasil dilakukan<br>2. User diarahkan ke halaman beranda | V | Pass |
| UC-01 | TC-02 | User melakukan pendaftaran dengan data password yang tidak sesuai ketentuan | Negatif | User sudah masuk ke halaman registrasi | 1. User memasukkan data nama, email yang benar.<br>2. User memasukkan data password yang tidak sesuai ketentuan<br>3. User memilih tombol "register" | Nama: Divi Putri<br>Email: divi.putri@gmail.com<br>Password: KataKunci123 | 1. Sistem menampilkan pesan error dan memberikan penanda pada kolom password<br>2. User gagal mendaftar | X | Fail |

#### II. PELAPORAN BUG

Bug adalah kesalahan pada program yang menyebabkan program tidak berperilaku atau tidak menghasilkan keluarkan yang semestinya. Semua bug yang telah dideteksi harus dilaporkan dan ditindaklanjuti. Pelaporan Bug / Bug Report perlu dilakukan secara benar sehingga kemungkinan bug tersebut diperbaiki menjadi semakin tinggi. Apabila bug tidak dilaporkan dengan benar, ada kemungkinan developer tidak bisa memperbaiki karena bug tersebut irreproducible atau tidak bisa ditemukan kembali. Beberapa informasi yang harus ditemukan dalam bug report antara lain.

1.  **Title:** Judul dari bug yang muncul. Judul harus jelas sehingga developer dapat segera memahami kesalahan yang terjadi.
2.  **Description:** Penjelasan yang lebih rinci dari bug yang terjadi
3.  **Platform:** Menjelaskan lingkungan pengujian termasuk versi software, OS, Browser, dst yang digunakan ketika melakukan pengujian.
4.  **Step to reproduce:** Langkah-langkah dalam bentuk numbered list yang menjelaskan langkah yang diperlukan untuk menghasilkan bug. Langkah-langkah ini diperlukan untuk mempermudah developer dalam memproduksi ulang bug
5.  **Expectation:** Ekspektasi sesuai dengan test case
6.  **Priority & Severity:** Bug dapat diklasifikasikan menurut priority dan severity-nya. Priority menunjukkan dampak kerugian bisnis yang dapat ditimbulkan (High, Medium, Low). Severity menunjukkan dampak kerusakan yang ditimbulkan pada keseluruhan sistem (Critical, Major, Minor, Low).
7.  **Attachment:** Bukti terjadinya bug bisa berapa foto, screenshot, atau video.

### 5.6 Latihan

#### 5.3.1 Membuat kasus uji berdasarkan user story dan acceptance criteria

**User story:** Sebagai pembeli, saya ingin menambahkan produk kedalam keranjang saya sehingga saya dapat dengan mudah melihat barang-barang yang ingin saya beli dan melanjutkan proses checkout

**Acceptance criteria:**

1.  Halaman daftar produk harus memiliki tombol tambah ke keranjang di setiap produknya
2.  Jumlah produk dalam keranjang belanja harus dapat dilihat melalui icon keranjang belanja di bagian navigasi
3.  Saat menekan tombol 'tambah ke keranjang' dan memasukkan jumlah barang, produk tersebut harus ditambahkan dalam keranjang belanja dan merubah jumlah produk dalam keranjang belanja
4.  Halaman keranjang belanja dapat diakses melalui icon keranjang belanja di bagian navigasi
5.  Halaman keranjang belanja harus memberikan informasi nama produk, jumlah yang dibeli dan harga
6.  Halaman keranjang belanja harus memiliki tombol "checkout" untuk lanjut ke proses pembelian

**Skenario Pengujian**

1.  Buka Excel dan buatlah kolom sesuai contoh pada Tael 1.
2.  Buat 1 kasus uji positif dan 1 kasus Uji negatif
3.  Hasil perancangan kasus uji

> **[DESKRIPSI GAMBAR]**
> **Gambar Tangkapan Layar Tabel Hasil Perancangan Kasus Uji**
> Gambar ini menunjukkan sebuah tabel yang berisi dua skenario pengujian (test case) untuk fitur "Keranjang Belanja". Tabel tersebut memiliki kolom: Fitur, ID, Description, Type, Pre-Condition, Test Step, Test Data, Expected Result, Actual Result, dan Status.
>
>   * **Baris Pertama (TC01 - Positif):** Menguji penambahan 1 produk ke keranjang. Ekspektasinya adalah ikon keranjang menunjukkan angka "1" dan halaman keranjang menampilkan detail produk tersebut.
>   * **Baris Kedua (TC02 - Negatif):** Menguji penambahan 0 (nol) produk ke keranjang. Ekspektasinya adalah sistem menampilkan pesan peringatan dan produk tidak masuk ke keranjang.

#### 5.3.2 Membuat Bug report berdasarkan kasus uji yang telah dibuat

| No | Title | User berhasil register dengan data tidak lengkap |
| :--- | :--- | :--- |
| 1 | Description | url: test.websistem.com<br>User berhasil mendaftar pada sistem tanpa menginputkan password |
| | Platform | Windows 10 Home, Chrome v.117 |
| | Step to Reproduce | 1. User memasukkan data nama, email yang benar.<br>2. User memasukkan data password yang tidak sesuai ketentuan<br>3. User memilih tombol "register" |
| | Expectation | 1. Sistem menampilkan pesan error dan memberikan penanda pada kolom password<br>2. User gagal mendaftar |
| | Priority, Severity | High, Critical |
| | Attachment | Foto |

### 5.4 Tugas:

A. Membuat kasus uji menggunakan bantuan tools QASE.io
Langkah-langkah:

1.  Masuk ke website QASE.io dan buatlah akun (disarankan akun yang free)
2.  Login menggunakan akun yang baru dibuat
3.  Buat Project baru dengan memilih menu "Create new project"
4.  BTest cases for shopping cart functionality
5.  Lakukan pengujian berdasarkan kasus uji yang telah dibuat\!
6.  Buatlah bug report untuk bug yang ditemukan\! ([https://help.gase.io/en/articles/5563710-defects](https://help.gase.io/en/articles/5563710-defects))
7.  Laporkan dalam bentuk langkah-langkah beserta screenshot tampilan QASE.io\!