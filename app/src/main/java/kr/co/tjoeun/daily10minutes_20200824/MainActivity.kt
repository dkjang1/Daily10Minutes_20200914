package kr.co.tjoeun.daily10minutes_20200824

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectAdapter
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ContextUtil
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

//12:프로젝트목록(로그아웃) -> 13:스플래시화면(activity_splash.xml)
class MainActivity : BaseActivity() {

    //12:목록만들기
    val mProjectList = ArrayList<Project>()

    //12-2:프로젝트목록어댑터
    lateinit var mProjectAdapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues() //화면띄우기
    }

    override fun setupEvents() {

        //18:
        projectListView.setOnItemClickListener { parent, view, position, id ->

            val clickedProject = mProjectList[position]
            val myIntent = Intent(mContext, ViewProjectDetailActivity::class.java)
            myIntent.putExtra("project", clickedProject)
            startActivity(myIntent)
        }

        logoutBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃확인")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                ContextUtil.setLoginUserToken(mContext,"")
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }
    }

    override fun setValues() {
        //16:FirebaseMessage : 등록된기기토큰 확인
        //기기토큰: eOkLb6A1Tm2fHBmgRsO8qw:APA91bEtAR_3d8iBx3s0eNoKMuIo95cW8grtFSs6zNu7p9MwQ03TlPKrDZq6p2lLmNGMx5epxW_SYbRJAWu0KF3lTe4k2ASX_MKA4f2pDj1enVAGYG3QjLf5VmFVUs_6SLCMl4FVevxj
        Log.d("기기토큰", FirebaseInstanceId.getInstance().token!!)

        //프로젝트목록가져오기
        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectList)
        projectListView.adapter = mProjectAdapter
    }

    //12-1:서버에서 목록가져오기
    fun getProjectListFromServer() {
        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectArr = dataObj.getJSONArray("projects")
                for (i in 0 until projectArr.length()) {
                    val projectObj = projectArr.getJSONObject(i)
                    //Log.d("프로젝트제목", projectObj.getString("title"))
                    /*
                    val project = Project()
                    project.id = projectObj.getInt("id")
                    project.title = projectObj.getString("title")
                    project.imageUrl = projectObj.getString("img_url")
                    project.description = projectObj.getString("description")
                    //project.proofMethod = projectObj.getString("proof_method")
                    */
                    val project = Project.getProjectFromJson(projectObj)

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