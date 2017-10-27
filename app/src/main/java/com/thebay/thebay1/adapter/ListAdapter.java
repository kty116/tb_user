package com.thebay.thebay1.adapter;

/**
 * Created by kyoungae on 2017-10-15.
 */

public class ListAdapter {

//    private int mPosition;
//    private LayoutInflater mLayoutInflater;
//    private ArrayList<Model> mDataList = new ArrayList<>();
//
//    public ListAdapter(Context context, ArrayList<Model> list) {
//        this.mDataList = list;
//        mLayoutInflater = LayoutInflater.from(context);
//    }
//
//    public void setData(ArrayList<Model> list) {
//        this.mDataList = list;
//        notifyDataSetChanged();
//    }
//
//    public void changeData() {
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_model, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//        ViewHolder viewHolder = (ViewHolder) holder;
//        viewHolder.itemStateInfoLayout.removeAllViews();
//        viewHolder.orderNumberText.setText(mDataList.get(position).getOrderNumber());
//        viewHolder.buyerText.setText(mDataList.get(position).getBuyer());
//        viewHolder.recieverText.setText(mDataList.get(position).getReciever());
//        viewHolder.sectionNumberText.setText(mDataList.get(position).getSectionNumber());
//        viewHolder.mailBoxText.setText(mDataList.get(position).getMailBox());
//        viewHolder.dateText.setText(mDataList.get(position).getDate());
//        viewHolder.totalNumberOfItemText.setText(mDataList.get(position).getTotalNumberOfItems());
//        viewHolder.wearingCountText.setText(mDataList.get(position).getWearingCount());
//        viewHolder.centerCountText.setText(mDataList.get(position).getCenterCount());
//        viewHolder.shootingCountText.setText(mDataList.get(position).getShootingCount());
//        viewHolder.completeCountText.setText(mDataList.get(position).getCompleteCount());
//        viewHolder.isCountText.setText(mDataList.get(position).getIsCount());
//        viewHolder.stateCommentText.setText(mDataList.get(position).getStateComment());
//        viewHolder.stateButton.setText(mDataList.get(position).getStateButton());
//        if (mDataList.get(position).isViewMore()) {
//            viewHolder.listTitleLayout.setVisibility(View.VISIBLE);
//            for (int i = 0; i < mDataList.get(position).getStateInfoItems().size(); i++) {
//                viewHolder.itemStateInfoLayout.addView(viewHolder.addCustomListView(mDataList.get(position).getStateInfoItems().get(i)));
//            }
//        } else {
//            viewHolder.listTitleLayout.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private LinearLayout mFileInputLayout;
//        private ListModelBinding binding;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            binding = DataBindingUtil.bind(itemView);
//
//            viewMoreButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mPosition = RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this);
//                    boolean isViewMore = mDataList.get(mPosition).isViewMore();
//                    if (isViewMore) {
//                        mDataList.get(mPosition).setViewMore(false);
//                        listTitleLayout.setVisibility(View.GONE);
//                    } else {
//                        mDataList.get(mPosition).setViewMore(true);
//                        listTitleLayout.setVisibility(View.VISIBLE);
//                    }
//                    notifyDataSetChanged();
//                }
//            });
//
//        }
//
//        public View addCustomListView(StateInfoItemModel itemModel) {
//
//            final View view = LayoutInflater.from(itemStateInfoLayout.getContext()).inflate(R.layout.view_item_state_info, itemStateInfoLayout, false);
//
//            mFileInputLayout = (LinearLayout) view.findViewById(R.id.input_layout);
//            TextView numberText = (TextView) view.findViewById(R.id.number_text);
//            TextView nameText = (TextView) view.findViewById(R.id.item_name_text);
//            TextView trackingNumberText = (TextView) view.findViewById(R.id.tracking_number_text);
//            TextView stateText = (TextView) view.findViewById(R.id.state_text);
//
//            numberText.setText(itemModel.getNumber());
//            nameText.setText(itemModel.getName());
//            trackingNumberText.setText(itemModel.getTrackingNumber());
//            stateText.setText(itemModel.getState());
//            return view;
//        }
//    }

}
