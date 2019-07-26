package com.tianao.peopledata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tianao.peopledata.adapter.MyPagerAdapter;
import com.tianao.peopledata.fragment.MineFragment;
import com.tianao.peopledata.fragment.PeopleDataFragment;
import com.tianao.peopledata.fragment.RecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.navigation)
    BottomNavigationView navigationView;
    @Bind(R.id.viewPage)
    ViewPager viewPager;
    private List<Fragment> fragmentList;
    private RecordFragment recordFragment;
    private PeopleDataFragment dataFragment;
    private MineFragment mineFragment;
    private MyPagerAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recordFragment = new RecordFragment();
        dataFragment = new PeopleDataFragment();
        mineFragment = new MineFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(recordFragment);
        fragmentList.add(dataFragment);
        fragmentList.add(mineFragment);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pageAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if ( i == 0) {
                    navigationView.setSelectedItemId(R.id.navigation_home);
                }else if (i == 1) {
                    navigationView.setSelectedItemId(R.id.navigation_data);
                } else if (i == 2){
                    navigationView.setSelectedItemId(R.id.navigation_notifications);

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_data:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
}
