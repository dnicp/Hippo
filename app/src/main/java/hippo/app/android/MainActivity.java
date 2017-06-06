package hippo.app.android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import hippo.app.android.fragment.MyTasksFragment;
import hippo.app.android.fragment.MyTopTasksFragment;
import hippo.app.android.fragment.RecentTasksFragment;

/** Main thread + dot dot menu items */

public class MainActivity extends hippo.app.android.BaseActivity {

    private static final String TAG = "MainActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start adjust buttom bar color
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        // end adjust bottom bar color

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    new RecentTasksFragment(),
                    new MyTasksFragment(),
                    new MyTopTasksFragment(),
            };
            private final String[] mFragmentNames = new String[]{
                    getString(R.string.heading_recent),
                    getString(R.string.heading_my_tasks),
                    getString(R.string.heading_my_top_tasks)
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Button launches NewTaskActivity
        findViewById(R.id.fab_new_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, hippo.app.android.NewTaskActivity.class));
            }
        });

    }
    // menu stuff starts

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, hippo.app.android.SignInActivity.class));
            finish();
            return true;
        }
        if (i == R.id.action_test) {
            startActivity(new Intent(this, categoryActivity.class));
            finish();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    // menu stuff ends


}
