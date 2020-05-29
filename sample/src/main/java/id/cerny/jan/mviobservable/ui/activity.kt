package id.cerny.jan.mviobservable.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.cerny.jan.mviobservable.R
import id.cerny.jan.mviobservable.data.RequestStatus
import id.cerny.jan.mviobservable.mvi.Action
import id.cerny.jan.mviobservable.mvi.AppState
import id.cerny.jan.mviobservable.mvi.store
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