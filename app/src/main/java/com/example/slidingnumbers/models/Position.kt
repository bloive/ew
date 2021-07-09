package com.example.slidingnumbers.models

class Position (var x: Int, var y: Int) {
    var brick: Brick? = null
    constructor(x: Int, y: Int, number:Int) : this(x, y) {
        brick = Brick(number)
    }
    fun isEmpty(): Boolean {
        return brick == null
    }
}