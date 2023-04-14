package com.webdigital.subbisky.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerCategoryModel;
import com.webdigital.subbisky.model.SellerCreateProductModel;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

public class SellerCreateProductActivity extends AppCompatActivity {
    Spinner SellerCategoryName;
    EditText SellerProductName,SellerProductDesc,SellerProductUnit,SellerProductMrp,SellerSellingPrice,SellerStockQuantity;
    ImageView SellerProductImage;
    Button SellerProductImagebtn,SellerCreateProduct;
    RadioGroup radioGroup;
    RadioButton radioActive,radioInActive;
    LinearLayout linerLayoutBack;
    Session session;
    String status;
    int flag = 0;
    String upload_path = "";
    List<String> serviceList;
    String[] serviceListSpinner;
    int[] serviceListTitleId;
    int sListId;
    private static final String IMAGE_DIRECTORY = "/image";
    private static final int BUFFER_SIZE = 1024 * 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_create_product);
        SellerCreateProduct=findViewById(R.id.SellerCreateProduct);
        SellerCategoryName=findViewById(R.id.SellerCategoryName);
        SellerProductName=findViewById(R.id.SellerProductName);
        SellerProductDesc=findViewById(R.id.SellerProductDesc);
        SellerProductUnit=findViewById(R.id.SellerProductUnit);
        SellerProductMrp=findViewById(R.id.SellerProductMrp);
        SellerSellingPrice=findViewById(R.id.SellerSellingPrice);
        SellerStockQuantity=findViewById(R.id.SellerStockQuantity);
        SellerProductImage=findViewById(R.id.SellerProductImage);
        SellerProductImagebtn=findViewById(R.id.SellerProductImagebtn);
        radioGroup=findViewById(R.id.radioGroup);
        radioActive=findViewById(R.id.radioActive);
        radioInActive=findViewById(R.id.radioInActive);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        SellerProductImage.setVisibility(View.GONE);
        SellerProductImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
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
        ServiceListSpinner();
        SellerCategoryName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                for(int k=0;k<serviceListSpinner.length;k++)
                {
                    if(selected.equals(serviceListSpinner[k]))
                        sListId=serviceListTitleId[k];


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SellerCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(SellerProductName.getEditableText().toString()).isEmpty() && !(SellerProductDesc.getEditableText().toString()).isEmpty() && !(SellerProductUnit.getEditableText().toString()).isEmpty()  && !(SellerProductMrp.getEditableText().toString()).isEmpty()  && !(SellerSellingPrice.getEditableText().toString()).isEmpty()  && !(SellerStockQuantity.getEditableText().toString()).isEmpty()  && !(SellerProductImage.toString()).isEmpty()  ) {
                    setProduct();
                }else {
                    Toast.makeText(SellerCreateProductActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SellerCreateProductActivity.this,SellerDashBoardActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();

    }

    private void pickFromGallery() {
//        CropImage.activity().start(this);
        try {
            if (ActivityCompat.checkSelfPermission(SellerCreateProductActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SellerCreateProductActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
            if (resultCode == RESULT_OK) {
                Uri resultUri = data.getData();
                upload_path = getImgePathFromURI(getApplicationContext(),resultUri);
                File imgFile = new File(upload_path);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    SellerProductImage.setImageBitmap(myBitmap);
                    SellerProductImage.setVisibility(View.VISIBLE);
                    Log.d("image_path", "Filename " + upload_path);
                }
                SellerProductImage.setVisibility(View.VISIBLE);
                Picasso.get().load(resultUri).into(SellerProductImage);
                SellerProductImage.setVisibility(View.VISIBLE);

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

    private void setProduct() {

        final ProgressDialog progressDialog = new ProgressDialog( SellerCreateProductActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        session = new Session(SellerCreateProductActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        RequestBody category_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(sListId));
//        String category_id = session.getSellerCategoryId(Integer.parseInt(Session.SELLER_CATEGORY_ID));
        Log.e("category_id",category_id.toString());
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), SellerProductName.getEditableText().toString().trim());
        Log.e("name", name.toString());
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), SellerProductDesc.getEditableText().toString().trim());
        Log.e("description",description.toString());
        RequestBody unit = RequestBody.create(MediaType.parse("text/plain"), SellerProductUnit.getEditableText().toString().trim());
        Log.e("unit",unit.toString());
        RequestBody mrp_price = RequestBody.create(MediaType.parse("text/plain"), SellerProductMrp.getEditableText().toString().trim());
        Log.e("mrp_price",mrp_price.toString());
        RequestBody selling_price = RequestBody.create(MediaType.parse("text/plain"), SellerSellingPrice.getEditableText().toString().trim());
        Log.e("selling_price",selling_price.toString());
        RequestBody stock = RequestBody.create(MediaType.parse("text/plain"), SellerStockQuantity.getEditableText().toString().trim());
        Log.e("stock",stock.toString());
        RequestBody statuses = RequestBody.create(MediaType.parse("text/plain"), status);
        Log.e("statuses",statuses.toString());
        final RequestBody requestFileprofile;
        final File image1 = new File(upload_path);
        requestFileprofile = RequestBody.create(MediaType.parse("image/*"), image1); ///*upload_path*/

        MultipartBody.Part image =
                MultipartBody.Part.createFormData("image", upload_path.substring(upload_path.lastIndexOf("/") + 1), requestFileprofile);
        Log.e("image1",image1.toString());

        Call<SellerCreateProductModel> call = RetrofitClient.getInstance().getApi().sellerCreateProduct(auth,category_id,
                name,description,mrp_price,selling_price,statuses,stock,unit,image);
        call.enqueue(new Callback<SellerCreateProductModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SellerCreateProductModel> call, Response<SellerCreateProductModel> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(SellerCreateProductActivity.this,SellerProductListActivity.class);
                    startActivity(intent);
                    Toast.makeText(SellerCreateProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }else {
//                    Toast.makeText(SellerCreateProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SellerCreateProductModel> call, Throwable t) {
                Toast.makeText(SellerCreateProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
    }
    private void  ServiceListSpinner()  {

        Session session = new Session(SellerCreateProductActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//
//        Log.e("Bearer ",auth);
//        HashMap<String, String> user = session.getUserDetails();
        Call<SellerCategoryModel> call = RetrofitClient.getInstance().getApi().getSellerCategory(auth);
        call.enqueue(new Callback<SellerCategoryModel>() {
            @Override
            public void onResponse(Call<SellerCategoryModel> call, Response<SellerCategoryModel> response) {
                if (response.isSuccessful()) {
//                    progressDialog.dismiss();
                    if (response.body().getCategories().size()!=0) {
                        int k = 0;
                        serviceList= new ArrayList<>();
                        serviceListSpinner= new String[response.body().getCategories().size()];
                        serviceListTitleId = new int[response.body().getCategories().size()];
                        sListId = response.body().getCategories().get(0).getId();
                        for (int i = 0; i < response.body().getCategories().size(); i++) {
                            serviceListSpinner[i] = response.body().getCategories().get(i).getName();
                            if (response.body().getStatusCode()==200) {
                                serviceList.add(response.body().getCategories().get(i).getName());
                                Log.d("TAG", "onResponse: " + response.body().getCategories().get(i).getName());
                                serviceListTitleId[k] = response.body().getCategories().get(i).getId();
                                k++;
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SellerCreateProductActivity.this, android.R.layout.simple_spinner_item, serviceList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SellerCategoryName.setAdapter(adapter);
                        Log.e("ServiceList", String.valueOf(SellerCategoryName));

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SellerCategoryModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });




    }
}