package id.cerny.jan.mviobservable.mvi

import id.cerny.jan.mviobservable.Dependencies
import id.cerny.jan.mviobservable.Store

val store = Store(
    initialState = AppState(),
    dependencies = Dependencies,
    rootReducer = ::rootReducer,
    epics = arrayOf(actionsLoggingEpic, stateLoggingEpic, incrementCounterEpic)
)