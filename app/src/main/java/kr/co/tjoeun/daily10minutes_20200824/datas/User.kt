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

            //기본값세팅된 user 만들고 jsno 변수를 이용해 내용물을 채우고 결과로 return
            val user = User()

            //json으로 user변수의 항목을 채우자
            user.id = json.getInt("id")
            user.email = json.getString("email")
            user.nickname = json.getString("nick_name")

            //사용자의 프사목록도 파싱해야함
            //json 변수내의 profile_image
            val pfImgJsonArr = json.getJSONArray("profile_images")
            for (i in 0 until pfImgJsonArr.length()){
                val pfImgObj = pfImgJsonArr.getJSONObject(i)
                val imageUrl = pfImgObj.getString("img_url")
                user.profileImageArrayList.add(imageUrl)
            }

            return user
        }
    }

}