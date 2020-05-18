package com.airbag.dis.disfoot.ui.splash

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context.MODE_PRIVATE
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.SHARED_PREFERENCES_KEY
import com.airbag.dis.disfoot.SHARED_PREFERENCES_KEY_BOARDED
import kotlinx.android.synthetic.main.fragment_splash.*

class FragmentSplash : Fragment() {

    companion object {
        fun newInstance() = FragmentSplash()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val boarded : Boolean = (activity?.getSharedPreferences(SHARED_PREFERENCES_KEY,MODE_PRIVATE)?.getBoolean(
            SHARED_PREFERENCES_KEY_BOARDED,false) ) ?: false

        splash_animation_view.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationEnd(animation: Animator?) {
                var direction : NavDirections

                if (boarded)
                    direction = FragmentSplashDirections.toMainShoesSelection()
                else
                    direction = FragmentSplashDirections.toBoarding()
                findNavController().navigate(direction)

            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationStart(animation: Animator?) {
                // TODO("Not yet implemented")
            }
        })
    }

}
