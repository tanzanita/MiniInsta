package com.example.carlos.miniinstaf;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    //ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast toast1 = Toast.makeText(getApplicationContext(), "Entro a main 2", Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER, 0, 0);

        toast1.show();

        //imagen  = (ImageView) findViewById(R.id.ivImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
/*
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String img = bundle.getString("IMAGEN");
            Bitmap bitmap = (Bitmap) bundle.get("intent");

            imagen.setImageBitmap(bitmap);

        }

*/


}

}