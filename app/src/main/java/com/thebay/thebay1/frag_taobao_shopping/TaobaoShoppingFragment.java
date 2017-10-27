package com.thebay.thebay1.frag_taobao_shopping;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.thebay1.login.LoginActivity;
import com.thebay.thebay1.R;
import com.thebay.thebay1.common.CommonLib;
import com.thebay.thebay1.databinding.FragmentTaobaoShoppingBinding;
import com.thebay.thebay1.databinding.ListItemHomeBinding;
import com.thebay.thebay1.dto.KeyDTO;
import com.thebay.thebay1.event.AddDataEvent;
import com.thebay.thebay1.event.MessageEvent;
import com.thebay.thebay1.event.ScrollButtonClickEvent;
import com.thebay.thebay1.main.ScanInStockModel;
import com.thebay.thebay1.lib.TheBayRestClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class TaobaoShoppingFragment extends Fragment {

    private FragmentTaobaoShoppingBinding binding;
    private ArrayList<ScanInStockModel> mDataList;
    private ListAdapter mDataAdapter;

    //스크롤 방향 체크 변수
    public static TaobaoShoppingFragment newInstance() {
        TaobaoShoppingFragment fragment = new TaobaoShoppingFragment();
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
        View view = inflater.inflate(R.layout.fragment_taobao_shopping, container, false);
        binding = DataBindingUtil.bind(view);
        getActivity().setTitle("THE BAY");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RequestParams params = new RequestParams();

//        String enId = Security.encrypt(id,"EJQPDL@)!&!))DJR").toString();
//        String enPw = Security.encrypt(password,"EJQPDL@)!&!))DJR").toString();
//        String enTp = Security.encrypt("U","EJQPDL@)!&!))DJR").toString();
        KeyDTO keyInfo = CommonLib.getKeyInfo(getContext());

        if (keyInfo != null) {
            params.put("AuthKey", keyInfo.getAuthKey());
            params.put("MemCode", keyInfo.getMemberCode());
        }
        params.put("PageNm", "타오바오 쇼핑몰");
        params.put("AppVer", Build.MODEL);
        params.put("ModelNo", Build.VERSION.RELEASE);

        try {
            getHttp("MyHome/MyHome_L.php", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        mDataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mDataList.add(new ScanInStockModel("예치금", "포인트", "결제구분", "미결제금액", "처리일자", "결제내역"));
//        }
//
//        mDataAdapter = new ListAdapter(getActivity(), mDataList);
////        mDataAdapter.setData(mDataList);
//
//        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
//        binding.recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
//
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        //add a HeaderView
////        RecyclerViewUtils.setHeaderView(binding.recyclerView, new TrackingSearchHeader(getContext()));
//
//        //add a FooterView
//        RecyclerViewUtils.setFooterView(binding.recyclerView, new ListFooter(getActivity()));
//        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                Log.d("onScrollStateChanged: ", newState + "");
//
//
//                if (recyclerView.computeVerticalScrollOffset() > 2000) {
//                    Log.d("onScrollStateChanged: ", "호출");
//                    EventBus.getDefault().post(new ScrollingEvent(true));
//                } else {
//                    Log.d("onScrollStateChanged: ", "호출");
//                    EventBus.getDefault().post(new ScrollingEvent(false));
//                }
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void getHttp(String relativeUrl, RequestParams params) throws JSONException {

        TheBayRestClient.post(relativeUrl, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // 받아온 JSONObject 자료 처리

                Log.d("onHomeSuccess: ", response.toString());


                try {
                    String error = response.getString("RstNo");
                    Log.d("onHomeSuccess: ",error);
                    if(error.equals("0")) {
//                        JSONArray array = response.getJSONArray("MainNotice");
//                        for (int i = 0; i < 4; i++) {
//                            JSONObject object = array.getJSONObject(i);
//                            noticeList.add(new noticeModel(object.getString("BbCode"), object.getString("BbsSeq"),
//                                    object.getString("Ct"), object.getString("Tit"), object.getString("InsDate")));
//                            titleViews[i].setText(noticeList.get(i).getTit());
//                            dateViews[i].setText(noticeList.get(i).getInsDate());
//                        }
                    }else {
                        Toast.makeText(getContext(), "로그인 정보가 틀립니다.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
//                    Log.d("onHomeSuccess: ", noticeList.toString());
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
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // 통신 실패시 호출 되는 메소드
                Toast.makeText(getContext(), "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ", throwable.toString());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event instanceof AddDataEvent) {
            //서버 통신시 프로그래스바

            //서버 통신하고 값이 20개이상이면 버튼 보이게
            //20개 미만이면 버튼 안보이게
            for (int i = 0; i < 10; i++) {
                mDataList.add(new ScanInStockModel("" + i, "1", "1", "1", "1", "1"));
            }
            mDataAdapter.setData(mDataList);
//            RecyclerViewUtils.removeFooterView(mRecyclerView);
        } else if (event instanceof ScrollButtonClickEvent) {
//            binding.recyclerView.smoothScrollToPosition(0);
//        } else if (event instanceof SearchEvent) {
//            SearchEvent searchEvent = (SearchEvent) event;
//            String searchData = searchEvent.getSearchData();  //서버로 값 넘기기
//        } else if (event instanceof ChangeLanguageEvent) {
//            ChangeLanguageEvent changeLanguageEvent = (ChangeLanguageEvent) event;
//            if (changeLanguageEvent.isKorean()) {
//                //프리퍼런스 랭귀지값이 한국어 일때
//                getActivity().setTitle("입고스캔");
//            } else {
//                getActivity().setTitle("快递单号记录");
//            }
//
//            mDataAdapter = new ListAdapter(getActivity(), mDataList);
//            HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
//            mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            //add a HeaderView
//            RecyclerViewUtils.setHeaderView(mRecyclerView, new TrackingSearchHeader(getContext()));
//        } else if (event instanceof ListHeaderFocusEvent) {
//            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    public class ListAdapter extends RecyclerView.Adapter {

        private LayoutInflater mLayoutInflater;
        private ArrayList<ScanInStockModel> mDataList = new ArrayList<>();
        private Map<Integer, Boolean> mCheckListMap = new HashMap<>();
        //        private ArrayList<Boolean> checkedList = new ArrayList<>();
//        private boolean[] checkedList = new boolean[10];

        public ListAdapter(Context context, ArrayList<ScanInStockModel> list) {
            this.mDataList = list;
            mLayoutInflater = LayoutInflater.from(context);

        }

        public void setData(ArrayList<ScanInStockModel> list) {
            this.mDataList = list;
            notifyDataSetChanged();
        }

        public void changeData() {
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_home, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.binding.text.setText("DD");

//        viewHolder.numberText.setText(mDataList.get(position).getNumber());
//        viewHolder.totalAmountPaidText.setText(mDataList.get(position).getTotalAmountPaid());
//        viewHolder.depositText.setText(mDataList.get(position).getDeposit());
//        viewHolder.pointText.setText(mDataList.get(position).getPoint());
//        viewHolder.billingCategoryText.setText(mDataList.get(position).getBillingCategory());
//        viewHolder.amountOutstandingText.setText(mDataList.get(position).getAmountOutstanding());
//        viewHolder.processingDateText.setText(mDataList.get(position).getProcessingDate());
//        viewHolder.paymentHistoryText.setText(mDataList.get(position).getPaymentHistory());
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.number_text)
//        TextView numberText;
//        @BindView(R.id.total_amount_paid_text)
//        TextView totalAmountPaidText;
//        @BindView(R.id.deposit_text)
//        TextView depositText;
//        @BindView(R.id.point_text)
//        TextView pointText;
//        @BindView(R.id.billing_category_text)
//        TextView billingCategoryText;
//        @BindView(R.id.amount_outstanding_text)
//        TextView amountOutstandingText;
//        @BindView(R.id.processing_date_text)
//        TextView processingDateText;
//        @BindView(R.id.payment_history_text)
//        TextView paymentHistoryText;

            public ListItemHomeBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);

            }
        }
    }

//
}
