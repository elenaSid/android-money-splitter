package com.elena.moneysplitter.spending.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SpendingEditActivityBinding
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity

/**
 * @author elena
 */
class SpendingEditActivity : MvpAppCompatActivity() {

    lateinit var binding: SpendingEditActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_spending)
        setToolbar()
    }

    private fun setToolbar() {
        supportActionBar?.let {
            it.elevation = 0f
            it.setHomeButtonEnabled(true)
            it.setTitle(
                    if (intent.hasExtra(PARAM_ITEM_ID)) {
                        R.string.spending_edit_edit
                    } else {
                        R.string.spending_edit_add
                    }
            )
        }
    }

    companion object {
        private const val MENU_DELETE_ID = 1
        private const val PARAM_ITEM_ID = "item_id"

        fun getInstance(context: Context, itemId: Int): Intent {
            val intent = Intent(context, SpendingEditActivity::class.java)
            intent.putExtra(PARAM_ITEM_ID, itemId)
            return intent
        }
    }
}