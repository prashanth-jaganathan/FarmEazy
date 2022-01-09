package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class show_predicted extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] langOptions = {"English", "Hindi", "Kannada"};
    Boolean speak = false;
    String predictedCrop;
    TextView textView;
    TextView wikipedia;
    private OkHttpClient okhttp;
    private SeekBar seek;
    private TextToSpeech mtts;
    TextView t;
    ImageButton b;
    String k;
    String lang;
    Spinner spinner;
    ConstraintLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_predicted);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        predictedCrop = intent.getStringExtra("crop-predicted");
        layout = findViewById(R.id.show_predicted_constraint_layout);
        //layout.setBackground(getResources().getDrawable(R.drawable.apple));
        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, langOptions);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        textView.setText(predictedCrop);
        b=findViewById(R.id.speak);
        okhttp=new OkHttpClient();
        t=findViewById(R.id.textView);
        //String lang;
        wikipedia = findViewById(R.id.wikipedia_content);
        lang="en";
        b.setBackground(getResources().getDrawable(R.drawable.no_sound_icon_foreground));

        RequestBody body=new FormBody.Builder().add("data", predictedCrop +" "+lang).build();

        Request request=new Request.Builder().url("http://niranjan.pythonanywhere.com/wiki").post(body).build();

        okhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(), "Serverdown", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "run() returned: "  );
                        e.printStackTrace();
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                k=response.body().string();
                if(response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            wikipedia.setText(k);
                            wikipedia.setMovementMethod(new ScrollingMovementMethod());
                            //System.out.println("hey:"+k);
                            //Intent intent=new Intent(MainActivity.this,Dynamic.class);
                            //intent.putExtra("key1",k);
                            //startActivity(intent);
                        }
                    });
                }
            }
        });

        mtts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int res=mtts.setLanguage(Locale.ENGLISH);
                    if(res == TextToSpeech.LANG_MISSING_DATA
                            || res == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Langauage not supportred");
                    }
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speak == false) {
                    speak = true;
                    b.setBackground(show_predicted.this.getResources().getDrawable(R.drawable.sound_icon_foreground));
                    Speak();
                }
                else
                {
                    mtts.stop();
                    b.setBackground(show_predicted.this.getResources().getDrawable(R.drawable.no_sound_icon_foreground));
                    speak = false;
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
    {
        //Toast.makeText(getApplicationContext(), langOptions[pos], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void Speak(){
        float pitch=0.5f;
        float speed=1f;
        mtts.setPitch(pitch);
        mtts.setSpeechRate(speed);
        mtts.speak(k,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onStop() {
        if(mtts != null){
            mtts.stop();
            mtts.shutdown();
        }
        super.onStop();

    }

}
