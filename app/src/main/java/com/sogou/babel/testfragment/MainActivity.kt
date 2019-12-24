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
    private var overlayFragment: OverlayFragment = OverlayFragment.newInstance("", "")

    enum class FragmentStatus {
        NO_MAIN, NO_OVERLAY, HAVE_OVERLAY
    }

    private var status: FragmentStatus = FragmentStatus.NO_MAIN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        mMainContainer = findViewById<View>(R.id.main_container_layout);
        mOverlayContainer = findViewById<View>(R.id.overlay_container_layout);

        findViewById<View>(R.id.change_overlay_btn).setOnClickListener {
            onFragmentOperate()
        }
    }

    private fun onFragmentOperate() {

        when (status) {
            FragmentStatus.NO_MAIN -> {
                status = FragmentStatus.NO_OVERLAY
                showMain()
            }
            FragmentStatus.NO_OVERLAY -> {
                status = FragmentStatus.HAVE_OVERLAY
                showOverlay()
            }
            FragmentStatus.HAVE_OVERLAY -> {
                status = FragmentStatus.NO_OVERLAY
                hideOverlay()
            }
        }
    }

    private fun showMain() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container_layout, mainFragment)
        transaction.commit()
    }

    private fun showOverlay() {

        val transaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.add(R.id.overlay_container_layout, overlayFragment)
        transaction.commit()
    }

    private fun hideOverlay() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(overlayFragment)
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
    }


}
