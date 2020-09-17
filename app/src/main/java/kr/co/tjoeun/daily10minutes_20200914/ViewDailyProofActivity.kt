package kr.co.tjoeun.daily10minutes_20200914

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_daily_proof.*
import kr.co.tjoeun.daily10minutes_20200914.adapters.ProofAdapter
import kr.co.tjoeun.daily10minutes_20200914.datas.Project
import kr.co.tjoeun.daily10minutes_20200914.datas.Proof
import kr.co.tjoeun.daily10minutes_20200914.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class ViewDailyProofActivity : BaseActivity() {

    //45-1:
    lateinit var mProject : Project

    //49:
    val mProofList = ArrayList<Proof>()

    //51:
    lateinit var mProofAdapter : ProofAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_daily_proof)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        //68-2:인증글작성 버튼:writeProofBtn


        selectDateBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                mContext,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    //43:calendar 변수를 생성. Calendar 객체를 담기
                    val selectedDate = Calendar.getInstance()
                    //일자값넣기(기본값:현재시간자동저장)
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    //년월일을 한번에 넣기
                    selectedDate.set(year, month, dayOfMonth)

                    Log.d("선택된년", selectedDate.get(Calendar.YEAR).toString())
                    Log.d("선택된월", selectedDate.get(Calendar.MONTH).toString())
                    Log.d("선택된일", selectedDate.get(Calendar.DAY_OF_MONTH).toString())

                    //날짜가 선택되면 실행해줄 코드
                    //Log.d("선택된월", month.toString())

                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    val selectedDateStr = sdf.format(selectedDate.time)
                    selectDateTxt.text = selectedDateStr

                    //46:
                    getProofListByDate(selectedDateStr)

                },2020, Calendar.JUNE, 15)
              //}, 2020, 9 ,15)
            datePickerDialog.show()
        }

    }

    override fun setValues() {


        //51-1:
        mProofAdapter = ProofAdapter(mContext, R.layout.proof_list_item, mProofList)
        proofListView.adapter = mProofAdapter

        //45-2:
        mProject = intent.getSerializableExtra("project") as Project

        //43-1:화면이 실행되면 오늘날짜를 2020년 9월5일 양식으로 selectedDateTxt 출력
        val todayCal = Calendar.getInstance() //기본값:오늘날짜
        val sdf = SimpleDateFormat("yyyy년 M월 d일")
        val todayDateStr = sdf.format(todayCal.time)
        selectDateTxt.text = todayDateStr

    }

    //45:
    fun getProofListByDate(date:String){

        ServerUtil.getRequestProjectProofByIdAndDate(mContext, mProject.id, date, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                //50:
                val dataObj = json.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val proofsJsonArr = projectObj.getJSONArray("proofs")

                //57:날짜가 달라지면 새로채워준다
                //https://www.nepp.kr/portfolio
                mProofList.clear()

                for (i in   0 until proofsJsonArr.length()) {
                    val proof = Proof.getProofFromJson(proofsJsonArr.getJSONObject(i))
                    mProofList.add(proof)
                }

                //52:프로젝트 프로필 새로고침
                runOnUiThread {
                    mProofAdapter.notifyDataSetChanged()
                }
            }
        })

    }

}