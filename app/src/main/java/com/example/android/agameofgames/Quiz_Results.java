package com.example.android.agameofgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import android.content.Intent;


import com.google.android.gms.tasks.OnSuccessListener;
import  com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Quiz_Results extends AppCompatActivity {

    private Button goHome;
    private Button retakeQuiz;
    private String imageID;
    private String resultNum;

    final TextView yourResult= findViewById(R.id.quizResult);
    final TextView flavorText= findViewById(R.id.resultText);

    ImageView burntToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__results);

        Intent intent = getIntent();
        final String quizID = intent.getStringExtra(MainActivity.QUIZ_ID);
        final int resultID = intent.getIntExtra(MainActivity.SCORE, 0);

        if (resultID <8) {
            resultNum = "result1";
        }
        else if (resultID <12) {
            resultNum = "result2";
        }
        else if (resultID <16) {
            resultNum = "result3";
        }
        else {
            resultNum = "result4";
        }

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        final DocumentReference quizResult = database.document("quizzes/" + "bread" + "/results/" + resultNum);
        quizResult.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                yourResult.setText(documentSnapshot.getString("type"));
                flavorText.setText(documentSnapshot.getString("message"));
            }
        });


        retakeQuiz = findViewById(R.id.retakeButton);
        retakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GO TO BEGINNING OF SAME QUIZ
            }
        });

        goHome = findViewById(R.id.homeButton);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RETURN TO HOMEPAGE
            }

        });

    }
}
