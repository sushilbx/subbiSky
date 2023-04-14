package com.webdigital.subbisky.APIs;

import com.webdigital.subbisky.model.AddReviewModel;
import com.webdigital.subbisky.model.AddToCartDescriptionModel;
import com.webdigital.subbisky.model.BannersModel;
import com.webdigital.subbisky.model.CartDecreaseModel;
import com.webdigital.subbisky.model.CartDeleteModel;
import com.webdigital.subbisky.model.CartIncreaseModel;
import com.webdigital.subbisky.model.CartListModel;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.CategoriesNearByItemModel;
import com.webdigital.subbisky.model.ChanagePassword;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.ContactUsModel;
import com.webdigital.subbisky.model.CustomerAddCartModel;
import com.webdigital.subbisky.model.CustomerEditProfile;
import com.webdigital.subbisky.model.CustomerLoginModel;
import com.webdigital.subbisky.model.CustomerOrderListModel;
import com.webdigital.subbisky.model.CustomerSignUpModel;
import com.webdigital.subbisky.model.DeliveryChargeModel;
import com.webdigital.subbisky.model.DeliveryStatusUpdateModel;
import com.webdigital.subbisky.model.DistanceCalcModel;
import com.webdigital.subbisky.model.EcomPlaceOrderModel;
import com.webdigital.subbisky.model.ExplorecitylistResponse;
import com.webdigital.subbisky.model.ForgotPasswordCreateModel;
import com.webdigital.subbisky.model.ForgotPasswordOtpModel;
import com.webdigital.subbisky.model.HomecityResponse;
import com.webdigital.subbisky.model.LoginSellerModel;
import com.webdigital.subbisky.model.LoginWithOtpModel;
import com.webdigital.subbisky.model.LogoutModel;
import com.webdigital.subbisky.model.NoofDaysModel;
import com.webdigital.subbisky.model.PasswordReset;
import com.webdigital.subbisky.model.PickupPaymentModel;
import com.webdigital.subbisky.model.ProductDetailModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.SearchResModel;
import com.webdigital.subbisky.model.SellerBankModel;
import com.webdigital.subbisky.model.SellerCategoryDeleteModel;
import com.webdigital.subbisky.model.SellerCategoryModel;
import com.webdigital.subbisky.model.SellerCategoryUpdateModel;
import com.webdigital.subbisky.model.SellerCreateCategoryModel;
import com.webdigital.subbisky.model.SellerCreateHotelDetailsModel;
import com.webdigital.subbisky.model.SellerCreateProductModel;
import com.webdigital.subbisky.model.SellerDashboardModel;
import com.webdigital.subbisky.model.SellerEditProductModel;
import com.webdigital.subbisky.model.SellerHotelAmenitiesModel;
import com.webdigital.subbisky.model.SellerHotelDeleteModel;
import com.webdigital.subbisky.model.SellerHotelListEditModel;
import com.webdigital.subbisky.model.SellerHotelListModel;
import com.webdigital.subbisky.model.SellerLoginWithOtpModel;
import com.webdigital.subbisky.model.SellerMyShopModel;
import com.webdigital.subbisky.model.SellerMyShopProfileUpdateModel;
import com.webdigital.subbisky.model.SellerOrderUpdateStatusModel;
import com.webdigital.subbisky.model.SellerProductDeleteModel;
import com.webdigital.subbisky.model.SellerProductDetailModel;
import com.webdigital.subbisky.model.SellerProductModel;
import com.webdigital.subbisky.model.SellerQuotesModel;
import com.webdigital.subbisky.model.SellerSaveQuotesModel;
import com.webdigital.subbisky.model.SellerServiceDetailsModel;
import com.webdigital.subbisky.model.SellerServiceOrderDetailsModel;
import com.webdigital.subbisky.model.SellerServiceOrderModel;
import com.webdigital.subbisky.model.SellerSignUpModel;
import com.webdigital.subbisky.model.SellerVerifyOtpModel;
import com.webdigital.subbisky.model.SellerWalletModel;
import com.webdigital.subbisky.model.ServiceDetailsModel;
import com.webdigital.subbisky.model.ServiceListModel;
import com.webdigital.subbisky.model.ServicePaymentModel;
import com.webdigital.subbisky.model.StayBookingPaymentModel;
import com.webdigital.subbisky.model.StayBookingSearchModel;
import com.webdigital.subbisky.model.StayBookinghModel;
import com.webdigital.subbisky.model.StoreRoomModel;
import com.webdigital.subbisky.model.SubServiceBySlugModel;
import com.webdigital.subbisky.model.SubServiceListModel;
import com.webdigital.subbisky.model.ToptrendinglistResponse;
import com.webdigital.subbisky.model.UserModel;
import com.webdigital.subbisky.model.VerifyAccountModel;
import com.webdigital.subbisky.model.VerifyOtpModel;
import com.webdigital.subbisky.model.ViewAddedRoomsModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {
    @FormUrlEncoded
    @POST("auth/customerSignUp")
    Call<CustomerSignUpModel> customersignUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation
    );

    @FormUrlEncoded
    @POST("SellerSignup")
    Call<SellerSignUpModel> sellerSignUp(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("city_id") String city_id,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("service_id") String service_id
    );

    @FormUrlEncoded
    @POST("auth/verify-account")
    Call<VerifyAccountModel> verifyAcount(
            @Field("user_id") String user_id,
            @Field("user_otp") String user_otp
    );

    @FormUrlEncoded
    @POST("auth/sellerlogin-with-otp")
    Call<SellerLoginWithOtpModel> sellerSendOtp(
            @Field("phone") String phone,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("auth/login-with-otp")
    Call<LoginWithOtpModel> customerSendOtp(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("auth/verify-login-otp")
    Call<VerifyOtpModel> verifyOtp(
            @Field("phone") String phone,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("auth/verify-sellerlogin-otp")
    Call<SellerVerifyOtpModel> sellerVerifyOtp(
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("auth/login")
    Call<CustomerLoginModel> customerLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/sellerLogin")
    Call<LoginSellerModel> sellerLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("cities")
    Call<CityListModel> cityList();

    @GET("services")
    Call<ServiceListModel> serviceList();


    @GET("auth/logout")
    Call<LogoutModel> logout(@Header("Authorization") String token);

    @GET("auth/user")
    Call<UserModel> userModel(@Header("Authorization") String token);

    @GET("home")
    Call<BannersModel> bannersModel(@Query("city") String city);

//    @GET("home/{cityname}")
//    Call<HomecityResponse> explorelist();

    @GET("subServices/{id}")
    Call<SubServiceListModel> subServiceList(@Path("id") String id);

    @GET("service/{slug}")
    Call<SubServiceBySlugModel> subServiceBySlug(@Path("slug") String slug,
                                                 @Query("city") String city);

    @GET("shopProducts/{seller_id}")
    Call<ProductDetailModel> productDetail(@Path("seller_id") Integer seller_id);

    @GET("auth/dashboard")
    Call<SellerDashboardModel> sellerDashboard(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("saveContact")
    Call<ContactUsModel> contactUs(
            @Field("name") String name,
            @Field("message") String message,
            @Field("subject") String subject,
            @Field("email") String email,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @PUT("auth/updateCustomerProfile")
    Call<CustomerEditProfile> updateProfile(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("pincode") String pincode,
            @Field("landmark") String landmark,
            @Field("address") String address,
            @Field("city_id") String city_id
    );

    @GET("auth/shop")
    Call<SellerMyShopModel> sellerMyShop(@Header("Authorization") String token);

    @Multipart
    @POST("auth/shopUpdate")
    Call<SellerMyShopProfileUpdateModel> sellerMyShopProfileUpdate(
            @Header("Authorization") String token,
            @Part("shop_name") RequestBody shop_name,
            @Part("shop_address") RequestBody shop_address,
            @Part("city_id") RequestBody city_id,
            @Part MultipartBody.Part shop_image,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("description") RequestBody description,
            @Part("iframe") RequestBody iframe,
            @Part("room_capacity") RequestBody room_capacity
    );

    @Multipart
    @POST("auth/shopUpdate")
    Call<SellerMyShopProfileUpdateModel> sellerMyShopProfileUpdateWithoutImage(
            @Header("Authorization") String token,
            @Part("shop_name") RequestBody shop_name,
            @Part("shop_address") RequestBody shop_address,
            @Part("city_id") RequestBody city_id,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("description") RequestBody description,
            @Part("iframe") RequestBody iframe,
            @Part("room_capacity") RequestBody room_capacity
    );

    @FormUrlEncoded
    @POST("auth/bank")
    Call<SellerBankModel> sellerBank(
            @Header("Authorization") String token,
            @Field("account_number") String account_number,
            @Field("account_name") String account_name,
            @Field("ifsc") String ifsc,
            @Field("upi_id") String upi_id
    );

    @FormUrlEncoded
    @POST("auth/changePassword")
    Call<ChanagePassword> changePassword(
            @Header("Authorization") String token,
            @Field("current-password") String current_password,
            @Field("new-password") String new_password,
            @Field("new-password_confirmation") String new_password_confirmation
    );

    @FormUrlEncoded
    @POST("auth/saveQuote")
    Call<SellerSaveQuotesModel> sellerQuotes(
            @Header("Authorization") String token,
            @Field("message") String message
    );

    @GET("auth/quotes")
    Call<SellerQuotesModel> getSellerQuotes(@Header("Authorization") String token);

    @GET("auth/wallet")
    Call<SellerWalletModel> getSellerWallet(@Header("Authorization") String token);


    @GET("auth/category")
    Call<SellerCategoryModel> getSellerCategory(@Header("Authorization") String token);

    @GET("auth/product")
    Call<SellerProductModel> getSellerProducts(@Header("Authorization") String token);


    @DELETE("auth/category/{id}")
    Call<SellerCategoryDeleteModel> deleteSellerCategory(@Header("Authorization") String token,
                                                         @Path("id") String id);

    @DELETE("auth/product/{id}")
    Call<SellerProductDeleteModel> sellerProductDelete(@Header("Authorization") String token,
                                                       @Path("id") String id);

    @DELETE("auth/hotels/{id}")
    Call<SellerHotelDeleteModel> deleteSellerHotel(@Header("Authorization") String token,
                                                   @Path("id") String id);

    @FormUrlEncoded
    @PUT("auth/category/{id}")
    Call<SellerCategoryUpdateModel> updateSellerCategory(@Header("Authorization") String token,
                                                         @Path("id") String id,
                                                         @Field("name") String name,
                                                         @Field("status") String status);

    @FormUrlEncoded
    @POST("auth/category")
    Call<SellerCreateCategoryModel> createSellerCategory(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("status") String status

    );

    @GET("auth/amenities")
    Call<SellerHotelAmenitiesModel> sellerHotelAmenitiesModel(@Header("Authorization") String token);

    @GET("auth/hotels")
    Call<SellerHotelListModel> sellerHotelList(@Header("Authorization") String token);

    @Multipart
    @POST("auth/product")
    Call<SellerCreateProductModel> sellerCreateProduct(@Header("Authorization") String token,
                                                       @Part("category_id") RequestBody category_id,
                                                       @Part("name") RequestBody name,
                                                       @Part("description") RequestBody description,
                                                       @Part("mrp_price") RequestBody mrp_price,
                                                       @Part("selling_price") RequestBody selling_price,
                                                       @Part("status") RequestBody status,
                                                       @Part("stock") RequestBody stock,
                                                       @Part("unit") RequestBody unit,
                                                       @Part MultipartBody.Part image
    );

    @GET("auth/product/{id}")
    Call<SellerProductDetailModel> sellerProductDetail(@Header("Authorization") String token,
                                                       @Path("id") String id);

    @FormUrlEncoded
    @PUT("auth/product/{id}")
    Call<SellerEditProductModel> sellerEditProduct(@Header("Authorization") String token,
                                                   @Path("id") int id,
                                                   @Field("category_id") String category_id,
                                                   @Field("name") String name,
                                                   @Field("description") String description,
                                                   @Field("mrp_price") String mrp_price,
                                                   @Field("selling_price") String selling_price,
                                                   @Field("status") String status,
                                                   @Field("stock") String stock,
                                                   @Field("unit") String unit
    );

    @Multipart
    @POST("auth/hotels")
    Call<SellerCreateHotelDetailsModel> sellerCreateHotelsDetails(
            @Header("Authorization") String token,
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("status") RequestBody status,
            @Part("available_rooms") RequestBody available_rooms,
            @Part("google_location") RequestBody google_location,
            @Part("bed_size") RequestBody bed_size,
            @Part("priceDescription") RequestBody priceDescription,
            @Part("adultExtra") RequestBody adultExtra,
            @Part("childrenExtra") RequestBody childrenExtra,
            @Part("room_square_feet") RequestBody room_square_feet,
            @Part("amenities") RequestBody amenities,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part imageTwo,
            @Part MultipartBody.Part imageThree,
            @Part MultipartBody.Part imageFour,
            @Part MultipartBody.Part imageFive,
            @Part("roomCapacity") RequestBody roomCapacity
    );

    @Multipart
    @POST("auth/hotelsUpdate/{id}")
    Call<SellerHotelListEditModel> sellerHotelListEdit(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("status") RequestBody status,
            @Part("available_rooms") RequestBody available_rooms,
            @Part("google_location") RequestBody google_location,
            @Part("bed_size") RequestBody bed_size,
            @Part("priceDescription") RequestBody priceDescription,
            @Part("adultExtra") RequestBody adultExtra,
            @Part("childrenExtra") RequestBody childrenExtra,
            @Part("room_square_feet") RequestBody room_square_feet,
            @Part("amenities") RequestBody amenities,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part imageTwo,
            @Part MultipartBody.Part imageThree,
            @Part MultipartBody.Part imageFour,
            @Part MultipartBody.Part imageFive,
            @Part("roomCapacity") RequestBody roomCapacity
    );

    @GET("auth/orderDetails/{order_id}")
    Call<SellerServiceOrderDetailsModel> sellerOrderDetails(@Header("Authorization") String token,
                                                            @Path("order_id") String order_id);

    @GET("serviceBySeller/{seller_id}")
    Call<ServiceDetailsModel> serviceDetails(@Path("seller_id") int seller_id);

    @GET("auth/orders")
    Call<SellerServiceOrderModel> sellerServiceOrder(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("auth/updateOrderStatus")
    Call<SellerOrderUpdateStatusModel> sellerOrderUpdate(
            @Header("Authorization") String token,
            @Field("order_id") String order_id,
            @Field("order_status") String order_status

    );

    @GET("shopCategorySeller/{seller_id}/category/{category_id}")
    Call<CategoriesNearByItemModel> categoriesNearByItem(@Path("seller_id") Integer seller_id,
                                                         @Path("category_id") Integer category_id);

    @GET("shopProductDetails/{seller_id}/product/{category_id}")
    Call<AddToCartDescriptionModel> addToCartDescription(@Path("seller_id") Integer seller_id,
                                                         @Path("category_id") Integer category_id);

    @GET("seller/{seller_id}/service/{service_id}")
    Call<SellerServiceDetailsModel> getSellerServiceDetails(@Path("seller_id") Integer seller_id,
                                                            @Path("service_id") Integer service_id);

    @FormUrlEncoded
    @POST("saveQuote")
    Call<SaveQuoteModel> saveQuote(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("message") String message,
            @Field("seller_id") String seller_id
    );

    @FormUrlEncoded
    @POST("auth/addCart")
    Call<CustomerAddCartModel> customerAddCart(
            @Header("Authorization") String token,
            @Field("product_id") String product_id,
            @Field("quantity") String quantity
    );

    @FormUrlEncoded
    @POST("auth/review")
    Call<AddReviewModel> customerAddReview(
            @Header("Authorization") String token,
            @Field("product_id") String product_id,
            @Field("quantity") String quantity
    );

    @FormUrlEncoded
    @POST("auth/deliveryUpdate")
    Call<DeliveryStatusUpdateModel> deliveryStatusUpdate(
            @Header("Authorization") String token,
            @Field("delivery_charge") String delivery_charge,
            @Field("delivery_status") String delivery_status,
            @Field("cod") String cod
    );

    @GET("auth/cart")
    Call<CartListModel> cartList(@Header("Authorization") String token);

    @GET("auth/cartCount")
    Call<CartcountModel> cartcount(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("auth/increaseQuantity")
    Call<CartIncreaseModel> increaseQuantity(@Header("Authorization") String token,
                                             @Field("cart_id") int cart_id
    );

    @FormUrlEncoded
    @PUT("auth/decreaseQuantity")
    Call<CartDecreaseModel> decreaseQuantity(@Header("Authorization") String token,
                                             @Field("cart_id") int cart_id
    );

    @DELETE("auth/cart/{cartid}")
    Call<CartDeleteModel> deleteCart(@Header("Authorization") String token,
                                     @Path("cartid") int cartid);

    @GET("auth/user")
    Call<UserModel> customerBookingList(@Header("Authorization") String token);

    @GET("auth/user")
    Call<UserModel> customerOrderList(@Header("Authorization") String token);

    @GET("auth/orderList/{id}")
    Call<CustomerOrderListModel> customerOrderDetails(@Header("Authorization") String token,
                                                      @Path("id") String id);

    @GET("auth/orderList/{id}")
    Call<CustomerOrderListModel> customerOrderDesc(@Header("Authorization") String token,
                                                   @Path("id") String id);


    //    @FormUrlEncoded
    @POST("auth/distanceAndroid/{from}/to/{to}")
    Call<DistanceCalcModel> distanceCalc(@Header("Authorization") String token,

                                         @Path("from") String from,
                                         @Path("to") String to
    );

    @GET("search/{slug}")
    Call<SearchResModel> searchResult(@Path("slug") String slug);

    @GET("auth/deliveryCharge/{sellerId}")
    Call<DeliveryChargeModel> deliveryCharge(@Header("Authorization") String token,
                                             @Path("sellerId") String sellerId);

    ///////////////////////payment /////////////
    @FormUrlEncoded
    @POST("auth/pickUp")
    Call<PickupPaymentModel> pickupPayment(
            @Header("Authorization") String token,
            @Field("quantity") String quantity,
            @Field("amount") String amount,
            @Field("distance") String distance,
            @Field("deliCon") String deliCon,
            @Field("seller_id") String seller_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("payuMoneyId") String payuMoneyId,
            @Field("pickUpAdd") String pickUpAdd,
            @Field("dropAdd") String dropAdd,
            @Field("pickUpLat") String pickUpLat,
            @Field("pickUpLong") String pickUpLong,
            @Field("dropLat") String dropLat,
            @Field("dropLong") String dropLong,
            @Field("sellerService_id") String sellerService_id
    );

    @FormUrlEncoded
    @POST("auth/placeOrder")
    Call<EcomPlaceOrderModel> placeOrder(
            @Header("Authorization") String token,
            @Field("seller_id") String seller_id,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("city_id") String city_id,
            @Field("pincode") String pincode,
            @Field("landmark") String landmark,
            @Field("address") String address,
            @Field("payable_price") String payable_price,
            @Field("payuMoneyId") String payuMoneyId,
            @Field("payment_mode") String payment_mode,
            @Field("takeAway") String takeAway
    );

    @FormUrlEncoded
    @POST("auth/booking")
    Call<ServicePaymentModel> servicePayment(
            @Header("Authorization") String token,
            @Field("seller_id") String seller_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("city_id") String city_id,
            @Field("pincode") String pincode,
            @Field("landmark") String landmark,
            @Field("address") String address,
            @Field("payable_price") String payable_price,
            @Field("payuMoneyId") String payuMoneyId,
            @Field("sellerService_id") String sellerService_id
    );

    @FormUrlEncoded
    @POST("auth/booking")
    Call<ServicePaymentModel> servicePaymentforHealth(
            @Header("Authorization") String token,
            @Field("seller_id") String seller_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("city_id") String city_id,
            @Field("pincode") String pincode,
            @Field("landmark") String landmark,
            @Field("address") String address,
            @Field("payable_price") String payable_price,
            @Field("payuMoneyId") String payuMoneyId,
            @Field("sellerService_id") String sellerService_id,
            @Field("appointment_time") String appointment_time,
            @Field("appointment_date") String appointment_date
    );

    @FormUrlEncoded
    @POST("auth/stayBookingPayment")
    Call<StayBookingPaymentModel> stayBookingPayment(
            @Header("Authorization") String token,
            @Field("hotel_id") String hotel_id,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("city_id") String city_id,
            @Field("state") String state,
            @Field("pincode") String pincode,
            @Field("address") String address,
            @Field("amount") String amount,
            @Field("payuMoneyId") String payuMoneyId,
            @Field("from") String from,
            @Field("to") String to,
            @Field("no_of_days") String no_of_days,
            @Field("adult") String adult,
            @Field("children") String children,
            @Field("room") String room,
            @Field("extraChildren") String extraChildren,
            @Field("extraAdult") String extraAdult
    );

    ///////////forgotpassword/////////////

    @FormUrlEncoded
    @POST("password/create")
    Call<ForgotPasswordCreateModel> passwordCreate(
            @Field("email") String email
    );

    @GET("password/find/{otp}")
    Call<ForgotPasswordOtpModel> passwordFind(
            @Path("otp") String otp
    );

    @FormUrlEncoded
    @POST("password/reset")
    Call<PasswordReset> passwordReset(
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("token") String token
    );


    //////////////////////////////stay booking///////////////////////////////
    @GET("auth/rooms/{orderId}")
    Call<ViewAddedRoomsModel> viewAddedRooms(@Header("Authorization") String token,
                                             @Path("orderId") String orderId
    );

    @FormUrlEncoded
    @POST("auth/noOfDays")
    Call<NoofDaysModel> noofDays(@Header("Authorization") String token,
                                 @Field("from") String from,
                                 @Field("to") String to
    );

    @FormUrlEncoded
    @POST("auth/stayBookingSearch/{seller_id}")
    Call<StayBookingSearchModel> stayBookingSearch(@Header("Authorization") String token,
                                                   @Field("from_date") String from,
                                                   @Field("to_date") String to,
                                                   @Field("search_id") String search_id,
                                                   @Path("seller_id") String seller_id
    );

    @FormUrlEncoded
    @POST("auth/rooms/store")
    Call<StoreRoomModel> storeRoom(@Header("Authorization") String token,
                                   @Field("seller_id") String seller_id,
                                   @Field("adult") String adult,
                                   @Field("children") String children
    );

    @FormUrlEncoded
    @POST("auth/rooms/store")
    Call<StoreRoomModel> storeRoomNextTime(@Header("Authorization") String token,
                                           @Field("seller_id") String seller_id,
                                           @Field("adult") String adult,
                                           @Field("children") String children,
                                           @Field("order_id") String order_id
    );

    @FormUrlEncoded
    @POST("stay-booking/{seller_id}")
    Call<StayBookinghModel> stayBookingSearchroom(
            @Path("seller_id") String seller_id,

            @Field("room") String room,
            @Field("adult") String adult,
            @Field("children") String children,
            @Field("from_date") String from_date,
            @Field("to_date") String to_date


    );


    ///new home changes api

    @GET("explore-your-city")
    Call<ExplorecitylistResponse> explorecity();

    @GET("top-trendings")
    Call<ToptrendinglistResponse> toptrending();

}

