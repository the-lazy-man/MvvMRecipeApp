package com.example.testapp

data class DataItem(
    val type : Int,
    val title: String? = null,
    val subtitle: String? = null
)   {
    companion object {
        const val TYPE_TITLE: Int = 0
        const val TYPE_SUBTITLE: Int = 1
    }
}
