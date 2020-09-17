package kr.co.tjoeun.daily10minutes_20200914.utils

import android.content.Context

//5:API데이터(setter,getter) -> 6:로그인버튼(LoginActivity.kt)
class ContextUtil {

    companion object {

        //5-1:자동로그인 값가져오기
        private val prefName = "Daily10MinutesPref"
        private val AUTO_LOGIN_CHECK = "AUTO_LOGIN_CHECK"
        private val LOGIN_USER_TOKEN = ""

        //5-1:자동로그인 값가져오기
        fun setAutoLoginCheck(context: Context, isAuto: Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN_CHECK, isAuto).apply()
        }
        fun getAutoLoginCheck(context: Context): Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN_CHECK, false)
        }

        //5-2:Token 값가져오기
        fun setLoginUserToken(context: Context, token: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }
        fun getLoginUserToken(context: Context): String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN,"")!!
        }
    }

}