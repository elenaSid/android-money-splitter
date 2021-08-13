package com.elena.moneysplitter.intro.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.IntroActivityBinding
import com.elena.moneysplitter.intro.mvp.IntroMvpView
import com.elena.moneysplitter.intro.mvp.IntroPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.ac_intro.view.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class IntroActivity : MvpAppCompatActivity(), IntroMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: IntroPresenter
    private val adapter = IntroAdapter(this)
    private lateinit var binding: IntroActivityBinding

    @ProvidePresenter
    fun provideIntroPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_intro)

        binding.vpIntro.adapter = adapter
        binding.indicators.setCurrentPosition(0)
        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.indicators.setCurrentPosition(position)
            }
        })
        binding.btnGetStarted.btnGetStarted.setOnClickListener { presenter.onGetStartedClicked() }
    }

    override fun changeIntroSlide() {
        val currentItem = binding.vpIntro.currentItem
        binding.vpIntro.currentItem = if (currentItem == adapter.itemCount - 1) 0 else currentItem + 1
    }
}