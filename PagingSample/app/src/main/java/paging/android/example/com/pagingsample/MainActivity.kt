package paging.android.example.com.pagingsample

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivity() {
    val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, CheeseViewModel.Factory(application)).get(CheeseViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = CheeseAdapter()
        cheeseList.adapter = adapter
        viewModel.allCheeses.observe(this, Observer {
            adapter.setList(it)
        })
        object : ItemTouchHelper(object : Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                (viewHolder as? CheeseViewHolder)?.cheese?.let {
                    viewModel.remove(it)
                }
            }
        }){}.attachToRecyclerView(cheeseList)
    }
}
