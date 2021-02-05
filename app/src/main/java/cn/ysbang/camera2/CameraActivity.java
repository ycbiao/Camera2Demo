package cn.ysbang.camera2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.Navigation;


public class CameraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_one_activity);
//        Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_permissions_to_camera);
//        Navigation.findNavController(this, R.id.fragment_container);
    }

    @Override
    public boolean onSupportNavigateUp() {

        return Navigation.findNavController(this, R.id.fragment_container).navigateUp();
    }
}
