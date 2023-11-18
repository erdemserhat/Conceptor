package com.erdemserhat.conceptor.ui.detailer.presenter
import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.detailer.view.DetailerMVPView
import com.erdemserhat.conceptor.ui.main.adapters.AdapterOperations

class DetailerPresenter:DetailerMVPPresenter {
    private lateinit var view:DetailerMVPView
    override fun handleDeleteDatabaseOperation(post: Posts) {
        val database:AppDatabase = AppDatabase(view.getViewContext())
        database.deletePost(post)
        database.readPosts()


    }

    override fun attachView(view: DetailerMVPView) {
        this.view = view

    }

    override fun detachView() {

    }
}