package com.spinwheeltest.apk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var play: Button
    lateinit var wheel: ImageView
   // private val sectors = arrayOf("1","2","3","4","5","6","7","8","9","10")
   private val sectors = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private var sectorsDegrees = arrayOfNulls<Int>(sectors.size)
    var random = Random
    var degree = 0
    var isSpinning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        play = findViewById(R.id.play)
        wheel = findViewById(R.id.wheel)

        getDegreeForSectors()

        play.setOnClickListener(View.OnClickListener {
          if(!isSpinning){
              spin()
              isSpinning = true
          }
        })

    }

    private fun spin() {
        degree = random.nextInt(sectors.size-1)
        var rotateAnimation: RotateAnimation = RotateAnimation(
            0F, ((360 * sectors.size) + sectorsDegrees[degree]!!).toFloat(), RotateAnimation.RELATIVE_TO_SELF,
        0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        rotateAnimation.duration = 3600
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                Toast.makeText(this@MainActivity,sectors[sectors.size - (degree + 1)].toString(),Toast.LENGTH_LONG).show()
               // Toast.makeText(this@MainActivity,"Hello",Toast.LENGTH_LONG).show()
               // Log.d("msg", sectors.size.toString())
                isSpinning = false
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

        wheel.startAnimation(rotateAnimation)
    }

    fun getDegreeForSectors(){

        var sectorDegree = 360/sectors.size

        for (i in sectors){
           sectorsDegrees[i-1] = (i) * sectorDegree
        }

    }
}