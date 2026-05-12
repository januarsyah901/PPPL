AUTOMATION TESTING (Page Object Model)

10.1 Tujuan Pembelajaran

* a. Mahasiswa mampu memahami konsep Page Object Model (POM)


* b. Mahasiswa mampu mengimplementasikan POM dalam pengujian



## 10. 2 Dasar Teori

Page Object Model (POM) adalah salah satu design pattern yang dapat diterapkan pada skrip pengujian perangkat lunak. POM bertujuan untuk meningkatkan keterbacaan, perawatan, dan ketahanan terhadap perubahan pada skrip pengujian. Dalam POM, setiap halaman web dianggap sebagai objek terpisah, dan setiap objek halaman memiliki metode-metode yang merepresentasikan perilaku dan interaksi dengan elemen-elemen di halaman tersebut. Pendekatan ini membantu memisahkan logika bisnis dan akses elemen dari implementasi pengujian, memungkinkan pengembang untuk membuat dan memelihara skrip pengujian dengan lebih mudah.

Metode atau pengujian kelas dapat menjadi rumit dan sulit dikelola karena kurangnya struktur yang jelas. Ketika elemen pencarian muncul beberapa kali di method atau kelas berbeda, hal ini dapat menyebabkan redundansi dan meningkat kesulitan dalam mengelola skrip pengujian. Dalam Page Object Model, elemen-elemen dan perilaku yang terkait dengan suatu halaman diisolasi dalam kelas Page Object yang khusus. Ini mengurangi duplikasi kode dan memungkinkan perubahan pada halaman web hanya memerlukan perubahan di satu tempat, meningkatkan keterbacaan dan perawatan kode pengujian.

Selain itu, POM menyediakan cara yang lebih terstruktur dan modular untuk mengorganisir skrip pengujian, menghasilkan pengujian yang lebih mudah dipahami dan dipelihara seiring berjalannya waktu. Dengan menerapkan POM, tim pengembang dapat mengoptimalkan pengujian, mengurangi kerumitan, dan meningkatkan efisiensi dalam siklus pengembangan perangkat lunak.

Berikut adalah contoh penggambaran implementasi POM pada kasus aplikasi managemen inventory. Pada kasus ini, terdapat 2 halaman yang terlibat dalam pengujian yaitu halaman login dan halaman inventory. Oleh karena itu, pada POM tiap halaman akan dibuatkan kelas yang berisi akses terhadap web elemen di halaman tersebut. Kelas untuk halaman login dan halaman inventory akan berisi field yang merepresentasikan web elemen, method untuk mengakses elemen, dan interaksi dengan elemen. Pada kelas halaman tidak perlu mengimplementasikan semua elemen yang ada pada halaman web. Kelas cukup berisi elemen yang akan digunakan dalam pengujian.

> 🖼️ **[Diagram Description]:** The diagram illustrates the Page Object Model structure for an inventory management application. It is divided into two main sections:
> * **Page Object:** This section contains two rectangular blocks. The first block is labeled "**Login**" and includes the description "**Web elements on login page**". The second block is labeled "**Inventory**" and includes the description "**Web elements on inventory page**".
> * **Test Cases:** This section contains a single block labeled "**Login Test Case**" with the description "**Test case to log in the app**".
>
>
> * **Connections:** Orange arrows originate from the "**Login Test Case**" block and point towards both the "**Login**" and "**Inventory**" Page Object blocks, indicating that the test case interacts with the elements and methods defined in these page classes.
>
>

---

10.3 Latihan

Pada kasus pengujian Bing Search, terdapat 3 kelas yang bisa kita implementasikan:

*
**Search page class**: berisi atribut dan method untuk mengakses elemen-elemen pada halaman pencarian


*
**Search result class**: berisi atribut dan method untuk mengakses elemen-elemen pada halaman hasil pencarian


*
**Search test class**: berisi method pengujian dan assertion



**Instruksi Latihan:**

1. Buatlah search page class yang berisi elemen untuk search bar dan button submit.


2. Buatlah result page class yang berisi akses kepada title halaman web.


3. Buatlah test class dan test method. Buat objek dari kelas search page. Lakukan pemanggilan method pada objek tersebut untuk mengakses elemen yang ada pada search page.



10.4 Tugas

1. Ubah kode test login (https://www.saucedemo.com/) kalian menjadi bentuk POM.


2. Explorasi dan implementasikan Centralized Locators. Buat class tersendiri berisi semua locator sehingga apabila ada perubahan locator hanya perlu mengubah di satu class.


3. Explorasi dan implementasikan class LatihanBasePage berisi interaksi umum (e.g., click, input text, wait for elements) yang dapat diakses oleh semua test class sehingga mengurangi code duplication.



---

**Contoh penerapan LatihanBasePage:**

```java
public WebElement waitForElementVisible(By by) throws InterruptedException {
    [cite_start]return wait.until(ExpectedConditions.visibilityOfElementLocated(by)); [cite: 40, 42]
}

public void click (By by) throws InterruptedException {
    [cite_start]waitForElementVisible(by).click(); [cite: 43]
}

```