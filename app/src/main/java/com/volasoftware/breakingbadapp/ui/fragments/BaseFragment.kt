package com.volasoftware.breakingbadapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, L> : Fragment() {

    protected lateinit var binding: T
    protected var activityListener: L? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    protected abstract fun onFragmentViewCreated(view: View, savedInstanceState: Bundle?)

    protected abstract fun getLayoutRes(): Int

    protected abstract fun setupToolbar()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        try {
            activityListener = context as L?
        } catch (e: ClassCastException) {
            throw RuntimeException("$context must implement the fragment listener.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }
}