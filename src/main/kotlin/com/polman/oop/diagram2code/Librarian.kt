package com.polman.oop.diagram2code

class Librarian(
    id: String,
    name: String,
    val staffCode: String
) : Person(id, name) {

    init {
        require(staffCode.isNotBlank()) { "staffCode tidak boleh kosong" }
    }

    override fun showInfo(): String {
        return "Librarian[id=$id, name=$name, staffCode=$staffCode]"
    }

    override fun calculateFee(daysLate: Int): Double {
        // Librarian tidak kena denda
        return 0.0
    }
}
