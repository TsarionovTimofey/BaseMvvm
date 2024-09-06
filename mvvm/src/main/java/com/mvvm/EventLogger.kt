package com.mvvm

fun interface EventLogger<in Event> {
    fun log(event: Event)
}