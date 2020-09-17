package kr.co.tjoeun.daily10minutes_20200914

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kr.co.tjoeun.daily10minutes_20200914.utils.ContextUtil

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({
            //자동로그인여부확인 + 로그인필요:로그인화면
            //자동로그인체크+로그인처리완료
            var myIntent : Intent
            if (ContextUtil.getAutoLoginCheck(mContext) && ContextUtil.getLoginUserToken(mContext) != "") {
                //자동로그인처리
                myIntent = Intent(mContext, MainActivity::class.java)
            } else { //로그인필요
                myIntent = Intent(mContext, LoginActivity::class.java)
            }
            startActivity(myIntent)
            finish()
        }, 2500) //2.5초

    }

}