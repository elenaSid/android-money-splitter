package com.elena.moneysplitter.users.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UsersFragmentBinding
import com.elena.moneysplitter.users.mvp.UsersPresenter
import com.elena.moneysplitter.users.mvp.UsersView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author elena
 *         Date: 03/01/2019
 *         Time: 23:03
 */
class UserFragment : MvpAppCompatFragment(), UsersView {
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
        binding.fabAddUser.setOnClickListener { presenter.onUserAddClicked() }
    }
}