package com.thebay.thebay1.taobao_purchase;


//public class PurchaseFragment extends Fragment implements Serializable, View.OnClickListener {
//
//    //    private Picasso picasso;
////    private LruCache picassoLruCache;
//    private FragmentFormatListBinding binding;
//    //    private ArrayList<String> mDataList;
//    private ListAdapter mDataAdapter;
//    private ArrayList<PurchaseModel> mListData;
//    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
//    boolean isLoading = false;
//    private int mTotalPage;
//    private int mCurrentPage = 1;
//    private RequestParams mParams;
//    private ListHeader mListHeader;
//    private boolean isDataExist = false;
//
//    public static PurchaseFragment newInstance() {
//        PurchaseFragment fragment = new PurchaseFragment();
//        return fragment;
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_format_list, container, false);
//        binding = DataBindingUtil.bind(view);
//        getActivity().setTitle("타오바오 구매");
//
////        picassoLruCache = new LruCache(getContext());
////        picasso = new Picasso.Builder(getContext()) //
////                .memoryCache(picassoLruCache) //
////                .build();
//
//        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (recyclerView.computeVerticalScrollOffset() > 2000) {
//                    Log.d("onScrollStateChanged: ", "호출");
//                    EventBus.getDefault().post(new ScrollingEvent(true));
//                } else {
//                    Log.d("onScrollStateChanged: ", "호출");
//                    EventBus.getDefault().post(new ScrollingEvent(false));
//                }
//            }
//        });
//
////        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
////        binding.recyclerView.setLayoutManager(mStaggeredGridLayoutManager);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event) {
//        if (event instanceof ProgressBarEvent) {
//            ProgressBarEvent progressBarEvent = (ProgressBarEvent) event;
//            boolean progressbarShown = progressBarEvent.isShow();
//            if (progressbarShown) {
////                binding.progressBar.setVisibility(View.VISIBLE);
//            } else {
////                binding.progressBar.setVisibility(View.GONE);
//            }
//        } else if (event instanceof PurchaseSearchEvent) {
//
//            if (isDataExist == false) {
//
//                isDataExist = true;
//                PurchaseSearchEvent purchaseSearchEvent = (PurchaseSearchEvent) event;
//                mParams = purchaseSearchEvent.getParams();
//                mListData = new ArrayList<>();
//                binding.recyclerView.scrollToPosition(0);
//                mDataAdapter = new ListAdapter(getActivity(), mListData, Glide.with(this));
//
//                HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
//                binding.recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
//
//                binding.noResultText.setVisibility(View.GONE);
//
//                try {
//                    searchProductsHttp("Taobao/Taobao_L.php", purchaseSearchEvent.getParams());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else if (event instanceof ScrollButtonClickEvent) {
//            binding.recyclerView.smoothScrollToPosition(0);
//        } else if (event instanceof PurchaseSearchEvent2) {
//
//            PurchaseSearchEvent2 purchaseSearchEvent = (PurchaseSearchEvent2) event;
//            mParams = purchaseSearchEvent.getParams();
//            mListData = new ArrayList<>();
//            binding.recyclerView.scrollToPosition(0);
//            mDataAdapter = new ListAdapter(getActivity(), mListData, Glide.with(this));
//
//            HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
//            binding.recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
//
//            binding.noResultText.setVisibility(View.GONE);
//
//            try {
//                searchProductsHttp("Taobao/Taobao_L.php", purchaseSearchEvent.getParams());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }






//}
