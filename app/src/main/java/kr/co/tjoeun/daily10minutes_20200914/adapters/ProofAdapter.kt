package kr.co.tjoeun.daily10minutes_20200914.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200914.R
import kr.co.tjoeun.daily10minutes_20200914.datas.Proof
import kr.co.tjoeun.daily10minutes_20200914.utils.ServerUtil
import org.json.JSONObject

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
        writerNickNameTxt.text = data.writer.nickName

        //56-4:좋아요개수,댓글개수 적용
        likeBtn.text = "좋아요 : ${data.likeCount}개"
        replyBtn.text = "댓글 : ${data.replyCount}개"

        //60-2:
        if (data.isMyLikeProof) {
            likeBtn.setBackgroundResource(R.drawable.red_border_box)
            likeBtn.setTextColor(mContext.resources.getColor(R.color.red))
        } else {
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)
            likeBtn.setTextColor(mContext.resources.getColor(R.color.darkGray))
        }


        //58:좋아요버튼클릭할경우 POST호출
        likeBtn.setOnClickListener {
            ServerUtil.postRequestLikeProof(mContext, data.id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val dataObj = json.getJSONObject("data")
                    val likeObj = dataObj.getJSONObject("like")
                    data.likeCount = likeObj.getInt("like_count")

                    //60-3:
                    data.isMyLikeProof = likeObj.getBoolean("my_like")

                    //data의 항목 변경 => 리스트뷰의 내용 변경 발생 => notifyDataSetChanged 실행
                    val myHandler = Handler(Looper.getMainLooper())
                    myHandler.post{
                        notifyDataSetChanged()
                    }

                }
            })
        }

        return row
    }


}