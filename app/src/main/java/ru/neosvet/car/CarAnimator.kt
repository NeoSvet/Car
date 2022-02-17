package ru.neosvet.car

import android.view.View

class CarAnimator(
    view: View,
    field: View
) {
    enum class Position {
        BOTTOM_LEFT, TOP_RIGHT
    }

    private val anim: AnimationUtils by lazy {
        val x = (field.width - view.width).toFloat()
        val y = (field.height - view.height) / 3f
        AnimationUtils(
            view = view,
            distanceX = x,
            distanceY = y
        )
    }
    private var position: Position = Position.BOTTOM_LEFT

    fun go() {
        if (anim.isBusy)
            return
        when (position) {
            Position.BOTTOM_LEFT -> goFromBottomLeftToTopRight()
            Position.TOP_RIGHT -> goFromTopRightToBottomLeft()
        }
    }

    private fun goFromBottomLeftToTopRight() {
        anim.addAnimation(
            AnimationUtils.Animation.ROTATE_RIGHT_CLOCKWISE,
            AnimationUtils.Animation.MOVE_RIGHT,
            AnimationUtils.Animation.ROTATE_TOP_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_TOP,
            AnimationUtils.Animation.ROTATE_LEFT_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_LEFT,
            AnimationUtils.Animation.ROTATE_TOP_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_TOP,
            AnimationUtils.Animation.ROTATE_RIGHT_CLOCKWISE,
            AnimationUtils.Animation.MOVE_RIGHT,
            AnimationUtils.Animation.ROTATE_TOP_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_TOP
        )
        anim.start()
        position = Position.TOP_RIGHT
    }

    private fun goFromTopRightToBottomLeft() {
        anim.addAnimation(
            AnimationUtils.Animation.ROTATE_LEFT_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_LEFT,
            AnimationUtils.Animation.ROTATE_BOTTOM_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_BOTTOM,
            AnimationUtils.Animation.ROTATE_RIGHT_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_RIGHT,
            AnimationUtils.Animation.ROTATE_BOTTOM_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_BOTTOM,
            AnimationUtils.Animation.ROTATE_LEFT_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_LEFT,
            AnimationUtils.Animation.ROTATE_BOTTOM_ANTICLOCKWISE,
            AnimationUtils.Animation.MOVE_BOTTOM,
            AnimationUtils.Animation.ROTATE_TOP_ANTICLOCKWISE,
        )
        anim.start()
        position = Position.BOTTOM_LEFT
    }
}