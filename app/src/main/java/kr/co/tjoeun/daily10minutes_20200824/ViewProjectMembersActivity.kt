package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_project_members.*
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectAdapter
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectMemberAdapter
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.datas.User
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

//27:
class ViewProjectMembersActivity : BaseActivity() {

    //프로젝트(...)
    lateinit var mProject : Project

    //33:
    val mProjectMembers = ArrayList<User>()

    //39:
    lateinit var mAdapter : ProjectMemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_members)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project //캐스팅함

        //39:
        mAdapter = ProjectMemberAdapter(mContext, R.layout.user_list_item, mProjectMembers)
        membersListView.adapter = mAdapter

        getProjectMembersFromServer()
    }

    //서버에서 프로젝트 참여멤버를 불러오는 기능
    fun getProjectMembersFromServer(){

        //36:
        ServerUtil.getRequestProjectMemberById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

                val ongoingUsersArr = projectObj.getJSONArray("ongoing_users")
                for (i in 0 until ongoingUsersArr.length()){
                    val memberObj = ongoingUsersArr.getJSONObject(i)
                    //memberObj => USer형태변환 = > ArrayList에 추가

                    //38:
                    val user = User.getUserFromJson(memberObj)
                    mProjectMembers.add(user)

                }

                //40:
                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                }

            }
        })

    }
}