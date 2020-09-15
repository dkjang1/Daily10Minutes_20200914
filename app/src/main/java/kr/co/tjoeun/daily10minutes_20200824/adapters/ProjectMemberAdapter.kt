package kr.co.tjoeun.daily10minutes_20200824.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200824.R
import kr.co.tjoeun.daily10minutes_20200824.datas.Project
import kr.co.tjoeun.daily10minutes_20200824.datas.User

//11:프로젝트목록어댑터(Glide) -> 12:프로젝트목록(MainActivity.kt)
class ProjectMemberAdapter(
    val mContext: Context,
    resId: Int,
    val mLIst: List<User>
) : ArrayAdapter<User>(mContext, resId, mLIst) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        var checkRow = convertView
        if (checkRow == null) {
            checkRow = inf.inflate(R.layout.user_list_item, null)
        }
        val row = checkRow!!

//        var id = 0
//        var email = ""
//        var nickname = ""
//        val profileImageArrayList = ArrayList<String>()

//        val projectImg = row.findViewById<ImageView>(R.id.projectImg)
//        val projectTitle = row.findViewById<TextView>(R.id.projectTitleTxt)
//        val projectDesc = row.findViewById<TextView>(R.id.projectDescTxt)
//        val data = mLIst[position]
//
//        projectTitle.text = data.title
//        projectDesc.text = data.description
//        //11-1:Glide(Manifest+build.gradle)
//        Glide.with(mContext).load(data.imageUrl).into(projectImg)

        return row
    }


}