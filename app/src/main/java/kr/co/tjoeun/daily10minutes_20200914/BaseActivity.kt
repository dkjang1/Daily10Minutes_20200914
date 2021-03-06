package kr.co.tjoeun.daily10minutes_20200914

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/*
//1:Baseactivity(BaseActivity.kt) -> 2:로그인화면(activity_login.xml)
<!--//2:로그인화면 -> 3:로그인처리(LoginActivity.kt)-->
//3:로그인처리(okHttp:Manifest+build.gradle) -> 4:API통신모듈(ServerUtil.kt)
//4:API통신모듈(POST/GET/PUT) -> 5:API데이터(Context.kt)
//5:API데이터(setter,getter) -> 6:로그인버튼(LoginActivity.kt)
//6:로그인버튼 -> 7:회원가입(activity_sign_up.xml)
<!--//7:회원가입 -> 8:회원가입(SignUpActivity.kt) -->
//8:회원가입 -> 9:프로젝트목록(activity_main.xml)
<!--//9:프로젝트목록 -> 10:프로젝트목록(project_list_item.xml)-->
<!--//10:프로젝트목록(datas:Project.kt) -> 11:프로젝트목록(ProjectAdapter.kt)-->
//11:프로젝트목록어댑터(Glide) -> 12:프로젝트목록(MainActivity.kt)
//12:프로젝트목록(로그아웃) -> 13:스플래시화면(activity_splash.xml)
<!--//13:스플래시화면 -> 14:스플래시화면(SplashActivity.kt)-->
//14:스플래시화면(Manifest+자동로그인확인) -> 15:FirebaseMessage(MyFCMService.kt)
//15:FirebaseMessage -> 16:
*/

//STEP1:BaseActivity만들기
abstract class BaseActivity : AppCompatActivity() {
    val mContext = this
    abstract fun setupEvents()
    abstract fun setValues()

    //71-2:
    lateinit var notiImg : ImageView

    //63:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //액션바가 null일경우 Theme.AppCompat.Light.NoActionBar
//        if (supportActionBar != null) {
//            setCustomActionBar()
//        }
        //kotlin
        supportActionBar?.let {
            setCustomActionBar()
        }

    }

    //62:
    fun setCustomActionBar(){
        val myActionBar = supportActionBar!!
        myActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        myActionBar.setCustomView(R.layout.my_custom_actionbar)
        val parentToolbar = myActionBar.customView.parent as Toolbar
        parentToolbar.setContentInsetsAbsolute(0,0)

        //71-3:
        //notiImg = myActionBar.customView.findViewById("")
    }

}