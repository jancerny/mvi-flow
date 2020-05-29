package id.cerny.jan.mviflow.sample.mvi

import id.cerny.jan.mviflow.sample.data.RequestStatus

data class AppState(
    val counterRequestStatus: RequestStatus<Int> = RequestStatus.None,
    val counterValue: Int = 0
)