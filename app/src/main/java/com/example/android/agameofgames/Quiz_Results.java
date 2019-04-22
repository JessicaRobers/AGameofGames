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


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import  com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class Quiz_Results extends AppCompatActivity {

    private Button goHome;
    private Button retakeQuiz;
    private String resultNum;

    private String quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__results);

        final TextView yourResult= findViewById(R.id.quizResult);
        final TextView flavorText = findViewById(R.id.resultText);


//        final ImageView image = findViewById(R.id.resultImage);
//        final TextView yourResult= findViewById(R.id.quizResult);
//        final TextView flavorText= findViewById(R.id.resultText);
//
//        private ImageView resultImage = findViewById(R.id.resultImage);

        Intent intent = getIntent();
        quizID = intent.getStringExtra(MainActivity.QUIZ_ID);
        final int resultID = intent.getIntExtra(MainActivity.SCORE, 0);

        int imageNum;

        if (resultID <8) {
            resultNum = "result1";
            imageNum = 1;
        }
        else if (resultID <12) {
            resultNum = "result2";
            imageNum = 2;
        }
        else if (resultID <16) {
            resultNum = "result3";
            imageNum = 3;
        }
        else {
            resultNum = "result4";
            imageNum = 4;
        }

        // downloading the result image with FirebaseUI Storage
        // child path is the directory path in Firebase Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("images/" + quizID + "/results/image" + imageNum + ".jpg");

        // attaching the image view in the layout to Java
        ImageView imageView = findViewById(R.id.resultImage);

        // passing the storage reference from the database to the local imageview
        Glide.with(this).load(storageReference).into(imageView);

        // connecting to firebase cloud storage
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        // getting a reference to the database, using the quizID and the score to keep track of
        // which data path to follow in the collections and documents
        final DocumentReference quizResult = database.document("quizzes/" + quizID + "/results/" + resultNum);
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
                Intent i = new Intent(getApplicationContext(), QuizPage.class);
                i.putExtra(MainActivity.QUIZ_ID, quizID);
                i.putExtra(MainActivity.QUESTION_NUM, 1);
                i.putExtra(MainActivity.SCORE, 0);

                startActivity(i);
            }
        });

        goHome = findViewById(R.id.homeButton);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
            }

        });

    }

}
