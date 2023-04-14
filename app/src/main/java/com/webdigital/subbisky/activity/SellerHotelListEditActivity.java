package com.webdigital.subbisky.activity;

import static com.webdigital.subbisky.activity.OTPVerifyActivity.userId;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.AmenitiesAdapter;
import com.webdigital.subbisky.adapter.SellerHotelListEditAmenitiesAdapter;
import com.webdigital.subbisky.model.SellerHotelAmenitiesModel;
import com.webdigital.subbisky.model.SellerHotelListEditModel;
import com.webdigital.subbisky.model.SellerHotelListModel;
import com.squareup.picasso.Picasso;


import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerHotelListEditActivity extends AppCompatActivity {
    EditText SellerEditHotelName,SellerEditHotelDesc,SellerEditHotelPriceDesc,
            SellerEditHotelLocLink,SellerEditHotelPrice,SellerEditHotelChildrenExtraCharge,
            SellerEditHotelAdultExtra,SellerEditHotelRoomSquareFeet,SellerEditHotelAvailableRooms,
            SellerEditHotelRoomCapacity;
    Spinner SellerEditHotelBedType,SellerCreateHotelstatus;
    Button EfirstImage,EsecondImage,EthirdImage,EfourthImage,EfifthImage,SellerHotelEditService,blockbtn;
    RadioGroup radioGroup;
    CheckBox radioAC,radioBathRoom,radioFood,radioParking,radioRestaurant,radioSpa,radioSwimmingPool,
            radioTV,radioWiFi;
    RadioButton radioActive,radioInActive;
    LinearLayout linerLayoutBack;
    ImageView SellerEditHotelFirstImage,SellerEditHotelSecondImage,
            SellerEditHotelThirdImage,SellerEditHotelFourthImage,SellerEditHotelFifthImage;
    String upload_path = "",upload_path1="",upload_path2="",upload_path3="",upload_path4="";
    private static int IMAGE_REQUEST = 1;
    String status;
    int flag = 0;
    CalendarView calender;
    CustomCalendar customCalendar;
    Dialog dialogmain;
//    LinearLayout linerLayoutBack;
    String AcId="1";
   int newposition=0;
    String id;
    Intent intent;
    private static final String IMAGE_DIRECTORY = "/image";
    private static final int BUFFER_SIZE = 1024 * 2;
    int hotel_id,position,sellerid;
    String Shotel_id,Ssellerid;
//    RecyclerView recyclerViewEditAmenities;
    SellerHotelListEditAmenitiesAdapter sellerHotelListEditAmenitiesAdapter;
    RecyclerView recyclerViewAmenities;

    public static List<String> amenitiesList = new ArrayList<String>();
    //    BirdsApi birdsApi;
    AmenitiesAdapter amenitiesAdapter;
//    private Object upload_path;
private WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_hotel_list_edit);
        intent=getIntent();
        hotel_id=intent.getIntExtra("hotel_id",hotel_id);
        sellerid=intent.getIntExtra("sellerid",sellerid);
        Shotel_id=String.valueOf(hotel_id);
        Ssellerid=String.valueOf(sellerid);
        newposition=intent.getIntExtra("newposition",position);


        recyclerViewAmenities=findViewById(R.id.hotelListEditAmenities);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        SellerEditHotelName=findViewById(R.id.SellerEditHotelName);
        SellerEditHotelDesc=findViewById(R.id.SellerEditHotelDesc);
        SellerEditHotelPriceDesc=findViewById(R.id.SellerEditHotelPriceDesc);
        SellerEditHotelLocLink=findViewById(R.id.SellerEditHotelLocLink);
        SellerEditHotelPrice=findViewById(R.id.SellerEditHotelPrice);
        SellerEditHotelChildrenExtraCharge=findViewById(R.id.SellerEditHotelChildrenExtraCharge);
        SellerEditHotelAdultExtra=findViewById(R.id.SellerEditHotelAdultExtra);
        SellerEditHotelRoomSquareFeet=findViewById(R.id.SellerEditHotelRoomSquareFeet);
        SellerEditHotelAvailableRooms=findViewById(R.id.SellerEditHotelAvailableRooms);
        SellerEditHotelRoomCapacity=findViewById(R.id.SellerEditHotelRoomCapacity);
        calender=findViewById(R.id.calender);
        customCalendar=findViewById(R.id.customCalendar);
        //Spinner
        SellerEditHotelBedType=findViewById(R.id.SellerEditHotelBedType);
        SellerCreateHotelstatus=findViewById(R.id.SellerCreateHotelstatus);
        //Button
        EfirstImage=findViewById(R.id.EfirstImage);
        EsecondImage=findViewById(R.id.EsecondImage);
        EthirdImage=findViewById(R.id.EthirdImage);
        EfourthImage=findViewById(R.id.EfourthImage);
        EfifthImage=findViewById(R.id.EfifthImage);
        SellerHotelEditService=findViewById(R.id.SellerHotelEditService);
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
        //ImageView
        SellerEditHotelFifthImage=findViewById(R.id.SellerEditHotelFifthImage);
        SellerEditHotelFourthImage=findViewById(R.id.SellerEditHotelFourthImage);
        SellerEditHotelThirdImage=findViewById(R.id.SellerEditHotelThirdImage);
        SellerEditHotelSecondImage=findViewById(R.id.SellerEditHotelSecondImage);
        SellerEditHotelFirstImage=findViewById(R.id.SellerEditHotelFirstImage);
        blockbtn=findViewById(R.id.blockbtn);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
//        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year,
//                                            int month, int dayOfMonth) {
//                calender .setMinDate(System.currentTimeMillis() - 1000);
//                dialogmain = new Dialog(SellerHotelListEditActivity.this);
//                dialogmain.setContentView(R.layout.roomblock_dialog);
//                dialogmain.show();
//                Button okbutton = (Button) dialogmain.findViewById(R.id.okbutton);
//                Button cancelbutton = (Button) dialogmain.findViewById(R.id.cancelbutton);
//                cancelbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialogmain.dismiss();
//                    }
//                });
//                okbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(SellerHotelListEditActivity.this, "Room blocked sucessfully", Toast.LENGTH_SHORT).show();
//                        dialogmain.dismiss();
//                    }
//                });
//                Toast.makeText(
//                        getApplicationContext(),
//                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
//
//            }
//
//        });
       // customCalendar.setDate(calendar,dateHashmap);

//        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
//                // get string date
//                String sDate=selectedDate.get(Calendar.DAY_OF_MONTH)
//                        +"/" +(selectedDate.get(Calendar.MONTH)+1)
//                        +"/" + selectedDate.get(Calendar.YEAR);
//
//                // display date in toast
//                Toast.makeText(getApplicationContext(),sDate, Toast.LENGTH_SHORT).show();
//            }
//        });

        blockbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebview = new WebView(SellerHotelListEditActivity.this);

                mWebview.loadUrl("https://subbisky.com/api/auth/block-hotel/"+sellerid+"/"+hotel_id);
                setContentView(mWebview);
            }
        });
        Session session = new Session(SellerHotelListEditActivity.this);
//        Session session = new Session(SellerCreateHotelDetailsActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<SellerHotelAmenitiesModel> call = RetrofitClient.getInstance().getApi().sellerHotelAmenitiesModel(auth);
        call.enqueue(new Callback<SellerHotelAmenitiesModel>() {
            @Override
            public void onResponse(Call<SellerHotelAmenitiesModel> call, Response<SellerHotelAmenitiesModel> response) {
                if (response.isSuccessful()){
//                    amenities=  response.body().getAmenities();
                    sellerHotelListEditAmenitiesAdapter= new SellerHotelListEditAmenitiesAdapter((List<SellerHotelAmenitiesModel.Amenities>) response.body().getAmenities(),SellerHotelListEditActivity.this);
                    recyclerViewAmenities.setAdapter(sellerHotelListEditAmenitiesAdapter);
//                        Toast.makeText(SellerCreateHotelDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else {
//                        Toast.makeText(SellerCreateHotelDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerHotelAmenitiesModel> call, Throwable t) {
                Toast.makeText(SellerHotelListEditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
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
                finish();
            }
        });
//        SellerEditHotelBedType = (Spinner) findViewById(R.id.SellerCreateHotelBedType);
        EfirstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=1;
            }
        });
        EsecondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=2;
            }
        });
        EthirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=3;
            }
        });
        EfourthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=4;
            }
        });
        EfifthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
                IMAGE_REQUEST=5;
            }
        });
        SellerHotelEditService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               setDataHotelList();

            }
        });
        getDataHotelList();
    }

    private void pickFromGallery() {
//        CropImage.activity().start(this);
        try {
            if (ActivityCompat.checkSelfPermission(SellerHotelListEditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SellerHotelListEditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
                        SellerEditHotelFirstImage.setImageBitmap(myBitmap);
                        SellerEditHotelFirstImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path);
                    }
                    SellerEditHotelFirstImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri).into(SellerEditHotelFirstImage);
                    SellerEditHotelFirstImage.setVisibility(View.VISIBLE);

                }
            }
            if (IMAGE_REQUEST == 2) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri1 = data.getData();
                    upload_path1 = getImgePathFromURI(getApplicationContext(),resultUri1);
                    File imgFile = new File(upload_path1);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerEditHotelSecondImage.setImageBitmap(myBitmap);
                        SellerEditHotelSecondImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path1);
                    }
                    SellerEditHotelSecondImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri1).into(SellerEditHotelSecondImage);
                    SellerEditHotelSecondImage.setVisibility(View.VISIBLE);

                }
            }
            if (IMAGE_REQUEST == 3) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri2 = data.getData();
                    upload_path2 = getImgePathFromURI(getApplicationContext(),resultUri2);
                    File imgFile = new File(upload_path2);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerEditHotelThirdImage.setImageBitmap(myBitmap);
                        SellerEditHotelThirdImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path2);
                    }
                    SellerEditHotelThirdImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri2).into(SellerEditHotelThirdImage);
                    SellerEditHotelThirdImage.setVisibility(View.VISIBLE);

                }
            }
            if (IMAGE_REQUEST == 4) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri3 = data.getData();
                    upload_path3 = getImgePathFromURI(getApplicationContext(),resultUri3);
                    File imgFile = new File(upload_path3);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerEditHotelFourthImage.setImageBitmap(myBitmap);
                        SellerEditHotelFourthImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path3);
                    }
                    SellerEditHotelFourthImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri3).into(SellerEditHotelFourthImage);
                    SellerEditHotelFourthImage.setVisibility(View.VISIBLE);

                }
            }
            if (IMAGE_REQUEST == 5) {
                if (resultCode == RESULT_OK) {
                    Uri resultUri4 = data.getData();
                    upload_path4 = getImgePathFromURI(getApplicationContext(),resultUri4);
                    File imgFile = new File(upload_path4);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        SellerEditHotelFifthImage.setImageBitmap(myBitmap);
                        SellerEditHotelFifthImage.setVisibility(View.VISIBLE);
                        Log.d("image_path", "Filename " + upload_path4);
                    }
                    SellerEditHotelFifthImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(resultUri4).into(SellerEditHotelFifthImage);
                    SellerEditHotelFifthImage.setVisibility(View.VISIBLE);

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
    private void setDataHotelList() {
        Session session = new Session(SellerHotelListEditActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelName.getEditableText().toString().trim());
        Log.e("Name",name.toString());
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelDesc.getEditableText().toString().trim());
        Log.e("description", description.toString());
        RequestBody google_location = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelLocLink.getEditableText().toString().trim());
        Log.e("google_location",google_location.toString());
        RequestBody price = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelPrice.getEditableText().toString().trim());
        Log.e("price",price.toString());
        RequestBody room_square_feet = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelRoomSquareFeet.getEditableText().toString().trim());
        Log.e("room_square_feet",room_square_feet.toString());
        RequestBody available_rooms = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelAvailableRooms.getEditableText().toString().trim());
        Log.e("available_rooms",available_rooms.toString());
        RequestBody bed_size = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelBedType.getSelectedItem().toString().trim());
        Log.e("bed_size",bed_size.toString());
        RequestBody statuses = RequestBody.create(MediaType.parse("text/plain"), SellerCreateHotelstatus.getSelectedItem().toString().trim());
        Log.e("statuses",statuses.toString());
        RequestBody amenities = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(amenitiesList));
        Log.e("amenities",amenities.toString());

        RequestBody adultExtra = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelAdultExtra.getEditableText().toString().trim());
        Log.e("adultExtra",adultExtra.toString());
        RequestBody childrenExtra = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelChildrenExtraCharge.getEditableText().toString().trim());
        Log.e("childrenExtra",childrenExtra.toString());
        RequestBody priceDescription = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelPriceDesc.getEditableText().toString().trim());
        Log.e("priceDescription",priceDescription.toString());
        RequestBody roomCapacity = RequestBody.create(MediaType.parse("text/plain"), SellerEditHotelRoomCapacity.getEditableText().toString());
        Log.e("roomCapacity",roomCapacity.toString());

        final RequestBody requestFileprofile;
        final File image1 = new File(upload_path);
        requestFileprofile = RequestBody.create(MediaType.parse("image/*"), image1); ///*upload_path*/

        MultipartBody.Part image =
                MultipartBody.Part.createFormData("image", upload_path.substring(upload_path.lastIndexOf("/") + 1), requestFileprofile);
        Log.e("image1",image1.toString());

        final RequestBody requestFileprofile2;
        final File image2 = new File(upload_path1);
        requestFileprofile2 = RequestBody.create(MediaType.parse("image/*"), image2); ///*upload_path*/

        MultipartBody.Part imageTwo =
                MultipartBody.Part.createFormData("imageTwo", upload_path1.substring(upload_path1.lastIndexOf("/") + 1), requestFileprofile2);
        Log.e("image2",image2.toString());

        final RequestBody requestFileprofile3;
        final File image3 = new File(upload_path2);
        requestFileprofile3 = RequestBody.create(MediaType.parse("image/*"), image3); ///*upload_path*/

        MultipartBody.Part imageThree =
                MultipartBody.Part.createFormData("imageThree", upload_path2.substring(upload_path2.lastIndexOf("/") + 1), requestFileprofile3);
        Log.e("image3",image3.toString());

        final RequestBody requestFileprofile4;
        final File image4 = new File(upload_path3);
        requestFileprofile4 = RequestBody.create(MediaType.parse("image/*"), image4); ///*upload_path*/

        MultipartBody.Part imageFour =
                MultipartBody.Part.createFormData("imageFour", upload_path3.substring(upload_path3.lastIndexOf("/") + 1), requestFileprofile4);
        Log.e("image4",image4.toString());

        final RequestBody requestFileprofile5;
        final File image5 = new File(upload_path4);
        requestFileprofile5 = RequestBody.create(MediaType.parse("image/*"), image5); ///*upload_path*/

        MultipartBody.Part imageFive =
                MultipartBody.Part.createFormData("imageFive", upload_path4.substring(upload_path4.lastIndexOf("/") + 1), requestFileprofile5);
        Log.e("image5",image5.toString());

        Call<SellerHotelListEditModel> call = RetrofitClient.getInstance().getApi().sellerHotelListEdit(auth,hotel_id,name,description,price,statuses,available_rooms,google_location,bed_size,priceDescription,adultExtra,childrenExtra,room_square_feet,amenities,image,imageTwo,imageThree,imageFour,imageFive,roomCapacity);
        call.enqueue(new Callback<SellerHotelListEditModel>() {
            @Override
            public void onResponse(Call<SellerHotelListEditModel> call, Response<SellerHotelListEditModel> response) {
                Toast.makeText(SellerHotelListEditActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<SellerHotelListEditModel> call, Throwable t) {
                Toast.makeText(SellerHotelListEditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });


    }


    private void getDataHotelList() {
        Session session = new Session(SellerHotelListEditActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<SellerHotelListModel> call = RetrofitClient.getInstance().getApi().sellerHotelList(auth);
        call.enqueue(new Callback<SellerHotelListModel>() {
            @Override
            public void onResponse(Call<SellerHotelListModel> call, Response<SellerHotelListModel> response) {
                SellerEditHotelName.setText(response.body().getHotels().get(newposition).getName());
                SellerEditHotelDesc.setText(response.body().getHotels().get(newposition).getDescription());
                SellerEditHotelPriceDesc.setText(response.body().getHotels().get(newposition).getPriceDescription());
                SellerEditHotelLocLink.setText(response.body().getHotels().get(newposition).getGoogle_location());
                SellerEditHotelPrice.setText(response.body().getHotels().get(newposition).getPrice());
                SellerEditHotelChildrenExtraCharge.setText(response.body().getHotels().get(newposition).getChildrenExtra());
                SellerEditHotelAdultExtra.setText(response.body().getHotels().get(newposition).getAdultExtra());
                SellerEditHotelRoomSquareFeet.setText(response.body().getHotels().get(newposition).getRoom_square_feet());
                SellerEditHotelAvailableRooms.setText(response.body().getHotels().get(newposition).getAvailable_rooms());
                SellerEditHotelRoomCapacity.setText(response.body().getHotels().get(newposition).getRoomCapacity());

                Glide.with(SellerEditHotelFirstImage)
                        .load(response.body().getHotels().get(newposition).getImage()).fitCenter().into(SellerEditHotelFirstImage);
                Glide.with(SellerEditHotelSecondImage)
                        .load(response.body().getHotels().get(newposition).getImageTwo()).fitCenter().into(SellerEditHotelSecondImage);
                Glide.with(SellerEditHotelThirdImage)
                        .load(response.body().getHotels().get(newposition).getImageThree()).fitCenter().into(SellerEditHotelThirdImage);
                Glide.with(SellerEditHotelFourthImage)
                        .load(response.body().getHotels().get(newposition).getImageFour()).fitCenter().into(SellerEditHotelFourthImage);
                Glide.with(SellerEditHotelFifthImage)
                        .load(response.body().getHotels().get(newposition).getImageFive()).fitCenter().into(SellerEditHotelFifthImage);
            }

            @Override
            public void onFailure(Call<SellerHotelListModel> call, Throwable t) {
                Toast.makeText(SellerHotelListEditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

    });


}
}