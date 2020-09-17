package kr.co.tjoeun.daily10minutes_20200824.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

//4:API통신모듈(POST/GET/PUT) -> 5:API데이터(Context.kt)
class ServerUtil {

    //4-1:서버응답에 대한 실행할 인터페이스
    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    //companion object : 변수+함수 객체를 이용하지않고 클래스 기능으로 활용
    companion object {

        //private : 내부에서만 사용가능하다
        private val BASE_URL = "http://15.164.153.174" //호스트주소

        //4-2:RestAPI.로그인 - postRequestLogin함수만들기
        fun postRequestLogin(id: String, pw: String, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlStr = "${BASE_URL}/user" //주소완성
            //파라미터(POST/PUT/PATCH) 값
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()
            //파라미터(POST/PUT/PATCH) 값 보내기
            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .build()

            client.newCall(request).enqueue(object : Callback {
                //서버연결성공할경우
                override fun onResponse(call: Call, response: Response) {
                    //서버가 보내준 본문
                    val bodyString = response.body!!.string()
                    //String -> jSON Object 변환
                    val json = JSONObject(bodyString)
                    Log.d("postRequestLogin", json.toString())
                    //{"code":400,"message":"존재하지 않는 이메일입니다."}
                    //{"code":400,"message":"비밀번호가 틀립니다."}
                    //{"code":200,"message":"로그인 성공.","data":{"user":{...},"token":"..."}}
                    //if (handler != null) handler.onResponse(json)
                    handler?.onResponse(json)
                } //onResponse

                //서버연결실패할경우
                override fun onFailure(call: Call, e: IOException) {
                }
            }) //client.newCall(request).enqueue
        } //postRequestLogin

        //4-3:RestAPI.이메일중복확인
        fun getRequestEmailCheck(emailAddress: String, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작

            val urlBuilder = ("${BASE_URL}/email_check").toHttpUrlOrNull()!!.newBuilder() //주소완성
            urlBuilder.addEncodedQueryParameter("email", emailAddress)

            //val urlStr = "${BASE_URL}/email_check?email=" + emailAddress //주소완성
            val urlStr = urlBuilder.build().toString()
            val request = Request.Builder() //파라미터(POST/PUT/PATCH) 값 보내기
                .url(urlStr)
                .get()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)
                    Log.d("getRequestEmailCheck", json.toString())
                    handler?.onResponse(json)
                }

                override fun onFailure(call: Call, e: IOException) {
                }

            }) //client.newCall(request).enqueue
        } //getRequestEmailCheck

        //4-4:RestAPI.회원가입
        fun putRequestSignUp(id: String, pw: String, nickName: String, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlStr = "${BASE_URL}/user" //주소완성
            //파라미터(POST/PUT/PATCH) 값
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .add("nick_name", nickName)
                .build()
            //파라미터(POST/PUT/PATCH) 값 보내기
            val request = Request.Builder()
                .url(urlStr)
                .put(formData)
                .build()

            client.newCall(request).enqueue(object : Callback {
                //서버연결성공할경우
                override fun onResponse(call: Call, response: Response) {

                    //서버가 보내준 본문
                    val bodyString = response.body!!.string()
                    //String -> jSON Object 변환
                    val json = JSONObject(bodyString)
                    Log.d("putRequestSignUp", json.toString())
                    handler?.onResponse(json)

                }

                //서버연결실패할경우
                override fun onFailure(call: Call, e: IOException) {

                }
            }) //client.newCall(request).enqueue
        } //putRequestSignUp

        //13:RestAPI.프로젝트목록
        fun getRequestProjectList(context: Context, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlBuilder = ("${BASE_URL}/project").toHttpUrlOrNull()!!.newBuilder() //주소완성
            val urlStr = urlBuilder.build().toString()
            val request = Request.Builder() //파라미터(POST/PUT/PATCH) 값 보내기
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)
                    Log.d("getRequestProjectList", json.toString())
                    handler?.onResponse(json)
                }

                override fun onFailure(call: Call, e: IOException) {
                }

            }) //client.newCall(request).enqueue
        } //getRequestProjectList

        //20:RestAPI.프로젝트상세
        fun getRequestProjectDetailById(context: Context, projectId: Int, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlBuilder =
                ("${BASE_URL}/project/${projectId}").toHttpUrlOrNull()!!.newBuilder() //주소완성
            val urlStr = urlBuilder.build().toString()
            val request = Request.Builder() //파라미터(POST/PUT/PATCH) 값 보내기
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)
                    Log.d("getRequestProjectDetailById", json.toString())
                    handler?.onResponse(json)
                }

                override fun onFailure(call: Call, e: IOException) {
                }

            }) //client.newCall(request).enqueue
        } //getRequestProjectDetailById

        //24:RestAPI.프로젝트등록
        fun postRequestApplyProject(context: Context, projectId: Int, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlStr = "${BASE_URL}/project" //주소완성
            //파라미터(POST/PUT/PATCH) 값 - formData활용
            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .build()
            //파라미터(POST/PUT/PATCH) 값 보내기
            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                //서버연결성공할경우
                override fun onResponse(call: Call, response: Response) {
                    //서버가 보내준 본문
                    val bodyString = response.body!!.string()
                    //String -> jSON Object 변환
                    val json = JSONObject(bodyString)
                    Log.d("postRequestApplyProject", json.toString())
                    //{"code":400,"message":"존재하지 않는 이메일입니다."}
                    //{"code":400,"message":"비밀번호가 틀립니다."}
                    //{"code":200,"message":"로그인 성공.","data":{"user":{...},"token":"..."}}
                    //if (handler != null) handler.onResponse(json)
                    handler?.onResponse(json)
                } //onResponse

                //서버연결실패할경우
                override fun onFailure(call: Call, e: IOException) {
                }
            }) //client.newCall(request).enqueue
        } //postRequestApplyProject

        //35:RestAPI.참여인원수
        fun getRequestProjectMemberById(context: Context, projectId: Int, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlBuilder =
                ("${BASE_URL}/project/${projectId}").toHttpUrlOrNull()!!.newBuilder() //주소완성
            urlBuilder.addEncodedQueryParameter("need_user_list","true").toString()
            val urlStr = urlBuilder.build().toString()
            val request = Request.Builder() //파라미터(POST/PUT/PATCH) 값 보내기
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)
                    Log.d("getRequestProjectMemberById", json.toString())
                    handler?.onResponse(json)
                }

                override fun onFailure(call: Call, e: IOException) {
                }

            }) //client.newCall(request).enqueue
        } //getRequestProjectMemberById

        //44:RestAPI.날짜별인증글(프로젝트의 해당 날짜에 맞는 인증글을 가져오기 위한 API)
        fun getRequestProjectProofByIdAndDate(context: Context, projectId: Int, date: String, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlBuilder =
                ("${BASE_URL}/project/${projectId}").toHttpUrlOrNull()!!.newBuilder() //주소완성
            urlBuilder.addEncodedQueryParameter("proof_date",date).toString()
            val urlStr = urlBuilder.build().toString()
            val request = Request.Builder() //파라미터(POST/PUT/PATCH) 값 보내기
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)
                    Log.d("getRequestProjectMemberById", json.toString())
                    handler?.onResponse(json)
                }

                override fun onFailure(call: Call, e: IOException) {
                }

            }) //client.newCall(request).enqueue
        } //getRequestProjectMemberById

        //58-1:RestAPI.좋아요버튼클릭할경우 POST호출
        fun postRequestLikeProof(context: Context, proofId: Int, handler: JsonResponseHandler?) {
            val client = OkHttpClient() //클라언트동작
            val urlStr = "${BASE_URL}/like_proof" //주소완성
            //파라미터(POST/PUT/PATCH) 값 - formData활용
            val formData = FormBody.Builder()
                .add("proof_id", proofId.toString())
                .build()
            //파라미터(POST/PUT/PATCH) 값 보내기
            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                //서버연결성공할경우
                override fun onResponse(call: Call, response: Response) {
                    //서버가 보내준 본문
                    val bodyString = response.body!!.string()
                    //String -> jSON Object 변환
                    val json = JSONObject(bodyString)
                    Log.d("postRequestLikeProof", json.toString())
                    //{"code":400,"message":"존재하지 않는 이메일입니다."}
                    //{"code":400,"message":"비밀번호가 틀립니다."}
                    //{"code":200,"message":"로그인 성공.","data":{"user":{...},"token":"..."}}
                    //if (handler != null) handler.onResponse(json)
                    handler?.onResponse(json)
                } //onResponse

                //서버연결실패할경우
                override fun onFailure(call: Call, e: IOException) {
                }
            }) //client.newCall(request).enqueue
        } //postRequestLikeProof


    } //companion object
}