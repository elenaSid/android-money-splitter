package com.elena.moneysplitter.users.edit

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UserEditActivityBinding

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 18:25
 */
class EditUserActivity: MvpAppCompatActivity() {

    private lateinit var binding:UserEditActivityBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_user)
    }
}