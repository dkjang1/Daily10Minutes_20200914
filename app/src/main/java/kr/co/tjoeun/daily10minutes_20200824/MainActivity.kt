package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

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

            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            //5-1:API통신(파라메터 서버에 보내기)
            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHeader {
                override fun onResponse(json: JSONObject) {
                    //실제 서버응답 실행
                    Log.d("MainActivity(서버응답본문)", json.toString())

                }

            })

        }

    }

    override fun setValues() {

    }

}