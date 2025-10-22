package com.polman.oop.diagram2code

class Member(
    id: String,
    name: String,
    val level: MemberLevel
) : Person(id, name) {

    override fun showInfo(): String {
        return "Member[id=$id, name=$name, level=$level]"
    }

    override fun calculateFee(daysLate: Int): Double {
        return when (level) {
            MemberLevel.REGULAR -> daysLate * 2000.0
            MemberLevel.GOLD -> daysLate * 1500.0
            MemberLevel.PLATINUM -> daysLate * 1000.0
        }
    }
}
