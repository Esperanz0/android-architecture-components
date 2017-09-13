package paging.android.example.com.pagingsample

import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList
import android.arch.paging.TiledDataSource

interface CheeseRepository {
    fun loadCheeseList() : LivePagedListProvider<Int, Cheese>
}
