package com.hxt.mvvmdemo

data class Dictionary(
    var expense: Expense? = null,
    var travel: Travel? = null
)

data class Expense(
    var title: String = ""
)

data class Travel(
    var from: String = "",
    var to: String = ""
)