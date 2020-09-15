package kr.co.tjoeun.daily10minutes_20200824

import android.app.DatePickerDialog
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
            val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //날짜가 선택되면 실행해줄 코드
                Log.d("선택된월", month.toString())

            }, 2020, 9 ,15)
            datePickerDialog.show()
        }

    }

    override fun setValues() {

    }

}