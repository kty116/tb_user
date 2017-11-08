package com.thebay.thebay1;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thebay.thebay1.databinding.ActivityOrderListBinding;
import com.thebay.thebay1.event.MessageEvent;
import com.thebay.thebay1.event.ProgressBarEvent;
import com.thebay.thebay1.shipping.model.ShippingProductModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class OrderListActivity extends ParentActivity implements View.OnClickListener {

    private ActivityOrderListBinding binding;
    private ArrayList<String> mDataList;
    private int mTotalPage;
    private int mCurrentPage;  //각 버튼 누를 때 1로 값 설정됨
    private int mIndex;
    private String mUrl;
    private String TAG = "OrderListActivity";
    private ArrayList<ShippingProductModel> mProductList;
    private String mSize;
    private String mColor;
    private String mTragkingNumberUrl = "https://buyertrade.taobao.com/trade/json/transit_step.do?bizOrderId=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_list);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("오더내역 가져오기");

        binding.sendButton.setOnClickListener(this);
        binding.confirmButton.setOnClickListener(this);
        binding.getOrderHistoryButton.setOnClickListener(this);
        binding.progressLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mProductList = new ArrayList<>();

        binding.workWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

//        WebSettings[] webSettingses = {binding.showWebView.getSettings(), binding.workWebView.getSettings()};

        WebSettings webSettings = binding.workWebView.getSettings();

//        for (int i = 0; i < webSettingses.length; i++) {
        webSettings.setSaveFormData(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
//
        if (Build.VERSION.SDK_INT >= 16) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webSettings.setJavaScriptEnabled(true);
        // JavaScript의 window.open 허용
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        // web client 를 chrome 으로 설정
//        }
//        binding.showWebView.setWebChromeClient(new WebChromeClient());
//        binding.showWebView.setWebViewClient(new ShowWebClient());
//        binding.showWebView.loadUrl("https://h5.m.taobao.com/mlapp/olist.html?tabCode=waitSend");

        mDataList = new ArrayList<>();
//        binding.workWebView.getSettings().setJavaScriptEnabled(true);
        binding.workWebView.addJavascriptInterface(new MyJavascriptInterface(), "Android");
//        mwebView.loadData(str,"text/html", “UTF-8″); // Android 4.0 이하 버전

//
//        binding.workWebView.getSettings().setDefaultTextEncodingName("utf-8");

        binding.workWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                EventBus.getDefault().post(new ProgressBarEvent(true));

//                Log.d(TAG, "onPageStarted: 누름");
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Document doc = Jsoup.connect("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&tabCode=waitConfirm").get();
//                            Log.d(TAG, "onPageStarted: "+doc);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                thread.start();


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?")) {
                    view.loadUrl("javascript:window.Android.getTaobaoDataHtml(document.getElementsByTagName('body')[0].innerHTML);"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                    if (mCurrentPage <= mTotalPage) {
                        ++mCurrentPage;
                        binding.workWebView.loadUrl(mUrl + mCurrentPage);
                    }
                }else if(url.contains(mTragkingNumberUrl)) {
                    view.loadUrl("javascript:window.Android.getTrackingDataHtml(document.getElementsByTagName('body')[0].innerHTML);");
//                    if(mIndex <= mProductList.size()){
//                        ++mIndex;
//                        binding.workWebView.loadUrl(mTragkingNumberUrl + mProductList.get(mIndex).getOrderNumber());
//
//                    }
                }
            }
        });
        //waitConfirm

        binding.workWebView.loadUrl("https://h5.m.taobao.com/mlapp/mytaobao.html#mlapp-mytaobao");
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.send_button:
                mCurrentPage = 1;
                binding.progressLayout.setVisibility(View.VISIBLE);
                mUrl = "https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?tabCode=waitConfirm&pageNum=";
                binding.workWebView.loadUrl(mUrl + mCurrentPage);
//                binding.workWebView.loadUrl("https://cart.taobao.com/cart.htm?spm=a1z09.2.1997525049.1.54ff7614Ud2jiO&from=mini&ad_id=&am_id=&cm_id=&pm_id=1501036000a02c5c3739");
                break;
            case R.id.confirm_button:
                mCurrentPage = 1;
                binding.progressLayout.setVisibility(View.VISIBLE);
                mUrl = "https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?tabCode=waitRate&pageNum=";
                binding.workWebView.loadUrl(mUrl + mCurrentPage);
                break;
            case R.id.get_order_history_button:
                mIndex = 0;
                binding.workWebView.loadUrl(mTragkingNumberUrl+mProductList.get(mIndex).getOrderNumber());
                Log.d(TAG, "onClick: "+mProductList.get(mIndex).getOrderNumber());

//                for (int i = 0; i < mProductList.size(); i++) {
//                    ShippingActivity.sProductModelList.add(mProductList.get(i));
//                }

//                EventBus.getDefault().post(new OrderListDataEvent(mProductList));
                break;
        }
    }

    public class MyJavascriptInterface {

        private File mFolder;
        private String mFileName;

        @JavascriptInterface //킷캣 이상에선 어노테이션을 붙여줘야됨
        public void getTaobaoDataHtml(String html) { //위 자바스크립트가 호출되면 여기로 html이 반환됨
//            Log.d(TAG, "getHtml webview: "+html);
//            System.out.println("getHtml"+html);
//            Log.d(TAG, "getHtml: "+html);
            String data = html.substring(html.indexOf("var data") + 23); //11은 var data = 를 제외하기 위한 자리수
            String data2 = data.substring(0, data.indexOf("</script>"));
            String data3 = data2.substring(0, data2.lastIndexOf("'"));
            Log.d(TAG, "getHtml33: " + data3);

            mDataList.add(data3.replace("\\\"", "\""));
            Log.d(TAG, "getHtml: " + mDataList.get(0).toString());
//
            JSONObject jsonObject = null;
//
            try {
                jsonObject = new JSONObject(mDataList.get(0));
                JSONArray mainOrdersArray = jsonObject.getJSONArray("mainOrders");
                JSONObject page = jsonObject.getJSONObject("page");
                mTotalPage = Integer.parseInt(page.getString("totalPage"));  //이게 0이면 돌릴필요 없음 토스트로 오더 내역 없다고 표시하기
                Log.d(TAG, "getHtml: page" + mTotalPage);
                for (int i = 0; i < mainOrdersArray.length(); i++) {
                    JSONObject object = mainOrdersArray.getJSONObject(i);
                    JSONArray subOrderList = object.getJSONArray("subOrders");
                    String rOrderId = valuesNullCheck(object.getString("id"));
                    for (int j = 0; j < subOrderList.length(); j++) {
                        JSONObject subOrder = subOrderList.getJSONObject(j);
                        String rQuantity = valuesNullCheck(subOrder.getString("quantity"));
                        JSONObject priceInfo = subOrder.getJSONObject("priceInfo");
                        String rPrice = valuesNullCheck(priceInfo.getString("realTotal"));

                        JSONObject itemInfo = subOrder.getJSONObject("itemInfo");

                        String rTitle = valuesNullCheck(itemInfo.getString("title"));
                        String rItemUrl = valuesNullCheck(itemInfo.getString("itemUrl"));
                        String rImageUrl = "";
                        if (itemInfo.toString().contains("\"pic\"")) {
                            rImageUrl = valuesNullCheck(itemInfo.getString("pic"));
                        }
                        if (itemInfo.toString().contains("\"skuText\"")) {
                            JSONArray skuTexts = itemInfo.getJSONArray("skuText");
                            for (int k = 0; k < skuTexts.length(); k++) {
                                JSONObject skuTextObject = skuTexts.getJSONObject(k);

                                if (skuTextsKeyCheck(skuTextObject.getString("name")).equals("size")) {
                                    mSize = valuesNullCheck(skuTextObject.getString("value"));
                                    Log.d(TAG, "getHtml: " + mSize);
                                } else if (skuTextsKeyCheck(skuTextObject.getString("name")).equals("color")) {
                                    mColor = valuesNullCheck(skuTextObject.getString("value"));
                                    Log.d(TAG, "getHtml: " + mColor);
                                }
                            }
                        }
                        mProductList.add(new ShippingProductModel("Taobao", rOrderId, "김경애", 0, rTitle, "트래킹번호", rPrice, rQuantity, mColor, mSize, rItemUrl, rImageUrl));
                    }

                }
                Log.d(TAG, "getHtml: " + mProductList.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (mCurrentPage >= mTotalPage) {
                EventBus.getDefault().post(new ProgressBarEvent(false));
            }
        }
        @JavascriptInterface //킷캣 이상에선 어노테이션을 붙여줘야됨
        public void getTrackingDataHtml(String html) { //위 자바스크립트가 호출되면 여기로 html이 반환됨
//            Log.d(TAG, "getHtml webview: "+html);
//            System.out.println("getHtml"+html);
            Log.d(TAG, "getHtml: "+html);

//            if (mCurrentPage >= mTotalPage) {
//                EventBus.getDefault().post(new ProgressBarEvent(false));
//            }
        }

    }

    public String valuesNullCheck(String value) {
        return (value == null) ? "" : value.toString();
    }

    public String skuTextsKeyCheck(String key) {
        if (key.equals("颜色分类")) {
            return "color";
        } else if (key.equals("尺寸")) {
            return "size";
        }
        return "";
    }

    class ShowWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
            Log.d("shouldOverride", url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            binding.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            binding.progressBar.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && binding.workWebView.canGoBack()) {
            binding.workWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event instanceof ProgressBarEvent) {
            ProgressBarEvent progressBarEvent = (ProgressBarEvent) event;
            boolean progressbarShown = progressBarEvent.isShow();
            if (progressbarShown) {
                binding.progressLayout.setVisibility(View.VISIBLE);
            } else {
                binding.progressLayout.setVisibility(View.GONE);
            }
        }
    }
//        if (event instanceof FragmentReplaceEvent) {
//            FragmentReplaceEvent fragmentReplaceEvent = (FragmentReplaceEvent) event;
//            if (fragmentReplaceEvent.isToolbarHide()) {
//                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//                Toast.makeText(this, "숨김", Toast.LENGTH_SHORT).show();
//            } else {
//                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//                params.setScrollFlags(0);
//                Toast.makeText(this, "보임", Toast.LENGTH_SHORT).show();
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragmentReplaceEvent.getFragment()).addToBackStack("null").commit();
//            setTitle(fragmentReplaceEvent.getFragmentName());
//
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
//        String url = getIntent().getStringExtra("url");
//        if (url != null) {
//            EventBus.getDefault().post(new WebviewUrlEvent(url));
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
