package com.markdrago.boatzee.domain

data class ScoreValue(val score: Int, val frozen: Boolean) {
    constructor(): this(0, false)
    constructor(score: Int): this(score, false)
}
