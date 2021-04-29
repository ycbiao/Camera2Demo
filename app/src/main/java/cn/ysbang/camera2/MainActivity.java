package cn.ysbang.camera2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.FileUtils;
import android.provider.MediaStore;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private  Uri mTakePhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toolbar = findViewById(R.id.btn_take);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CameraActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void copyToGallery(File file, Activity activity, Uri uri){
        ContentResolver contentResolver = activity.getContentResolver();
        if(uri == null) {
            return;
        }
        try {
            OutputStream out = contentResolver.openOutputStream(uri);
            FileInputStream fis = new FileInputStream(file);
            FileUtils.copy(fis,out);
            fis.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mTakePhotoUri = createMediaImageUri(this);
//        File mFile = ((File)data.getSerializableExtra(MediaStore.EXTRA_OUTPUT));
//        copyToGallery(mFile,this,mTakePhotoUri);
//        Toast.makeText(this,mTakePhotoUri.toString(),Toast.LENGTH_LONG).show();
    }

    public static Uri createMediaImageUri(Context context){
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "IMG_" + timeStamp;
//            File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//            File image = File.createTempFile(imageFileName,  ".jpg", storageDir);
//            Uri insertUri = getUriFromFile(mContext,image);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DESCRIPTION, imageFileName);
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName);
            values.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//                values.put(MediaStore.Images.Media.IS_PENDING, 0);
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/");
            }

            ContentResolver resolver = context.getContentResolver();
            Uri external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            return resolver.insert(external, values);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}