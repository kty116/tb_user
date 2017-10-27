package com.thebay.thebay1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;

import com.thebay.thebay1.databinding.ActivityShippingBinding;
import com.thebay.thebay1.event.MessageEvent;
import com.thebay.thebay1.shipping.AcceptTermsFragment;
import com.thebay.thebay1.shipping.ProductInformationFragment;
import com.thebay.thebay1.shipping.RecieverInformationFragment;
import com.thebay.thebay1.shipping.RequestsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

public class ShippingActivity extends ParentActivity implements View.OnClickListener {

    private ActivityShippingBinding binding;
    private PagerAdapter pagerAdapter;
    private boolean isMovePageThread = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shipping);

        setTitle("배송대행신청");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTabLayout();

        binding.fab.setOnClickListener(this);
//        movePage();
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
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int getSelectedPosition = binding.viewPager.getCurrentItem();  //현재 위치
                            int movePosition = getSelectedPosition + 1;  //이동할 위치
                            binding.viewPager.setCurrentItem(movePosition);
//                            if (getSelectedPosition == 0 || getSelectedPosition == 1 || getSelectedPosition == 2) {
//                                binding.viewPager.setCurrentItem(movePosition);
//                            } else {
//                                binding.viewPager.setCurrentItem(0,false);
//
//                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }


    public void setTabLayout() {

        // Initializing the TabLayout
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("약관동의"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("상품정보"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("수취인정보"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("요청사항"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Creating TabPagerAdapter adapter
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    binding.fab.show();
                } else if (position == 2) {
                    binding.fab.show();
                } else if (position == 3) {
                    binding.fab.show();
                } else {
                    binding.fab.show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Set TabSelectedListener
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
//        if (event instanceof FragmentReplaceEvent) {
//            FragmentReplaceEvent fragmentReplaceEvent = (FragmentReplaceEvent) event;
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
        isMovePageThread = false;
    }

    public void popDateDialog(DatePickerDialog.OnDateSetListener dateSetListener) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
        if (Build.VERSION.SDK_INT >= 24) {
            // API 24 이상일 경우 시스템 기본 테마 사용
            context = this;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);
        datePickerDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                int getSelectedPosition = binding.tabLayout.getSelectedTabPosition();  //현재 위치
                int movePosition = getSelectedPosition + 1;  //이동할 위치
                if (getSelectedPosition == 0 || getSelectedPosition == 1 || getSelectedPosition == 2) {
                    binding.viewPager.setCurrentItem(movePosition);
                }
        }
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
//
            // Returning the current tabs
            switch (position) {
                case 0:
                    AcceptTermsFragment tabFragment1 = new AcceptTermsFragment();
                    return tabFragment1;
                case 1:
                    ProductInformationFragment tabFragment2 = new ProductInformationFragment();
                    return tabFragment2;
                case 2:
                    RecieverInformationFragment tabFragment3 = new RecieverInformationFragment();
                    return tabFragment3;
                case 3:
                    RequestsFragment tabFragment4 = new RequestsFragment();
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
}
