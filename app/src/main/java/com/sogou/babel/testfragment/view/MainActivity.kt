package com.sogou.babel.testfragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.sogou.babel.testfragment.databinding.ActivityMainBinding
import com.sogou.babel.testfragment.viewmodel.FragmentSetViewModel


class MainActivity : AppCompatActivity(), OverlayFragment.OnFragmentInteractionListener,
    MainFragment.OnFragmentInteractionListener {

    private var isAddBack: Boolean = false
    private var isAddBackAnimation: Boolean = false
    private lateinit var mMainContainer: View
    private lateinit var mOverlayContainer: View

    private val mainFragment: MainFragment = MainFragment.newInstance("", "")
    private val mainReplaceFragment: MainReplaceFragment = MainReplaceFragment.newInstance("", "")
    private var overlayFragment: OverlayFragment = OverlayFragment.newInstance("", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val model = ViewModelProviders.of(this)[FragmentSetViewModel::class.java]
        activityMainBinding.viewModel = model
        activityMainBinding.executePendingBindings()
        initView()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "isAddBackStack: $isAddBack", Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        mMainContainer = findViewById<View>(R.id.main_container_layout)
        mOverlayContainer = findViewById<View>(R.id.overlay_container_layout)

        val switch = findViewById<Switch>(R.id.switch_add_back)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            isAddBack = isChecked
            Toast.makeText(this, "isAddBackStack: $isAddBack", Toast.LENGTH_LONG).show()
        }
        switch.isChecked = false

        val switchAnimation = findViewById<Switch>(R.id.switch_add_back_animation)
        switchAnimation.setOnCheckedChangeListener { buttonView, isChecked ->
            isAddBackAnimation = isChecked
            Toast.makeText(this, "isAddBackAnimation: $isAddBackAnimation", Toast.LENGTH_LONG).show()
        }
        switchAnimation.isChecked = false
    }

    fun addMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.activity_in_from_right,
            R.anim.activity_out_to_right
        )
        transaction.add(R.id.main_container_layout, mainFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    private fun setAnimation(transaction: FragmentTransaction) {
        if (isAddBackAnimation) {
            transaction.setCustomAnimations(
                R.anim.activity_in_from_right,
                R.anim.activity_out_to_right
            )
        } else {
            transaction.setCustomAnimations(
                R.anim.activity_in_from_right,
                R.anim.activity_out_to_right,
                R.anim.activity_in_from_right,
                R.anim.activity_out_to_right
            )
        }
    }


    fun removeMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.activity_in_from_right,
            R.anim.activity_out_to_bottom
        )

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

    fun addReplaceMain(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container_layout, mainReplaceFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }


    fun addOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_from_bottom, 0)
        transaction.add(R.id.overlay_container_layout, overlayFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun removeOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(0, R.anim.activity_out_to_right)
        transaction.remove(overlayFragment)
        if (isAddBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun hideOverlay(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(0, R.anim.activity_out_to_right)
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

}
