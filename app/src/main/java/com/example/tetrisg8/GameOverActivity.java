package com.example.tetrisg8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewPuntuacion;
    TextView textViewTiempo;
    DatabaseClass myDb;
    Button btnviewAll;
    ImageView fotoUsuario;
    ImageView[] listimage;
    TextView[] nameTextView;
    AlertDialog.Builder dialogBuilder;
    Bitmap foto;
    int cont;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        //Recibimos la puntuación del main
        int puntuacion = getIntent().getExtras().getInt("puntuacion");
        //Mostramos la puntuación
        int tiempo = getIntent().getExtras().getInt("tiempo");
        DateFormat df = new SimpleDateFormat("mm:ss");
        foto= (Bitmap)getIntent().getParcelableExtra("foto");
        fotoUsuario= (ImageView) findViewById(R.id.imageViewPlayer);
        fotoUsuario.setImageBitmap(foto);
        //Mostramos la puntuación
        textViewPuntuacion = (TextView) findViewById(R.id.score);
        textViewPuntuacion.setText(String.valueOf(puntuacion));
        textViewTiempo = (TextView) findViewById(R.id.time);
        textViewTiempo.setText(String.valueOf( df.format(tiempo)));
        Button botonSalir = (Button) this.findViewById(R.id.botSalir);
        botonSalir.setOnClickListener(this);
        Button botonJugar = (Button) this.findViewById(R.id.botJugar);
        botonJugar.setOnClickListener(this);
        //Si la puntuación alcanza los 250 puntos mostramos el botón de reiniciar partida
        if(puntuacion >= 250){
            botonJugar.setVisibility(View.VISIBLE);
        }
        myDb = new DatabaseClass(this);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        this.dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.ranking_dialog, null);

        dialogBuilder.setView(dialogView);
        listimage= new ImageView[]{(ImageView)dialogView.findViewById(R.id.imageView22), (ImageView)dialogView.findViewById(R.id.imageView23),(ImageView)dialogView.findViewById(R.id.imageView27),(ImageView)dialogView.findViewById(R.id.imageView28),(ImageView)dialogView.findViewById(R.id.imageView29),(ImageView)dialogView.findViewById(R.id.imageView30),(ImageView)dialogView.findViewById(R.id.imageView31),(ImageView)dialogView.findViewById(R.id.imageView32),(ImageView)dialogView.findViewById(R.id.imageView33),(ImageView)dialogView.findViewById(R.id.imageView34)};
        nameTextView= new TextView[]{(TextView) dialogView.findViewById(R.id.textView2),(TextView) dialogView.findViewById(R.id.textView13),(TextView) dialogView.findViewById(R.id.textView14),(TextView) dialogView.findViewById(R.id.textView15),(TextView) dialogView.findViewById(R.id.textView16),(TextView) dialogView.findViewById(R.id.textView17),(TextView) dialogView.findViewById(R.id.textView18),(TextView) dialogView.findViewById(R.id.textView19),(TextView) dialogView.findViewById(R.id.textView20),(TextView) dialogView.findViewById(R.id.textView21)};

        viewAll();
    }

     private void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage();
                    }
                }
        );
    }


    @Override
    public void onBackPressed() {
        //Impedimos que se pueda salir de la ventana al pulsar el botón de atrás del movil
    }

    public void showMessage() {
        Cursor res = myDb.getAllData();
  cont=0;



        if (res.getCount() == 0) {
            // show message

            Toast toast1 =Toast.makeText(getApplicationContext(),
                    "NOTHING FOUND", Toast.LENGTH_SHORT);

            toast1.show();
            return;
        }
        while(res.moveToNext()){
            if (cont < 10) {
                nameTextView[cont].append("     Name :" +res.getString(1) + "\n");
                nameTextView[cont].append("     Score:"+res.getString(2) + "\n");
                nameTextView[cont].append("     Time :" +res.getString(3) );
                Bitmap image;
                if (res.getBlob(4)!=null){
                    image = BitmapFactory.decodeByteArray(res.getBlob(4),0,res.getBlob(4).length);
                }else{
                    image=BitmapFactory.decodeResource(this.getResources(), R.drawable.take_photo);
                }
                listimage[cont].setImageBitmap(image);
            }
            cont++;
        }

        dialogBuilder.create().show();
    }




    public void onClick (View v){
            if (v.getId() == R.id.botSalir) {
                StartGame.ventanaInicio.finish();
                MainActivity.ventanaTablero.finish();
                Alert.ventanaNombre.finish();
                //TakePhoto.ventanaFoto.finish();
                finish();

            }else if(v.getId() == R.id.botJugar){
                /*Intent pantallaInicial = new Intent(this, StartGame.class);
                startActivity(pantallaInicial);*/
                Alert.ventanaNombre.finish();
                MainActivity.ventanaTablero.finish();
                //TakePhoto.ventanaFoto.finish();
                finish();
            }
        }
    }


