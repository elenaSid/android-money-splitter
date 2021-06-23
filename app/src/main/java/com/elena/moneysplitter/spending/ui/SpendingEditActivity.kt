package com.elena.moneysplitter.spending.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.elena.domain.user.UserEntity
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SpendingEditActivityBinding
import com.elena.moneysplitter.extras.*
import com.elena.moneysplitter.spending.mvp.SpendingEditMvpView
import com.elena.moneysplitter.spending.mvp.SpendingEditPresenter
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class SpendingEditActivity : MvpAppCompatActivity(), SpendingEditMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SpendingEditPresenter
    lateinit var binding: SpendingEditActivityBinding

    private val adapterPayers = SpendingUserAdapter { presenter.onPayersSelected(it) }
    private val adapterConsumers = SpendingUserAdapter { presenter.onConsumersSelected(it) }
    private val textWatcherItem: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            presenter.onItemNameChanged(s.trimmedContent())
        }
    }
    private val textWatcherPrice: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            val price = s.trimmedNumberContent().toDouble()
            presenter.onItemPriceChanged(price)
        }
    }

    @ProvidePresenter
    fun provideSpendingEditPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_spending)
        setToolbar()
        initUsersLists()
        binding.btnSave.setOnClickListener { presenter.onItemSaveRequested() }
        parseItemId()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (intent != null && intent.hasExtra(PARAM_ITEM_ID)) {
            menu?.let {
                val item = menu.add(Menu.NONE, MENU_DELETE_ID, Menu.NONE, R.string.spending_edit_delete)
                item.setIcon(R.drawable.ic_delete)
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_DELETE_ID) {
            presenter.onItemDeleteRequested()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.edtItemPrice.removeTextChangedListener(textWatcherPrice)
        binding.edtItemName.removeTextChangedListener(textWatcherItem)
    }

    override fun setItemName(itemName: String?) {
        binding.edtItemName.removeTextChangedListener(textWatcherItem)
        binding.edtItemName.setText(itemName)
        if (itemName == null) {
            binding.edtItemName.requestFocus()
        }
        binding.edtItemName.addTextChangedListener(textWatcherItem)
    }

    override fun setItemPrice(itemPrice: Double) {
        binding.edtItemPrice.removeTextChangedListener(textWatcherPrice)
        binding.edtItemPrice.setText(if (itemPrice == 0.0) null else itemPrice.toString())
        binding.edtItemPrice.addTextChangedListener(textWatcherPrice)
    }

    override fun updatePayerUsers(users: List<UserEntity>, selectedUsers: List<UserEntity>) {
        adapterPayers.update(users, selectedUsers)
    }

    override fun updateConsumerUsers(users: List<UserEntity>, selectedUsers: List<UserEntity>) {
        adapterConsumers.update(users, selectedUsers)
    }

    override fun manageSaveBtnAvailability(isEnabled: Boolean) {
        binding.btnSave.isEnabled = isEnabled
    }

    override fun saveFinish() {
        KeyboardManager.hide(this, binding.root.windowToken)
        finish()
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

    private fun initUsersLists() {
        binding.rvPaidByUsers.layoutManager = TaggedLayoutManager()
        val spaces = listOf(4.toPx(), 4.toPx(), 4.toPx(), 4.toPx())
        binding.rvPaidByUsers.addItemDecoration(SpaceDecoration(spaces))
        binding.rvPaidByUsers.adapter = adapterPayers

        binding.rvConsumedByUsers.layoutManager = TaggedLayoutManager()
        binding.rvConsumedByUsers.addItemDecoration(SpaceDecoration(spaces))
        binding.rvConsumedByUsers.adapter = adapterConsumers
    }

    private fun parseItemId() {
        intent?.let {
            if (intent.hasExtra(PARAM_ITEM_ID)) {
                presenter.onItemIdParsed(intent.getIntExtra(PARAM_ITEM_ID, 0))
            }
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