package com.example.jose.instantsample;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sairamkrishna.myapplication.R;

import java.io.IOException;

import static com.example.sairamkrishna.myapplication.R.id.rep1;
import static com.example.sairamkrishna.myapplication.R.id.rep2;

public class play extends AppCompatActivity {

    TextView nombre;
    Bundle bMain;
    String [] nombres;
    ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12;
    TextView n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12;
    MediaPlayer mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        bMain = this.getIntent().getExtras();

        n1 = (TextView) findViewById(R.id.nombreGrabacion1);
        btn1 = (ImageButton) findViewById(R.id.rep1);
        mp1= assingButton(btn1, Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/0.3gp");

        n2 = (TextView) findViewById(R.id.nombreGrabacion2);
        btn2 = (ImageButton) findViewById(R.id.rep2);
        mp2= assingButton(btn1, Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/1.3gp");

        n3 = (TextView) findViewById(R.id.nombreGrabacion3);
        btn3 = (ImageButton) findViewById(R.id.rep3);
        mp3= assingButton(btn1, Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/2.3gp");

        n4 = (TextView) findViewById(R.id.nombreGrabacion4);
        btn4 = (ImageButton) findViewById(R.id.rep4);
        mp4= assingButton(btn1, Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/3.3gp");

        n5 = (TextView) findViewById(R.id.nombreGrabacion5);
        btn5 = (ImageButton) findViewById(R.id.rep5);
        mp5= assingButton(btn1, Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/4.3gp");

        n6 = (TextView) findViewById(R.id.nombreGrabacion6);
        btn6 = (ImageButton) findViewById(R.id.rep6);
        mp6= assingButton(btn1, Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/5.3gp");

        n7 = (TextView) findViewById(R.id.nombreGrabacion7);
        btn7 = (ImageButton) findViewById(R.id.rep7);
        mp7 = assingButton(btn7,Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/6.3gp");

        n8 = (TextView) findViewById(R.id.nombreGrabacion8);
        btn8 = (ImageButton) findViewById(R.id.rep8);
        mp8 = assingButton(btn8,Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/7.3gp");

        n9 = (TextView) findViewById(R.id.nombreGrabacion9);
        btn9 = (ImageButton) findViewById(R.id.rep9);
        mp9 = assingButton(btn9,Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/8.3gp");

        n10 = (TextView) findViewById(R.id.nombreGrabacion10);
        btn10 = (ImageButton) findViewById(R.id.rep10);
        mp10 = assingButton(btn10,Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/9.3gp");

        n11 = (TextView) findViewById(R.id.nombreGrabacion11);
        btn11 = (ImageButton) findViewById(R.id.rep11);
        mp11 = assingButton(btn11,Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/10.3gp");

        n12 = (TextView) findViewById(R.id.nombreGrabacion12);
        btn12 = (ImageButton) findViewById(R.id.rep12);
        mp12 = assingButton(btn12,Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/11.3gp");


        llenarNombres();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1.start();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp2.start();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3.start();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp4.start();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp5.start();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp6.start();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp7.start();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp8.start();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp9.start();
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp10.start();
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp11.start();
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp12.start();
            }
        });
    }


    public MediaPlayer assingButton(ImageButton boton,String source){
        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(source);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaPlayer;
    }


    public void llenarNombres(){
        if(bMain.getString("name0") != null){
            n1.setText(bMain.getString("name0"));
        }
        else{
            btn1.setEnabled(false);
            n1.setText("No hay sonido");
        }

        if(bMain.getString("name1") != null){
            n2.setText(bMain.getString("name1"));
        }
        else{
            btn2.setEnabled(false);
            n2.setText("No hay sonido");
        }

        if(bMain.getString("name2") != null){
            n3.setText(bMain.getString("name2"));
        }
        else{
            n3.setText("No hay sonido");
            btn3.setEnabled(false);
        }

        if(bMain.getString("name3") != null){
            n4.setText(bMain.getString("name3"));
        }
        else{
            n4.setText("No hay sonido");
            btn4.setEnabled(false);
        }

        if(bMain.getString("name4") != null){
            n5.setText(bMain.getString("name4"));
        }
        else{
            n5.setText("No hay sonido");
            btn5.setEnabled(false);
        }

        if(bMain.getString("name5") != null){
            n6.setText(bMain.getString("name5"));
        }
        else{
            n6.setText("No hay sonido");
            btn5.setEnabled(false);
        }

        if(bMain.getString("name6") != null){
            n7.setText(bMain.getString("name6"));
        }
        else{
            n7.setText("No hay sonido");
            btn7.setEnabled(false);
        }

        if(bMain.getString("name7") != null){
            n8.setText(bMain.getString("name7"));
        }
        else{
            n8.setText("No hay sonido");
            btn8.setEnabled(false);
        }

        if(bMain.getString("name8") != null){
            n9.setText(bMain.getString("name8"));
        }
        else{
            n9.setText("No hay sonido");
            btn9.setEnabled(false);
        }

        if(bMain.getString("name9") != null){
            n10.setText(bMain.getString("name9"));
        }
        else{
            n10.setText("No hay sonido");
            btn10.setEnabled(false);
        }

        if(bMain.getString("name10") != null){
            n11.setText(bMain.getString("name10"));
        }
        else{
            n11.setText("No hay sonido");
            btn11.setEnabled(false);
        }

        if(bMain.getString("name11") != null){
            n12.setText(bMain.getString("name11"));
        }
        else{
            n12.setText("No hay sonido");
            btn12.setEnabled(false);
        }


    }
}
