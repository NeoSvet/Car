package ru.neosvet.car

import android.view.View

class AnimationUtils(
    private val view: View,
    private val distanceX: Float,
    private val distanceY: Float
) {
    companion object {
        private const val START_DELAY = 20L
        private const val DURATION_X = 200L
        private const val DURATION_Y = 100L
        private const val ROTATE_DURATION = 500L
        private const val ANGLE_RIGHT_CLOCKWISE = 90f
        private const val ANGLE_LEFT_ANTICLOCKWISE = -90f
        private const val ANGLE_RIGHT_ANTICLOCKWISE = -270f
        private const val ANGLE_LEFT_CLOCKWISE = 270f
        private const val ANGLE_TOP_ANTICLOCKWISE = 0f
        private const val ANGLE_BOTTOM_CLOCKWISE = 180f
        private const val ANGLE_TOP_CLOCKWISE = 360f
        private const val ANGLE_BOTTOM_ANTICLOCKWISE = -180f
    }

    enum class Animation {
        ROTATE_RIGHT_CLOCKWISE, ROTATE_RIGHT_ANTICLOCKWISE,
        ROTATE_LEFT_CLOCKWISE, ROTATE_LEFT_ANTICLOCKWISE,
        ROTATE_TOP_CLOCKWISE, ROTATE_TOP_ANTICLOCKWISE,
        ROTATE_BOTTOM_CLOCKWISE, ROTATE_BOTTOM_ANTICLOCKWISE,
        MOVE_RIGHT, MOVE_LEFT, MOVE_TOP, MOVE_BOTTOM
    }

    var isBusy: Boolean = false
        private set
    private val animations = arrayListOf<Animation>()
    private var index = 0

    fun addAnimation(vararg animation: Animation) {
        animations.addAll(animation)
    }

    fun start() {
        if (isBusy)
            return
        index = 0
        isBusy = true
        nextAnimation()
    }

    private fun nextAnimation() {
        if (index == animations.size) {
            finishAnimation()
            return
        }
        when (animations[index]) {
            Animation.ROTATE_RIGHT_CLOCKWISE -> rotate(ANGLE_RIGHT_CLOCKWISE)
            Animation.ROTATE_RIGHT_ANTICLOCKWISE -> rotate(ANGLE_RIGHT_ANTICLOCKWISE)
            Animation.ROTATE_LEFT_CLOCKWISE -> rotate(ANGLE_LEFT_CLOCKWISE)
            Animation.ROTATE_LEFT_ANTICLOCKWISE -> rotate(ANGLE_LEFT_ANTICLOCKWISE)
            Animation.ROTATE_TOP_CLOCKWISE -> rotate(ANGLE_TOP_CLOCKWISE)
            Animation.ROTATE_TOP_ANTICLOCKWISE -> rotate(ANGLE_TOP_ANTICLOCKWISE)
            Animation.ROTATE_BOTTOM_CLOCKWISE -> rotate(ANGLE_BOTTOM_CLOCKWISE)
            Animation.ROTATE_BOTTOM_ANTICLOCKWISE -> rotate(ANGLE_BOTTOM_ANTICLOCKWISE)
            Animation.MOVE_RIGHT -> moveX(distanceX)
            Animation.MOVE_LEFT -> moveX(-distanceX)
            Animation.MOVE_TOP -> moveY(-distanceY)
            Animation.MOVE_BOTTOM -> moveY(distanceY)
        }
        index++
    }

    private fun finishAnimation() {
        isBusy = false
        animations.clear()
    }

    private fun rotate(angle: Float) = view.animate()
        .withEndAction(this::nextAnimation)
        .setStartDelay(START_DELAY)
        .setDuration(ROTATE_DURATION)
        .rotation(angle)
        .start()

    private fun moveX(distance: Float) = view.animate()
        .withEndAction(this::nextAnimation)
        .setStartDelay(START_DELAY)
        .setDuration(DURATION_X)
        .translationXBy(distance)
        .start()

    private fun moveY(distance: Float) = view.animate()
        .withEndAction(this::nextAnimation)
        .setStartDelay(START_DELAY)
        .setDuration(DURATION_Y)
        .translationYBy(distance)
        .start()
}