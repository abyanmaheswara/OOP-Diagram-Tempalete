package com.polman.oop.diagram2code

class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val totalCount: Int
) : Loanable {

    private var availableCount: Int = totalCount

    init {
        require(id.isNotBlank()) { "id tidak boleh kosong" }
        require(title.isNotBlank()) { "title tidak boleh kosong" }
        require(author.isNotBlank()) { "author tidak boleh kosong" }
        require(year in 1400..2100) { "year harus antara 1400..2100" }
        require(totalCount >= 0) { "totalCount harus >= 0" }
    }

    fun inStock(): Boolean {
        return availableCount > 0
    }

    override fun loan(to: Member): Boolean {
        return if (inStock()) {
            availableCount--
            true
        } else {
            false
        }
    }

    fun returnOne() {
        if (availableCount >= totalCount) {
            throw IllegalArgumentException("Stok sudah penuh, tidak bisa dikembalikan lagi")
        }
        availableCount++
    }

    fun available(): Int = availableCount

    fun info(): String {
        return "Book[id=$id, title=$title, author=$author, year=$year, stock=$availableCount/$totalCount]"
    }
}
