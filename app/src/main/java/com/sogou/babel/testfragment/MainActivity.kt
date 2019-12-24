package com.sogou.babel.testfragment

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity(), OverlayFragment.OnFragmentInteractionListener,
    MainFragment.OnFragmentInteractionListener {

    private var isAddBack: Boolean = false
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

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "isAddBackStack: $isAddBack", Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        mMainContainer = findViewById<View>(R.id.main_container_layout);
        mOverlayContainer = findViewById<View>(R.id.overlay_container_layout);

    }

    fun addMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container_layout, mainFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }


    fun removeMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(mainFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }


    fun replaceMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container_layout, mainReplaceFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun addOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.add(R.id.overlay_container_layout, overlayFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun removeOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(overlayFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun hideOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.hide(overlayFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun showOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.show(overlayFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    fun switchAddBack(view: View) {
        isAddBack = !isAddBack
        Toast.makeText(this, "isAddBackStack: $isAddBack", Toast.LENGTH_LONG).show()
    }

}
