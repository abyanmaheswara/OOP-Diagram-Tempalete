# Dokumentasi Desain: Keputusan Validasi dan Penanganan Kesalahan

## 1. Pendahuluan

Dokumen ini menjelaskan keputusan desain yang diambil terkait validasi data dan penanganan kondisi kesalahan dalam implementasi model domain perpustakaan. Fokus utama adalah pada penggunaan `require()` untuk validasi invarian objek dan pemilihan antara mengembalikan nilai boolean atau melempar exception untuk skenario tertentu.

## 2. Keputusan Validasi Menggunakan `require()`

Dalam proyek ini, validasi properti objek (seperti `id`, `name`, `year`, `totalCount`, `staffCode`) dilakukan menggunakan fungsi `require()` yang tersedia di Kotlin. `require()` digunakan di dalam blok `init` konstruktor dan/atau setter properti.

**Alasan Pemilihan `require()`:**

*   **Invarian Objek:** `require()` sangat cocok untuk menegakkan invarian objek. Invarian adalah kondisi yang harus selalu benar untuk sebuah objek agar dianggap dalam keadaan valid. Jika sebuah objek dibuat atau dimodifikasi dengan data yang melanggar invarian ini, maka objek tersebut berada dalam keadaan yang tidak sah.
*   **Pencegahan Objek Tidak Valid:** Dengan melempar `IllegalArgumentException` saat kondisi `require()` tidak terpenuhi, kita mencegah pembuatan atau modifikasi objek menjadi keadaan yang tidak valid. Ini memastikan bahwa setiap instance objek yang berhasil dibuat atau dimodifikasi selalu sah.
*   **Kesalahan Pemrograman/Input:** Pelanggaran terhadap kondisi `require()` seringkali mengindikasikan kesalahan pada sisi pemanggil (misalnya, mencoba membuat `Person` dengan nama kosong) atau input data yang tidak sesuai dengan aturan bisnis dasar. Dalam kasus seperti ini, `IllegalArgumentException` adalah cara yang tepat untuk memberi tahu pemrogram tentang masalah tersebut.
*   **Keterbacaan Kode:** Penggunaan `require()` membuat validasi eksplisit dan mudah dibaca langsung di tempat properti didefinisikan atau diinisialisasi.

**Contoh Penggunaan:**

*   `Person`: `id` tidak boleh kosong, `name` harus memiliki panjang antara 2 hingga 100 karakter (setelah trim).
*   `Book`: `id`, `title`, `author` tidak boleh kosong; `year` harus dalam rentang [1400..2100]; `totalCount` harus non-negatif.
*   `Librarian`: `staffCode` tidak boleh kosong.

## 3. Pemilihan Boolean vs. Exception untuk `Loanable.loan()` dan `Book.returnOne()`

Keputusan antara mengembalikan nilai boolean (indikasi sukses/gagal) atau melempar exception (indikasi kesalahan) didasarkan pada sifat operasional dan ekspektasi dari setiap metode.

### 3.1. `Loanable.loan(to: Member): Boolean`

Metode `loan()` dirancang untuk mengembalikan `true` jika peminjaman berhasil (stok tersedia dan berkurang), dan `false` jika peminjaman gagal (stok habis).

**Alasan Mengembalikan Boolean:**

*   **Skenario Bisnis Normal:** Dalam sistem perpustakaan, upaya meminjam buku yang stoknya habis adalah skenario bisnis yang umum dan diharapkan. Ini bukan kondisi "kesalahan" yang luar biasa, melainkan hasil yang valid dari operasi tersebut.
*   **Penanganan yang Anggun:** Mengembalikan `false` memungkinkan kode pemanggil untuk menangani situasi "stok habis" dengan anggun, misalnya dengan menampilkan pesan kepada pengguna atau mencoba meminjam buku lain, tanpa perlu menangkap dan memproses exception.
*   **Tidak Melanggar Invarian:** Gagal meminjam karena stok habis tidak melanggar invarian objek `Book`. Objek `Book` tetap dalam keadaan valid; hanya saja operasi peminjaman tidak dapat diselesaikan.

### 3.2. `Book.returnOne()`

Metode `returnOne()` dirancang untuk menambah stok buku yang tersedia. Namun, jika metode ini dipanggil saat `availableCount` sudah sama dengan `totalCount` (yaitu, semua eksemplar sudah tersedia dan dikembalikan), maka akan melempar `IllegalArgumentException`.

**Alasan Melempar Exception untuk Over-Capacity:**

*   **Pelanggaran Invarian Kritis:** Kondisi "over-capacity" (mencoba mengembalikan buku saat stok sudah penuh) adalah pelanggaran langsung terhadap invarian `0 <= availableCount <= totalCount`. Jika operasi ini diizinkan, objek `Book` akan masuk ke keadaan tidak valid (`availableCount > totalCount`).
*   **Indikasi Kesalahan Logika/Pemrograman:** Mencoba mengembalikan buku yang sudah penuh stoknya biasanya mengindikasikan kesalahan logika dalam kode pemanggil atau alur kerja yang tidak benar. Ini bukan skenario bisnis yang diharapkan seperti "stok habis", melainkan situasi yang seharusnya tidak terjadi jika sistem beroperasi dengan benar.
*   **Memaksa Penanganan:** Melempar exception memaksa pemrogram untuk secara eksplisit menangani kondisi ini, yang penting karena ini menunjukkan potensi bug atau ketidaksesuaian dalam alur aplikasi. Ini mencegah objek berada dalam keadaan yang tidak konsisten.

Dengan demikian, pemilihan antara boolean dan exception didasarkan pada apakah kondisi tersebut merupakan bagian dari alur bisnis yang diharapkan dan dapat ditangani secara normal, atau apakah itu merupakan pelanggaran invarian atau kesalahan logika yang memerlukan intervensi lebih serius.
