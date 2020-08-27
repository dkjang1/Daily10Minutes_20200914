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

//12
class ProjectAdapter(
    val mContext: Context,
    resId: Int,
    val mLIst: List<Project>
) : ArrayAdapter<Project>(mContext, resId, mLIst) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        var checkRow = convertView
        if (checkRow == null) {
            checkRow = inf.inflate(R.layout.project_list_item, null)
        }
        val row = checkRow!!

        val projectImg = row.findViewById<ImageView>(R.id.projectImg)
        val projectTitle = row.findViewById<TextView>(R.id.projectTitleTxt)
        val projectDesc = row.findViewById<TextView>(R.id.projectDescTxt)
        val data = mLIst[position]

        projectTitle.text = data.title
        projectDesc.text = data.description
        //14:Glide
        Glide.with(mContext).load(data.imageUrl).into(projectImg)

        return row
    }


}