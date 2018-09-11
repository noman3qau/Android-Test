package com.github.android

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    /**
     * Global databinding handling
     */
    lateinit var mDataBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataBinding = DataBindingUtil.setContentView(this, setLayout())
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init(savedInstanceState)
    }

    abstract fun setLayout(): Int

    abstract fun init(savedInstanceState: Bundle?)


    /**
     * Start new Activity with adding bundle
     *
     * @param activity
     * @param clazz
     * @param bundle
     */
    fun startNewActivity(activity: Activity, clazz: Class<*>, bundle: Bundle) {

        val intent = Intent(activity, clazz)
        intent.putExtras(bundle)
        activity.startActivity(intent)

    }



}