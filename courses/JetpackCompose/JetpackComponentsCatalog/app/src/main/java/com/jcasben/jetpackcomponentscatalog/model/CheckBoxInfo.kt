package com.jcasben.jetpackcomponentscatalog.model

data class CheckBoxInfo(
    val title: String,
    var selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
