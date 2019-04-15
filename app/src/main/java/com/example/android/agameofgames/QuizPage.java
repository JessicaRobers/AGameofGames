package com.example.android.agameofgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class QuizPage extends AppCompatActivity {

//    Quiz page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.QUIZ_ID);

//        Quiz page;

        // initializing views
        final TextView question = findViewById(R.id.question);
        final Button op1 = findViewById(R.id.op1);
        final Button op2 = findViewById(R.id.op2);
        final Button op3 = findViewById(R.id.op3);
        final Button op4 = findViewById(R.id.op4);
        Button next = findViewById(R.id.next);

        // setting button click listeners


        // initializing Cloud Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        Quiz quiz = new Quiz("Pick a color:", "red", "yellow", "blue", "green");
//        db.collection("quizzes").document("color").set(quiz);

        DocumentReference docRef = db.collection("quizzes")
                .document(id).collection("questions")
                .document("question1");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Quiz quiz = documentSnapshot.toObject(Quiz.class);

                question.setText(quiz.getQuestion());
                op1.setText(quiz.getOp1());
                op2.setText(quiz.getOp2());
                op3.setText(quiz.getOp3());
                op4.setText(quiz.getOp4());
            }
        });





    }
}