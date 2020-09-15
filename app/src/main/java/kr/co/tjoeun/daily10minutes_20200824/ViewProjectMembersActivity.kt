package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectAdapter
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.datas.User

//27:
class ViewProjectMembersActivity : BaseActivity() {

    //프로젝트(...)
    lateinit var mProject : Project

    //33:
    val mProjectMembers = ArrayList<User>()

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

        getProjectMembersFromServer()
    }

    //서버에서 프로젝트 참여멤버를 불러오는 기능
    fun getProjectMembersFromServer(){

    }
}