package cmpe.mobile.app.restaraunt.finder.restaurantfinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    public FavouriteFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // String str = "{"_id""
        DBHandler db_handle = ((MainActivity)getActivity()).getDBHandle();
        System.out.println("DB  "+db_handle.readAll());
        db_handle.deleteEntry("the-blue-door-restaurant-and-bar-san-jose");



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

}
