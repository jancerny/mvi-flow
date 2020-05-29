package id.cerny.jan.mviflow

import kotlinx.coroutines.flow.Flow

typealias Epic<State, Action, Dependencies> = suspend (Flow<Action>, Flow<State>, Dependencies) -> Flow<Action>
