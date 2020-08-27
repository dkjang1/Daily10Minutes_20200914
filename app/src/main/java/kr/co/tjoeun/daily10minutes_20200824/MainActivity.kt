package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

//11:프로젝트목록 -> 12:
class MainActivity : BaseActivity() {

    //13
    val mProjectList = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        //프로젝트목록가져오기
        getProjectListFromServer()
    }

    //13-1
    fun getProjectListFromServer() {
        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectArr = dataObj.getJSONArray("projects")
                for (i in 0 until projectArr.length()) {
                    val projectObj = projectArr.getJSONObject(i)
                    //Log.d("프로젝트제목", projectObj.getString("title"))
                    val project = Project()
                    project.id = projectObj.getInt("id")
                    project.title = projectObj.getString("title")
                    project.imageUrl = projectObj.getString("img_url")
                    project.description = projectObj.getString("description")
                    //project.proofMethod = projectObj.getString("proof_method")
                    //project.proofMethod = projectObj.getString("proof_method")

                    mProjectList.add(project)
                }
            }
        })
    }
}