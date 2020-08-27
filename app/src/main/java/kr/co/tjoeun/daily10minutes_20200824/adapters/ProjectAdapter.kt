package kr.co.tjoeun.daily10minutes_20200824.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        return row
    }


}