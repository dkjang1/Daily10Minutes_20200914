package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//1:Baseactivity(BaseActivity.kt) -> 2:로그인화면(activity_main.xml)
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()

        //idEdt.text = ""
        //pwEdt.text = ""
    }

    override fun setupEvents() {

        //5:로그인버튼 -> 6:
        loginBtn.setOnClickListener {

            val inputId =  idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            //5-1:API통신(파라메터 서버에 보내기)


        }

    }

    override fun setValues() {

    }

}