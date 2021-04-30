package com.elena.moneysplitter.family.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.FamilyEditFragmentBinding
import com.elena.moneysplitter.extras.trimmedContent
import moxy.MvpAppCompatActivity

/**
 * @author elena
 */
class FamilyEditActivity : MvpAppCompatActivity() {

    lateinit var binding: FamilyEditFragmentBinding

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            val isNotEmpty = s.trimmedContent().isNotEmpty()
            manageSaveBtnAvailability(isNotEmpty)
            manageErrorMessage(isNotEmpty)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_family)
        setToolbar()
        binding.edtFamilyName.addTextChangedListener(textWatcher)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.edtFamilyName.removeTextChangedListener(textWatcher)
    }

    private fun setToolbar() {
        supportActionBar?.let {
            it.elevation = 0f
            it.setHomeButtonEnabled(true)
        }
    }

    private fun manageSaveBtnAvailability(isEnabled: Boolean) {
        binding.btnSave.isEnabled = isEnabled
    }

    private fun manageErrorMessage(hasNoError: Boolean) {
        binding.tilFamilyName.error = if (hasNoError) null else getString(R.string.family_not_set_error)
    }
}