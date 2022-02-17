package ru.neosvet.car

import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var car: CarAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivCar = findViewById<ImageView>(R.id.ivCar)
        car = CarAnimator(
            view = ivCar,
            field = window.findViewById(Window.ID_ANDROID_CONTENT)
        )
        ivCar.setOnClickListener {
            car.go()
        }
    }
}