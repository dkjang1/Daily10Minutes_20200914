package kr.co.tjoeun.daily10minutes_20200914.datas

import org.json.JSONObject
import java.io.Serializable

class Project : Serializable {

    var id = 0
    var title = ""
    var imageUrl = ""
    var description = ""
    var proofMethod = ""
    var onGoingMemberCount = 0
    var myLastStatus: String? = null

    //22:
    companion object {
        fun getProjectFromJson(json: JSONObject): Project {
            val project = Project()

            project.id = json.getInt("id")
            project.title = json.getString("title")
            project.imageUrl = json.getString("img_url")
            project.description = json.getString("description")
            project.proofMethod = json.getString("proof_method")
            project.onGoingMemberCount = json.getInt("ongoing_users_count")

            //서버에서 데이터값이 null일경우
            if (!json.isNull("my_last_status")) {
                project.myLastStatus = json.getString("my_last_status")
            }


            return project
        }
    }
}