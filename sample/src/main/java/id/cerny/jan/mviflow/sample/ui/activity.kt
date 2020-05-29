package id.cerny.jan.mviflow.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import id.cerny.jan.mviflow.sample.R
import id.cerny.jan.mviflow.sample.data.RequestStatus
import id.cerny.jan.mviflow.sample.mvi.Action
import id.cerny.jan.mviflow.sample.mvi.AppState
import id.cerny.jan.mviflow.sample.mvi.store
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            store.state.collect { state ->
                render(state)
            }
        }

        fab.setOnClickListener {
            store.dispatch(Action.IncrementCounter)
        }
    }

    private fun render(state: AppState) {
        counter.text = "${state.counterValue}"

        when (state.counterRequestStatus) {
            RequestStatus.Progress -> {
                counter.isVisible = false
                fab.isEnabled = false
                progress.isVisible = true
            }
            else -> {
                counter.isVisible = true
                fab.isEnabled = true
                progress.isVisible = false
            }
        }
    }
}