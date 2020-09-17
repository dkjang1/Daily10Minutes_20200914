package kr.co.tjoeun.daily10minutes_20200914

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.tjoeun.daily10minutes_20200914.utils.ContextUtil
import kr.co.tjoeun.daily10minutes_20200914.utils.ServerUtil
import org.json.JSONObject

//1:Baseactivity(BaseActivity.kt) -> 2:로그인화면(activity_main.xml)
//3:로그인처리(okHttp:Manifest+build.gradle) -> 4:API통신모듈(ServerUtil.kt)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    //6:로그인버튼 -> 7:회원가입(activity_sign_up.xml)
    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    Log.d("MainActivity", json.toString())
                    val codeNum = json.getInt("code")

                    if (codeNum == 200) { //서버응답 성공
                        //6-1:토큰값가져오기
                        val data = json.getJSONObject("data")
                        val token = data.getString("token")
                        Log.d("token", token)
                        ContextUtil.setLoginUserToken(mContext, token)

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    } else { //서버응답 실패
                        Log.d("로그인시도", "로그인실패")
                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } //onResponse
            }) //ServerUtil.postRequestLogin
        } //loginBtn.setOnClickListener

        //6-2:자동로그인체크 저장하기
        autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            Log.d("자동로그인체크여부", isChecked.toString())
//            if (isChecked == true) {
//                ContextUtil.setAutoLoginCheck(mContext, true)
//            } else {
//                ContextUtil.setAutoLoginCheck(mContext, false)
//            }
            ContextUtil.setAutoLoginCheck(mContext, isChecked)
        }

        //6-3:회원가입
        signUpBtn.setOnClickListener {
            val myInent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myInent)
        }
    }

    override fun setValues() {
        //6-3:자동로그인체크 값가져오기
        autoLoginCheckBox.isChecked = ContextUtil.getAutoLoginCheck(mContext)

        //member1
        idEdt.setText("kj_cho@nepp.kr")
        pwEdt.setText("Test!1234")
        //member2
        idEdt.setText("dkjang@naver.com")
        pwEdt.setText("@1234qwer")
        //member3
        idEdt.setText("test2@naver.com")
        pwEdt.setText("test2test")
        //loginBtn.callOnClick()
    }

}