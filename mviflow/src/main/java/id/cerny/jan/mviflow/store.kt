package id.cerny.jan.mviflow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
@FlowPreview
class Store<State, Action, Dependencies>(
    initialState: State,
    private val scope: CoroutineScope = GlobalScope,
    private val epics: Array<Epic<State, Action, Dependencies>> = emptyArray(),
    private val dependencies: Dependencies,
    private val rootReducer: (State, Action) -> State
) {
    private val stateFlow: MutableStateFlow<State> = MutableStateFlow(initialState)
    private val actionsChannel: BroadcastChannel<Action> = BroadcastChannel(BUFFERED)

    private val actions: Flow<Action> get() = actionsChannel.asFlow()

    val state: StateFlow<State> get() = stateFlow

    init {
        scope.launch {
            actions.scan(initialState) { state, action ->
                rootReducer(state, action)
            }.collect { newState ->
                stateFlow.value = newState
            }
        }

        epics.forEach(this::addEpic)
    }

    fun dispatch(action: Action) {
        scope.launch {
            actionsChannel.send(action)
        }
    }

    fun addEpic(epic: Epic<State, Action, Dependencies>) {
        scope.launch {
            epic(actions, state, dependencies).collect { action ->
                dispatch(action)
            }
        }
    }
}
