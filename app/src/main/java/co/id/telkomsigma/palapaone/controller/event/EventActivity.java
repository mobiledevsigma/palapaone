package co.id.telkomsigma.palapaone.controller.event;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class EventActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    private SessionManager sessionManager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_map);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        getEvent(sessionManager.getParentID());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void getEvent(String id) {
        AndroidNetworking.get(ConstantUtils.URL.EVENT + "{parent_id}")
                .addPathParameter("parent_id", id)
                .setTag("Event")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            adapter = new ViewPagerAdapter(getSupportFragmentManager());
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.EVENT.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                final String id = object.getString(ConstantUtils.EVENT.TAG_ID);
                                String name = object.getString(ConstantUtils.EVENT.TAG_NAME);
                                String start = object.getString(ConstantUtils.EVENT.TAG_START);
                                String end = object.getString(ConstantUtils.EVENT.TAG_END);
                                String theme = object.getString(ConstantUtils.EVENT.TAG_THEME);

                                Bundle bundle = new Bundle();
                                bundle.putString("id", id );
                                bundle.putString("name", name );
                                bundle.putString("start", start );
                                bundle.putString("end", end );
                                bundle.putString("theme", theme );
                                EventFragment fragInfo = new EventFragment();
                                fragInfo.setArguments(bundle);

                                adapter.addFragment(fragInfo, name, id);
                                viewPager.setAdapter(adapter);

                                tabLayout.setupWithViewPager(viewPager);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private final List<String> mFragmentIDList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            System.out.println("frag indes" + position);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title, String id) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            mFragmentIDList.add(id);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}