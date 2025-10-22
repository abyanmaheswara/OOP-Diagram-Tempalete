package com.polman.oop.diagram2code

abstract class Person(
    val id: String,
    name: String
) {
    var name: String = name
        set(value) {
            validateName(value)
            field = value.trim()
        }

    init {
        require(id.isNotBlank()) { "id tidak boleh kosong" }
        validateName(name)
    }

    protected fun validateName(n: String) {
        require(n.isNotBlank()) { "name tidak boleh kosong" }
        require(n.trim().length in 2..100) { "name harus 2..100 karakter" }
    }

    abstract fun showInfo(): String
    abstract fun calculateFee(daysLate: Int): Double
}
