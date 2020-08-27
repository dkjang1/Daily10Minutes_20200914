package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectAdapter
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

//11:프로젝트목록 -> 12:
class MainActivity : BaseActivity() {

    //13
    val mProjectList = ArrayList<Project>()

    //13-2
    lateinit var mProjectAdapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues() //화면띄우기
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        //프로젝트목록가져오기
        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectList)
        projectListView.adapter = mProjectAdapter
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
                //비동기처리로 인해서 어댑터연결이 끝나고 프로젝트목록이 나중에 추가될수 있다.
                runOnUiThread {
                    mProjectAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}