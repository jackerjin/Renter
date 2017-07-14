package com.example.tarena.renter.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.tarena.renter.R;
import com.example.tarena.renter.adapter.MyFragmentPagerAdapter;
import com.example.tarena.renter.fragment.HomeFragment;
import com.example.tarena.renter.fragment.KanfangFragment;
import com.example.tarena.renter.fragment.MessageFragment;
import com.example.tarena.renter.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mRadioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.ViewPager_main)
  ViewPager mViewPager;
    MyFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        setListener();

    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio01:
                        mViewPager.setCurrentItem(0,true);
                        break;
                    case R.id.radio02:
                        mViewPager.setCurrentItem(1,true);
                        break;
                    case R.id.radio03:
                        mViewPager.setCurrentItem(2,true);
                        break;
                    case R.id.radio04:
                        mViewPager.setCurrentItem(3,true);
                        break;
                }
            }
        });
    }

    private void initFragment() {
        FragmentManager fragment = getSupportFragmentManager();
        adapter = new MyFragmentPagerAdapter(fragment);
        mViewPager.setAdapter(adapter);
        HomeFragment homeFragment= new HomeFragment();
        MessageFragment messageFragment= new MessageFragment();
        KanfangFragment kanfangFragment= new KanfangFragment();
        MyFragment myFragment= new MyFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(messageFragment);
        adapter.addFragment(kanfangFragment);
        adapter.addFragment(myFragment);
        mViewPager.setCurrentItem(0,true);

    }
}
