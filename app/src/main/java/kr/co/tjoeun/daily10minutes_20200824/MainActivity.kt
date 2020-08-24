package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//1
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupEvents() {

        //5
        loginBtn.setOnClickListener {

            val inputId =  idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            //서버에 보내기


        }

    }

    override fun setValues() {

    }

}