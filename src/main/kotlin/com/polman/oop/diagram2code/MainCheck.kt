package com.polman.oop.diagram2code

fun main() {
    println("=== DEMO PROGRAM PERPUSTAKAAN ===")

    // Studi Kasus 1: Registrasi & Informasi Dasar
    println("\n// --- Studi Kasus 1: Registrasi & Informasi Dasar ---")
    println("Informasi Person:")

    val m1 = Member("M001", "Ani", MemberLevel.REGULAR)
    val m2 = Member("M002", "Budi", MemberLevel.PLATINUM)
    val lib = Librarian("L001", "Sari", "LIB-777")

    println(m1.showInfo())
    println(m2.showInfo())
    println(lib.showInfo())

    m1.name = "Ani Putri"
    println("Nama m1 setelah update: ${m1.name}")

    // Studi Kasus 2: Denda Polimorfik
    println("\n// --- Studi Kasus 2: Denda Polimorfik ---")
    println("Kalkulasi Denda (daysLate = 3):")
    println("Fee REGULAR (3 hari): ${m1.calculateFee(3)}")
    println("Fee PLATINUM (3 hari): ${m2.calculateFee(3)}")
    println("Fee Librarian (3 hari): ${lib.calculateFee(3)}")

    // Studi Kasus 3: Peminjaman Buku
    println("\n// --- Studi Kasus 3: Peminjaman Buku ---")
    println("Informasi Buku:")
    val b1 = Book("B001", "Clean Code", "Robert C. Martin", 2008, 2)
    println(b1.info())
    println("Status Stok Awal:")
    println("inStock() = ${b1.inStock()} -> stok=${b1.available()}")

    println("\nSkenario Peminjaman:")
    println("loan(m1) = ${b1.loan(m1)} -> stok=${b1.available()}")
    println("loan(m2) = ${b1.loan(m2)} -> stok=${b1.available()}")
    println("loan(m2) (habis) = ${b1.loan(m2)} -> stok=${b1.available()}")

    // Studi Kasus 4: Pengembalian & Over-Capacity
    println("\n// --- Studi Kasus 4: Pengembalian & Over-Capacity ---")
    println("Skenario Pengembalian:")
    b1.returnOne()
    println("Setelah returnOne -> stok=${b1.available()}")

    println("\nSkenario Over-Capacity (Pengembalian Berlebih):")
    try {
        b1.returnOne()
        b1.returnOne() // Harus error
    } catch (e: IllegalArgumentException) {
        println("OK: exception over-capacity -> ${e.message}")
    }
    println("Stok akhir setelah percobaan over-capacity: ${b1.available()}")

    // Studi Kasus 5: Laporan Ringkas & Konsistensi
    println("\n// --- Studi Kasus 5: Laporan Ringkas & Konsistensi ---")
    println("=== LAPORAN PERSONS ===")
    println(m1.showInfo())
    println(m2.showInfo())
    println(lib.showInfo())
    println("=== LAPORAN BOOKS ===")
    println(b1.info())
}
