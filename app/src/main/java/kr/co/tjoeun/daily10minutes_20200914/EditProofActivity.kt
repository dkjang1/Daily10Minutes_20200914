package kr.co.tjoeun.daily10minutes_20200914

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.daily10minutes_20200914.datas.Project

//67:...EditProofActivity
class EditProofActivity : BaseActivity() {

    //67-3:
    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_proof)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        //67-1:인증글작성
        //67-2:postRequestWriteProof
        //69:인증글등록 완료코드(200/400)


    }

    override fun setValues() {

        //68-1
        mProject = intent.getSerializableExtra("project") as Project

    }

}