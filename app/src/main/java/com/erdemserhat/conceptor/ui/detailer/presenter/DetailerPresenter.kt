package com.erdemserhat.conceptor.ui.detailer.presenter
import android.content.Intent
import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.detailer.view.DetailerMVPView
import com.erdemserhat.conceptor.ui.main.adapters.AdapterOperations
import com.erdemserhat.conceptor.ui.main.view.MainActivity

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

    override fun clearActivitiesAndGoHome() {
        val intent:Intent = Intent(view.getViewContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        view.getViewContext().startActivity(intent)
    }
}