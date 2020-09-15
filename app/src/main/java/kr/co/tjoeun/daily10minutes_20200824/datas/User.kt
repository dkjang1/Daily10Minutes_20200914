package kr.co.tjoeun.daily10minutes_20200824.datas

import org.json.JSONObject
import java.io.Serializable

//29:
class User : Serializable {

    var id = 0
    var email = ""
    var nickname = ""
    val profileImageArrayList = ArrayList<String>()

    //37:(...)
    companion object{

        fun getUserFromJson(json: JSONObject) : User{

            val user = User()
            user.id = json.getInt("id")
            user.email = json.getString("email")
            user.nickname = json.getString("nick_name")


            return user
        }
    }

}