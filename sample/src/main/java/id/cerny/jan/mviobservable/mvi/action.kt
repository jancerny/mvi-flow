package id.cerny.jan.mviobservable.mvi

import id.cerny.jan.mviobservable.data.RequestStatus

sealed class Action {
    object IncrementCounter : Action()
    data class CounterRequestFinished(val resultStatus: RequestStatus<Int>) : Action()
}