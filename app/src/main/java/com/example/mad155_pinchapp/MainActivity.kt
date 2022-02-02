package com.example.mad155_pinchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView

lateinit var iv: ImageView //variable to be set later.
var scale = 1f

class MainActivity : AppCompatActivity() {

    var ourSD: ScaleGestureDetector? = null //optional?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv = findViewById(R.id.mainImage) //var has been initialized
        ourSD = ScaleGestureDetector(this, ScaleListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean { //triggers onTouch
        ourSD!!.onTouchEvent(event) // !! to handle null sent to 'connect' - I should think of this like Lua Pcalls
        return super.onTouchEvent(event)
    }

    class ScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener(){ //Initialize the class here
        override fun onScale(detector: ScaleGestureDetector): Boolean { //this is an existing function! Note: came with a '?' which was removed
            // This function actually scales the image up when the pinch happens.
            scale *= detector.scaleFactor
            scale = Math.max(0.1f, Math.min(scale, 5.0f))
            iv.scaleX = scale
            iv.scaleY = scale
            //return super.onScale(detector)
            return true
        }

    }
}