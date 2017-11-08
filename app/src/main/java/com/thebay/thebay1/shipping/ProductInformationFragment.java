package com.thebay.thebay1.shipping;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.thebay.thebay1.OrderListActivity;
import com.thebay.thebay1.R;
import com.thebay.thebay1.ShippingActivity;
import com.thebay.thebay1.databinding.FragmentProductInformationBinding;
import com.thebay.thebay1.databinding.ListItemShippingProductBinding;
import com.thebay.thebay1.event.MessageEvent;
import com.thebay.thebay1.lib.HeaderAndFooterRecyclerViewAdapter;
import com.thebay.thebay1.lib.RecyclerViewUtils;
import com.thebay.thebay1.shipping.model.ShippingProductModel;
import com.thebay.thebay1.view.NextFooter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductInformationFragment extends Fragment implements Serializable, View.OnClickListener {

    private FragmentProductInformationBinding binding;
    private boolean isTextWatcher = true;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    //스크롤 방향 체크 변수
    public static ProductInformationFragment newInstance() {
        ProductInformationFragment fragment = new ProductInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Log.d(TAG, "onSaveInstanceState: 호출");
//
//        outState.putSerializable("listData", mProductModelHashMap);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_information, container, false);
        binding = DataBindingUtil.bind(view);

//        if (savedInstanceState == null) {
//            ShippingActivity.sProductModelHashMap = new HashMap<>();
//            for (int i = 0; i < 1; i++) {
//                ShippingActivity.sProductModelHashMap.put(i, new ShippingProductModel("타오바오", "", "", "", "", "", "", "", "", "", "", "", ""));
//            }
//
//        } else {
//            mProductModelHashMap = (HashMap<Integer, ShippingProductModel>) savedInstanceState.getSerializable("listData");
//        }
        ArrayList<ShippingProductModel> mProductModels = ShippingActivity.sProductModelList;

//        for (int i = 0; i < ShippingActivity.sProductModelList.size(); i++) {
//            if (ShippingActivity.sProductModelList.get(i) != null) {
//                mProductModels.add(ShippingActivity.sProductModelList.get(i));
//            }
//        }


        ListAdapter listAdapter = new ListAdapter(getActivity(), ShippingActivity.sProductModelList, Glide.with(ProductInformationFragment.this));
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(listAdapter);

        binding.recyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(manager);

        RecyclerViewUtils.setFooterView(binding.recyclerView, new NextFooter(getActivity()));


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        RequestParams params = new RequestParams();
//
////        String enId = Security.encrypt(id,"EJQPDL@)!&!))DJR").toString();
////        String enPw = Security.encrypt(password,"EJQPDL@)!&!))DJR").toString();
////        String enTp = Security.encrypt("U","EJQPDL@)!&!))DJR").toString();
//        KeyDTO keyInfo = CommonLib.getKeyInfo(getContext());
//
//        if (keyInfo != null) {
//            params.put("AuthKey", keyInfo.getAuthKey());
//            params.put("MemCode", keyInfo.getMemberCode());
//        }
//        // TODO: 2017-10-19 page name 수정
//        params.put("PageNm", "배송대행 신청> 상품");
//        params.put("AppVer", Build.MODEL);
//        params.put("ModelNo", Build.VERSION.RELEASE);
//
//        try {
//            getHttp("Acting/ReqPro_S.php", params);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        // TODO: 2017-10-19 프래그먼트 툴바 아이콘 부분 수정
//        inflater.inflate(R.menu.menu_home, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_setting:
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, MainFragment.newInstance()).commit();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
//        if (event instanceof OrderListDataEvent) {
//            mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.get_order_history_button:
////                FragmentManager fm = getActivity().getSupportFragmentManager();
////                GetOrderHistoryFragmentDialog dialogFragment = new GetOrderHistoryFragmentDialog();
////                dialogFragment.show(fm, "get_order_history");
//                CommonLib.subActivityIntent(getActivity(),GetOrderHistoryFragment.newInstance());
//                break;
        }
    }

    public class ListAdapter extends RecyclerView.Adapter {
        private int mPosition;
        private LayoutInflater mLayoutInflater;
        private ArrayList<ShippingProductModel> list;
        private final RequestManager glide;

        public ListAdapter(Context context, ArrayList<ShippingProductModel> list, RequestManager glide) {
            this.list = list;
            mLayoutInflater = LayoutInflater.from(context);
            this.glide = glide;

        }

        public void setData(ArrayList<ShippingProductModel> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_shipping_product, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ViewHolder viewHolder = (ViewHolder) holder;

            if (list.get(position) != null) {
                if (list.get(position).getImageUrl() != null) {
                    Log.d("onBindViewHolder: ","ddddddddd");
//                    String url = list.get(position).getImageUrl().replace("//","");
                    glide.load(list.get(position).getImageUrl())
//                            .override(100, 100)
//                            .centerCrop()
                            .into(viewHolder.listBinding.image);
                }
                viewHolder.listBinding.productsIndex.setText("상품정보 "+position);
                viewHolder.listBinding.orderNumberEdit.setText(list.get(position).getOrderNumber());
                viewHolder.listBinding.buyerEdit.setText(list.get(position).getBuyer());
                viewHolder.listBinding.productsNameEdit.setText(list.get(position).getProductName());
                viewHolder.listBinding.trackingNumberEdit.setText(list.get(position).getTrackingNumber());
                viewHolder.listBinding.priceEdit.setText(list.get(position).getPrice());
                viewHolder.listBinding.quantityEdit.setText(list.get(position).getQuantity());
                viewHolder.listBinding.colorEdit.setText(list.get(position).getColor());
                viewHolder.listBinding.sizeEdit.setText(list.get(position).getSize());
                viewHolder.listBinding.goodsUrlEdit.setText(list.get(position).getGoodsUrl());
                viewHolder.listBinding.imageUrlEdit.setText(list.get(position).getImageUrl());

                //리스트가 들어있어야 하고 밖에 해쉬맵
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, TextWatcher {

            public ListItemShippingProductBinding listBinding;

            public ViewHolder(View itemView) {
                super(itemView);
                listBinding = DataBindingUtil.bind(itemView);

                setButtonView();
                if (isTextWatcher) {
                    setTextWatcher();
                }
            }

            public void setButtonView() {
                listBinding.getOrderListButton.setOnClickListener(this);
                listBinding.copyButton.setOnClickListener(this);
                listBinding.addButton.setOnClickListener(this);
                listBinding.deleteButton.setOnClickListener(this);
            }

            public void setTextWatcher() {

                listBinding.orderNumberEdit.addTextChangedListener(this);
                listBinding.buyerEdit.addTextChangedListener(this);
                listBinding.productsNameEdit.addTextChangedListener(this);
                listBinding.trackingNumberEdit.addTextChangedListener(this);
                listBinding.priceEdit.addTextChangedListener(this);
                listBinding.quantityEdit.addTextChangedListener(this);
                listBinding.colorEdit.addTextChangedListener(this);
                listBinding.sizeEdit.addTextChangedListener(this);
                listBinding.goodsUrlEdit.addTextChangedListener(this);
                listBinding.imageUrlEdit.addTextChangedListener(this);

            }

            @Override
            public void onClick(View v) {
                mPosition = RecyclerViewUtils.getAdapterPosition(binding.recyclerView, ViewHolder.this);
                switch (v.getId()) {
                    case R.id.get_order_list_button:
                        getActivity().startActivity(new Intent(getActivity(), OrderListActivity.class));

                        break;
                    case R.id.copy_button:
                        list.add(list.size(), new ShippingProductModel("Taoboa", list.get(mPosition).getOrderNumber(), list.get(mPosition).getBuyer(), 0,
                                list.get(mPosition).getProductName(), list.get(mPosition).getTrackingNumber(), list.get(mPosition).getPrice(), list.get(mPosition).getQuantity(),
                                list.get(mPosition).getColor(), list.get(mPosition).getSize(), list.get(mPosition).getGoodsUrl(), list.get(mPosition).getImageUrl()));
                        notifyDataSetChanged();
                        break;
                    case R.id.add_button:
                        list.add(list.size(), new ShippingProductModel("Taobao", "rrr", "", 0, "", "", "", "", "", "", "", ""));
                        notifyDataSetChanged();
                        break;
                    case R.id.delete_button:
                        if (list.size() > 1) {
                            list.remove(mPosition);
                            notifyDataSetChanged();
                        }
                        break;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                saveText(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            public void saveText(String text) {
                mPosition = RecyclerViewUtils.getAdapterPosition(binding.recyclerView, ViewHolder.this);
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    switch (view.getId()) {
                        case R.id.order_number_edit:
                            list.get(mPosition).setOrderNumber(text);
                            break;
                        case R.id.buyer_edit:
                            list.get(mPosition).setBuyer(text);
                            break;
                        case R.id.products_name_edit:
                            list.get(mPosition).setProductName(text);
                            break;
                        case R.id.tracking_number_edit:
                            list.get(mPosition).setTrackingNumber(text);
                            break;
                        case R.id.price_edit:
                            list.get(mPosition).setPrice(text);
                            break;
                        case R.id.quantity_edit:
                            list.get(mPosition).setQuantity(text);
                            break;
                        case R.id.color_edit:
                            list.get(mPosition).setColor(text);
                            break;
                        case R.id.size_edit:
                            list.get(mPosition).setSize(text);
                            break;
                        case R.id.goods_url_edit:
                            list.get(mPosition).setGoodsUrl(text);
                            break;
                        case R.id.image_url_edit:
                            list.get(mPosition).setImageUrl(text);
                            break;
                    }
                }
            }
        }
    }


//    public class ListAdapter extends RecyclerView.Adapter {
//
//        private LayoutInflater mLayoutInflater;
//        private ArrayList<ScanInStockModel> mDataList = new ArrayList<>();
//        private Map<Integer, Boolean> mCheckListMap = new HashMap<>();
//        //        private ArrayList<Boolean> checkedList = new ArrayList<>();
////        private boolean[] checkedList = new boolean[10];
//
//        public ListAdapter(Context context, ArrayList<ScanInStockModel> list) {
//            this.mDataList = list;
//            mLayoutInflater = LayoutInflater.from(context);
//
//        }
//
//        public void setData(ArrayList<ScanInStockModel> list) {
//            this.mDataList = list;
//            notifyDataSetChanged();
//        }
//
//        public void changeData() {
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_shipping_product, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//            ViewHolder viewHolder = (ViewHolder) holder;
//            viewHolder.binding.text.setText("DD");
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDataList.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//            public ListItemHomeBinding binding;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                binding = DataBindingUtil.bind(itemView);
//
//            }
//        }
//    }

//
}
