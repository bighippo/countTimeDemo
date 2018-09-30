package com.example.hippo.simpledemo.ui.countdown.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.base.view.BaseMVPActivity
import com.example.hippo.simpledemo.ui.countdown.model.RecordModel
import com.example.hippo.simpledemo.ui.countdown.presenter.CountDownPresenter
import com.example.hippo.simpledemo.ui.countdown.view.CountDownView
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_countdown.*
import java.util.concurrent.TimeUnit

class CountDownActivity : BaseMVPActivity<CountDownView, CountDownPresenter>(), CountDownView {
    private enum class TimerStatus {
        START,
        PAUSE,
        RESTART,
        FINISH
    }

    private var handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){
                0 -> {
                    reset()
                }
            }
        }
    }

    private var mTimerStatus = TimerStatus.FINISH

    private val MAX_TIME  = (1 * 60000).toLong()

    private val MIN_TIME = 1000L

    private var mTimeCountInMilliSeconds = 0L

    private var progress_max = 0

    private var current_progress = 0

    private var left_time = 0L

    private lateinit var countDownTimer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)

        initListener()

        mTimeCountInMilliSeconds = 10000L
    }

    override fun createPresenter():  CountDownPresenter{
        return CountDownPresenter()
    }

    override fun start() {
        // call to initialize the progress bar values
        //setProgressBarValues()
        //initProgressBarValues()

        // hide the up and down icon
        iv_up.visibility = View.INVISIBLE
        iv_down.visibility = View.INVISIBLE

        //change background theme
        //activity_main.setBackgroundResource(R.drawable.drawable_counting_background)

        // changing play icon to stop icon
        iv_StartStop.setImageResource(R.drawable.icon_stop)

        // making edit text not editable
        editTextTask.isEnabled = false

        // changing the timer status to started
        mTimerStatus = TimerStatus.START

        //insert task
        var task = RecordModel()

        task.taskName = editTextTask.text.toString()

        task.start =  mTimeCountInMilliSeconds.toInt() / 1000

        task.save()
        // call to start the count down timer
        startCountDownTimer(mTimeCountInMilliSeconds)
    }

    override fun increase() {

        mTimeCountInMilliSeconds += 1000L

        textViewTime.text = msTimeFormatter(mTimeCountInMilliSeconds)
    }

    override fun decrease() {

        mTimeCountInMilliSeconds -= 1000L

        textViewTime.text = msTimeFormatter(mTimeCountInMilliSeconds)
    }

    override fun finishCountDown() {
        //activity_main.setBackgroundResource(R.drawable.drawable_background)

        textViewTime.text = msTimeFormatter(mTimeCountInMilliSeconds)

        // call to initialize the progress bar values
        //finishProgressBar()

        var msg = Message.obtain()

        msg.what = 0

        handler.sendMessageDelayed(msg, 1000)
    }

    private fun reset() {
        current_progress = 0

        //initProgressBarValues()

        // show the up and down icon
        iv_up.visibility = View.VISIBLE
        iv_down.visibility = View.VISIBLE

        // changing stop icon to start icon
        iv_StartStop.setImageResource(R.drawable.icon_start)

        // making edit text editable
        editTextTask.isEnabled = true

        editTextTask.setText("")

        // changing the timer status to stopped
        mTimerStatus = TimerStatus.FINISH
    }

    override fun pause() {
        // changing stop icon to start icon
        iv_StartStop.setImageResource(R.drawable.icon_start)

        // changing the timer status to stopped
        mTimerStatus = TimerStatus.PAUSE

        stopCountDownTimer()
    }

    override fun reStart() {
        // changing play icon to stop icon
        iv_StartStop.setImageResource(R.drawable.icon_stop)

        // making edit text not editable
        editTextTask.isEnabled = false

        // changing the timer status to started
        mTimerStatus = TimerStatus.RESTART

        // call to start the count down timer
        startCountDownTimer(left_time)
    }

    private fun initListener(){
        // show personal info
        iv_info.setOnClickListener{
            val intent = Intent(this, PersonInfoActivity::class.java)
            startActivity(intent)
        }

        // show history
        iv_history.setOnClickListener{
            if(RecordModel().queryAll().isEmpty()) {
                Toast.makeText(this, "No histroy, record one now", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, RecordListActivity::class.java)
            startActivity(intent)
        }

        // increase time
        iv_up.setOnClickListener{
            if (mTimeCountInMilliSeconds < MAX_TIME)
                getPresenter().increase()
            else
                Toast.makeText(this, "max time is 1 minute", Toast.LENGTH_LONG).show()
        }

        //decrease time
        iv_down.setOnClickListener{
            if (mTimeCountInMilliSeconds > MIN_TIME)
                getPresenter().decrease()
            else
                Toast.makeText(this, "min time is 1 second", Toast.LENGTH_LONG).show()
        }

        iv_StartStop.setOnClickListener{
            if (editTextTask.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter task name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            when(mTimerStatus) {
                TimerStatus.FINISH -> {
                    getPresenter().startCountDownTimer()
                }

                TimerStatus.START -> {
                    getPresenter().pause()
                }

                TimerStatus.RESTART -> {
                    getPresenter().pause()
                }

                TimerStatus.PAUSE ->{
                    getPresenter().reStart()
                }
            }
        }
    }

    private fun startCountDownTimer(time: Long) {
        countDownTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                textViewTime.text = msTimeFormatter(millisUntilFinished)

                //current_progress = progress_max - (millisUntilFinished / 1000).toInt()

                //progressBarCircle.progress = current_progress

                left_time = millisUntilFinished
            }

            override fun onFinish() {

                getPresenter().finish()
            }

        }.start()
        countDownTimer.start()
    }

    /**
     * method to stop count down timer
     */
    private fun stopCountDownTimer(){
        countDownTimer.cancel()
    }

    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return mm:ss time formatted string
     */
    fun msTimeFormatter(milliSeconds : Long) :String {
        var ms = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)))

        return ms
    }

    /**
     * method to set circular progress bar values
     */
    fun setProgressBarValues() {
        progress_max = mTimeCountInMilliSeconds.toInt() / 1000

        progressBarCircle.max = progress_max

        //progressBarCircle.progress = mTimeCountInMilliSeconds.toInt() / 1000
        progressBarCircle.progress = current_progress
    }

    /**
     * method to init circular progress bar values
     */
    private fun initProgressBarValues() {
        progress_max = mTimeCountInMilliSeconds.toInt() / 1000

        progressBarCircle.max = progress_max

        //progressBarCircle.progress = mTimeCountInMilliSeconds.toInt() / 1000
        progressBarCircle.progress = current_progress
    }

    private fun finishProgressBar(){
        progress_max = mTimeCountInMilliSeconds.toInt() / 1000

        progressBarCircle.max = progress_max

        progressBarCircle.progress = progress_max
    }

    override fun onDestroy() {
        Log.d("abcd", "onDestroy")
        super.onDestroy()
    }
}
