package com.github.grupo6cineview.cineview.extension

import android.animation.Animator
import android.view.View
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.doInTheEnd(action: () -> Unit) {
    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(p0: Animator?) {}

        override fun onAnimationEnd(p0: Animator?) {
            action()
        }

        override fun onAnimationCancel(p0: Animator?) {}

        override fun onAnimationRepeat(p0: Animator?) {}
    })
}