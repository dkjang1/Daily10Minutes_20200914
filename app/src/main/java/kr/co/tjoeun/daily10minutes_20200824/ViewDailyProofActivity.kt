package kr.co.tjoeun.daily10minutes_20200824

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_view_daily_proof.*

class ViewDailyProofActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_daily_proof)
        setupEvents()
        setValues()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun setupEvents() {

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

                },2020, Calendar.JUNE, 15)
              //}, 2020, 9 ,15)
            datePickerDialog.show()
        }

    }

    override fun setValues() {

    }

}