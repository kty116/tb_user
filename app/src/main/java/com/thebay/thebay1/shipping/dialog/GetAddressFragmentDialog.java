package com.thebay.thebay1.shipping.dialog;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thebay.thebay1.login.LoginActivity;
import com.thebay.thebay1.R;
import com.thebay.thebay1.common.CommonLib;
import com.thebay.thebay1.databinding.DialogGetAddressBinding;
import com.thebay.thebay1.dto.KeyDTO;
import com.thebay.thebay1.lib.TheBayRestClient;
import com.thebay.thebay1.shipping.model.GetAddressModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GetAddressFragmentDialog extends DialogFragment {

    private DialogGetAddressBinding binding;

//                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

    public static GetAddressFragmentDialog newInstance() {
        GetAddressFragmentDialog fragment = new GetAddressFragmentDialog();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.dialog_get_address, container, false);
        binding = DataBindingUtil.bind(view);
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
        // TODO: 2017-10-19 page name 수정
        params.put("PageNm", "배송대행 신청> 수취인정보 : 주소록 가져오기");
        params.put("AppVer", Build.MODEL);
        params.put("ModelNo", Build.VERSION.RELEASE);

        try {
            getHttp("Acting/DlvrAddr_S.php", params);
        } catch (JSONException e) {
            e.printStackTrace();
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
//                            Log.d("onHomeSuccess: ",homeModelList.toString());
                            binding.addressText.setText(homeModelList.toString());
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

//    public class ListAdapter extends RecyclerView.Adapter {
//
//        private LayoutInflater mLayoutInflater;
//        private ArrayList<SettlementApplyCouponListModel> mDataList = new ArrayList<>();
//        private Map<Integer, Boolean> mCheckListMap = new HashMap<>();
//
//        public ListAdapter(Context context) {
//            mLayoutInflater = LayoutInflater.from(context);
//        }
//
//        public void setData(ArrayList<SettlementApplyCouponListModel> list) {
//            this.mDataList = list;
//            notifyDataSetChanged();
//            for (int i = 0; i < mDataList.size(); i++) {
//                mCheckListMap.put(i, false);
//
//            }
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_settlement_coupon_apply, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//            ViewHolder viewHolder = (ViewHolder) holder;
//
//            if (mCheckListMap.get(position)) {
//                viewHolder.couponRadioButton.setChecked(true);
//            } else {
//                viewHolder.couponRadioButton.setChecked(false);
//            }
//
//            viewHolder.couponNameText.setText(mDataList.get(position).getCouponName());
//            viewHolder.validityText.setText(mDataList.get(position).getValidity());
//            viewHolder.discountedMoneyText.setText(mDataList.get(position).getDiscountedMoney());
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDataList.size();
//        }
//
//        private class ViewHolder extends RecyclerView.ViewHolder {
//
//            private CardView cardView;
//            private RadioButton couponRadioButton;
//            private TextView couponNameText;
//            private TextView validityText;
//            private TextView discountedMoneyText;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//
//                cardView = (CardView) itemView.findViewById(R.id.card_view);
//                couponRadioButton = (RadioButton) itemView.findViewById(R.id.coupon_radio_button);
//                couponNameText = (TextView) itemView.findViewById(R.id.coupon_name_text);
//                validityText = (TextView) itemView.findViewById(R.id.validity_text);
//                discountedMoneyText = (TextView) itemView.findViewById(R.id.discount_text);
//
//                cardView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        for (int i = 0; i < mCheckListMap.size(); i++) {
//                            mCheckListMap.put(i, false);
//                        }
//                        int position = RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this);
//                        mCheckListMap.put(position, true);
//                        for (int i = 0; i < mCheckListMap.size(); i++) {
//                            Log.d("----", "onClick: " + mCheckListMap.get(i));
//                        }
//                        notifyDataSetChanged();
//
//                    }
//                });
//
////                couponRadioButton.setChecked(false);
////                couponRadioButton.setFocusable(false);
//
////                cardView.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        position = RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this);
////                        for (int i = 0; i < mCheckListMap.size(); i++) {
////                            mCheckListMap.put(i, false);
////                            Log.d("----", "onClick: "+mCheckListMap.get(i));
////                        }
////                        mCheckListMap.put(position, true);
////                    }
////                });
////                if (mCheckListMap.get(position)) {
////                    viewHolder.couponRadioButton.setChecked(true);
////                } else {
////                    viewHolder.couponRadioButton.setChecked(false);
////                }
//
//
//            }
//        }
//    }
}
