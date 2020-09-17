package kr.co.tjoeun.daily10minutes_20200914.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200914.R
import kr.co.tjoeun.daily10minutes_20200914.datas.User

//32:
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


        //41:
        val userFirstProfileImg = row.findViewById<ImageView>(R.id.userFirstProfileImg)
        var userNickNameTxt = row.findViewById<TextView>(R.id.userNickNameTxt)
        var userEmailTxt = row.findViewById<TextView>(R.id.userEmailTxt)

        val user = mLIst[position]
        userNickNameTxt.text = user.nickName
        userEmailTxt.text = user.email
        Glide.with(mContext).load(user.profileImageArrayList[0]).into(userFirstProfileImg)

//        val projectImg = row.findViewById<ImageView>(R.id.projectImg)
//        val projectTitle = row.findViewById<TextView>(R.id.projectTitleTxt)
//        val projectDesc = row.findViewById<TextView>(R.id.projectDescTxt)
//        val data = mLIst[position]
//        projectTitle.text = data.title
//        projectDesc.text = data.description
//        //11-1:Glide(Manifest+build.gradle)
//        Glide.with(mContext).load(data.imageUrl).into(projectImg)

        return row
    }


}