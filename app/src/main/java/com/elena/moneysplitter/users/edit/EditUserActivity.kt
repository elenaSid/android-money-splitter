package com.elena.moneysplitter.users.edit

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UserEditActivityBinding

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 18:25
 */
open class EditUserActivity : AppCompatActivity() {

    companion object {
        private const val MENU_DONE_ITEM_ID = 1
    }

    private lateinit var binding:UserEditActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_user)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null) {
            return super.onCreateOptionsMenu(menu)
        }
        val item = menu.add(Menu.NONE, MENU_DONE_ITEM_ID, Menu.NONE, R.string.user_edit_menu_item_save)
        item.setIcon(R.drawable.ic_done_gray)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return super.onOptionsItemSelected(item)
        }
        if (item.itemId == MENU_DONE_ITEM_ID) {
            val intent = Intent()
            //TODO:передать сохраненного пользователя
            setResult(Activity.RESULT_OK, intent)
        }
        finish()
        return true
    }
}