package cn.ysbang.camera2;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.android.example.cameraxbasic.fragments.CameraFragment;

public class CameraActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.component_sign_camera_activity);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.cl_component_sign_camera_activity_content, CameraFragment.Companion.newInstance())
                    .commit();
        }
    }
}
