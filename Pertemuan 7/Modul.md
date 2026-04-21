Ini dia format modulnya yang sudah dirapikan, Bro. Karena bentuknya teks, ini disusun sedemikian rupa agar siap di-convert ke PDF (misal di-print to PDF melalui browser atau Word), lengkap dengan deskripsi gambar secara *to the point*:

***

# [cite_start]AUTOMATION TESTING (Selenium Getting Started) [cite: 1]

### [cite_start]7.1 Tujuan Pembelajaran [cite: 2]
* a. [cite_start]Mahasiswa mampu memahami kakas bantu pengujian antarmuka [cite: 3]
* b. [cite_start]Mahasiswa mampu mengimplementasikan kode dasar pengujian antarmuka menggunakan kakas bantu [cite: 4]

### [cite_start]7.2 Dasar Teori [cite: 5]
[cite_start]Selenium web driver adalah Library yang dapat digunakan untuk mengontrol web browser menggunakan program. [cite: 6] [cite_start]Dengan menggunakan selenium web driver, penguji perangkat lunak dapat membuat skrip otomasi untuk mengontrol atau berinteraksi dengan elemen-elemen pada halaman web. [cite: 7] [cite_start]Selenium mendukung berbagai macam Bahasa pemrograman seperti Java, Python, dan C#. [cite: 8] [cite_start]Berikut adalah alur untuk berinteraksi dengan web browser menggunakan Selenium dan Java: [cite: 9]

> **[DETAIL GAMBAR FLOWCHART]**
> Diagram alur berupa panah-panah yang saling menyambung ke arah kanan dengan urutan:
> [cite_start]1. Instantiate Webdriver [cite: 10]
> [cite_start]2. Navigate to the web page [cite: 12] [cite_start]*(Catatan: Di antara langkah 1 dan 2, terdapat panah bercabang ke atas bertuliskan "Get Browser information")* [cite: 11]
> [cite_start]3. Locate the element [cite: 13]
> [cite_start]4. Perform action on the element [cite: 14, 15]
> [cite_start]5. Record result [cite: 15]
> [cite_start]6. Close Session [cite: 15]

#### [cite_start]1. Instantiate web driver [cite: 16]
[cite_start]Pada maven project dapat menambahkan dependency seperti pada `pom.xml` [cite: 17]
```xml
[cite_start]<dependency> [cite: 18]
    [cite_start]<groupId>org.seleniumhq.selenium</groupId> [cite: 19]
    [cite_start]<artifactId>selenium-java</artifactId> [cite: 20]
    [cite_start]<version>4.43.0</version> [cite: 21]
    [cite_start]<scope>compile</scope> [cite: 22]
[cite_start]</dependency> [cite: 23]
```
[cite_start]Untuk yang tidak menggunakan maven, dapat menambahkan library (jar files) Tambahkan java library dengan melakukan Download Java language binding pada: `https://www.selenium.dev/downloads/`. [cite: 25] [cite_start]Extract file yang didapatkan dan masukkan semua file library pada project anda. [cite: 26] [cite_start]Untuk memasukkan library pilih File-Project structure pada IntelliJ IDEA [cite: 27]

#### [cite_start]2. Navigate to the web page [cite: 28]
[cite_start]Pada modul ini kita akan mencoba mengakses halaman Google menggunakan selenium [cite: 29]
[cite_start]Buatlah sebuah kelas pengujian dengan nama Google Test [cite: 30]
Buat satu test method. [cite_start]Dalam melakukan akses browser, kita terlebih dahulu perlu membuat objek WebDriver disesuaikan dengan browser yang digunakan. [cite: 31] [cite_start]Pada contoh ini kita menggunakan browser Chrome. [cite: 32]
```java
[cite_start]WebDriver driver; [cite: 33]
[cite_start]driver = new ChromeDriver(); [cite: 34]
```
[cite_start]Objek driver dalam selenium berfungsi untuk mengendalikan browser dalam melakukan berbagai tindakan. [cite: 35] Gunakan method get untuk mengarahkan browser ke alamat web tertentu. [cite_start]`driver.get("https://bing.com/");` [cite: 36] [cite_start]Manajemen window (maximize/minimize), mengganti fokus, atau menutup browser. [cite: 37]
```java
[cite_start]driver.manage().window().maximize(); [cite: 38]
[cite_start]driver.quit(); [cite: 39]
```
[cite_start]Keseluruhan kode pada test method adalah sebagai berikut: [cite: 40]
```java
[cite_start]WebDriver driver; [cite: 41]
[cite_start]@Test [cite: 42]
[cite_start]public void search() [cite: 43]
[cite_start]{ [cite: 44]
    [cite_start]driver = new ChromeDriver(); [cite: 45]
    [cite_start]driver.manage().window().maximize(); [cite: 47]
    [cite_start]driver.get("https://bing.com/"); [cite: 48]
    [cite_start]String title = driver.getTitle(); [cite: 49]
    [cite_start]driver.quit(); [cite: 50]
[cite_start]} [cite: 46]
```
Jalankan test method. [cite_start]Browser akan dijalankan dan membuka halaman Google. [cite: 51] [cite_start]Apabila ingin menutup browser secara otomatis ketika proses pengujian selesai, tutup koneksi dengan driver menggunakan method `driver.quit()` [cite: 52]

Objek driver juga dapat digunakan untuk mengakses informasi dari browser. [cite_start]Berikut adalah beberapa method yang bisa digunakan: [cite: 53]
* [cite_start]`getTitle()`: mendapatkan title dari halaman web yang sedang dibuka [cite: 54, 55]
* [cite_start]`getCurrentUrl()`: mendapatkan url dari halaman yang sedang dibuka [cite: 56]
* [cite_start]`findElement (By)` : mengakses web element pertama yang sesuai dengan Locator (By) yang digunakan. [cite: 57]

[cite_start]Method lain dapat dilihat pada dokumentasi selenium: `https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/WebDriver.html` [cite: 58]

### [cite_start]7.2.1 Locate and Interact with Element (Basic) [cite: 59]
[cite_start]Web elemen locator adalah cara untuk mengidentifikasi dan menemukan elemen-elemen di halaman web yang ingin kita kendalikan atau kontrol menggunakan selenium. [cite: 60] [cite_start]Memilih locator yang tepat sangat penting karena akan memastikan bahwa skrip otomasi kita dapat berinteraksi dengan elemen yang diinginkan. [cite: 61] [cite_start]Locator utama yang dapat digunakan adalah atribut ID dan value-nya. [cite: 62]
[cite_start]`driver.findElement(By.id("atribut_id_value"));` [cite: 63]

[cite_start]**Contoh penggunaan:** [cite: 64]
[cite_start]Berikut adalah potongan kode HTML: [cite: 65]
```html
[cite_start]<form action="/action_page.php"> [cite: 66]
    [cite_start]<label for="fname">First Name: </label><br> [cite: 67]
    [cite_start]<input class="information" type="text" id="fname" name="fname" value="Jane"><br><br> [cite: 68]
    [cite_start]<label for="lname">Last Name: </label><br> [cite: 69]
    [cite_start]<input class="information" type="text" id="lname" name="lname" value="Doe"><br><br> [cite: 70]
[cite_start]</form> [cite: 71]
```
[cite_start]Berdasarkan kode diatas, First name dan Last name dapat diakses menggunakan locator ID dengan kode sebagai berikut: [cite: 72]
```java
[cite_start]WebElement first_name = driver.findElement(By.id("fname")); [cite: 73]
[cite_start]WebElement last_name = driver.findElement(By.id("lname")); [cite: 73]
```
[cite_start]Setelah elemen diidentifikasi dengan web elemen locator, kita dapat melakukan berbagai macam tindakan atau interaksi dengan elemen tersebut. [cite: 74] Pada beberapa command perlu dipastikan elemen tertampil dengan sempuna pada laman web untuk dapat diakses. [cite_start]Beberapa elemen dasar adalah: [cite: 75]

**a. [cite_start]Send keys** [cite: 76]
[cite_start]Interaksi ini hanya dapat dilakukan pada text elemen dan elemen lain yang dapat diedit kontennya (content-editable attribute). [cite: 77]
[cite_start]HTML: [cite: 78]
```html
[cite_start]<input class="information" type="text" id="fname" name="fname" value=""> [cite: 79]
```
[cite_start]Selenium skrip: [cite: 80]
```java
[cite_start]WebElement info = driver.findElement(By.ID("fname")); [cite: 81]
[cite_start]info.clear(); [cite: 82]
[cite_start]info.sendKeys("trpl@ugm.ac.id"); [cite: 83]
```

**b. [cite_start]Click** [cite: 84]
Interaksi ini dapat dilakukan pada semua jenis elemen. Perintah click akan dieksekusi pada bagian tengah elemen. [cite_start]Contoh: [cite: 85]
[cite_start]HTML: [cite: 86]
```html
[cite_start]<input type="checkbox" name="newsletter" value="1" /> [cite: 87]
```
[cite_start]Selenium skrip: [cite: 87]
```java
[cite_start]WebElement cek = driver.findElement(By.name("newsletter")); [cite: 88]
[cite_start]cek.click(); [cite: 88]
```

### [cite_start]7.3 Tugas [cite: 89]
[cite_start]Buatlah kode untuk melakukan pengujian pada halaman Log in `https://www.saucedemo.com/` [cite: 90]