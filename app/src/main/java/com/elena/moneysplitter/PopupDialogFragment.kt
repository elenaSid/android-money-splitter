package com.elena.moneysplitter

import android.app.Dialog
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.elena.moneysplitter.databinding.PopupDialogBinding

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 16:29
 */
abstract class PopupDialogFragment : androidx.fragment.app.DialogFragment() {

    private lateinit var binding: PopupDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.MoneySplitter_PopupDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog == null || dialog.window == null) {
            return
        }
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.view_popup_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
    }

    private fun initWidgets() {
        binding.ibClose.setOnClickListener { dismissAllowingStateLoss() }
        binding.llRoot.setOnClickListener { dismissAllowingStateLoss() }
        binding.cvDialog.setOnClickListener(null)
        binding.tvDialogTitle.text = getTitle()
        binding.llDialogContainer.addView(getContent())
    }

    protected abstract fun getTitle(): String

    protected abstract fun getContent(): View

    fun showAllowingStateLoss(fragmentManager: FragmentManager, tag: String) {
        fragmentManager.beginTransaction()
                .add(this, tag)
                .commitAllowingStateLoss()
    }
}