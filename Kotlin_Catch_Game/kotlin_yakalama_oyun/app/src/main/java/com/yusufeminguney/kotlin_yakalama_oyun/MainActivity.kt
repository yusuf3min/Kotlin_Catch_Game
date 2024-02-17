package com.yusufeminguney.kotlin_yakalama_oyun

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yusufeminguney.kotlin_yakalama_oyun.databinding.ActivityMainBinding
import kotlin.random.Random as Random

private lateinit var binding:ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray= ArrayList<ImageView>()
    var runnable=Runnable{}
    var handler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.kenny)
        imageArray.add(binding.kenny1)
        imageArray.add(binding.kenny2)
        imageArray.add(binding.kenny3)
        imageArray.add(binding.kenny4)
        imageArray.add(binding.kenny5)
        imageArray.add(binding.kenny6)
        imageArray.add(binding.kenny7)
        imageArray.add(binding.kenny8)
        hideImages()
        object:CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text="Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.timeText.text="Time: 0"
                handler.removeCallbacks(runnable)

                for(image in imageArray){

                    image.visibility=View.INVISIBLE
                }

                var alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialogInterface,i->
                    val intentFromMain=intent
                    finish()
                    startActivity(intentFromMain)
                }

                alert.setNegativeButton("No",){dialogInterface,i->}
                Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                alert.show()
            }



        }.start()


    }
    fun hideImages(){

        runnable=object:Runnable{
            override fun run() {
                for(image in imageArray){

                    image.visibility=View.INVISIBLE
                }
                val random= Random
                val randomIndex=random.nextInt(0,9)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)

            }


        }
        handler.post(runnable)



    }

    fun increaseScore(view: View){
        score=score+1
        binding.scoreText.text="Score: ${score}"


    }

}