package kr.co.tjoeun.daily10minutes_20200824.datas

import org.json.JSONObject


//47:
class Proof {

    var id = 0
    var content = ""
    val imgList = ArrayList<String>()

    //54:
    lateinit var writer : User

    //56-1:댓글개수,좋아요개수
    var replyCount = 0
    var likeCount = 0

    companion object {

        fun getProofFromJson(json : JSONObject) : Proof {

            val proof = Proof()
            proof.id = json.getInt("id")
            proof.content = json.getString("content")

            val imgJsonArr = json.getJSONArray("images")
            for (i in  0 until imgJsonArr.length()){
                val imgObj = imgJsonArr.getJSONObject(i)
                proof.imgList.add(imgObj.getString("img_url"))
                //proof.imgList.add(imgJsonArr.getJSONObject(i).getString("img_url"))
            }

            //54-1:인증글 작성자 파싱
            val userObj = json.getJSONObject("user")
            proof.writer = User.getUserFromJson(userObj)

            //56-2:댓글개수 좋아요개수 파싱+버튼id 설정(proof_list_item.xml)
            proof.replyCount = json.getInt("reply_count")
            proof.likeCount = json.getInt("like_count")

            return proof
        }

    }

}