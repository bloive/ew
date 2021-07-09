package com.example.slidingnumbers.models

class Brick (private var number: Int? = null){
    fun number(): Int {
        return number!!
    }
}