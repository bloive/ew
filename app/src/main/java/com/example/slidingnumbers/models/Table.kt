//package com.example.slidingnumbers.models
//
//import android.text.TextUtils.indexOf
//
//class Table(size: Int) {
//    private val size: Int
//    private var numOfMoves: Int
//    private val coordinates: MutableList<Coordinates>
//    private val listeners: MutableList<TableChangeListener>
//
//    fun updateTable() {
//        numOfMoves = 0
//        for (i in 0 until size * size) {
//            swapBricks()
//        }
//        do {
//            swapBricks()
//        } while (!solvable() || solved)
//    }
//
//    //swap two bricks
//    private fun swapBricks() {
//        val p1: Coordinates? = at(randoms.nextInt(size) + 1, randoms.nextInt(size) + 1)
//        val p2: Coordinates? = at(randoms.nextInt(size) + 1, randoms.nextInt(size) + 1)
//
//        if (p1 != p2) {
//            val brick: Brick? = p1?.brick
//            p1!!.brick = p2?.brick
//            p2!!.brick = brick
//        }
//    }
//
//    private fun solvable(): Boolean {
//        var i = 0
//        for (p: Coordinates in coordinates) {
//            val pt: Brick? = p.brick
//            for (q: Coordinates in coordinates) {
//                var qt: Brick? = q.brick
//                if (p != q && pt != null && qt != null && indexOf(p) < indexOf(q) && pt.number() > qt.number()) {
//                    i++
//                }
//            }
//        }
//    }
//
//    private fun indexOf(p: Coordinates)
//
//}