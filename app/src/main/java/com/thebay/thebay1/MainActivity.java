package com.thebay.thebay1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.thebay1.common.CommonLib;
import com.thebay.thebay1.databinding.ActivityMain2Binding;
import com.thebay.thebay1.dto.KeyDTO;
import com.thebay.thebay1.lib.TheBayRestClient;
import com.thebay.thebay1.login.LoginActivity;
import com.thebay.thebay1.main.ButtonModel;
import com.thebay.thebay1.main.NoticeFragment;
import com.thebay.thebay1.main.noticeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;

public class MainActivity extends ParentActivity implements Serializable, View.OnClickListener {

    private final long FINISH_INTERVAL_TIME = 2000;
    private int mClickId = -1;
    private long backPressedTime = 0;
    private ActivityMain2Binding binding;
    private ImageView[] mImageViews;
    private int[] mResourceImgs;
    private ArrayList<noticeModel> mNoticeList;
    private boolean isMovePageThread = true;
    private ArrayList<ButtonModel> mButtonImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        setTitle("THE BAY");

        setSupportActionBar(binding.toolbar);

        RequestParams params = new RequestParams();

        KeyDTO keyInfo = CommonLib.getKeyInfo(this);

        if (keyInfo.getAuthKey() != null) {
            params.put("AuthKey", keyInfo.getAuthKey());
            params.put("MemCode", keyInfo.getMemberCode());
        }
        params.put("PageNm", "메인");
        params.put("AppVer", Build.MODEL);
        params.put("ModelNo", Build.VERSION.RELEASE);

        try {
            getHttp("Main.php", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mImageViews = new ImageView[]{binding.mainButton1, binding.mainButton2, binding.mainButton3, binding.mainButton4, binding.mainButton5, binding.mainButton6};
//        addCustomImageInputView(R.layout.toolbar_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, MainFragment.newInstance()).commit();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
//        menu.findItem(R.id.action_language_change).setTitle(mLanguageValue);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

//        if (mClickId != -1) {
//            if (mImageViews[mClickId].getId() != v.getId()) { //전에 눌렀던 얘랑 같지 않을때
//                mImageViews[mClickId].setImageResource(mResourceImgs[mClickId]);
//                Toast.makeText(getApplicationContext(), "ddddddd", Toast.LENGTH_SHORT).show();
//            }
//        }

        switch (v.getId()) {

            case R.id.main_button1:
//                mClickId = 0;
//                binding.mainButton1.setImageURI(Uri.parse(mButtonImageList.get(0).getImageUrl()));
                startActivity(new Intent(this, ShippingActivity.class));
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, TaobaoShoppingFragment.newInstance()).commit();
//                getActivity().startActivity(new Intent(getActivity(), ShippingActivity.class));
                break;
            case R.id.main_button2:

                break;
            case R.id.main_button3:

                break;
            case R.id.main_button4:

                break;

            case R.id.main_button5:

                break;
            case R.id.main_button6:

                break;
        }
    }

    private void initViewPager() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 4);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setPageTransformer(false, new DefaultTransformer());
        binding.viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        // Count number of tabs
        private int tabCount;

        public PagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {

            // Returning the current tabs
            switch (position) {
                case 0:
                    NoticeFragment tabFragment1 = NoticeFragment.newInstance(mNoticeList.get(position).getTit(), mNoticeList.get(position).getInsDate());
                    return tabFragment1;
                case 1:
                    NoticeFragment tabFragment2 = NoticeFragment.newInstance(mNoticeList.get(position).getTit(), mNoticeList.get(position).getInsDate());
                    return tabFragment2;
                case 2:
                    NoticeFragment tabFragment3 = NoticeFragment.newInstance(mNoticeList.get(position).getTit(), mNoticeList.get(position).getInsDate());
                    return tabFragment3;
                case 3:
                    NoticeFragment tabFragment4 = NoticeFragment.newInstance(mNoticeList.get(position).getTit(), mNoticeList.get(position).getInsDate());
                    return tabFragment4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }

    public void movePage() {
        final Handler handler = new Handler();
        //현재 위치
//이동할 위치
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isMovePageThread) {
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int getSelectedPosition = binding.viewPager.getCurrentItem();  //현재 위치
                            int movePosition = getSelectedPosition + 1;  //이동할 위치
                            binding.viewPager.setCurrentItem(movePosition);
                            if (getSelectedPosition == 0 || getSelectedPosition == 1 || getSelectedPosition == 2) {
                                binding.viewPager.setCurrentItem(movePosition);
                            } else {
                                binding.viewPager.setCurrentItem(0);

                            }
                        }
                    });
                }
            }
        });
        thread.start();
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

                Log.d("onMainSuccess: ", response.toString());

                mNoticeList = new ArrayList<>();

//                TextView[] titleViews = {binding.title1, binding.title2, binding.title3, binding.title4};
//                TextView[] dateViews = {binding.date1, binding.date2, binding.date3, binding.date4};

                try {
                    String error = response.getString("RstNo");
                    Log.d("onMainSuccess: ", error);
                    if (error.equals("0")) {
                        JSONArray noticeArray = response.getJSONArray("MainNotice");
                        JSONArray toolbarImageArray = response.getJSONArray("MainRolling");
                        JSONObject toolbarImageObject = toolbarImageArray.getJSONObject(0);
                        JSONArray buttonImageArray = response.getJSONArray("MainBnr");

                        JSONArray costomerImageArray = response.getJSONArray("MainSupoort");
                        JSONObject costomerImageObject = costomerImageArray.getJSONObject(0);

                        for (int i = 0; i < 4; i++) {  //공지사항
                            JSONObject noticeObject = noticeArray.getJSONObject(i);
                            mNoticeList.add(new noticeModel(noticeObject.getString("BbCode"), noticeObject.getString("BbsSeq"),
                                    noticeObject.getString("Ct"), noticeObject.getString("Tit"), noticeObject.getString("InsDate")));
                        }

                        binding.toolbarImage.setImageURI(Uri.parse(toolbarImageObject.getString("ImgUrl")));  //툴바이미지

                        binding.mainButton1.setOnClickListener(MainActivity.this);  //메인 버튼
                        binding.mainButton2.setOnClickListener(MainActivity.this);
                        binding.mainButton3.setOnClickListener(MainActivity.this);
                        binding.mainButton4.setOnClickListener(MainActivity.this);
                        binding.mainButton5.setOnClickListener(MainActivity.this);
                        binding.mainButton6.setOnClickListener(MainActivity.this);

                        mButtonImageList = new ArrayList<>();
                        for (int i = 0; i < buttonImageArray.length(); i++) {
                            JSONObject buttonImageObject = buttonImageArray.getJSONObject(i);
                            mButtonImageList.add(new ButtonModel(buttonImageObject.getString("ImgUrl"),buttonImageObject.getString("AppMenu")));
                            mImageViews[i].setImageURI(Uri.parse(mButtonImageList.get(i).getImageUrl()));
                        }

                        binding.customerBottomBanner.setImageURI(Uri.parse(costomerImageObject.getString("ImgUrl")));

                        initViewPager();
                        movePage();
                        binding.parentLayout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 정보가 틀립니다.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                    Log.d("onMainSuccess: ", mNoticeList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // 받아온 JSONArray 자료 처리
            }

            @Override
            public void onFinish() {
                // 끝나면 호출 되는 메소드
//                EventBus.getDefault().post(new ProgressBarEvent(false));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // 통신 실패시 호출 되는 메소드
                Toast.makeText(getApplicationContext(), "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ", throwable.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event) {
//        if (event instanceof FragmentReplaceEvent) {
//            FragmentReplaceEvent fragmentReplaceEvent = (FragmentReplaceEvent) event;
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragmentReplaceEvent.getFragment()).addToBackStack("null").commit();
//        } else if (event instanceof FragmentDialogEvent) {
//            FragmentDialogEvent fragmentDialogEvent = (FragmentDialogEvent) event;
//            fragmentDialogEvent.getDialogFragment().show(getSupportFragmentManager(), "다이얼로그");
//        }
//        if (event instanceof ScrollingEvent) {
//            ScrollingEvent scrollingEvent = (ScrollingEvent) event;
//            boolean buttonShown = scrollingEvent.isShown();
//            if (buttonShown) {
//                binding.scrollUpButton.show();
//            } else {
//                binding.scrollUpButton.hide();
//            }
//        } else if (event instanceof ProgressBarEvent) {
//            ProgressBarEvent progressBarEvent = (ProgressBarEvent) event;
//            boolean progressbarShown = progressBarEvent.isShow();
//            if (progressbarShown) {
//                binding.progressBar.setVisibility(View.VISIBLE);
//            } else {
//                binding.progressBar.setVisibility(View.INVISIBLE);
//            }
//        }

//        } else if (event instanceof BottomBarAnimEvent) {
//            BottomBarAnimEvent bottomBarAnimEvent = (BottomBarAnimEvent) event;
//            Animation animation;
//            if (bottomBarAnimEvent.isShow()) {
//                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_nav_show);
//                Toast.makeText(getApplicationContext(), "올림", Toast.LENGTH_SHORT).show();
//            } else {
//                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_nav_hide);
//                Toast.makeText(getApplicationContext(), "내림", Toast.LENGTH_SHORT).show();
//            }
//            binding.bottomBar.setAnimation(animation);
//        }
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


//        mNav.getMenu().findItem(item.getItemId()).setChecked(true);
//        if (item.getItemId() != checkedMenuItem) { //원래 체크되있던 아이템은 로드 x
//            checkedMenuItem = item.getItemId();
//            mScrollUpButton.hide();
//            removeCustomToolbar();
//
//            switch (item.getItemId()) {
//
//                case R.id.scan_in_stock_item:
//                    //입고스캔
//
//                    addCustomImageInputView(R.layout.toolbar_in_scan_in_stock);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, ScanInStockFragment.newInstance()).commit();
//                    break;
////
//                case R.id.shooting_item:
//                    //실사촬영
//                    addCustomImageInputView(-1);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, ShootingFragment.newInstance()).commit();
//                    break;
////
//                case R.id.retake_item:
//                    //재촬영
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, RetakeFragment.newInstance()).commit();
//
//                    break;
//
//                case R.id.finished_wearing_item:
//                    //입고완료
//
//                    addCustomImageInputView(R.layout.toolbar_in_finished_wearing);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, FinishedWearingFragment.newInstance()).commit();
//                    break;
//
//                case R.id.outbound_scan_item:
//                    //출고스캔
//                    addCustomImageInputView(R.layout.toolbar_in_outbound_scan);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, OutboundScanFragment.newInstance()).commit();
//                    break;
//
//                case R.id.packaging_history_item:
//                    //포장이력
//                    addCustomImageInputView(R.layout.toolbar_in_packaging_history);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, PackagingHistoryFragment.newInstance()).commit();
//                    break;
//
//                case R.id.scan_section_item:
//                    //섹션스캔
//                    addCustomImageInputView(R.layout.toolbar_in_scan_section);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, ScanSectionFragment.newInstance()).commit();
//                    break;
//
//                case R.id.no_data_item:
//                    //노데이타
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, NoDataFragment.newInstance()).commit();
//                    break;
//
//            }
//        }
//        binding.drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
        isMovePageThread = false;
    }


//    @Override
//    public void onTabSelected(@IdRes int tabId) {
//
//        if (tabId == R.id.tab_favorites1) {
//            addCustomImageInputView(R.layout.toolbar_home);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, MainFragment.newInstance()).commit();
//             The tab with id R.id.tab_favorites was selected,
//             change your content accordingly.
//        }
//    }
}


