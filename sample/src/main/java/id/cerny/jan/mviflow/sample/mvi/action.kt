package id.cerny.jan.mviflow.sample.mvi

import id.cerny.jan.mviflow.sample.data.RequestStatus

sealed class Action {
    object IncrementCounter : Action()
    data class CounterRequestFinished(val resultStatus: RequestStatus<Int>) : Action()
}