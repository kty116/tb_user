package com.thebay.thebay1.shipping.dialog;


//public class ShootingFragment extends Fragment {
//    private RecyclerView recyclerView;

//    private FragmentFormatListBinding binding;
//    private ListAdapter mDataAdapter;
//    private int mPosition;
//    private ArrayList<ShootingModel> mDataList;
//    private HashMap<Integer, String> photoPaths;
//    private LinearLayout mFileInputLayout;
//    public static ArrayList<String> sImageFileList;
//    public static HashMap<Integer, String> sImageFileMap;
//
//    public static ShootingFragment newInstance() {
//        ShootingFragment fragment = new ShootingFragment();
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
////        setHasOptionsMenu(true);
//        View view = inflater.inflate(R.layout.fragment_format_list, container, false);
//        binding = DataBindingUtil.bind(view);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        mDataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mDataList.add(new ShootingModel("주문번호", "트래킹번호", "", "", "", false, "","","","","","","",new HashMap<Integer, String>(),new ArrayList<String>()));
//        }
//
//        mDataAdapter = new ListAdapter(getActivity(), mDataList);
//
//        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
//        binding.recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
//
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        //add a HeaderView
////        RecyclerViewUtils.setHeaderView(mRecyclerView, new TrackingSearchHeader(getActivity()));
//
//        //add a FooterView
////        RecyclerViewUtils.setFooterView(mRecyclerView, new ListFooter(getActivity()));
//
////        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////                getActivity().getCurrentFocus().clearFocus();
////                if (recyclerView.computeVerticalScrollOffset() > 2000) {
////                    Log.d("onScrollStateChanged: ", "호출");
////                    EventBus.getDefault().post(new ScrollingEvent(true));
////                } else {
////                    Log.d("onScrollStateChanged: ", "호출");
////                    EventBus.getDefault().post(new ScrollingEvent(false));
////                }
////            }
////        });
//
//        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                View current = getActivity().getCurrentFocus();
//                if (current != null) current.clearFocus();
//                if (recyclerView.computeVerticalScrollOffset() > 2000) {
//                    Log.d("onScrollStateChanged: ", "호출");
//                    EventBus.getDefault().post(new ScrollingEvent(true));
//                } else {
//                    Log.d("onScrollStateChanged: ", "호출");
//                    EventBus.getDefault().post(new ScrollingEvent(false));
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//        Toast.makeText(getContext(), "start", Toast.LENGTH_SHORT).show();
//        mDataAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//        Toast.makeText(getContext(), "stop", Toast.LENGTH_SHORT).show();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event) {
//        if (event instanceof AddDataEvent) {
//            //서버 통신시 프로그래스바
//
//            //서버 통신하고 값이 20개이상이면 버튼 보이게
//            //20개 미만이면 버튼 안보이게
////            for (int i = 0; i < 10; i++) {
////                mDataList.add(new ShootingModel("주문번호", "트래킹번호", "", "", "", new ArrayList<String>()));
////            }
////            mDataAdapter.changeData();
//        } else if (event instanceof ScrollButtonClickEvent) {
//            binding.recyclerView.smoothScrollToPosition(0);
////        } else if (event instanceof SearchEvent) {
////            SearchEvent searchEvent = (SearchEvent) event;
////            String searchData = searchEvent.getSearchData();  //서버로 값 넘기기
////        } else if (event instanceof ChangeLanguageEvent) {
////            ChangeLanguageEvent changeLanguageEvent = (ChangeLanguageEvent) event;
////            if (changeLanguageEvent.isKorean()) {
////                //프리퍼런스 랭귀지값이 한국어 일때
////                getActivity().setTitle("실사촬영");
////            } else {
////                getActivity().setTitle("上传图片");
////            }
////
////            mDataAdapter = new ListAdapter(getActivity(), mDataList);
////            HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
////            mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
////            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
////            //add a HeaderView
////            RecyclerViewUtils.setHeaderView(mRecyclerView, new TrackingSearchHeader(getContext()));
////        } else if (event instanceof ListHeaderFocusEvent) {
////            mRecyclerView.smoothScrollToPosition(0);
//        }
//    }
//
//    public void getHttp(String relativeUrl, RequestParams params) throws JSONException {
//
////        RequestParams params = new RequestParams();
////        params.put("key","value");
//
//        TheBayRestClient.get(relativeUrl, params, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                //통신 시작으로 프로그래스바 여기서 띄우기
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // 받아온 JSONObject 자료 처리
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                // 받아온 JSONArray 자료 처리
//            }
//
//            @Override
//            public void onFinish() {
//                // 끝나면 호출 되는 메소드
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                // 통신 실패시 호출 되는 메소드
//            }
//        });
//    }
//
//    public class ListAdapter extends RecyclerView.Adapter {
//        private int mUploadFileCount = 0;
//        private int shootingText;
//        private int smsText;
//        private int mailText;
//        private LayoutInflater mLayoutInflater;
//        private ArrayList<ShootingModel> mDataList = new ArrayList<>();
//
//        public ListAdapter(Context context, ArrayList<ShootingModel> list) {
//            this.mDataList = list;
//            mLayoutInflater = LayoutInflater.from(context);
//        }
//
//        public void setData(ArrayList<ShootingModel> list) {
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
//            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_shooting, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//            ViewHolder viewHolder = (ViewHolder) holder;
////            viewHolder.photoListLayout.removeAllViews();
////            viewHolder.orderNumberText.setText(viewHolder.orderNumberText.getDefaultText() + mDataList.get(position).getOrderNumber());
////            viewHolder.trackingNumberText.setText(viewHolder.trackingNumberText.getDefaultText() + mDataList.get(position).getTrackingNumber());
////            viewHolder.shootingStateText.setText(mDataList.get(position).getShootingState());
////            viewHolder.smsStateText.setText(mDataList.get(position).getSmsState());
////            viewHolder.mailStateText.setText(mDataList.get(position).getMailState());
////            if (mDataList.get(position).getNewImageFileMap() != null) {
////                for (int i = 0; i < mDataList.get(position).getNewImageFileMap().size(); i++) {
////                    if (mDataList.get(position).getNewImageFileMap().get(i) != null) {
////                        viewHolder.photoListLayout.addView(viewHolder.addCustomImageInputView(position, mDataList.get(position).getNewImageFileMap().get(i), i));
////                    }
////                }
////            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDataList.size();
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//            public ListItemShootingBinding binding;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
////                ButterKnife.bind(itemView);
//                binding = DataBindingUtil.bind(itemView);
//
////                shootingStateButton.setOnClickListener(this);
////                smsStateButton.setOnClickListener(this);
////                mailStateButton.setOnClickListener(this);
//
////                itemView.findViewById(R.id.file_add_button).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        mPosition = RecyclerViewUtils.getAdapterPosition(ShootingFragment.this.binding.recyclerView, ViewHolder.this);
////                        sImageFileMap = mDataList.get(mPosition).getNewImageFileMap();
////                        String[] items = {"사진 촬영", "사진 가져오기"};
////                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
////                                .setTitle("사진 선택")
////                                .setItems(items, new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface dialog, int which) {
////
////                                        switch (which) {
////                                            case 0:
////                                                //사진 촬영
////                                                photoShooting();
////                                                break;
////                                            case 1:
////                                                //사진 가져오기
////
////                                                bringPhoto();
////                                                break;
////                                        }
////                                    }
////                                });
////                        builder.create().show();
//
//                    }
////                });
////                itemView.findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////                    }
////                });
////            }
//
////            public View addCustomImageInputView(final int viewPosition, String imagePath, final int itemIndex) {
////                final View view = LayoutInflater.from(photoListLayout.getContext()).inflate(R.layout.view_file_input, photoListLayout, false);
////                mFileInputLayout = (LinearLayout) view.findViewById(R.id.image_input_layout);
////                ImageView fileImage = (ImageView) view.findViewById(R.id.file_image);
////                ImageButton fileRemoveButton = (ImageButton) view.findViewById(R.id.file_remove_button);
////                Glide.with(ShootingFragment.this).load(imagePath).into(fileImage);
////                Log.d("onClick: ", itemIndex + "");
////                for (int i = 0; i < mDataList.get(viewPosition).getNewImageFileMap().size(); i++) {
////                    if (mDataList.get(viewPosition).getNewImageFileMap().get(i) != null) {
////                        Log.d("onItemListValue: ", mDataList.get(viewPosition).getNewImageFileMap().get(i).toString());
////                    }
////                }
////
////                fileRemoveButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Log.d("onClick: ", itemIndex + "");
////                        mDataList.get(viewPosition).getNewImageFileMap().put(itemIndex, null);
////                        notifyDataSetChanged();
//////                        mFileInputLayout.removeView(view);
//////                        for (int i = 0; i < mDataList.get(viewPosition).getNewImageFileMap().size(); i++) {
//////                            Log.d("onItemListValue: ", mDataList.get(viewPosition).getNewImageFileMap().get(i).toString());
//////                        }
////                        for (int i = 0; i < mDataList.get(viewPosition).getNewImageFileMap().size(); i++) {
////                            if (mDataList.get(viewPosition).getNewImageFileMap().get(i) != null) {
////                                Log.d("onItemListValue: ", mDataList.get(viewPosition).getNewImageFileMap().get(i).toString());
////                            }
////                        }
////                    }
////                });
////                return view;
////            }
//
////            @Override
////            public void onClick(View v) {
////                mPosition = RecyclerViewUtils.getAdapterPosition(binding.recyclerView, ViewHolder.this);
////                switch (v.getId()) {
////                    case R.id.shooting_state_button:
////                        setOnPopup(v, R.menu.shooting_state_menu);
////                        break;
////                    case R.id.sms_state_button:
////                        setOnPopup(v, R.menu.sms_state_menu);
////                        break;
////                    case R.id.mail_state_button:
////                        setOnPopup(v, R.menu.mail_state_menu);
////                        break;
////                }
////            }
//
////            public void setOnPopup(View clickView, int menuResorce) {
////                PopupMenu popup = new PopupMenu(getActivity(), clickView);//v는 클릭된 뷰를 의미
////                getActivity().getMenuInflater().inflate(menuResorce, popup.getMenu());
////                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////                    @Override
////                    public boolean onMenuItemClick(MenuItem item) {
////                        switch (item.getItemId()) {
////                            case R.id.enrollment:
////                                shootingStateText.setText(item.getTitle());
////                                mDataList.get(mPosition).setShootingState(item.getTitle().toString());
////                                break;
////                            case R.id.sms_no_send:
////                                smsStateText.setText(item.getTitle());
////                                mDataList.get(mPosition).setSmsState(item.getTitle().toString());
////                                break;
////                            case R.id.sms_send_member:
////                                smsStateText.setText(item.getTitle());
////                                mDataList.get(mPosition).setSmsState(item.getTitle().toString());
////                                break;
////
////                            case R.id.sms_send_reciever:
////                                smsStateText.setText(item.getTitle());
////                                mDataList.get(mPosition).setSmsState(item.getTitle().toString());
////                                break;
////                            case R.id.mail_no_send:
////                                mailStateText.setText(item.getTitle());
////                                mDataList.get(mPosition).setSmsState(item.getTitle().toString());
////                                break;
////                            case R.id.mail_send:
////                                mailStateText.setText(item.getTitle());
////                                mDataList.get(mPosition).setSmsState(item.getTitle().toString());
////                                break;
////                        }
////                        return false;
////                    }
////                });
////                popup.show();//Popup Menu 보이기
////            }
//
////            public void addCustomImageInputView(String imgTitle, final int position) {
////                final View view = LayoutInflater.from(mInputParent.getContext()).inflate(R.layout.view_file_input, mInputParent, false);
////                mFileInputLayout = (LinearLayout) view.findViewById(R.id.image_input_layout);
////                TextView fileText = (TextView) view.findViewById(R.id.file_name_text);
////                ImageButton fileRemoveButton = (ImageButton) view.findViewById(R.id.file_remove_button);
////                fileText.setText(imgTitle);
////
////                fileRemoveButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        mUploadFiles.remove(position);
////                        mInputParent.removeView(view);
////                    }
////                });
////
////                mInputParent.addView(view);
////
////            }
//
//        }
//    }
//}
