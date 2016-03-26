package cmpe.mobile.app.restaraunt.finder.restaurantfinder;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchActivityFragment extends ListFragment {

    ListView listView;
    SearchResultsAdapter mSearchResultsAdapter;
    ArrayList<SearchResults> mSearchResults ;
    SearchResultLab mSearchResultLab;
    ProgressBar progress;

    public static final String QUERY_URL = "SearchActivityFragment.QUERY_URL";
    private static final String TAG = "SearchActivityFragment";

    static ResultsDownloader<ImageView> mResultsDownloader;

    public SearchActivityFragment() {
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
        View searchView = inflater.inflate(R.layout.fragment_search,container,false);
        listView = (ListView)searchView.findViewById(android.R.id.list);

        progress = (ProgressBar)searchView.findViewById(R.id.progressBar);
        progress.setVisibility(View.VISIBLE);
        String url = getArguments().getString(QUERY_URL) + "radius_filter=17000&limit=20&";
        mSearchResultLab = SearchResultLab.getSearchResultLab(getContext());


        SearchAsyncTask task = new SearchAsyncTask(progress);
        task.execute(url);
        mSearchResults = mSearchResultLab.getSearchResults();

        return searchView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mResultsDownloader.clearQueue();
    }

    public static SearchActivityFragment getUrl(String url){
        Bundle args = new Bundle();
        args.putString(QUERY_URL, url);

        SearchActivityFragment searchActivityFragment = new SearchActivityFragment();
        searchActivityFragment.setArguments(args);

        return searchActivityFragment;
    }

    public class SearchAsyncTask extends AsyncTask<String ,Void , Boolean> {

        ProgressBar mProgressBar;
        public SearchAsyncTask(ProgressBar progressBar){
            mProgressBar = progressBar;
        }

        @Override
        protected Boolean doInBackground(String... url) {

            String CONSUMER_KEY = "by4s6bw5RrnHgff5hqRZ-g";
            String CONSUMER_SECRET = "DIKfyKsz8aa5COaG8Lky4usv2u0";
            String ACCESS_TOKEN = "rwLWR5I7w0PVMoGJasOu3xAYWFpwNPsH";
            String TOKEN_SECRET = "ZGESy3izFg8hbxalnKSoHv1bfGc";

            OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,
                    CONSUMER_SECRET);
            consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);


            // create an HTTP request to a protected resource

            try {
                //Encoding the url
               // requestUrl = URLEncoder.encode(String.valueOf(url[0]), "UTF-8");
                String requestUrl = String.valueOf(url[0]);
                HttpGet request = new HttpGet("https://api.yelp.com/v2/search?" + requestUrl);

                consumer.sign(request);
                Log.i("URL_used",request.getURI().toString());

                HttpClient httpClient = new DefaultHttpClient();
                HttpResponse response = httpClient.execute(request);
                int responseStatus = response.getStatusLine().getStatusCode();

                if(responseStatus == 200) {
                    String json_string = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObject = new JSONObject(json_string);
                    Log.i("OauthTest", String.valueOf(response.getStatusLine()));
                    Log.i("OauthTest",jsonObject.toString());
                    JSONArray jsonArray =jsonObject.getJSONArray("businesses");

                    DBHandler db_handle = ((MainActivity)getActivity()).getDBHandle();

                    for(int i = 0 ;i < jsonArray.length(); i ++){
                        SearchResults searchResults = new SearchResults();
                        JSONObject requiredObject =  jsonArray.getJSONObject(i);
                        // begin saving in db

                      //  db_handle.addEntries(requiredObject.toString(), requiredObject.getString("id") );

                        //end db save
                        searchResults.setId(requiredObject.getString("id"));
                        searchResults.setName(requiredObject.getString("name"));

                        JSONObject display_address = requiredObject.getJSONObject("location");
                        searchResults.setDisplayAddress(createDisplayAddress(display_address.getJSONArray("display_address")));

                        searchResults.setCategories(createCategoriesString(requiredObject.getJSONArray("categories")));

                        searchResults.setImageUrl(requiredObject.getString("image_url"));
                        searchResults.setReviewCount(requiredObject.getInt("review_count"));


                        searchResults.setRatingImgUrl(requiredObject.getString("rating_img_url_large"));

                        // These two values are required for Search Fragment detail view.
                        searchResults.setSnippetText(requiredObject.getString("snippet_text"));
                        searchResults.setPhone(requiredObject.getString("phone"));

                       // searchResults.setLatitude(requiredObject.getDouble("latitude"));
                        //searchResults.setLongitude(requiredObject.getDouble("longitude"));
                       // System.out.println("Lati  "+searchResults.getLatitude());

                        mSearchResults.add(searchResults);
                    }
                  return true;

                }

            }catch (OAuthMessageSignerException e) {
                e.printStackTrace();
            } catch (OAuthExpectationFailedException e) {
                e.printStackTrace();
            } catch (OAuthCommunicationException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        private String createCategoriesString(JSONArray jsonArray){
            if(jsonArray.length() > 0){
                String categories = "";

                    for(int i = 0 ;i < jsonArray.length(); i ++){
                        try {

                            JSONArray jsonArrayTemp = (JSONArray) jsonArray.get(i);
                            if(i == jsonArray.length()-1){
                                categories += jsonArrayTemp.getString(0);
                                continue;
                            }
                            categories += jsonArrayTemp.getString(0) + ", ";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                return categories;
            }
            return null;
        }

        private String createDisplayAddress(JSONArray jsonArray){
            String address = "";
            if(jsonArray.length() > 0){
                ArrayList<String> requiredList = new ArrayList<>();
                    for(int i = 0 ;i < jsonArray.length() - 1; i ++){
                        try {

                            if(i == jsonArray.length() - 2 ){
                                address += jsonArray.get(i).toString();
                                continue;
                            }
                            address += jsonArray.get(i).toString() + ", ";
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                return address;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result.equals(true) && isVisible()){

                mSearchResultsAdapter = new SearchResultsAdapter
                        (getActivity(),R.layout.search_result_row, mSearchResults );

                listView.setAdapter(mSearchResultsAdapter);
                mProgressBar.setVisibility(View.GONE);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SearchResults sr = (SearchResults)parent.getItemAtPosition(position);
                        Intent i = new Intent(getActivity(),DetailSearchPagerActivity.class);
                        i.putExtra(DetailSearchFragment.SEARCH_RESULT_ID, sr.getId());
                        startActivity(i);
                    }
                });

            }else if(isVisible() && result.equals(false)){
                Toast.makeText(getContext(),"No results to show", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
