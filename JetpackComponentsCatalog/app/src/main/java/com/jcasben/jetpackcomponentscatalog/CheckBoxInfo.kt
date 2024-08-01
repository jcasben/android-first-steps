package com.jcasben.jetpackcomponentscatalog

data class CheckBoxInfo(
    val title: String,
    var selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
