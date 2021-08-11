package com.elena.moneysplitter.intro.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.IntroBillFragmentBinding
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat as AnimatedVectorDrawableCompat

/**
 * @author elena
 */
class IntroFragment : Fragment() {

    private lateinit var binding: IntroBillFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_intro, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val introType = IntroType.values()[it.getInt(PARAM_TYPE)]
            binding.tvTitle.setText(introType.titleRes)
            binding.ivIntro.setImageDrawable(
                    introType.drawableRes.getAnimatedDrawable(requireContext())
            )
            binding.ivIntro.animateDrawable()
        }
    }

    /**
     * Возвращает векторную анимацию
     */
    private fun Int.getAnimatedDrawable(context: Context) =
            AnimatedVectorDrawableCompat.create(context, this)

    /**
     * Запускает векторную анимацию
     */
    private fun ImageView.animateDrawable() {
        when (val drawable = this.drawable) {
            is AnimatedVectorDrawableCompat -> {
                drawable.start()
            }
        }
    }

    companion object {

        private const val PARAM_TYPE = "param_type"

        fun getInstance(type: IntroType): IntroFragment {
            val introFragment = IntroFragment()
            val args = Bundle()
            args.putInt(PARAM_TYPE, type.ordinal)
            introFragment.arguments = args
            return introFragment
        }
    }
}