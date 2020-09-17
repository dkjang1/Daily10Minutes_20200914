package kr.co.tjoeun.daily10minutes_20200914

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10minutes_20200914.datas.Project
import kr.co.tjoeun.daily10minutes_20200914.utils.ServerUtil
import org.json.JSONObject

//19:프로젝트상세페이지 ->
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

        //42:
        viewDailyProofBtn.setOnClickListener {

            val myIntent = Intent(mContext, ViewDailyProofActivity::class.java)
            //34:어떤 프로젝트의인증글 보고싶은지 전달용
            myIntent.putExtra("project", mProject)
            startActivity(myIntent)

        }

        //28:
        viewAllMembersBtn.setOnClickListener{

            val myIntent = Intent(mContext, ViewProjectMembersActivity::class.java)
            //34:어떤 프로젝트의멤버를 보고싶은지 전달용
            myIntent.putExtra("project", mProject)
            startActivity(myIntent)

        }

        //25:
        applyBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("프로젝트 참가확인")
            //독서하기 프로젝트에 참가하시겠습니까?
            alert.setMessage("${mProject.title} 프로젝트에 참가하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                //프로젝트 신청처리
                ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
                    override fun onResponse(json: JSONObject) {

                        //getProjectDetailFromServer() //신청후 새로고침
                        //자동 새로고침이 구현은 되지만 => 서버를 한번 더 다녀와야함.
                        //신청 결과에서 알려주는 데이터를 화면에 반영.
                        val code = json.getInt("code")
                        if(code == 200){
                            val data = json.getJSONObject("data")
                            val projectObj = data.getJSONObject("project")
                            mProject = Project.getProjectFromJson(projectObj)
                        }
                        runOnUiThread{
                            Toast.makeText(mContext, "프로젝트 참가신청 완료", Toast.LENGTH_SHORT).show()
                            //인원수 참가인원등 변경
                            refreshProjectDataToUI()
                        }
                    }
                })
            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }
    }

    override fun setValues() {

        //19-2
        mProject = intent.getSerializableExtra("project") as Project
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

    }

    //21:프로젝트상세.가져오기
    override fun onResume() {
        super.onResume()
        getProjectDetailFromServer()
    }

    //23:프로젝트상세데이터처리
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
                        refreshProjectDataToUI()
//                        proofMethodTxt.text = mProject.proofMethod
//                        onGoingMemberCountTxt.text = "(현재 참여인원 : ${mProject.onGoingMemberCount}명)"
//
//                        //23:
//                        //myLastStatus = 마지막 변경되 프로젝트 신청상태
//                        //null : 신청한적없음
//                        //ONGOING : 신청진행중
//                        //FAIL : 중도포기 / 3일연속 인증글없음(자동포기)
//                        //COMPLETE : 66일(프로젝트마다다름) 짜리 모두 수행완료
//                        if (mProject.myLastStatus == "ONGOING") {
//                            giveUpBtn.isEnabled = true
//                            applyBtn.isEnabled = false
//                        } else {
//                            giveUpBtn.isEnabled = false
//                            applyBtn.isEnabled = true
//                        }
                    }
                }
            })
    }


    //26:서버에서 보내준 프로젝트정보(mProject)를 새로 반영하는 기능
    fun refreshProjectDataToUI(){
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