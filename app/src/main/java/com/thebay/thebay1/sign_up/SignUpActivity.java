package com.thebay.thebay1.sign_up;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.thebay1.MainActivity;
import com.thebay.thebay1.ParentActivity;
import com.thebay.thebay1.R;
import com.thebay.thebay1.Security;
import com.thebay.thebay1.common.CommonLib;
import com.thebay.thebay1.databinding.ActivitySignUpBinding;
import com.thebay.thebay1.dto.KeyDTO;
import com.thebay.thebay1.lib.TheBayRestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.thebay.thebay1.common.CommonLib.id;
import static com.thebay.thebay1.common.CommonLib.password;

public class SignUpActivity extends ParentActivity implements View.OnClickListener {
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private String mLanguageValue;
    private ActivitySignUpBinding binding;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    public static KeyDTO sKeyDTO = null;
    public static String mMemCode;
    public static String mAuthKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        ArrayList<String> subscriptionPathSpinnerList = new ArrayList<>();
        subscriptionPathSpinnerList.add("가입경로 선택");
        subscriptionPathSpinnerList.add("카페내 검색으로");
        subscriptionPathSpinnerList.add("추천을 받음");
        subscriptionPathSpinnerList.add("타 카페를 통해서");
        subscriptionPathSpinnerList.add("네이버 블로그");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_text, subscriptionPathSpinnerList);
        binding.signUp2.subscriptionPathSpinner.setAdapter(adapter);

        binding.bottomButton.setOnClickListener(this);

    }

    public void getHttp(final String relativeUrl, RequestParams params) throws JSONException {

        TheBayRestClient.post(relativeUrl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // 받아온 JSONArray 자료 처리
                try {
                    JSONObject object = null;
                    for (int i = 0; i < response.length(); i++) {
                        object = response.getJSONObject(i);
                    }
                    String error = object.getString("RstNo");
                    Log.d("onSuccess: ", response.toString());

                    if (error.equals("0")) {
                        if ((mPref.getString(CommonLib.authKey, null) != null) || (mPref.getString(CommonLib.memCode, null) != null)) {
                            mEditor.remove(CommonLib.authKey);
                            mEditor.remove(CommonLib.memCode);
                            mEditor.commit();
                        }

                        mEditor.putString(CommonLib.authKey, object.getString("AuthKey")); //현재 로그인 id값
                        mEditor.putString(CommonLib.memCode, object.getString("MemCode"));
                        mEditor.commit();

                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        finish();

                    } else {
                        Toast.makeText(SignUpActivity.this, "아이디 또는 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("onSuccess: ", response.getString(0).toString());
                } catch (JSONException e) {
                }
            }

            @Override
            public void onFinish() {
                // 끝나면 호출 되는 메소드
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(SignUpActivity.this, "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (binding.signUp2.parentLayout.getVisibility() == View.VISIBLE) {
            binding.termsBar.setVisibility(View.VISIBLE);
            binding.infoBar.setVisibility(View.INVISIBLE);
            binding.confirmBar.setVisibility(View.INVISIBLE);
            binding.signUp1.parentLayout.setVisibility(View.VISIBLE);
            binding.signUp2.parentLayout.setVisibility(View.INVISIBLE);
            binding.signUp3.parentLayout.setVisibility(View.INVISIBLE);
            binding.bottomButton.setText("다음 페이지");
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bottom_button:

                if (binding.signUp1.parentLayout.getVisibility() == View.VISIBLE) {
                    if (binding.signUp1.checkbox1.isChecked() && binding.signUp1.checkbox2.isChecked()) {
                        binding.termsBar.setVisibility(View.INVISIBLE);
                        binding.infoBar.setVisibility(View.VISIBLE);
                        binding.confirmBar.setVisibility(View.INVISIBLE);
                        binding.signUp1.parentLayout.setVisibility(View.INVISIBLE);
                        binding.signUp2.parentLayout.setVisibility(View.VISIBLE);
                        binding.signUp3.parentLayout.setVisibility(View.INVISIBLE);
                        binding.bottomButton.setText("회원 가입");
                    }else {
                        Toast.makeText(this, "약관에 동의에 모두 체크 하셔야 가입할 수 있습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (binding.signUp2.parentLayout.getVisibility() == View.VISIBLE) {
                    binding.termsBar.setVisibility(View.INVISIBLE);
                    binding.infoBar.setVisibility(View.INVISIBLE);
                    binding.confirmBar.setVisibility(View.VISIBLE);
                    binding.signUp1.parentLayout.setVisibility(View.INVISIBLE);
                    binding.signUp2.parentLayout.setVisibility(View.INVISIBLE);
                    binding.signUp3.parentLayout.setVisibility(View.VISIBLE);
                    binding.bottomButton.setText("더베이 메인화면으로");
                } else if (binding.signUp3.parentLayout.getVisibility() == View.VISIBLE) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

                // TODO: 2017-10-27 1. 값 받은거 서버로 넘기는 작업 2.키보드 올라올때 화면 안짤리게 조정하는거 3.주소 가져오는거 웹뷰해서 연결

                break;

            case R.id.confirm_button:
                //서버 통신후 로그인

//                String id = binding.idText.getText().toString();
//                String password = binding.passwordText.getText().toString();

                //id랑 password 빈 값 확인
                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(password)) {
                    if (TextUtils.isEmpty(id)) {
//                        editTextNullCheck(binding.idTextLayout, binding.passwordTextLayout, "아이디를 입력해주세요.");
                        Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(this, "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    if ((mPref.getString(id, null) != null) || (mPref.getString(password, null) != null)) {
                        mEditor.remove(id);
                        mEditor.remove(password);
                        mEditor.commit();
                    }
                    mEditor.putString(id, id); //현재 로그인 id값
                    mEditor.putString(password, password); //현재 로그인 password값
//                    mEditor.putString("loginPageId", id);
//                    mEditor.putString("loginPagePassword", password);
                    mEditor.commit();
                    RequestParams params = new RequestParams();

                    String enId = Security.encrypt(id, "EJQPDL@)!&!))DJR").toString();
                    String enPw = Security.encrypt(password, "EJQPDL@)!&!))DJR").toString();
                    String enTp = Security.encrypt("U", "EJQPDL@)!&!))DJR").toString();

                    params.put("MemId", enId);
                    params.put("MemPw", enPw);
                    params.put("EnterTp", enTp);
                    params.put("AppVer", Build.MODEL);
                    params.put("ModelNo", Build.VERSION.RELEASE);

                    try {
                        getHttp("Login_M.php", params);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //로그인
                break;
        }
    }

    public void editTextNullCheck(TextInputLayout nullLayout, TextInputLayout layout, String errorMessage) {
        nullLayout.setError(errorMessage);
        layout.setError(null);
        layout.setErrorEnabled(false);
    }
}
