package com.example.ritschedulingapptry2

import java.time.LocalDateTime

class Event(
    private val name: String,
    private val description: String,
    private val time: LocalDateTime
) {

    fun getName(): String { return this.name }
    fun getDescription(): String { return this.description }
    fun getTime(): LocalDateTime { return this.time }

    override fun toString(): String { return this.name + " " + description + " " + time.toString() }
}