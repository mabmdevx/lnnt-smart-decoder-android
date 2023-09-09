package com.example.mea.smartdecoder;

/**
 * Created by mea on 6/19/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mea.smartdecoder.AndroidMultiPartEntity.ProgressListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class DiagnosticsActivity extends AppCompatActivity {
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btnSubmitSmartSolve;
    private EditText editTextSerialNum;
    private TextView txtViewDiagInfo;
    long totalSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostics);

        txtViewDiagInfo = (TextView) findViewById(R.id.txtViewDiagInfo);
        btnSubmitSmartSolve = (Button) findViewById(R.id.btnSubmitSmartSolve);
        editTextSerialNum = (EditText) findViewById(R.id.editTextSerialNum);

        // Onload Get DiagInfo
        new GetDiagInfoFromServer().execute();

        btnSubmitSmartSolve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Sleep
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                showAlert("Ticket ID: INC000001");

                editTextSerialNum.setEnabled(false);
                btnSubmitSmartSolve.setEnabled(false);
                btnSubmitSmartSolve.setBackgroundColor(Color.parseColor("#C0C0C0"));

            }
        });

    }

    /**
     * Method to show alert dialog
     * */
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle("Submitted to Smart Solve")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    /**
     * Uploading the file to server
     * */
    private class GetDiagInfoFromServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            super.onPreExecute();
        }



        @Override
        protected String doInBackground(Void... params) {
            return getDiagInfo();
        }

        @SuppressWarnings("deprecation")
        private String getDiagInfo() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Config.DIAG_URL);

            try {

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(TAG, "Response from server: " + result);

            txtViewDiagInfo.setText(result);

            super.onPostExecute(result);
        }

    }

}