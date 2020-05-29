package id.cerny.jan.mviflow.sample.data

import kotlinx.coroutines.delay

suspend fun incrementCounter(counterValue: Int): Int {
    delay(3_000L)

    return counterValue + 1
}