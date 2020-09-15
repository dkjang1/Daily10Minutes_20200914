package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.utils.ServerUtil
import org.json.JSONObject

//19:상세화면
class ViewProjectDetailActivity : BaseActivity() {

    //19-1:
    lateinit var mProject: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        //19-2
        mProject = intent.getSerializableExtra("project") as Project
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

    }

    //21:
    override fun onResume() {
        super.onResume()
        getProjectDetailFromServer()
    }

    fun getProjectDetailFromServer() {
        ServerUtil.getRequestProjectDetailById(
            mContext,
            mProject.id,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val data = json.getJSONObject("data")
                    val projectObj = data.getJSONObject("project")
                    //mProject.id = projectObj.getInt("id")
                    mProject = Project.getProjectFromJson(projectObj)

                    runOnUiThread {
                        proofMethodTxt.text = mProject.proofMethod
                        onGoingMemberCountTxt.text = "(현재 참여인원 : ${mProject.onGoingMemberCount}명)"

                        //23:
                        //myLastStatus = 마지막 변경되 프로젝트 신청상태
                        //null : 신청한적없음
                        //ONGOING : 신청진행중
                        //FAIL : 중도포기 / 3일연속 인증글없음(자동포기)
                        //COMPLETE : 66일(프로젝트마다다름) 짜리 모두 수행완료
                        if (mProject.myLastStatus == "ONGOING") {
                            giveUpBtn.isEnabled = true
                            applyBtn.isEnabled = false
                        } else {
                            giveUpBtn.isEnabled = false
                            applyBtn.isEnabled = true
                        }
                    }
                }
            })
    }

}