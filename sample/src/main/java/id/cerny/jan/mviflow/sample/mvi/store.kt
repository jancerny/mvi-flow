package id.cerny.jan.mviflow.sample.mvi

import id.cerny.jan.mviflow.sample.Dependencies
import id.cerny.jan.mviflow.Store

val store = Store(
    initialState = AppState(),
    dependencies = Dependencies,
    rootReducer = ::rootReducer,
    epics = arrayOf(actionsLoggingEpic, stateLoggingEpic, incrementCounterEpic)
)