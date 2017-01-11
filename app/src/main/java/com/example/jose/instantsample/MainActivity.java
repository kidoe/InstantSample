package com.example.jose.instantsample;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.example.sairamkrishna.myapplication.R;


public class MainActivity extends AppCompatActivity {

    Button buttonStart, buttonStop, buttonPlayLastRecordAudio,
            buttonStopPlayingRecording, buttonSave, buttonNext;
    String AudioSavePathInDevice = null;

    String [] grabaciones;
    int nGrabaciones;
    TextView name, nGuardados;
    MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;
    File folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.button);
        buttonStop = (Button) findViewById(R.id.button2);
        buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);
        buttonStopPlayingRecording = (Button)findViewById(R.id.button4);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        name =(TextView) findViewById(R.id.text1);
        nGuardados = (TextView) findViewById(R.id.nGuardados);
        nGrabaciones =0 ;
        grabaciones = new String[12];

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);
        buttonSave.setEnabled(false);
        name.setEnabled(false);
        buttonNext.setEnabled(false);
        random = new Random();
        folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/InstantSample/");
        folder.delete();
        folder.mkdirs();
        AudioSavePathInDevice =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/" + nGrabaciones + ".3gp";



        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Comprobar si tenemos los permisos apropiados
                if(checkPermission()) {

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantSample/" + nGrabaciones + ".3gp";
                    MediaRecorderReady();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();

                    } catch (IllegalStateException e) {
                        /* TODO Auto-generated catch block */
                        e.printStackTrace();
                    } catch (IOException e) {
                    // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    buttonStart.setEnabled(false);
                    buttonStop.setEnabled(true);
                    buttonNext.setEnabled(false);
                    buttonSave.setEnabled(false);
                    name.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Recording started",
                            Toast.LENGTH_SHORT).show();
                } else {
                    requestPermission();
                }

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mediaRecorder.stop();
                }
                catch (RuntimeException stopException){
                    stopException.getMessage();
                }

                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonSave.setEnabled(true);
                name.setEnabled(true);
                buttonNext.setEnabled(true);
                Toast.makeText(MainActivity.this, "Recording Completed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(MainActivity.this, "Recording Playing",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);

                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MediaRecorderReady();
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(buttonStart.isEnabled() == true){

                    if (nGrabaciones < 11){
                   // mediaRecorder.setOutputFile("/raw/" + name.getText().toString());
                    grabaciones[nGrabaciones] = name.getText().toString();
                    nGuardados.setText("Saved: " + nGrabaciones + 1 + " of 12");
                    name.setText("Name it");
                    buttonSave.setEnabled(false);
                    name.setEnabled(false);
                    buttonPlayLastRecordAudio.setEnabled(false);

                    nGrabaciones = nGrabaciones +1;
                    Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_SHORT).show();

                    }
                else{
                    Toast.makeText(MainActivity.this,"12 sound limit reached",Toast.LENGTH_SHORT).show();
                }

                }
                else{
                    Toast.makeText(MainActivity.this,"You have to record something before",Toast.LENGTH_SHORT).show();
                }
            }

        });
        name.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                name.setText("");
            }



        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, play.class);
                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();

                b.putString("name0",grabaciones[0]);
                b.putString("name1",grabaciones[1]);
                b.putString("name2",grabaciones[2]);
                b.putString("name3",grabaciones[3]);
                b.putString("name4",grabaciones[4]);
                b.putString("name5",grabaciones[5]);
                b.putString("name6",grabaciones[6]);
                b.putString("name7",grabaciones[7]);
                b.putString("name8",grabaciones[8]);
                b.putString("name9",grabaciones[9]);
                b.putString("name10",grabaciones[10]);
                b.putString("name11ª",grabaciones[11]);


                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

//    public String CreateRandomAudioFileName(int string){
//        StringBuilder stringBuilder = new StringBuilder( string );
//        int i = 0 ;
//        while(i < string ) {
//            stringBuilder.append(RandomAudioFileName.
//                    charAt(random.nextInt(RandomAudioFileName.length())));
//
//            i++ ;
//        }
//        return stringBuilder.toString();
//    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

}