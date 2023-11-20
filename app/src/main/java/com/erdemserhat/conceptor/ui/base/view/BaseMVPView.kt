package com.erdemserhat.conceptor.ui.base.view

import android.content.Context

interface BaseMVPView<T> {

    fun getViewContext(): T
}