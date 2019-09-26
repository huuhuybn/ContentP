package com.dotplays.contentp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadContacts(View view) {


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Toast.makeText(this,"Chua cap quyen doc Danh Ba",Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    999);
        }else {

            Log.e("ABC","GET CONTACT1");
            // khoi tao duong dan toi noi luu danh ba
            Uri uri = Uri.parse("content://contacts/people");
            // khoi tao 1 bien de luu cac danh ba

            String contacts = "";

            CursorLoader cursorLoader = new CursorLoader(this, uri,
                    null, null, null, null);

            Log.e("ABC","GET CONTACT2");

            // truy van lay du lieu bo con con tro?
            Cursor cursor = cursorLoader.loadInBackground();
            Log.e("ABC","GET CONTACT3");

            if (cursor != null && cursor.getCount() > 0) {
                Log.e("ABC","GET CONTACT4 :" + cursor.getCount());

                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    Log.e("ABC","GET CONTACT XXX");

                    String idColumnName = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
                    String name = cursor.getString(cursor.getColumnIndex(idColumnName));


                    contacts += name   + "\n";

                    cursor.moveToNext();


                }
                cursor.close();

                TextView tvContact = findViewById(R.id.tvContacts);
                tvContact.setText(contacts);


            }else {
                Toast.makeText(this,"Khong Co Danh Ba",Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 999){

        }

    }
}
