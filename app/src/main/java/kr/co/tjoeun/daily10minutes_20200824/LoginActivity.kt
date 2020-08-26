package kr.co.tjoeun.daily10minutes_20200824

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

//1:Baseactivity(BaseActivity.kt) -> 2:로그인화면(activity_main.xml)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        //5:로그인버튼 -> 6:회원가입
        loginBtn.setOnClickListener {

            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            //5-1:API통신(파라메터 서버에 보내기)
            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    //실제 서버응답 실행
                    //Log.d("MainActivity(서버응답본문)", json.toString())
                    val codeNum = json.getInt("code")

                    if (codeNum == 200) { //서버응답 성공
                        //Log.d("로그인시도", "로그인성공")
                        val user = json.getJSONObject("user")
                        val nickname = user.getString("nick_name")
                        runOnUiThread {
                            //Toast.makeText(mContext, "${nickname}", Toast.LENGTH_SHORT).show()
                        }

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()

                    } else { //서버응답 실패
                        Log.d("로그인시도", "로그인실패")
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                            //Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })

        } //loginBtn.setOnClickListener

        //6
        signUpBtn.setOnClickListener {
            val myInent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myInent)
        }

    }

    override fun setValues() {
        idEdt.setText("kj_cho@nepp.kr")
        pwEdt.setText("Test!1234")
    }

}