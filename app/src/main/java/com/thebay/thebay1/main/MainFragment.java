package com.thebay.thebay1.main;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.thebay1.login.LoginActivity;
import com.thebay.thebay1.R;
import com.thebay.thebay1.ShippingActivity;
import com.thebay.thebay1.common.CommonLib;
import com.thebay.thebay1.databinding.FragmentMainBinding;
import com.thebay.thebay1.dto.KeyDTO;
import com.thebay.thebay1.lib.TheBayRestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;

public class MainFragment extends Fragment implements View.OnClickListener {

    private FragmentMainBinding binding;
    private int mClickId = -1;
    private ImageView[] mImageViews;
    private int[] mResourceImgs;
    private boolean isMovePageThread = true;
    private ArrayList<noticeModel> mNoticeList;
    private int textPosition = 0;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        binding = DataBindingUtil.bind(view);
        getActivity().setTitle("THE BAY");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RequestParams params = new RequestParams();

        KeyDTO keyInfo = CommonLib.getKeyInfo(getContext());

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

        binding.mainButton1.setOnClickListener(this);
        binding.mainButton2.setOnClickListener(this);
        binding.mainButton3.setOnClickListener(this);
        binding.mainButton4.setOnClickListener(this);
        binding.mainButton5.setOnClickListener(this);
        binding.mainButton6.setOnClickListener(this);

//        mImageViews = new ImageView[]{binding.mainButton1, binding.mainButton2, binding.mainButton3, binding.mainButton4, binding.mainButton5, binding.mainButton6};
//        mResourceImgs = new int[]{main_btn_taobao_normal, R.drawable.main_btn_cash_normal, R.drawable.main_btn_mypage_normal, R.drawable.main_btn_check_normal, R.drawable.main_btn_qna_normal, R.drawable.main_btn_message_normal};
    }

    private void initViewPager() {
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), 4);
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


//    public void movePage() {
//        final Handler handler = new Handler();
//        //현재 위치
////이동할 위치
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (isMovePageThread) {
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (mNoticeList.size() == textPosition) {
//                                textPosition = 0;
//                            }
//                            binding.title.setText(mNoticeList.get(textPosition).getTit());
//                            binding.date.setText(mNoticeList.get(textPosition).getInsDate());
//                            ++textPosition;
////                            int getSelectedPosition = binding.viewPager.getCurrentItem();  //현재 위치
////                            int movePosition = getSelectedPosition + 1;  //이동할 위치
////                            binding.viewPager.setCurrentItem(movePosition);
////                            if (getSelectedPosition == 0 || getSelectedPosition == 1 || getSelectedPosition == 2) {
////                                binding.viewPager.setCurrentItem(movePosition);
////                            } else {
////                                binding.viewPager.setCurrentItem(0,false);
////
////                            }
//                        }
//                    });
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.start();
//    }

    @Override
    public void onClick(View v) {

        if (mClickId != -1) {
            if (mImageViews[mClickId].getId() != v.getId()) { //전에 눌렀던 얘랑 같지 않을때
                mImageViews[mClickId].setImageResource(mResourceImgs[mClickId]);
                Toast.makeText(getContext(), "ddddddd", Toast.LENGTH_SHORT).show();
            }
        }

        switch (v.getId()) {

            case R.id.main_button1:
                mClickId = 0;
                binding.mainButton1.setImageResource(R.drawable.main_btn_taobao_click);
                getActivity().startActivity(new Intent(getActivity(), ShippingActivity.class));
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, TaobaoShoppingFragment.newInstance()).commit();
//                getActivity().startActivity(new Intent(getActivity(), ShippingActivity.class));
                break;
            case R.id.main_button2:
                mClickId = 1;
                binding.mainButton2.setImageResource(R.drawable.main_btn_cash_click);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, PayHistoryFragment.newInstance()).commit();
                break;
            case R.id.main_button3:
                mClickId = 2;
                binding.mainButton3.setImageResource(R.drawable.main_btn_mypage_click);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, HomeFragment.newInstance()).commit();
                break;
            case R.id.main_button4:
                mClickId = 3;
                binding.mainButton4.setImageResource(R.drawable.main_btn_check_click);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, HomeFragment.newInstance()).commit();
                break;

            case R.id.main_button5:
                mClickId = 4;
                binding.mainButton5.setImageResource(R.drawable.main_btn_qna_click);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, HomeFragment.newInstance()).commit();
                break;
            case R.id.main_button6:
                mClickId = 5;
                binding.mainButton6.setImageResource(R.drawable.main_btn_message_click);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, HomeFragment.newInstance()).commit();
                break;
        }
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_home, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_setting:
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

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


//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//        isMovePageThread = false;
//    }

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
                        JSONArray array = response.getJSONArray("MainNotice");
                        Log.d("onSuccess: ", array.toString());
                        for (int i = 0; i < 4; i++) {
                            JSONObject object = array.getJSONObject(i);
                            mNoticeList.add(new noticeModel(object.getString("BbCode"), object.getString("BbsSeq"),
                                    object.getString("Ct"), object.getString("Tit"), object.getString("InsDate")));

//                            EventBus.getDefault().post();

                            //툴바 이미지 바뀌게
                            //공지사항 바뀌게
//                            titleViews[i].setText(noticeList.get(i).getTit());
//                            dateViews[i].setText(noticeList.get(i).getInsDate());
                        }
                        initViewPager();
//                        movePage();
                        binding.parentLayout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), "로그인 정보가 틀립니다.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
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
                Toast.makeText(getContext(), "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ", throwable.toString());
            }
        });
    }
}
