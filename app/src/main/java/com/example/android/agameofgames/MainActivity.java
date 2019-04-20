package com.example.android.agameofgames;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button goHome;
    Button retakeQuiz;
    int imageID;
    ImageView burntToast;

    public static final String QUIZ_ID = "com.example.android.agameofgames.ID";
    public static final String QUESTION_NUM = "com.example.android.agameofgames.NUM";
    public static final String SCORE = "com.example.android.agameofgames.SCORE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
