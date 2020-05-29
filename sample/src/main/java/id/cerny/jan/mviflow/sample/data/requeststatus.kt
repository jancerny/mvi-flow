package id.cerny.jan.mviflow.sample.data

sealed class RequestStatus<out T> {
    val isSuccess: Boolean get() = this is Success
    val isFail: Boolean get() = this is Fail
    val isCompleted get() = isSuccess || isFail
    val isProgress get() = this == Progress
    val isStarted get() = isCompleted || isProgress
    val isNone get() = this == None
    val isNoneOrFailed get() = isNone || isFail
    val isProgressOrSuccess get() = isProgress || isSuccess

    object None : RequestStatus<Nothing>()
    object Progress : RequestStatus<Nothing>()
    class Success<T>(val result: T) : RequestStatus<T>()
    class Fail(val error: Throwable) : RequestStatus<Nothing>()
}