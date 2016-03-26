package cmpe.mobile.app.restaraunt.finder.restaurantfinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    DBHandler db_handle;
    String allFavorites;
    String[] favoritesList;
    public FavouriteFragment() {
        // Required empty public constructor
        db_handle = ((MainActivity)getActivity()).getDBHandle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // String str = "{"_id""
        String allFavorites = db_handle.readAll();
        favoritesList = allFavorites.split("~");
        //JSONObject jsonObject = new JSONObject(favoritesList);
       //System.out.println("DB  "+db_handle.readAll());
       // db_handle.deleteEntry("the-blue-door-restaurant-and-bar-san-jose");

      /*  for(int i = 0 ;i < favoritesList.length(); i ++){
            SearchResults searchResults = new SearchResults();
            JSONObject jsonObject = new JSONObject(favoritesList(i));
            JSONObject requiredObject =  jsonArray.getJSONObject(i);
            searchResults.setYelpJson(jsonArray.getJSONObject(i).toString());


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

            mSearchResults.add(searchResults);*/
     //   }



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

}
