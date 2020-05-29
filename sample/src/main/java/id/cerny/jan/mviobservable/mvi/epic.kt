package id.cerny.jan.mviobservable.mvi

import android.util.Log
import id.cerny.jan.mviobservable.Dependencies
import id.cerny.jan.mviobservable.Epic
import id.cerny.jan.mviobservable.data.RequestStatus
import id.cerny.jan.mviobservable.data.incrementCounter
import kotlinx.coroutines.flow.*

typealias AppEpic = Epic<AppState, Action, Dependencies>

val actionsLoggingEpic: AppEpic = { actions, _, _ ->
    actions.onEach { action ->
        Log.d("Action", action.toString())
    }.filter { false }
}

val stateLoggingEpic: AppEpic = { _, state, _ ->
    state.onEach { action ->
        Log.d("State", action.toString())
    }.filter { false }.filterIsInstance()
}

val incrementCounterEpic: AppEpic = { actions, state, _ ->
    actions.filterIsInstance<Action.IncrementCounter>()
        .flatMapLatest {
            state.take(1)
                .map { stateInstance ->
                    Action.CounterRequestFinished(
                        RequestStatus.Success(
                            incrementCounter(
                                stateInstance.counterValue
                            )
                        )
                    )
                }
        }
}