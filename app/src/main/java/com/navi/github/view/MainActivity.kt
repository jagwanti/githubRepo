package com.navi.github.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.navi.github.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            var fragment  = MainFragment.newInstance()
            fragment?.let {
                // Insert the fragment by replacing any existing fragment
                val fragmentManager: FragmentManager = supportFragmentManager
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
        }
        supportActionBar?.title = resources.getString(R.string.pull_requests)

    }

    fun replaceFragment(fragmentClass: Class<*>, data:Bundle?=null) {
        var fragment: Fragment? = null
        try {
            fragment = fragmentClass.newInstance() as Fragment
            fragment.arguments = data
        } catch (e: Exception) {
            e.printStackTrace()
        }
        fragment?.let {
            // Insert the fragment by replacing any existing fragment
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(fragment.javaClass.simpleName).commit()
        }
    }
}