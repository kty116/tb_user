package com.thebay.thebay1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.thebay.thebay1.databinding.ActivitySubBinding;
import com.thebay.thebay1.event.MessageEvent;
import com.thebay.thebay1.event.ProgressBarEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SubActivity extends ParentActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (getIntent().getSerializableExtra("data") != null) {


            ActivityChangeIntentDataModel dataModel = (ActivityChangeIntentDataModel) intent.getSerializableExtra("data");
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, dataModel.getFragment()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
    if (event instanceof ProgressBarEvent){
        ProgressBarEvent progressBarEvent = (ProgressBarEvent) event;
        boolean progressbarShown = progressBarEvent.isShow();
        if(progressbarShown){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.progressBar.setVisibility(View.GONE);
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
}
