package com.example.carlos.miniinstaf;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    ImageView ivImage;
    //ImageView icono;
    //TextView text;
    Integer REQUEST_CAMERA =1, SELECT_FILE=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       ivImage = (ImageView) findViewById(R.id.ivImage);


        //Icono botones
        //Drawable drawable = ContextCompat.getDrawable(this,R.drawable.polaroid);
        //Bitmap bitmap =((BitmapDrawable)drawable).getBitmap();
        //icono.setImageBitmap(bitmap);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                SeleccionarImagen();
            }
        });
    }

    private void SeleccionarImagen(){
        final CharSequence[] items = {"Camara","Galeria", "Cancelar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Agregar imagen");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(items[which].equals("Camara")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }else if(items[which].equals("Galeria")){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media. EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Seleccione un archivo"),SELECT_FILE);
                }else if(items[which].equals("Cancelar")){
                    dialog.dismiss();
                }
            }
        });
        //mostrarFiltros();
        builder.show();

    }
/*
    public void mostrarFiltros(){
        //Asignar ubicacion de los botones
        LinearLayout btnConteiner = new LinearLayout(getApplicationContext());
        btnConteiner.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
        btnConteiner.setOrientation(LinearLayout.VERTICAL);
        btnConteiner.setGravity(Gravity.RIGHT);
        //Crear botones dinamicamente
        for(int i=0; i<5;i++) {
            final LinearLayout buttonContainer = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main, null);
            ImageView btnImg = (ImageView) buttonContainer.findViewById(R.id.icon);
            TextView btnText = (TextView) buttonContainer.findViewById(R.id.text);
            btnText.setText("Filtro" + i);
            btnText.setBackgroundColor(43);
            btnImg.setImageResource(R.mipmap.ic_launcher);
            buttonContainer.setTag(i);

            buttonContainer.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast.makeText(getApplicationContext(),"Listener botón" + v.getTag(),Toast.LENGTH_SHORT).show();
                }


        });
            //Va agregando botones al contenedor
            btnConteiner.addView(buttonContainer);

        }
        //Crear contenedor para agregar contenedor de botones
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(400,1500,Gravity.RIGHT);
        //Agregar contenedor de botones
        addContentView(btnConteiner,params);


    }
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode==REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bitmap = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bitmap);

            }else if(requestCode==SELECT_FILE){
                Uri selectImageUri = data.getData();
                ivImage.setImageURI(selectImageUri);

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
