package com.mvvm

fun interface ScreenMapper<in State, out Model> {
    fun map(state: State): Model
}