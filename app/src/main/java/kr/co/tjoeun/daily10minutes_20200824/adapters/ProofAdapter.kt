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
import kr.co.tjoeun.daily10minutes_20200824.datas.Proof
import kr.co.tjoeun.daily10minutes_20200824.datas.User

//48
class ProofAdapter(
    val mContext: Context,
    resId: Int,
    val mLIst: List<Proof>
) : ArrayAdapter<Proof>(mContext, resId, mLIst) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        var checkRow = convertView
        if (checkRow == null) {
            checkRow = inf.inflate(R.layout.proof_list_item, null)
        }
        val row = checkRow!!


        //48-1:
        val userFirstProfileImg = row.findViewById<ImageView>(R.id.userFirstProfileImg)
        var userNickNameTxt = row.findViewById<TextView>(R.id.userNickNameTxt)
        var userEmailTxt = row.findViewById<TextView>(R.id.userEmailTxt)

        val user = mLIst[position]
        userNickNameTxt.text = user.nickname
        userEmailTxt.text = user.email
        Glide.with(mContext).load(user.profileImageArrayList[0]).into(userFirstProfileImg)

        return row
    }


}