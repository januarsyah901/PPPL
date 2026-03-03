Bab IV Parameterized Test 

4.1 Tujuan Pembelajaran 

1. Mahasiswa mampu memahami tipe-tipe parameter dalam test method 


2. Mahasiswa mampu mengimplementasikan tipe-tipe parameter pada pengujian 



4.2 Dasar Teori 

Parameterized test adalah fitur yang diperkenalkan pada JUnit5. Fitur ini memungkinkan kita untuk mengeksekusi test method beberapa kali dengan parameter yang berbeda-beda atau dengan berbagai nilai input. Hal ini akan sangat membantu ketika kita ingin melakukan pengujian terhadap berbagai kasus atau skenario tanpa harus membuat test method yang berbeda untuk setiap inputnya. Untuk menggunakan parameterized test, kita harus mengganti anotasi `@Test` dengan anotasi `@ParameterizedTest`.

Berikut ini adalah beberapa cara mengimplementasikan parameterized test: 

1. Anotasi @ValueSource Anotasi ini digunakan untuk mendefinisikan single-value parameter atau parameter yang hanya berisi satu nilai. Parameter yang didefinisikan bisa menggunakan berbagai tipe data. Berikut adalah syntax yang dapat digunakan: 

```java
[cite_start]@ParameterizedTest [cite: 412]
[cite_start]@ValueSource(ints = {nilai1, nilai2, nilai3..., nilai-n}) [cite: 413]

```

2. Anotasi @CsvSource Anotasi ini digunakan untuk memberikan parameter melalui format Csv (Comma-separated-value). Selain itu kita juga dapat menggunakan anotasi `@CsvFileSource` untuk menyediakan parameter dengan format Csv melalui file eksternal. Berikut adalah syntax yang dapat digunakan: 

```java
[cite_start]@ParameterizedTest [cite: 418]
[cite_start]@CsvSource({"a1,b1", "a2,b2", "a3,b3", ...}) [cite: 419]

```

3. Anotasi @MethodSource Parameter dapat kita sediakan juga melalui method lain. Method tersebut harus mengembalikan stream atau iterable objek sebagai sumber parameter. Anotasi ini digunakan untuk mendefinisikan nama method tersebut.

Pertama kita harus membuat method sebagai penyedia parameter. Berikut adalah contoh methodnya: 

```java
[cite_start]private static Stream<Arguments> provideParameters() { [cite: 424]
    [cite_start]return Stream.of( [cite: 424]
        [cite_start]Arguments.of("x1", "y1"), [cite: 425, 426]
        [cite_start]Arguments.of("x2", "y2"), [cite: 427, 428, 429]
        [cite_start]Arguments.of("x3", "y3"), [cite: 430, 431]
        [cite_start]Arguments.of("...") [cite: 432]
    [cite_start]); [cite: 433]
[cite_start]} [cite: 434]

```

Setelah itu kita dapat menggunakan method `provideParameters` tersebut pada test method dengan syntax sebagai berikut: 

```java
[cite_start]@ParameterizedTest [cite: 437]
[cite_start]@MethodSource("provideParameters") [cite: 438]

```

4.3 Latihan 

Latihan pada modul ini menggunakan studi kasus kalkulator.

1. Tambahkan sebuah method pada kelas Kalkulator untuk mengecek apakah sebuah nilai adalah nilai Ganjil atau Genap. Kembalian dari method ini adalah Boolean.



```java
public boolean cekEven(int number) { 
    return number % 2 == 0; 
[cite_start]} [cite: 442]

```

2. Buatlah sebuat test method untuk menguji method `cekEven`. Gunakan parameterized test dengan memanfaatkan `ValueSource`.



```java
[cite_start]@ParameterizedTest(name = "Eksekusi ke- {index}, nilai: {arguments}") [cite: 444]
[cite_start]@ValueSource(ints = {2, 4, 6}) [cite: 444]
[cite_start]void cekEven(int param) { [cite: 444]
    [cite_start]Kalkulator kalk = new Kalkulator(); [cite: 445]
    [cite_start]assertTrue(kalk.cekEven(param)); [cite: 446]
[cite_start]} [cite: 447]

```

3. Jalankan dan perhatikan hasilnya. Test method `cekEven` akan dijalankan 3 kali sesuai jumlah parameter yang didefinisikan.



> **[Deskripsi Output Console]**
> * cekEven(int) 107 ms * Eksekusi ke- 1, nilai: 2 (102 ms) * Eksekusi ke- 2, nilai: 4 (3 ms) * Eksekusi ke- 3, nilai: 6 (2 ms) 
> 
> 

4. Gunakan parameterized test dengan `CsvSource` pada method tambah. Pada argumennya kita dapat mendefinisikan beberapa nilai yaitu nilai a, nilai b, dan expected resultnya.



```java
[cite_start]@ParameterizedTest [cite: 460]
[cite_start]@CsvSource({"2,4,6", "1,2,3", "100,588,600"}) [cite: 461]
[cite_start]void tambahParameterized(int a, int b, int expect) { [cite: 462]
    [cite_start]Kalkulator kalk = new Kalkulator(a,b); [cite: 464]
    [cite_start]int actual = kalk.tambah(); [cite: 465]
    [cite_start]assertEquals(expect, actual); [cite: 466]
[cite_start]} [cite: 463]

```

5. Gunakan parameterized test dengan `MethodSource` pada method tambah. Buatlah terlebih dahulu method untuk mengirimkan stream parameter.



```java
[cite_start]private static Stream<Arguments> provideParameters() { [cite: 469]
    [cite_start]return Stream.of( [cite: 470]
        [cite_start]Arguments.of("2", "4", "6"), [cite: 471]
        [cite_start]Arguments.of("1", "2", "3"), [cite: 472, 473]
        [cite_start]Arguments.of("100", "500", "600") [cite: 473, 474]
    [cite_start]); [cite: 475]
[cite_start]} [cite: 476]

```

6. Buatlah test method dengan anotasi `MethodSource` dan mengambil nilai dari method `provideParameters`.



```java
[cite_start]@ParameterizedTest [cite: 479]
[cite_start]@MethodSource("provideParameters") [cite: 480]
[cite_start]void tambahparam(int a, int b, int expect) { [cite: 481]
    [cite_start]Kalkulator kalk = new Kalkulator(a,b); [cite: 483]
    [cite_start]int actual = kalk.tambah(); [cite: 484]
    [cite_start]assertEquals(expect, actual); [cite: 485]
[cite_start]} [cite: 482]

```

7. Jalankan test method yang telah dibuat dan perhatikan hasilnya.



4.4 Tugas 

Implementasikan parameterized test pada studi kasus Wallet.

1. Gunakan single-value parameter untuk menguji nominal cash valid (angka positif) dan nominal cash tidak valid (angka negatif).


2. Buatlah file csv untuk 2 kasus valid dan invalid data (withdraw cash). Gunakan `@CsvFileSource` untuk mengakses parameter dari file.


* 
**Contoh data valid:** 


* deposit, withdraw, expected Total 


* 10000, 0, 10000 


* 20000, 5000, 15000 


* 50000, 25000, 25000 


* 100000, 100000, 0 




* 
**Contoh data tidak valid:** Saat ada pengurangan nilai yang tidak valid atau saldo kurang maka fungsi withdraw akan melempar exception. Lakukan assertion untuk memastikan fungsi withdraw melempar exception yang sesuai.


* initial Deposit, withdraw, exception Type 


* 10000, 15000, Insufficient FundsException 


* 10000, 0, IllegalArgumentException 


* 10000, -1000, IllegalArgumentException 


* 0, 1000, Insufficient Funds Exception 






3. Pada studi kasus sebelumnya owner bertipe string. Pada soal ini, Anda diminta menaikkan level dengan membuat owner sebagai object class.



```java
[cite_start]public class Owner { [cite: 503]
    [cite_start]private final String id; [cite: 504]
    [cite_start]private final String name; [cite: 505]
    [cite_start]private final String email; [cite: 506]
    [cite_start]// constructor, getter setter [cite: 507]
[cite_start]} [cite: 508]

```

Kemudian tambahkan fungsi `setOwner(Owner owner)` pada wallet class. Buatkan pengujian untuk method tersebut. Gunakan `MethodSource` dan argument provider untuk membuat parameter object owner.