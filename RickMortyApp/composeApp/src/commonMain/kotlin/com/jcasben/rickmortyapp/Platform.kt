package com.jcasben.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform