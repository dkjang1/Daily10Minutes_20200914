package kr.co.tjoeun.daily10minutes_20200824.datas

import java.io.Serializable

//29:
class User : Serializable {

    var id = 0
    var email = ""
    var nickname = ""
    val profileImageArrayList = ArrayList<String>()

}