package com.hxt.mvvmdemo

data class TestData(
    var user: User? = null
) {
    companion object {
        fun getInstance(): TestData {
            return SingleInstance.instance
        }
    }

    private object SingleInstance {
        val instance = translateTestData()
    }
}

data class User(
    var name: String = "",
    var age: String = ""
)