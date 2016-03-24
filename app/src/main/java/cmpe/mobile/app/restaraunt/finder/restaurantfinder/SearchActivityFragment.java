package cmpe.mobile.app.restaraunt.finder.restaurantfinder;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchActivityFragment extends ListFragment {

    ListView listView;
    ArrayList<SearchResults> mSearchResults ;
    SearchResultLab mSearchResultLab;
    ProgressBar progress;
    ListFragment mListFragment;
    String searchValue;

    public static final String QUERY_URL = "SearchActivityFragment.QUERY_URL";
    private static final String TAG = "SearchActivityFragment";

    //static ResultsDownloader<ImageView> mResultsDownloader;

    public SearchActivityFragment() {
        mListFragment = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        //This following code is for implementation of handlers and loopers.
      /*  mResultsDownloader = new ResultsDownloader<ImageView>(new Handler());
        mResultsDownloader.setListener(new ResultsDownloader.Listener<ImageView>(){

            @Override
            public void ThumbnailDownloaded(ImageView imageView, Bitmap thumbnail) {
                if(isVisible()){
                    imageView.setImageBitmap(thumbnail);
                }
            }
        });
        mResultsDownloader.start();
        mResultsDownloader.getLooper();
        Log.i(TAG, "Background thread started");*/
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
  //      mResultsDownloader.quit();
        Log.i(TAG, "Background thread destroyed");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        listView = (ListView)searchView.findViewById(android.R.id.list);

        progress = (ProgressBar)searchView.findViewById(R.id.progressBar);
        String url = getArguments().getString(QUERY_URL);
        mSearchResultLab = SearchResultLab.getSearchResultLab(getContext());
        executeSearch(url);
        mSearchResults = mSearchResultLab.getSearchResults();

        return searchView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mResultsDownloader.clearQueue();
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Restaurants, Bars, Food..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                String urlEncoder = null;
                try {
                    urlEncoder = URLEncoder.encode(query, "UTF-8");
                    searchValue = urlEncoder;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String url = "term=restaurants&location=" + urlEncoder;
                executeSearch(url);

                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }



    public static SearchActivityFragment getUrl(String url){
        Bundle args = new Bundle();
        args.putString(QUERY_URL, url);

        SearchActivityFragment searchActivityFragment = new SearchActivityFragment();
        searchActivityFragment.setArguments(args);

        return searchActivityFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String urlBuilder = "";
        switch (item.getItemId()){
            case R.id.sortByRatings:
                if(searchValue == null){
                    urlBuilder = "location=sanjose&sort=2";
                }else{
                    urlBuilder = "location=" + searchValue + "&sort=2";
                }
                executeSearch(urlBuilder);
                return true;
            case R.id.sortByRelevance:
                if(searchValue == null){
                    urlBuilder = "location=sanjose&sort=0";
                }else{
                    urlBuilder = "location=" + searchValue + "&sort=0";
                }
                executeSearch(urlBuilder);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void executeSearch(String url){
        String outputUrl = "radius_filter=17000&limit=20&" + url +"&";
        SearchAsyncTask sortByRelevanceTask = new SearchAsyncTask(progress, mListFragment, listView);
        mSearchResultLab.getSearchResults().clear();
        sortByRelevanceTask.execute(outputUrl);

    }
}
