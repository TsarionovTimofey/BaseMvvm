package com.mvvm

fun interface Reducer<State, in Result> {
    fun State.reduce(result: Result): State
}