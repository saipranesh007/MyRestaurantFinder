package cmpe.mobile.app.restaraunt.finder.restaurantfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by saipranesh on 23-Mar-16.
 */
public class DetailSearchPagerActivity extends AppCompatActivity {

    FragmentStatePagerAdapter fragmentStatePagerAdapter;
    ViewPager mViewPager;
    private ArrayList<SearchResults> mSearchResults ;
    ActionBar mActionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.detailFragmentContainer);
        setContentView(mViewPager);
        mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mSearchResults = SearchResultLab.getSearchResultLab(getApplicationContext()).getSearchResults();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                SearchResults searchResults = mSearchResults.get(position);
                return DetailSearchFragment.newInstance(searchResults.getId());
            }

            @Override
            public int getCount() {
                return mSearchResults.size();
            }
        };
        mViewPager.setAdapter(fragmentStatePagerAdapter);


        final String searchId = getIntent().getStringExtra(DetailSearchFragment.SEARCH_RESULT_ID);
        for(int i =0 ; i< mSearchResults.size() ; i ++){
            if(mSearchResults.get(i).getId().equals(searchId)){
                mViewPager.setCurrentItem(i);
                mActionBar.setTitle(mSearchResults.get(i).getName());
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SearchResults searchResults = mSearchResults.get(position);
                if(searchResults!= null){
                 //   mActionBar.setDisplayShowTitleEnabled(true);
                    mActionBar.setTitle(searchResults.getName());
                    mActionBar.setLogo(R.mipmap.ic_launcher);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentStatePagerAdapter.notifyDataSetChanged();
    }
}
