# OOP-Diagram-Template

Proyek ini adalah template untuk diagram Object-Oriented Programming (OOP), diimplementasikan dalam Kotlin.

## Memulai

Instruksi ini akan membantu Anda mendapatkan salinan proyek yang berjalan di mesin lokal Anda untuk tujuan pengembangan dan pengujian.

### Prasyarat

Hal-hal yang perlu Anda instal untuk perangkat lunak dan cara menginstalnya:

*   Java Development Kit (JDK) 11 atau lebih tinggi
*   Gradle (biasanya dibundel dengan proyek)

### Membangun dan Menjalankan

Untuk membangun proyek, navigasikan ke direktori root proyek dan jalankan:

```bash
./gradlew build
```

Untuk menjalankan aplikasi utama, gunakan:

```bash
./gradlew run
```

Untuk menjalankan tes, gunakan:

```bash
./gradlew test
```

### Contoh Output

Berikut adalah contoh output yang diharapkan saat menjalankan perintah di atas:

#### Build

```
> Task :compileKotlin
> Task :processResources NO-SOURCE
> Task :classes
> Task :jar
> Task :assemble
> Task :compileTestKotlin
> Task :processTestResources NO-SOURCE
> Task :testClasses
> Task :test

BUILD SUCCESSFUL in Xs
```

#### Run

```
> Task :run
// Output of your application will appear here
```

#### Test

```
> Task :test

BUILD SUCCESSFUL in Xs
```
