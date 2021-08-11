package com.elena.moneysplitter.intro.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.IntroActivityBinding
import moxy.MvpAppCompatActivity

/**
 * @author elena
 */
class IntroActivity : MvpAppCompatActivity() {

    private val intros = listOf<Fragment>(
            IntroFragment.getInstance(IntroType.BILL),
            IntroFragment.getInstance(IntroType.DEBTS),
            IntroFragment.getInstance(IntroType.SPENDING)
    )
    lateinit var binding: IntroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_intro)

        val adapter = IntroAdapter(this)
        binding.vpIntro.adapter = adapter
        adapter.setFragmentList(intros)
        binding.indicators.selectCurrentPosition(0)
        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.indicators.selectCurrentPosition(position)
            }
        })
        //TODO: Добавить переход к визарду и запомнить что интро было показано
    }
}