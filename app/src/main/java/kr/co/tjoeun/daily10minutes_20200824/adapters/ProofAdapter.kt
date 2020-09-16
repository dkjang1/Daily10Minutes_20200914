package kr.co.tjoeun.daily10minutes_20200824.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
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
        var proofContentTxt = row.findViewById<TextView>(R.id.proofContentTxt)
        val proofFirstImg = row.findViewById<ImageView>(R.id.proofFirstImg)
        val writerProfileImg = row.findViewById<ImageView>(R.id.writeProfileImg)
        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        //56-3:버튼연결
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        
        val data = mLIst[position]
        proofContentTxt.text = data.content

        //53:
        //첨부된 이미지가 있을경우 화면표시, 인증글 사진이 없을경우 이미지 숨김
        //Glide.with(mContext).load(user.profileImageArrayList[0]).into(userFirstProfileImg)
        if (data.imgList.size == 0){
            proofFirstImg.visibility = View.GONE
        } else {
            proofFirstImg.visibility = View.VISIBLE
            Glide.with(mContext).load(data.imgList[0]).into(proofFirstImg)
        }

        //55:작성자 프사 / 닉네임 반영
        Glide.with(mContext).load(data.writer.profileImageArrayList[0]).into(writerProfileImg)
        writerNickNameTxt.text = data.writer.nickname //56:? nickName

        //56
        //56-4:좋아요개수 댓글개수 적용
        //likeBtn.text = "좋아요 : ${data.likeCount}개"
        //replyBtn.text = "댓글 : ${data.replyCount}개"

        //58:좋아요버튼클릭할경우 POST호출


        return row
    }


}