### User Story (saya sesuaikan dengan UAT):

> Sebagai **admin bengkel**, saya ingin **mengelola data karyawan dan pengaturan bengkel** sehingga saya dapat mengatur operasional bengkel dengan benar dan aman.

### Acceptance Criteria (berdasarkan 3 test item):

1. Admin dapat mengubah nama bengkel dan perubahan harus tampil di header serta laporan.
2. Admin dapat menambahkan mekanik baru dan nama mekanik harus muncul di dropdown antrean.
3. Akun dengan role **Kasir (Non-Admin)** tidak boleh mengakses menu Laporan dan fitur Hapus Data.

---

### **Tabel Hasil Perancangan Kasus Uji**

| Fitur                 | ID    | Description                                     | Tipe    | Pre-Condition                                                 | Test Step                                                                                                                                                           | Test Data                                                               | Expected Result                                                                                                                             | Actual Result | Status |
| :-------------------- | :---- | :---------------------------------------------- | :------ | :------------------------------------------------------------ | :------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :---------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------ | :------------ | :----- |
| Pengaturan & Karyawan | TC-01 | Admin mengubah nama bengkel dengan data valid   | Positif | Menu Pengaturan terbuka                                       | 1. Admin klik opsi "Info Bengkel"<br>2. Admin ubah nama bengkel<br>3. Admin klik "Simpan"<br>4. Admin cek header halaman<br>5. Admin buka menu Laporan              | Nama lama: "Bengkel A"<br>Nama baru: "Bengkel Maju Jaya"                | 1. Notifikasi sukses muncul<br>2. Header berubah menjadi "Bengkel Maju Jaya"<br>3. Nama bengkel di laporan juga berubah                     |               | Pass   |
| Pengaturan & Karyawan | TC-02 | Admin menambah mekanik baru dengan data lengkap | Positif | Menu Karyawan terbuka                                         | 1. Admin klik "Tambah Mekanik"<br>2. Admin isi semua data mekanik<br>3. Admin klik "Simpan"<br>4. Admin buka halaman Antrean<br>5. Admin cek dropdown pilih mekanik | Nama: Budi Santoso<br>No Telp: 08123456789<br>Alamat: Jl. Merdeka No.10 | 1. Notifikasi sukses muncul<br>2. Data mekanik tersimpan<br>3. Nama "Budi Santoso" muncul di dropdown Antrean                               |               | Pass   |
| Pengaturan & Karyawan | TC-03 | Kasir (Non-Admin) mencoba akses menu Laporan    | Negatif | Akun Kasir sudah dibuat (username: kasir1, password: test123) | 1. Login dengan akun Kasir<br>2. Klik menu Laporan<br>3. Coba klik tombol "Hapus Data"                                                                              | Role: Kasir (Non-Admin)                                                 | 1. Login berhasil<br>2. Menu Laporan terbuka tapi tombol "Hapus Data" tidak muncul atau tidak aktif<br>3. Muncul pesan "Akses ditolak"      |               | Fail   |
| Pengaturan & Karyawan | TC-04 | Admin menambah mekanik dengan nama kosong       | Negatif | Menu Karyawan terbuka                                         | 1. Admin klik "Tambah Mekanik"<br>2. Admin biarkan field nama kosong<br>3. Admin isi data lain dengan benar<br>4. Admin klik "Simpan"                               | Nama: (kosong)<br>No Telp: 08123456789<br>Alamat: Jl. Merdeka No.10     | 1. Sistem menampilkan pesan error "Nama mekanik wajib diisi"<br>2. Data tidak tersimpan<br>3. Mekanik baru tidak muncul di dropdown Antrean |               | Pass   |

---

## 🐞 **5.3.2 Membuat Bug Report**

Berdasarkan **TC-03** yang saya prediksi akan gagal (karena biasanya sistem belum membatasi role dengan benar), berikut bug report-nya:

| No    | Title                  | Kasir (Non-Admin) dapat mengakses menu Laporan dan fitur Hapus Data                                                                                                                                                |
| :---- | :--------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **1** | **Description**        | **URL:** test.bengkel.com<br>User dengan role **Kasir (Non-Admin)** berhasil mengakses menu **Laporan** dan dapat menekan tombol **Hapus Data** yang seharusnya hanya bisa diakses oleh Admin.                     |
|       | **Platform**           | Windows 11 Pro, Chrome v.122, Backend: Laravel 10                                                                                                                                                                  |
|       | **Steps to Reproduce** | 1. Login ke sistem menggunakan akun Kasir (username: kasir1, password: test123)<br>2. Buka menu **Laporan**<br>3. Cari tombol **Hapus Data** di halaman Laporan<br>4. Klik tombol tersebut                         |
|       | **Expectation**        | 1. Menu Laporan tidak bisa diakses oleh Kasir atau hanya bisa dilihat tanpa aksi hapus<br>2. Tombol **Hapus Data** tidak muncul atau dalam keadaan non-aktif<br>3. Muncul pesan error "Akses ditolak" jika dipaksa |
|       | **Actual Result**      | 1. Kasir berhasil masuk ke halaman Laporan<br>2. Tombol **Hapus Data** muncul dan dapat diklik<br>3. Data laporan berhasil dihapus tanpa konfirmasi role                                                           |
|       | **Priority, Severity** | High, Critical                                                                                                                                                                                                     |
|       | **Attachment**         | 📷 `bug_screenshot_kasir_hapus_data.png`<br>📹 `video_reproduce_bug.mp4`                                                                                                                                           |

---
