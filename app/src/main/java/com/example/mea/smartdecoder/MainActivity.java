package com.example.mea.smartdecoder;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button btnLedDecoder, btnShellAccess, btnRunDiagnostics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLedDecoder = (Button) findViewById(R.id.btnLedDecoder);
        btnShellAccess = (Button) findViewById(R.id.btnShellAccess);
        btnRunDiagnostics = (Button) findViewById(R.id.btnRunDiagnostics);

        btnLedDecoder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(MainActivity.this, CaptureActivity.class);
                startActivity(nextScreen);
            }
        });

        btnShellAccess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(Config.SHELL_URL));
                startActivity(intent);
            }
        });

        btnRunDiagnostics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(MainActivity.this, DiagnosticsActivity.class);
                startActivity(nextScreen);
            }
        });

    }
}