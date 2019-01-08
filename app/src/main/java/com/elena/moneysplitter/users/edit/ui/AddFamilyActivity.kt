package com.elena.moneysplitter.users.edit.ui

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.AddFamilyActivityBinding

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 12:01
 */
class AddFamilyActivity : AppCompatActivity() {

    private lateinit var binding: AddFamilyActivityBinding

    companion object {
        const val PARAM_FAMILY = "family"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_add_family)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null) {
            return super.onCreateOptionsMenu(menu)
        }
        val item = menu.add(Menu.NONE, R.id.menu_item_done, Menu.NONE, R.string.user_edit_menu_item_save)
        item.setIcon(R.drawable.ic_done_gray)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return super.onOptionsItemSelected(item)
        }
        if (item.itemId == R.id.menu_item_done) {
            val intent = Intent()
            val family = binding.edtBabyName.text.toString().trim()

            if (family.isEmpty()) {
                showError()
                return false
            }
            intent.putExtra(PARAM_FAMILY, family)
            setResult(Activity.RESULT_OK, intent)
        }
        finish()
        return true
    }

    private fun showError() {
        Toast.makeText(this, R.string.family_not_set_error, Toast.LENGTH_SHORT).show()
    }
}