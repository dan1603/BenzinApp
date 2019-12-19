package com.shifthackz.benzinapp.presentation.base

import android.app.ActionBar
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.shifthackz.benzinapp.App
import com.shifthackz.benzinapp.di.component.ViewModelComponent
import com.shifthackz.benzinapp.utils.hideKeyboardEx
import com.shifthackz.benzinapp.utils.showSnack
import com.shifthackz.benzinapp.utils.showToast

abstract class BaseFragment : Fragment() {
    private val appBar: ActionBar? = activity?.actionBar
    protected fun disableHomeAsUp() = appBar?.setDisplayHomeAsUpEnabled(false)

    protected fun initializeNavigationBar(title: String, showBackButton: Boolean, @DrawableRes resId: Int) {
        appBar?.apply {
            this.setDisplayHomeAsUpEnabled(showBackButton)
            this.setHomeAsUpIndicator(resId)
            this.elevation = 4f
        }
    }

    protected abstract fun injectDependency(component: ViewModelComponent)

    private fun createDaggerDependencies() {
        injectDependency((requireActivity().application as App).getViewModelComponent())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> fragmentManager?.popBackStackImmediate()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayout(), container, false)
        return view
    }

    protected fun showToast(text: String) = activity?.showToast(text)
    protected fun showSnack(text: String) = activity?.showSnack(text)
    protected fun hideKeyboard() = activity?.hideKeyboardEx()
}