package id.cerny.jan.mviobservable.mvi

import id.cerny.jan.mviobservable.data.RequestStatus

fun rootReducer(state: AppState, action: Action): AppState = when (action) {
    Action.IncrementCounter -> state.copy(counterRequestStatus = RequestStatus.Progress)
    is Action.CounterRequestFinished -> {
        state.copy(
            counterRequestStatus = action.resultStatus,
            counterValue = if (action.resultStatus is RequestStatus.Success) {
                action.resultStatus.result
            } else {
                state.counterValue
            }
        )
    }
    else -> state
}