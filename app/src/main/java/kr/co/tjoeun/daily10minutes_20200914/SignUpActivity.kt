package kr.co.tjoeun.daily10minutes_20200914

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.daily10minutes_20200914.utils.ServerUtil
import org.json.JSONObject

//8:회원가입 -> 9:프로젝트목록(activity_main.xml)
class SignUpActivity : BaseActivity() {

    //8-1:이메일중복확인
    //아이디 중복검사 통과여부
    var isIdok = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        //8-1:이메일중복확인
        emailCheckBtn.setOnClickListener {
            //입력한 이메일확인 - 서버중복검사결과요청
            val inputEmail = signUpEmailEdt.text.toString()

            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val codeNum = json.getInt("code")
                    val message = json.getString("message")

                    runOnUiThread {
                        if (codeNum == 200) {
                            emailCheckResultTxt.text = message
                            isIdok = true
                        } else {
                            emailCheckResultTxt.text = message
                            isIdok = false
                        }
                    }
                }
            }) //ServerUtil.getRequestEmailCheck
        } //signUpEmailCheckBtn.setOnClickListener

        //8-2:이메일입력내용이 변경된경우 중복검사 확인
        signUpEmailEdt.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("입력문구", p0.toString())
                emailCheckResultTxt.text = "중복확인을 해주세요.."
                isIdok = false
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        //8-3:비밀번호 유효성
        //비밀번호 입력내용 확인문구출력
        //0자 : 비밀번호를 입력해주세요..
        //8자미만 : 비밀번호가 너무 짧습니다.
        //8자이상 : 사용해도 좋은 비밀번호입니다.
        signUpPasswordEdt.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when (p0.toString().length) {
                    0 -> passwordCheckResultTxt.text = "비밀번호를 입력해주세요.."
                    in 1..8 -> passwordCheckResultTxt.text = "비밀번호가 너무 짧습니다."
                    else -> passwordCheckResultTxt.text = "사용해도 좋은 비밀번호입니다."
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        //8-4:회원가입버튼
        signUpBtn.setOnClickListener {
            //아이디 유효성
            if (!isIdok) {
                //사용할수 없을경우
                Toast.makeText(mContext, "아이디중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //비밀번호 유효성
            if (signUpPasswordEdt.text.length < 8) {
                Toast.makeText(mContext, "비밀번호는 8자이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //닉네임은 한번정하면 변경할수 없습니다. 정말 회원가입 하시겠습니까?
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("회원가입 안내")
            alert.setMessage("닉네임은 한번정하면 변경할수 없습니다. 정말 회원가입 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

                //회원가입성공일경우 회원이 되신것을 환영합니다. : 토스트+로그인화면 복귀
                //회원가입실패할경우 서버가 알려주는 토스트메세지
                val inputId = signUpEmailEdt.text.toString()
                val inputPw = signUpPasswordEdt.text.toString()
                val inputNickName = nickNameEdt.text.toString()

                //회원가입 API호출
                ServerUtil.putRequestSignUp(
                    inputId,
                    inputPw,
                    inputNickName,
                    object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(json: JSONObject) {
                            val codeNum = json.getInt("code")
                            val message = json.getString("message")

                            runOnUiThread {
                                if (codeNum == 200) {
                                    Toast.makeText(mContext, "회원이 되신것을 환영합니다", Toast.LENGTH_SHORT).show()
                                    finish()
                                } else {
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }) //ServerUtil.putRequestSignUp
            }) //alert.setPositiveButton
            alert.setNegativeButton("취소", null)
            alert.show()
        } //signUpBtn.setOnClickListener

    }

    override fun setValues() {
        //member1
        signUpEmailEdt.setText("dkjang@naver.com")
        signUpPasswordEdt.setText("@1234qwer")
        nickNameEdt.setText("하늘나무")

        //member2
        signUpEmailEdt.setText("test2@naver.com")
        signUpPasswordEdt.setText("test2test")
        nickNameEdt.setText("test2")
    }

}