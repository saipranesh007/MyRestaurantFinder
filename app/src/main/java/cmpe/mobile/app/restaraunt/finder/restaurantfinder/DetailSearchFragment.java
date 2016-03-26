package cmpe.mobile.app.restaraunt.finder.restaurantfinder;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by saipranesh on 22-Mar-16.
 */
public class DetailSearchFragment extends Fragment {


    public static final String SEARCH_RESULT_ID = "cmpe.mobile.app.restaraunt.finder.restaurantfinder.DetailSearchFragment.SEARCH_RESULT_ID";

    SearchResultLab mSearchResultLab;
    ArrayList<SearchResults> mSearchResultsArrayList;
    public ImageView restaurantImage;
    public ImageView ratingsImage;
    public TextView restaurantName;
    public TextView reviewCount;
    public TextView displayAddress;
    public TextView displayCategories;
    public TextView phoneNumber;
    public TextView snippet_text;
    String searchId;
    SearchResults mSearchResults;
    DBHandler db_handle;
    //MenuItem fav;
    //Menu favorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db_handle =  new DBHandler(this.getContext());

        setRetainInstance(true);
        setHasOptionsMenu(true);
        searchId = getArguments().getString(SEARCH_RESULT_ID);
        mSearchResultLab = SearchResultLab.getSearchResultLab(getContext());
        mSearchResultsArrayList = mSearchResultLab.getSearchResults();

        for(SearchResults searchResults : mSearchResultsArrayList){
            if(searchResults.getId().equals(searchId)){
                System.out.println(searchResults.getId());
                mSearchResults = searchResults;
                break;
            }
        }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // fav = menu.add(favorite);
        //  DBHandler db_handle =  new DBHandler(this.getContext());//((MainActivity)getActivity()).getDBHandle();
        inflater.inflate(R.menu.menu_detail, menu);
        MenuItem item = menu.findItem(R.id.favorite);
        if (db_handle.checkFavorite(searchId) == 1) {
            item.setIcon(R.drawable.ic_fav_enable);
        } else {
            item.setIcon(R.mipmap.ic_fav_disable);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){

            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            case R.id.favorite:
                if(db_handle.checkFavorite(searchId) == 1) {
                    item.setIcon(R.mipmap.ic_fav_disable);
                    db_handle.deleteEntry(searchId);
                } else {
                    item.setIcon(R.drawable.ic_fav_enable);
                    db_handle.addEntry("Dummy", searchId);
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_search_detail,container,false);

        restaurantImage = (ImageView)view.findViewById(R.id.restaurant_image_detail);
        //restaurantName = (TextView)view.findViewById(R.id.restaurant_name_detail);
        displayAddress = (TextView)view.findViewById(R.id.display_address_detail);
        displayCategories = (TextView)view.findViewById(R.id.categories_detail);
        ratingsImage =(ImageView)view.findViewById(R.id.rating_image_detail);
        reviewCount = (TextView)view.findViewById(R.id.review_count_detail);
        phoneNumber = (TextView)view.findViewById(R.id.phoneNumber);
        snippet_text = (TextView)view.findViewById(R.id.snippet_text);

        if(mSearchResults != null) {
            new DownloadImageTask(restaurantImage).execute(mSearchResults.getImageUrl());
            new DownloadImageTask(ratingsImage).execute(mSearchResults.getRatingImgUrl());
            displayAddress.setText(mSearchResults.getDisplayAddress());
            displayCategories.setText(mSearchResults.getCategories());
            if(mSearchResults.getPhone() != null)
                phoneNumber.setText(mSearchResults.getPhone());
            snippet_text.setText(mSearchResults.getSnippetText());
            int valueReviewCount = mSearchResults.getReviewCount();
            reviewCount.setText(String.valueOf(valueReviewCount));
           // restaurantName.setText(mSearchResults.getName());
        }
        return view;
    }

    public static DetailSearchFragment newInstance(String searchId){
        Bundle args = new Bundle();
        args.putString(SEARCH_RESULT_ID, searchId);

        DetailSearchFragment detailSearchFragment = new DetailSearchFragment();
        detailSearchFragment.setArguments(args);

        return detailSearchFragment;
    }

    private class DownloadImageTask extends AsyncTask<String, Void , Bitmap> {

        ImageView mImageView;

        private DownloadImageTask(ImageView imageView){
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String inputUrl = urls[0];
            Bitmap imageIcon =  null;

            try {
                InputStream in = new URL(inputUrl).openStream();
                imageIcon = BitmapFactory.decodeStream(in);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageIcon;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}
