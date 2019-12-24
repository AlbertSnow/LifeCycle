package com.sogou.babel.testfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun log(msg: String) {
        com.sogou.babel.testfragment.util.log("${javaClass.simpleName}: $msg")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        log("onAttach ")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate ")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView ")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        log("onActivityCreated ")

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        log("onStart ")
    }

    override fun onResume() {
        super.onResume()
        log("onResume ")

    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        log("onHiddenChanged: $hidden ")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        log("onViewStateRestored")

    }

    override fun onPause() {
        super.onPause()
        log("onPause ")

    }

    override fun onStop() {
        super.onStop()
        log("onStop ")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState ")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView ")

    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy ")

    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach ")
    }


}