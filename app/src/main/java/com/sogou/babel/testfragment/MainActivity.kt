package com.sogou.babel.testfragment

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(), OverlayFragment.OnFragmentInteractionListener,
    MainFragment.OnFragmentInteractionListener {

    private lateinit var mMainContainer: View
    private lateinit var mOverlayContainer: View

    private val mainFragment: MainFragment = MainFragment.newInstance("", "")
    private val mainReplaceFragment: MainReplaceFragment = MainReplaceFragment.newInstance("", "")
    private var overlayFragment: OverlayFragment = OverlayFragment.newInstance("", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        mMainContainer = findViewById<View>(R.id.main_container_layout);
        mOverlayContainer = findViewById<View>(R.id.overlay_container_layout);

    }

    fun addMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container_layout, mainFragment)
        transaction.commit()
    }


    fun removeMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(mainFragment)
        transaction.commit()
    }


    fun replaceMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container_layout, mainReplaceFragment)
        transaction.commit()
    }

    fun addOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.add(R.id.overlay_container_layout, overlayFragment)
        transaction.commit()
    }

    fun removeOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(overlayFragment)
        transaction.commit()
    }

    fun hideOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.hide(overlayFragment)
        transaction.commit()
    }

    fun showOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.show(overlayFragment)
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

}
