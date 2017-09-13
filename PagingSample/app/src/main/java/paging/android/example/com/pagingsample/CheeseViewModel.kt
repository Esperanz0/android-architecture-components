package paging.android.example.com.pagingsample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import kotlin.concurrent.thread

class CheeseViewModel(app : Application, val startPos: Int = 0) : AndroidViewModel(app) {
    val dao = CheeseDb.get(app).cheeseDao()
    val allCheeses = dao
            .allCheesesByName().create(startPos, 50)

    fun insert(text : String) {
        thread {
            dao.insert(Cheese(id = 0, name = text))
        }
    }

    fun remove(cheese : Cheese) {
        thread {
            dao.delete(cheese)
        }
    }

    class Factory(val app : Application, val startPos : Int = 0) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CheeseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CheeseViewModel(app = app, startPos = startPos) as T
            }
            throw IllegalArgumentException("can only create cheese VMs")
        }
    }
}