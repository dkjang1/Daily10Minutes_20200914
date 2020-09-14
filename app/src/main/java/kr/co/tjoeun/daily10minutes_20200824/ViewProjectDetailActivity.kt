package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10minutes_20200824.datas.Project

//17:상세화면
class ViewProjectDetailActivity : BaseActivity() {

    //19:
    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        //19
        mProject = intent.getSerializableExtra("project") as Project
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

    }

}