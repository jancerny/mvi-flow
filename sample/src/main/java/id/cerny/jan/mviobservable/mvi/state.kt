package id.cerny.jan.mviobservable.mvi

import id.cerny.jan.mviobservable.data.RequestStatus

data class AppState(
    val counterRequestStatus: RequestStatus<Int> = RequestStatus.None,
    val counterValue: Int = 0
)