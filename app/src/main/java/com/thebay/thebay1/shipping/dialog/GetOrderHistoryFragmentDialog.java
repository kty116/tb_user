package com.thebay.thebay1.shipping.dialog;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.thebay1.login.LoginActivity;
import com.thebay.thebay1.R;
import com.thebay.thebay1.databinding.DialogGetOrderHistoryBinding;
import com.thebay.thebay1.lib.TheBayRestClient;
import com.thebay.thebay1.shipping.model.GetAddressModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;

public class GetOrderHistoryFragmentDialog extends Fragment {

    private DialogGetOrderHistoryBinding binding;
    private String mHtml;


    public static GetOrderHistoryFragmentDialog newInstance() {
        GetOrderHistoryFragmentDialog fragment = new GetOrderHistoryFragmentDialog();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.dialog_get_order_history, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                if (url.equals("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?")) {
                    Log.d(TAG, "onPageFinished: "+url.toString());
                    view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML);"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                }
            }
        });

        binding.webView.getSettings().setJavaScriptEnabled(true); //Javascript를 사용하도록 설정
        binding.webView.addJavascriptInterface(new MyJavascriptInterface(), "Android");

//        binding.webView.loadUrl("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
        binding.webView.loadUrl("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&tabCode=waitSend&pageNum=1");
//        binding.webView.loadUrl("https://member1.taobao.com/member/fresh/deliver_address.htm");

    }

    public class MyJavascriptInterface {

        private File mFolder;
        private String mFileName;

        @JavascriptInterface //킷캣 이상에선 어노테이션을 붙여줘야됨
        public void getHtml(String html) { //위 자바스크립트가 호출되면 여기로 html이 반환됨
//            System.out.println(html);

            String data = html.substring(html.indexOf("var data") + 11); //11은 var data = 를 제외하기 위한 자리수
            String data2 = data.substring(0, data.indexOf("</script>"));
            System.out.print(data2);
            mHtml += "\n" + data2;
            for (int i = 0; i < 3; i++) {
                binding.webView.loadUrl("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&tabCode=waitSend&pageNum="+i);
            }
        }

    }

    public void getHttp(String relativeUrl, RequestParams params) throws JSONException {

        TheBayRestClient.post(relativeUrl, params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
//                EventBus.getDefault().post(new ProgressBarEvent(true));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // 받아온 JSONObject 자료 처리

                Log.d("onHomeSuccess: ", response.toString());

                // TODO: 2017-10-19 데이터 받은거 수정

                ArrayList<GetAddressModel> homeModelList = new ArrayList<>();

                try {
                    String error = response.getString("RstNo");
                    if (error.equals("0")) {
                        JSONArray array = response.getJSONArray("MemAddr");
                        Log.d("onHomeSuccess: ", array.toString());
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            homeModelList.add(new GetAddressModel(object.getString("AddrSeq"), object.getString("AdrsKr"),
                                    object.getString("AdrsEn"), object.getString("Zip"), object.getString("Addr1"),
                                    object.getString("Addr2"), object.getString("Addr1En"), object.getString("Addr2En")
                                    , object.getString("MobNo"), object.getString("RrnNo"), object.getString("RrnCd"),
                                    object.getString("MainYn")));
//                            binding.addressText.setText(homeModelList.toString());
                        }
                        binding.parentLayout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), "로그인 정보가 틀립니다.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

//                Log.d("onHomeSuccess: ", response.toString());
                // 받아온 JSONArray 자료 처리

//                try {
//                    JSONObject object = null;
//                    for (int i = 0; i < response.length(); i++) {
//                        object = response.getJSONObject(i);
//                    }
//                    String error = object.getString("RstNo");
//                    Log.d("onSuccess: ",response.toString());
//
//                    if (error.equals("0")) {
//                        Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
////                        LoginActivity.mMemCode= object.getString("MemCode");
////                        mAuthKey = object.getString("AuthKey");
////                        getKeyInfo();
//
//                    }else {
//                        Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
//                    }
//                    Log.d("onSuccess: ", response.getString(0).toString());
//                } catch(JSONException e) {
//                }
            }

            @Override
            public void onFinish() {
                // 끝나면 호출 되는 메소드
//                EventBus.getDefault().post(new ProgressBarEvent(false));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // 통신 실패시 호출 되는 메소드
                Toast.makeText(getContext(), "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ", throwable.toString());
            }
        });
    }
}
