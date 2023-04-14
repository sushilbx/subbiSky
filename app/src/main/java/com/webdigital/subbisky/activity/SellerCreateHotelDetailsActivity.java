package com.webdigital.subbisky.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.AmenitiesAdapter;
import com.webdigital.subbisky.adapter.SellerHotelListCreateAmenitiesAdapter;
import com.webdigital.subbisky.model.SellerCreateHotelDetailsModel;
import com.webdigital.subbisky.model.SellerHotelAmenitiesModel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerCreateHotelDetailsActivity extends AppCompatActivity {
    private static final String IMAGE_DIRECTORY = "/image";
    private static final int BUFFER_SIZE = 1024 * 2;
    RecyclerView recyclerViewAmenities;
    EditText SellerCreateHotelName,SellerCreateHotelDesc,SellerCreateHotelPriceDesc,
            SellerCreateHotelLocLink,SellerCreateHotelPrice,SellerCreateHotelChildrenExtraCharge,
            SellerCreateHotelAdultExtra,SellerCreateHotelRoomSquareFeet,SellerCreateHotelAvailableRooms,
            SellerCreateHotelRoomCapacity;
    Spinner SellerCreateHotelBedType,SellerCreateHotelstatus;

    //    List<String> cityList;
//    String[] cityListSpinner;
//    int[] cityListTitleId;
//    int sListId;
    Button firstImage,secondImage,thirdImage,fourthImage,fifthImage,SellerCreateHotelCreateService;
    RadioGroup radioGroup;
    CheckBox radioAC,radioBathRoom,radioFood,radioParking,radioRestaurant,radioSpa,radioSwimmingPool,
            radioTV,radioWiFi;
    RadioButton radioActive,radioInActive;
    LinearLayout linerLayoutBack;
    ImageView SellerCreateHotelFirstImage,SellerCreateHotelSecondImage,
            SellerCreateHotelThirdImage,SellerCreateHotelFourthImage,SellerCreateHotelFifthImage;
    String upload_path = "",upload_path1="",upload_path2="",upload_path3="",upload_path4="";
    private static int IMAGE_REQUEST = 1;
    String status;
    int flag = 0;
    String AcId="1";
    ArrayList<String> aminitiesArray = new ArrayList<String>();
    SellerHotelListCreateAmenitiesAdapter sellerHotelListCreateAmenitiesAdapter;

//    ListView recyclerViewAmenities;
    List<SellerHotelAmenitiesModel.Amenities>amenities;
//    BirdsApi birdsApi;
    AmenitiesAdapter amenitiesAdapter;
    public static List<String> amenitiesList = new ArrayList<String>();
//    private Object upload_path;
    int i = 0;
    private WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_create_hotel_details);
        recyclerViewAmenities=findViewById(R.id.hotelListCreateAmenities);
        recyclerViewAmenities.setHasFixedSize(true);
        recyclerViewAmenities.setLayoutManager(new LinearLayoutManager(SellerCreateHotelDetailsActivity.this,LinearLayoutManager.VERTICAL, false));

        //EditText
        SellerCreateHotelName=findViewById(R.id.SellerCreateHotelName);
        SellerCreateHotelDesc=findViewById(R.id.SellerCreateHotelDesc);
        SellerCreateHotelPriceDesc=findViewById(R.id.SellerCreateHotelPriceDesc);
        SellerCreateHotelLocLink=findViewById(R.id.SellerCreateHotelLocLink);
        SellerCreateHotelPrice=findViewById(R.id.SellerCreateHotelPrice);
        SellerCreateHotelChildrenExtraCharge=findViewById(R.id.SellerCreateHotelChildrenExtraCharge);
        SellerCreateHotelAdultExtra=findViewById(R.id.SellerCreateHotelAdultExtra);
        SellerCreateHotelRoomSquareFeet=findViewById(R.id.SellerCreateHotelRoomSquareFeet);
        SellerCreateHotelAvailableRooms=findViewById(R.id.SellerCreateHotelAvailableRooms);
        SellerCreateHotelRoomCapacity=findViewById(R.id.SellerCreateHotelRoomCapacity);
        //Spinner
        SellerCreateHotelBedType=findViewById(R.id.SellerCreateHotelBedType);
        SellerCreateHotelstatus=findViewById(R.id.SellerCreateHotelstatus);
        //Button
        firstImage=findViewById(R.id.firstImage);
        secondImage=findViewById(R.id.secondImage);
        thirdImage=findViewById(R.id.thirdImage);
        fourthImage=findViewById(R.id.fourthImage);
        fifthImage=findViewById(R.id.fifthImage);
        SellerCreateHotelCreateService=findViewById(R.id.SellerCreateHotelCreateService);

        //ImageView
        SellerCreateHotelFifthImage=findViewById(R.id.SellerCreateHotelFifthImage);
        SellerCreateHotelFourthImage=findViewById(R.id.SellerCreateHotelFourthImage);
        SellerCreateHotelThirdImage=findViewById(R.id.SellerCreateHotelThirdImage);
        SellerCreateHotelSecondImage=findViewById(R.id.SellerCreateHotelSecondImage);
        SellerCreateHotelFirstImage=findViewById(R.id.SellerCreateHotelFirstImage);

        SellerCreateHotelFirstImage.setVisibility(View.GONE);
        SellerCreateHotelSecondImage.setVisibility(View.GONE);
        SellerCreateHotelThirdImage.setVisibility(View.GONE);
        SellerCreateHotelFourthImage.setVisibility(View.GONE);
        SellerCreateHotelFifthImage.setVisibility(View.GONE);

        //CheckBox
        radioGroup=findViewById(R.id.radioGroup);
        radioActive=findViewById(R.id.radioActive);
        radioInActive=findViewById(R.id.radioInActive);
//        radioAC=findViewById(R.id.radioAC);
//        radioBathRoom=findViewById(R.id.radioBathRoom);
//        radioFood=findViewById(R.id.radioFood);
//        radioParking=findViewById(R.id.radioParking);
//        radioRestaurant=findViewById(R.id.radioRestaurant);
//        radioSpa=findViewById(R.id.radioSpa);
//        radioSwimmingPool=findViewById(R.id.radioSwimmingPool);
//        radioTV=findViewById(R.id.radioTV);
//        radioWiFi=findViewById(R.id.radioWiFi);
//
//        if(radioAC.isChecked()){
//            aminitiesArray.add("1");
//        }else {
//            aminitiesArray.remove("1");
//        }
//        if(radioBathRoom.isChecked()){
//            aminitiesArray.add("2");
//        }else {
//            aminitiesArray.remove("2");
//        }
//        if(radioFood.isChecked()){
//            aminitiesArray.add("3");
//        }else {
//            aminitiesArray.remove("3");
//        }
//        if(radioParking.isChecked()){
//            aminitiesArray.add("4");
//        }else {
//            aminitiesArray.remove("4");
//        }
//        if(radioRestaurant.isChecked()){
//            aminitiesArray.add("5");
//        }else {
//            aminitiesArray.remove("5");
//        }
//        if(radioSpa.isChecked()){
//            aminitiesArray.add("6");
//        }else {
//            aminitiesArray.remove("6");
//        }
//        if(radioSwimmingPool.isChecked()){
//            aminitiesArray.add("7");
//        }else {
//            aminitiesArray.remove("7");
//        }
//        if(radioTV.isChecked()){
//            aminitiesArray.add("8");
//        }else {
//            aminitiesArray.remove("8");
//        }
//        if(radioWiFi.isChecked()){
//            aminitiesArray.add("9");
//        }else {
//            aminitiesArray.remove("9");
//        }

        String[] myArray = new String[aminitiesArray.size()];
        aminitiesArray.toArray(myArray);
        Log.e("myArray", String.valueOf(myArray));





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioActive) {
                    status = "Active";
                    flag = 1;
                }else if (checkedId == R.id.radioInActive) {
                    status = "InActive";
                    flag = 2;
                }

            }
        });
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        SellerCreateHotelBedType = (Spinner) findViewById(R.id.SellerCreateHotelBedType);
        SellerCreateHotelstatus = (Spinner) findViewById(R.id.SellerCreateHotelstatus);
        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=1;
            }
        });
        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=2;
            }
        });
        thirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=3;
            }
        });
        fourthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=4;
            }
        });

        fifthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=5;
            }
        });
        SellerCreateHotelCreateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                    mWebview = new WebView(SellerCreateHotelDetailsActivity.this);
//                    mWebview.loadUrl("https://www.subbisky.com/seller/home");
//                    mWebview.getSettings().setJavaScriptEnabled(true);
//                    setContentView(mWebview);
                setDatacallApi();
            }
        });
        Session session = new Session(SellerCreateHotelDetailsActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<SellerHotelAmenitiesModel> call = RetrofitClient.getInstance().getApi().sellerHotelAmenitiesModel(auth);
        call.enqueue(new Callback<SellerHotelAmenitiesModel>() {
            @Override
            public void onResponse(Call<SellerHotelAmenitiesModel> call, Response<SellerHotelAmenitiesModel> response) {
                if (response.isSuccessful()){
//                        amenities=  response.body().getAmenities();
                    sellerHotelListCreateAmenitiesAdapter = new SellerHotelListCreateAmenitiesAdapter((List<SellerHotelAmenitiesModel.Amenities>) response.body().getAmenities(),SellerCreateHotelDetailsActivity.this);
                    recyclerViewAmenities.setAdapter(sellerHotelListCreateAmenitiesAdapter);
//                        Toast.makeText(SellerCreateHotelDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
//                        Toast.makeText(SellerCreateHotelDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            @Override
            public void onFailure(Call<SellerHotelAmenitiesModel> call, Throwable t) {
                Toast.makeText(SellerCreateHotelDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void pickFromGallery() {
//        CropImage.activity().start(this);
        try {
            if (ActivityCompat.checkSelfPermission(SellerCreateHotelDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SellerCreateHotelDetailsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (IMAGE_REQUEST == 1) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri = data.getData();
                    upload_path = getImgePathFromURI(getApplicationContext(),resultUri);
                    File imgFile = new File(upload_path);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerCreateHotelFirstImage.setImageBitmap(myBitmap);
                        SellerCreateHotelFirstImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path);
                    }
                    SellerCreateHotelFirstImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri).into(SellerCreateHotelFirstImage);
                    SellerCreateHotelFirstImage.setVisibility(View.VISIBLE);

//                    Uri seletecdImageUri = data.getData();
//                    upload_path=getImgePathFromURI(this,seletecdImageUri);
//                    Log.v("TAG","path "+ upload_path);
//                    File imgFile=new File(upload_path);
//                    SellerCreateHotelFirstImage.setVisibility(View.VISIBLE);
//                    SellerCreateHotelFirstImage.setImageURI(seletecdImageUri);
                }
            }
            if (IMAGE_REQUEST == 2) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri1 = data.getData();
                    upload_path1 = getImgePathFromURI(getApplicationContext(),resultUri1);
                    File imgFile = new File(upload_path1);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerCreateHotelSecondImage.setImageBitmap(myBitmap);
                        SellerCreateHotelSecondImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path1);
                    }
                    SellerCreateHotelSecondImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri1).into(SellerCreateHotelSecondImage);
                    SellerCreateHotelSecondImage.setVisibility(View.VISIBLE);

//                    Uri seletecdImageUri1 = data.getData();
//                    upload_path1=getImgePathFromURI(this,seletecdImageUri1);
//                    Log.v("TAG","path "+ upload_path1);
//                    File imgFile=new File(upload_path1);
//                    SellerCreateHotelSecondImage.setVisibility(View.VISIBLE);
//                    SellerCreateHotelSecondImage.setImageURI(seletecdImageUri1);
                }
            }
            if (IMAGE_REQUEST == 3) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri2 = data.getData();
                    upload_path2 = getImgePathFromURI(getApplicationContext(),resultUri2);
                    File imgFile = new File(upload_path2);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerCreateHotelThirdImage.setImageBitmap(myBitmap);
                        SellerCreateHotelThirdImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path2);
                    }
                    SellerCreateHotelThirdImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri2).into(SellerCreateHotelThirdImage);
                    SellerCreateHotelThirdImage.setVisibility(View.VISIBLE);

//                    Uri seletecdImageUri2 = data.getData();
//                    upload_path2=getImgePathFromURI(this,seletecdImageUri2);
//                    Log.v("TAG","path "+ upload_path2);
//                    File imgFile=new File(upload_path2);
//                    SellerCreateHotelThirdImage.setVisibility(View.VISIBLE);
//                    SellerCreateHotelThirdImage.setImageURI(seletecdImageUri2);
                }
            }
            if (IMAGE_REQUEST == 4) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri3 = data.getData();
                    upload_path3 = getImgePathFromURI(getApplicationContext(),resultUri3);
                    File imgFile = new File(upload_path3);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerCreateHotelFourthImage.setImageBitmap(myBitmap);
                        SellerCreateHotelFourthImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path3);
                    }
                    SellerCreateHotelFourthImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri3).into(SellerCreateHotelFourthImage);
                    SellerCreateHotelFourthImage.setVisibility(View.VISIBLE);

//                    Uri seletecdImageUri3 = data.getData();
//                    upload_path3=getImgePathFromURI(this,seletecdImageUri3);
//                    Log.v("TAG","path "+ upload_path3);
//                    File imgFile=new File(upload_path3);
//                    SellerCreateHotelFourthImage.setVisibility(View.VISIBLE);
//                    SellerCreateHotelFourthImage.setImageURI(seletecdImageUri3);
                }
            }
            if (IMAGE_REQUEST == 5) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri4 = data.getData();
                    upload_path4 = getImgePathFromURI(getApplicationContext(),resultUri4);
                    File imgFile = new File(upload_path4);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerCreateHotelFifthImage.setImageBitmap(myBitmap);
                        SellerCreateHotelFifthImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path4);
                    }
                    SellerCreateHotelFifthImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri4).into(SellerCreateHotelFifthImage);
                    SellerCreateHotelFifthImage.setVisibility(View.VISIBLE);

//                    Uri seletecdImageUri4 = data.getData();
//                    upload_path4=getImgePathFromURI(this,seletecdImageUri4);
//                    Log.v("TAG","path "+ upload_path4);
//                    File imgFile=new File(upload_path4);
//                    SellerCreateHotelFifthImage.setVisibility(View.VISIBLE);
//                    SellerCreateHotelFifthImage.setImageURI(seletecdImageUri4);
                }
            }
        }
    }
    public static String getImgePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        File wallpaperDirectory = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+File.separator+"MYAppFolder");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(wallpaperDirectory + File.separator + fileName);
            // create folder if not exists

            copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }
    public String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//        byte[] byteArray = bytes.toByteArray();
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        Log.e("pathsize", path);
//        return Uri.parse(path);
//    }
    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }
    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

//    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
//        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            return contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            return cursor.getString(idx);
//        }
//    }

    private void setDatacallApi() {
        Session session = new Session(SellerCreateHotelDetailsActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//        auth,name,description,price,status,available_rooms,google_location,bed_size,room_square_feet,amenities,
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelName.getEditableText().toString().trim());
        Log.e("Name",name.toString());
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelDesc.getEditableText().toString().trim());
        Log.e("description", description.toString());
        RequestBody google_location = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelLocLink.getEditableText().toString().trim());
        Log.e("google_location",google_location.toString());
        RequestBody price = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelPrice.getEditableText().toString().trim());
        Log.e("price",price.toString());
        RequestBody room_square_feet = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelRoomSquareFeet.getEditableText().toString().trim());
        Log.e("room_square_feet",room_square_feet.toString());
        RequestBody available_rooms = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelAvailableRooms.getEditableText().toString().trim());
        Log.e("available_rooms",available_rooms.toString());
        RequestBody bed_size = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelBedType.getSelectedItem().toString().trim());
        Log.e("bed_size",bed_size.toString());
        RequestBody adultExtra = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelAdultExtra.getEditableText().toString().trim());
        Log.e("adultExtra",adultExtra.toString());
        RequestBody childrenExtra = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelChildrenExtraCharge.getEditableText().toString().trim());
        Log.e("childrenExtra",childrenExtra.toString());
        RequestBody priceDescription = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelPriceDesc.getEditableText().toString().trim());
        Log.e("priceDescription",priceDescription.toString());
        RequestBody roomCapacity = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelRoomCapacity.getEditableText().toString());
        Log.e("roomCapacity",roomCapacity.toString());
//        RequestBody statuses = RequestBody.create(MediaType.parse("text/plain"), status);
//        Log.e("statuses",statuses.toString());
        RequestBody statuses = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelstatus.getSelectedItem().toString().trim());
        Log.e("statuses",statuses.toString());
        RequestBody amenities = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(amenitiesList));
        Log.e("amenities",amenities.toString());
//String name=SellerCreateHotelName.getEditableText().toString().trim();
//String description=SellerCreateHotelDesc.getEditableText().toString().trim();
//String google_location=SellerCreateHotelLocLink.getEditableText().toString().trim();
//String price=SellerCreateHotelPrice.getEditableText().toString().trim();
//String room_square_feet=SellerCreateHotelRoomSquareFeet.getEditableText().toString().trim();
//String available_rooms=SellerCreateHotelAvailableRooms.getEditableText().toString().trim();
//String bed_size=SellerCreateHotelBedType.getSelectedItem().toString().trim();
//String statuses=status.toString();
//        String amenities=AcId.toString();

        final RequestBody requestFileprofile;
        final File image1 = new File(upload_path);
        requestFileprofile = RequestBody.create(MediaType.parse("image/*"), image1); ///*upload_path*/

        MultipartBody.Part image =
                MultipartBody.Part.createFormData("image", upload_path.substring(upload_path.lastIndexOf("/") + 1), requestFileprofile);
        Log.e("image1",image.toString());

        final RequestBody requestFileprofile2;
        final File image2 = new File(upload_path1);
        requestFileprofile2 = RequestBody.create(MediaType.parse("image/*"), image2); ///*upload_path*/

        MultipartBody.Part imageTwo =
                MultipartBody.Part.createFormData("imageTwo", upload_path1.substring(upload_path1.lastIndexOf("/") + 1), requestFileprofile2);
        Log.e("image2",imageTwo.toString());

        final RequestBody requestFileprofile3;
        final File image3 = new File(upload_path2);
        requestFileprofile3 = RequestBody.create(MediaType.parse("image/*"), image3); ///*upload_path*/

        MultipartBody.Part imageThree =
                MultipartBody.Part.createFormData("imageThree", upload_path2.substring(upload_path2.lastIndexOf("/") + 1), requestFileprofile3);
        Log.e("image3",imageThree.toString());

        final RequestBody requestFileprofile4;
        final File image4 = new File(upload_path3);
        requestFileprofile4 = RequestBody.create(MediaType.parse("image/*"), image4); ///*upload_path*/

        MultipartBody.Part imageFour =
                MultipartBody.Part.createFormData("imageFour", upload_path3.substring(upload_path3.lastIndexOf("/") + 1), requestFileprofile4);
        Log.e("image4",imageFour.toString());

        final RequestBody requestFileprofile5;
        final File image5 = new File(upload_path4);
        requestFileprofile5 = RequestBody.create(MediaType.parse("image/*"), image5); ///*upload_path*/

        MultipartBody.Part imageFive =
                MultipartBody.Part.createFormData("imageFive", upload_path4.substring(upload_path4.lastIndexOf("/") + 1), requestFileprofile5);
        Log.e("image5",imageFive.toString());

        Call<SellerCreateHotelDetailsModel> call = RetrofitClient.getInstance().getApi().sellerCreateHotelsDetails(auth,name,description,price,statuses,available_rooms,google_location,bed_size,priceDescription,adultExtra,childrenExtra,room_square_feet,amenities,image,imageTwo,imageThree,imageFour,imageFive,roomCapacity);
        call.enqueue(new Callback<SellerCreateHotelDetailsModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SellerCreateHotelDetailsModel> call, Response<SellerCreateHotelDetailsModel> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(SellerCreateHotelDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SellerCreateHotelDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerCreateHotelDetailsModel> call, Throwable t) {
                Toast.makeText(SellerCreateHotelDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}