package com.webdigital.subbisky.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.SellerMyShopModel;
import com.webdigital.subbisky.model.SellerMyShopProfileUpdateModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.squareup.picasso.Picasso;

public class SellerMyShopEditProfileActivity extends AppCompatActivity {
    EditText SellerMyShopNameEditDetails,
            SellerMyShopPhoneEditDetails,SellerMyShopEmailEditDetails,
            SellerMyShopAddressEditDetails,SellerMyShopAboutusEditDetails,SellerMyShopIframeEditDetails,
            SellerMyShopMaxRoomEditDetails,SellerMyShopopencloseEditDetails;
    ImageView SellermyShopImageEditDetails;
    Spinner SellerMyShopCityEditDetails;
    Button SellerMyShopEditDetailsUpdate,chooseImagesellerprofile;
    String sName,sCity,sPhone,sEmail,sAddress,sAboutus,sIframe,smaxRoom,sImage;
    List<String> cityList;
    String[] cityListSpinner;

    String Strcityid="";
    int[] cityListTitleId;
    int cListId;
    String upload_path = "";
    public static int IMAGE_REQUEST = 1;
    private static final int REQUEST_GALLERY_CODE = 200;
    String encodedImage;
    public static final int CAMERA_REQUEST = 100;
    public static final int STORAGE_REQUEST = 101;
    String[] cameraPermission;
    String[] storagePermission;
    Session session;
    private String  profile_pic_path = "";
    LinearLayout linerLayoutBack,ecom,staybook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_my_shop_edit_profile);
        SellerMyShopNameEditDetails=findViewById(R.id.SellerMyShopNameEditDetails);
        SellerMyShopCityEditDetails=findViewById(R.id.SellerMyShopCityEditDetails);
        SellerMyShopPhoneEditDetails=findViewById(R.id.SellerMyShopPhoneEditDetails);
        SellerMyShopEmailEditDetails=findViewById(R.id.SellerMyShopEmailEditDetails);
        SellerMyShopAddressEditDetails=findViewById(R.id.SellerMyShopAddressEditDetails);
        SellerMyShopAboutusEditDetails=findViewById(R.id.SellerMyShopAboutusEditDetails);
        SellerMyShopIframeEditDetails=findViewById(R.id.SellerMyShopIframeEditDetails);
        SellerMyShopMaxRoomEditDetails=findViewById(R.id.SellerMyShopMaxRoomEditDetails);
        SellermyShopImageEditDetails=findViewById(R.id.SellermyShopImageEditDetails);
        SellerMyShopEditDetailsUpdate=findViewById(R.id.SellerMyShopEditDetailsUpdate);
        chooseImagesellerprofile=findViewById(R.id.chooseImagesellerprofile);
        SellerMyShopopencloseEditDetails=findViewById(R.id.SellerMyShopopencloseEditDetails);
        ecom=findViewById(R.id.ecom);
        staybook=findViewById(R.id.staybook);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        if(SellerDashBoardActivity.usertype.equalsIgnoreCase("ecom")){
            staybook.setVisibility(View.GONE);
            ecom.setVisibility(View.VISIBLE);

        }
        else{
            staybook.setVisibility(View.VISIBLE);
            ecom.setVisibility(View.GONE);
        }

        getDatacallApi();
        chooseImagesellerprofile.setOnClickListener(new View.OnClickListener() {
            //            @RequiresApi(api = Build.VERSION_CODES.M)
//            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//                int picd = 0;
//                if (picd == 0) {
//                    if (!checkCameraPermission()) {
//                        requestCameraPermissions();
//                    } else {
//                        pickFromGallery();
//                    }
//                } else if (picd == 1) {
//                    if (!checkStroagePermission()) {
//                        requestStoragePermission();
//
//                    } else {
                pickFromGallery();

//                    }
//                }
            }
        });
        SellerMyShopCityEditDetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i != 0) {
//                    service_id = cityList.get(i).toString();
//                    Log.e("ServiceId", service_id);
//                } else {
//                    service_id = cityList.get(i).toString();
//                    Log.e("ServiceId", service_id);
//                }
                String selected = adapterView.getItemAtPosition(i).toString();
                for(int k=0;k<cityListSpinner.length;k++)
                {
                    if(selected.equals(cityListSpinner[k]))
                        cListId=cityListTitleId[k];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SellerMyShopEditDetailsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatacallApi();
            }
        });

    }

    private void setDatacallApi() {
        Session session = new Session(SellerMyShopEditProfileActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        RequestBody sName = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopNameEditDetails.getEditableText().toString().trim());
        RequestBody sCity = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopCityEditDetails.getSelectedItem().toString().trim());
        RequestBody sPhone = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopPhoneEditDetails.getEditableText().toString().trim());
        RequestBody sEmail = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopEmailEditDetails.getEditableText().toString().trim());
        RequestBody sAddress = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopAddressEditDetails.getEditableText().toString().trim());
        RequestBody sAboutus = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopAboutusEditDetails.getEditableText().toString().trim());
        RequestBody sIframe = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopIframeEditDetails.getEditableText().toString().trim());
        RequestBody stime = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopopencloseEditDetails.getEditableText().toString().trim());
        RequestBody smaxRoom = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopMaxRoomEditDetails.getEditableText().toString().trim());
//        RequestBody sIframe = RequestBody.create(MediaType.parse("text/plain"), SellerMyShopIframeEditDetails.getEditableText().toString().trim());


        final RequestBody requestFileprofile;
        final File image = new File(upload_path);
        requestFileprofile = RequestBody.create(MediaType.parse("image/*"), image); ///*upload_path*/

        MultipartBody.Part sImage =
                MultipartBody.Part.createFormData("shop_image", upload_path.substring(upload_path.lastIndexOf("/") + 1), requestFileprofile);

//        sIframe=SellerMyShopIframeEditDetails.getEditableText().toString();
//        Session session = new Session(SellerMyShopActivity.this);
//        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        if (!upload_path.isEmpty()) {
            Call<SellerMyShopProfileUpdateModel> call = RetrofitClient.getInstance().getApi().  sellerMyShopProfileUpdate(auth,sName,sAddress,sCity,sImage,sPhone,sEmail,sAboutus,sIframe,smaxRoom);
            call.enqueue(new Callback<SellerMyShopProfileUpdateModel>() {
                @Override
                public void onResponse(Call<SellerMyShopProfileUpdateModel> call, Response<SellerMyShopProfileUpdateModel> response) {
                    if (response.isSuccessful()){
                        Intent intent=new Intent(SellerMyShopEditProfileActivity.this,SellerMyShopActivity.class);
                        startActivity(intent);
                        Toast.makeText(SellerMyShopEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(SellerMyShopEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<SellerMyShopProfileUpdateModel> call, Throwable t) {
                    Toast.makeText(SellerMyShopEditProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }else
        {
            Call<SellerMyShopProfileUpdateModel> call = RetrofitClient.getInstance().getApi().sellerMyShopProfileUpdateWithoutImage(auth,sName,sAddress,sCity,sPhone,sEmail,sAboutus,sIframe,smaxRoom);
            call.enqueue(new Callback<SellerMyShopProfileUpdateModel>() {
                @Override
                public void onResponse(Call<SellerMyShopProfileUpdateModel> call, Response<SellerMyShopProfileUpdateModel> response) {
                    if (response.isSuccessful()){
                        Intent intent=new Intent(SellerMyShopEditProfileActivity.this,SellerMyShopActivity.class);
                        startActivity(intent);
                        Toast.makeText(SellerMyShopEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(SellerMyShopEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<SellerMyShopProfileUpdateModel> call, Throwable t) {
                    Toast.makeText(SellerMyShopEditProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }


    private boolean checkStroagePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
//        CropImage.activity().start(this);
        try {
            if (ActivityCompat.checkSelfPermission(SellerMyShopEditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SellerMyShopEditProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
//        openGalleryIntent.setType("image/*");
//        startActivityForResult(openGalleryIntent, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermissions() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = data.getData();
                upload_path = getRealPathFromURIPath(resultUri, this);
                File imgFile = new File(upload_path);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    SellermyShopImageEditDetails.setImageBitmap(myBitmap);
                    Log.d("image_path", "Filename " + upload_path);
                }
                Picasso.get().load(resultUri).into(SellermyShopImageEditDetails);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == (PackageManager.PERMISSION_GRANTED);
                    boolean storage_accepted = grantResults[1] == (PackageManager.PERMISSION_GRANTED);
                    if (camera_accepted && storage_accepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please enable camera and storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {
                    boolean storage_accepted = grantResults[0] == (PackageManager.PERMISSION_GRANTED);
                    if (storage_accepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please enable storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;

        }

    }
    private void CityListSpinner()  {

        Session session = new Session(SellerMyShopEditProfileActivity.this);
//        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//
//        Log.e("Bearer ",auth);
//        HashMap<String, String> user = session.getUserDetails();
        Call<CityListModel> call=RetrofitClient.getInstance().getApi().cityList();
        call.enqueue(new Callback<CityListModel>() {
            @Override
            public void onResponse(Call<CityListModel> call, Response<CityListModel> response) {
                if (response.isSuccessful()) {
                    int Cityposition=0;
//                    progressDialog.dismiss();
                    if (response.body().getCities().size()!=0) {


                        int k = 0;
                        cityList= new ArrayList<>();
                        cityListSpinner= new String[response.body().getCities().size()];
                        cityListTitleId = new int[response.body().getCities().size()];
                        cListId = response.body().getCities().get(0).getId();
                        for (int i = 0; i < response.body().getCities().size(); i++) {
                            cityListSpinner[i] = response.body().getCities().get(i).getName();
                            if (response.body().getStatusCode()==200) {



                                cityList.add(response.body().getCities().get(i).getName());
                                Log.d("TAG", "onResponse: " + response.body().getCities().get(i).getName());
                                cityListTitleId[k] = response.body().getCities().get(i).getId();
                                k++;
                                if(Strcityid.equalsIgnoreCase(response.body().getCities().get(i).getName().toString())){
                                  Cityposition=i;

                                }
                            }

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SellerMyShopEditProfileActivity.this, android.R.layout.simple_spinner_item, cityList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SellerMyShopCityEditDetails.setAdapter(adapter);
                        Log.e("cityList", String.valueOf(SellerMyShopCityEditDetails));
                        SellerMyShopCityEditDetails.setSelection(Cityposition);

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CityListModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });




    }

    private void getDatacallApi() {
        Session session = new Session(SellerMyShopEditProfileActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<SellerMyShopModel> call = RetrofitClient.getInstance().getApi().sellerMyShop(auth);
        call.enqueue(new Callback<SellerMyShopModel>() {
            @Override
            public void onResponse(Call<SellerMyShopModel> call, Response<SellerMyShopModel> response) {
                if (response.isSuccessful()){

                    CityListSpinner();

                    Glide.with(SellermyShopImageEditDetails)
                            .load(response.body().getSeller().getShop_image()).fitCenter().into(SellermyShopImageEditDetails);
//                    upload_path=response.body().getSeller().getShop_image();
//                    Log.e("upload_path1",upload_path);
                    SellerMyShopNameEditDetails.setText(response.body().getSeller().getShop_name());
//                    SellerMyShopCityEditDetails.setText(response.body().getBasicinfo().getCity_id());
                  Strcityid=response.body().getBasicinfo().getCity_id().toString();
                    SellerMyShopPhoneEditDetails.setText(response.body().getBasicinfo().getPhone());
                    SellerMyShopEmailEditDetails.setText(response.body().getBasicinfo().getEmail());
                    SellerMyShopAddressEditDetails.setText(response.body().getSeller().getShop_address());
                    SellerMyShopAboutusEditDetails.setText(response.body().getSeller().getDescription());
                    SellerMyShopMaxRoomEditDetails.setText(response.body().getSeller().getRoom_capacity());
                    SellerMyShopopencloseEditDetails.setText(response.body().getSeller().getOpen_close_time());
                    SellerMyShopIframeEditDetails.setText(response.body().getSeller().getIframe());
                    Toast.makeText(SellerMyShopEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SellerMyShopEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerMyShopModel> call, Throwable t) {
                Toast.makeText(SellerMyShopEditProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}