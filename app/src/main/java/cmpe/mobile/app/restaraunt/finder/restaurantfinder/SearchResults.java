package cmpe.mobile.app.restaraunt.finder.restaurantfinder;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saipranesh on 21-Mar-16.
 */
public class SearchResults {

        private boolean isClaimed;
        private double rating;
        private String mobileUrl;
        private String ratingImgUrl;
        private int reviewCount;
        private String name;
        private String ratingImgUrlSmall;
        private String url;
        private String categories;
        private String phone;
        private String snippetText;
        private String imageUrl;
        private String snippetImageUrl;
        private String displayPhone;
        private String ratingImgUrlLarge;
        private String id;
        private boolean isClosed;
        private Location location;
        private String city;
        private String displayAddress;
        private double geoAccuracy;
        private List<String> neighborhoods = new ArrayList<>();
        private String postalCode;
        private String countryCode;
        private List<String> address = new ArrayList<>();
        private String stateCode;
        private double latitude;
        private double longitude;
        private String yelpJson;

        public SearchResults(){

        }

    /**
         *
         * @return
         * The isClaimed
         */
        public boolean isIsClaimed() {
            return isClaimed;
        }

        /**
         *
         * @param isClaimed
         * The is_claimed
         */
        public void setIsClaimed(boolean isClaimed) {
            this.isClaimed = isClaimed;
        }

        /**
         *
         * @return
         * The rating
         */
        public double getRating() {
            return rating;
        }

        /**
         *
         * @param rating
         * The rating
         */
        public void setRating(double rating) {
            this.rating = rating;
        }

        /**
         *
         * @return
         * The mobileUrl
         */
        public String getMobileUrl() {
            return mobileUrl;
        }

        /**
         *
         * @param mobileUrl
         * The mobile_url
         */
        public void setMobileUrl(String mobileUrl) {
            this.mobileUrl = mobileUrl;
        }

        /**
         *
         * @return
         * The ratingImgUrl
         */
        public String getRatingImgUrl() {
            return ratingImgUrl;
        }

        /**
         *
         * @param ratingImgUrl
         * The rating_img_url
         */
        public void setRatingImgUrl(String ratingImgUrl) {
            this.ratingImgUrl = ratingImgUrl;
        }

        /**
         *
         * @return
         * The reviewCount
         */
        public int getReviewCount() {
            return reviewCount;
        }

        /**
         *
         * @param reviewCount
         * The review_count
         */
        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The ratingImgUrlSmall
         */
        public String getRatingImgUrlSmall() {
            return ratingImgUrlSmall;
        }

        /**
         *
         * @param ratingImgUrlSmall
         * The rating_img_url_small
         */
        public void setRatingImgUrlSmall(String ratingImgUrlSmall) {
            this.ratingImgUrlSmall = ratingImgUrlSmall;
        }

        /**
         *
         * @return
         * The url
         */
        public String getUrl() {
            return url;
        }

        /**
         *
         * @param url
         * The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         *
         * @return
         * The categories
         */
        public String getCategories() {
            return categories;
        }

        /**
         *
         * @param categories
         * The categories
         */
        public void setCategories(String categories) {
            this.categories = categories;
        }

        /**
         *
         * @return
         * The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         * The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         *
         * @return
         * The snippetText
         */
        public String getSnippetText() {
            return snippetText;
        }

        /**
         *
         * @param snippetText
         * The snippet_text
         */
        public void setSnippetText(String snippetText) {
            this.snippetText = snippetText;
        }

        /**
         *
         * @return
         * The imageUrl
         */
        public String getImageUrl() {
            return imageUrl;
        }

        /**
         *
         * @param imageUrl
         * The image_url
         */
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        /**
         *
         * @return
         * The snippetImageUrl
         */
        public String getSnippetImageUrl() {
            return snippetImageUrl;
        }

        /**
         *
         * @param snippetImageUrl
         * The snippet_image_url
         */
        public void setSnippetImageUrl(String snippetImageUrl) {
            this.snippetImageUrl = snippetImageUrl;
        }

        /**
         *
         * @return
         * The displayPhone
         */
        public String getDisplayPhone() {
            return displayPhone;
        }

        /**
         *
         * @param displayPhone
         * The display_phone
         */
        public void setDisplayPhone(String displayPhone) {
            this.displayPhone = displayPhone;
        }

        /**
         *
         * @return
         * The ratingImgUrlLarge
         */
        public String getRatingImgUrlLarge() {
            return ratingImgUrlLarge;
        }

        /**
         *
         * @param ratingImgUrlLarge
         * The rating_img_url_large
         */
        public void setRatingImgUrlLarge(String ratingImgUrlLarge) {
            this.ratingImgUrlLarge = ratingImgUrlLarge;
        }

        /**
         *
         * @return
         * The id
         */
        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The isClosed
         */
        public boolean isIsClosed() {
            return isClosed;
        }

        /**
         *
         * @param isClosed
         * The is_closed
         */
        public void setIsClosed(boolean isClosed) {
            this.isClosed = isClosed;
        }

        /**
         *
         * @return
         * The location
         */
        public Location getLocation() {
            return location;
        }

        /**
         *
         * @param location
         * The location
         */
        public void setLocation(Location location) {
            this.location = location;
        }




        /**
         *
         * @return
         * The latitude
         */
        public double getLatitude() {
            return latitude;
        }

        /**
         *
         * @param latitude
         * The latitude
         */
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        /**
         *
         * @return
         * The longitude
         */
        public double getLongitude() {
            return longitude;
        }

        /**
         *
         * @param longitude
         * The longitude
         */
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }


        /**
         *
         * @return
         * The city
         */
        public String getCity() {
            return city;
        }

        /**
         *
         * @param city
         * The city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         *
         * @return
         * The displayAddress
         */
        public String getDisplayAddress() {
            return displayAddress;
        }

        /**
         *
         * @param displayAddress
         * The display_address
         */
        public void setDisplayAddress(String displayAddress) {
            this.displayAddress = displayAddress;
        }

        /**
         *
         * @return
         * The geoAccuracy
         */
        public double getGeoAccuracy() {
            return geoAccuracy;
        }

        /**
         *
         * @param geoAccuracy
         * The geo_accuracy
         */
        public void setGeoAccuracy(double geoAccuracy) {
            this.geoAccuracy = geoAccuracy;
        }

        /**
         *
         * @return
         * The neighborhoods
         */
        public List<String> getNeighborhoods() {
            return neighborhoods;
        }

        /**
         *
         * @param neighborhoods
         * The neighborhoods
         */
        public void setNeighborhoods(List<String> neighborhoods) {
            this.neighborhoods = neighborhoods;
        }

        /**
         *
         * @return
         * The postalCode
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         *
         * @param postalCode
         * The postal_code
         */
        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        /**
         *
         * @return
         * The countryCode
         */
        public String getCountryCode() {
            return countryCode;
        }

        /**
         *
         * @param countryCode
         * The country_code
         */
        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        /**
         *
         * @return
         * The address
         */
        public List<String> getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(List<String> address) {
            this.address = address;
        }


        /**
         *
         * @return
         * The stateCode
         */
        public String getStateCode() {
            return stateCode;
        }

        /**
         *
         * @param stateCode
         * The state_code
         */
        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

         public void  setYelpJson(String str){
             this.yelpJson = str;
          }

        public String getYelpJson(){
            return yelpJson;
        }




}