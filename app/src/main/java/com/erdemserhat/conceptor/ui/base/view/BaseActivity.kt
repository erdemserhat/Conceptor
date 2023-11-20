package com.erdemserhat.conceptor.ui.base.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.erdemserhat.conceptor.R
import com.erdemserhat.conceptor.ui.base.presenter.BaseMVPPresenter
import com.erdemserhat.conceptor.ui.importer.view.ImporterActivity
import com.erdemserhat.conceptor.ui.main.view.MainActivity

abstract class BaseActivity<VB : ViewBinding> :AppCompatActivity(), BaseMVPView {
    lateinit var binding:VB
    private var presenter:BaseMVPPresenter<BaseMVPView> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View binding
        binding = getViewBinding()
        val view = binding.root
        setContentView(view)
        //presenter?.attachView(this)


    }

    /**
     * Creates the options menu for the activity.
     *
     * @param menu The Menu object defining the menu to be created.
     * @return A Boolean value. Returns true if the menu creation is successful.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu    ,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.optionsMenuAddNewConcept->{
                val intent: Intent = Intent(this,ImporterActivity::class.java)
                startActivity(intent)
                return true

            }
        }

        return super.onOptionsItemSelected(item)
    }

    abstract fun getViewBinding(): VB

    override fun getViewContext(): Context {
        return this
    }
}