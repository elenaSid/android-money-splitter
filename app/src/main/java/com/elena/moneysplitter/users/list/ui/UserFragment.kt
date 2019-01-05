package com.elena.moneysplitter.users.list.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UsersFragmentBinding
import com.elena.moneysplitter.root.ui.RootActivity
import com.elena.moneysplitter.users.edit.EditUserActivity
import com.elena.moneysplitter.users.list.mvp.UsersPresenter
import com.elena.moneysplitter.users.list.mvp.UsersView
import com.elena.moneysplitter.utils.DisplayUtils
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author elena
 *         Date: 03/01/2019
 *         Time: 23:03
 */
class UserFragment : MvpAppCompatFragment(), UsersView, UserAdapter.UserListener {

    companion object {
        private const val REQUEST_EDIT = 0;
        private const val REQUEST_CREATE = 1;
        private const val PARAM_USER = "user_param"
    }
    private lateinit var binding: UsersFragmentBinding

    @Inject
    @InjectPresenter
    internal lateinit var presenter: UsersPresenter

    @ProvidePresenter
    fun provideUsersPresenter(): UsersPresenter {
        return presenter
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_users, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.fabAddUser.setOnClickListener { launchEditUserActivity(null) }
        initUsersList()
    }

    private fun initUsersList() {
        val adapter = UserAdapter(this)
        val users = ArrayList<Pair<String, String>>()
        users.add(Pair("West", "West's"))
        users.add(Pair("Elena", "West's"))
        adapter.users = users

        binding.rvUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = adapter
    }

    private fun launchEditUserActivity(user: Pair<String, String>?) {
        if (user == null) {
            startActivity(Intent(activity, EditUserActivity::class.java))

        } else {
            val bundle = Bundle()
            bundle.putSerializable(PARAM_USER, user)
            startActivityForResult(Intent(activity, EditUserActivity::class.java), REQUEST_EDIT, bundle)
        }
    }

    override fun onMoreClicked(anchor: View, user: Pair<String, String>) {
        val popupWindow = PopupWindow(context)
        val popupContent = View.inflate(context, R.layout.menu_view, null)
        (popupContent.findViewById(R.id.btnEdit) as TextView).setOnClickListener {
            //TODO: вызвать экран редактирования
            popupWindow.dismiss()
        }
        (popupContent.findViewById(R.id.btnDelete) as TextView).setOnClickListener {
            //TODO:Удалять пользователей
            popupWindow.dismiss()
        }
        popupWindow.contentView = popupContent
        popupWindow.width = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.contentView = popupContent
        popupWindow.isOutsideTouchable = true

        val anchorScreenPosition = IntArray(2)
        anchor.getLocationInWindow(anchorScreenPosition)
        val yOffset = anchorScreenPosition[1] - DisplayUtils.dpToPx(resources, 48F)
        popupWindow.showAtLocation(anchor, Gravity.END or Gravity.TOP,
                DisplayUtils.dpToPx(resources, 19F), yOffset)
    }
}