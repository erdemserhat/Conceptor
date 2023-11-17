package com.erdemserhat.conceptor.ui.detailer.presenter
import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.main.adapters.AdapterOperations

class DetailerPresenter:DetailerContract.Presenter {
    private lateinit var view:DetailerContract.View
    override fun handleDeleteDatabaseOperation(post: Posts) {
        val database:AppDatabase = AppDatabase(view.getViewContext())
        database.deletePost(post)
        database.readPosts()
       // AdapterOperations.notifyDataSet()


    }

    override fun attachView(view: DetailerContract.View) {
        this.view = view

    }

    override fun detachView() {

    }
}