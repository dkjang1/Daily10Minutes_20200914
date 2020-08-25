package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        emailCheckBtn.setOnClickListener {
            //입력한 이메일확인 - 서버중복검사결과요청
            val inputEmail = signUpEmailEdt.text.toString()

            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHeader {
                override fun onResponse(json: JSONObject) {
                    val codeNum = json.getInt("code")
                    val message = json.getString("message")

                    runOnUiThread {
                        if (codeNum == 200) { //서버응답 성공
                            emailCheckResultTxt.text = "${message}"
                        } else { //서버응답 실패
                            emailCheckResultTxt.text = "${message}"
                        }
                    }
                }
            })


        }

    }

    override fun setValues() {
        signUpEmailEdt.setText("kj_cho@nepp.kr")
        //signUpEmailEdt.setText("dkjang@naver.com")
    }

}