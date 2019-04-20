package com.example.android.agameofgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class QuizPage extends AppCompatActivity {

    private Integer selection;
//    private int questionNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        Intent intent = getIntent();
        final String id = intent.getStringExtra(MainActivity.QUIZ_ID);
        final int questionNum = intent.getIntExtra(MainActivity.QUESTION_NUM, 1);
        final int score = intent.getIntExtra(MainActivity.SCORE, 0);

        // initializing views
        final TextView question = findViewById(R.id.question);
        final Button op1 = findViewById(R.id.op1);
        final Button op2 = findViewById(R.id.op2);
        final Button op3 = findViewById(R.id.op3);
        final Button op4 = findViewById(R.id.op4);
        Button next = findViewById(R.id.next);

//        op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//        op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//        op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//        op4.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));

        // initializing Cloud Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        Quiz quiz = new Quiz("Pick a color:", "red", "yellow", "blue", "green");
//        db.collection("quizzes").document("color").set(quiz);

//        DocumentReference docRef = db.collection("quizzes")
//                .document(id).collection("questions")
//                .document("question1");

        DocumentReference docRef2 = db.document("quizzes/" + id + "/questions/question" + questionNum);

        docRef2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                documentSnapshot.get("results");
                Quiz quiz = documentSnapshot.toObject(Quiz.class);

                question.setText(quiz.getQuestion());
                op1.setText(quiz.getOp1());
                op2.setText(quiz.getOp2());
                op3.setText(quiz.getOp3());
                op4.setText(quiz.getOp4());
            }
        });

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 1;
//                op1.setSelected(true);
//                op1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
////                op2.setSelected(false);
//                op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
////                op3.setSelected(false);
//                op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
////                op4.setSelected(false);
//                op4.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
            }
        });

        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 2;
//                op1.setSelected(false);
//                op2.setSelected(true);
//                op3.setSelected(false);
//                op4.setSelected(false);
//                op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
//                op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op4.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
            }
        });

        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 3;
//                op1.setSelected(false);
//                op2.setSelected(false);
//                op3.setSelected(true);
//                op4.setSelected(false);
//                op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
//                op4.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
            }
        });

        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = 4;
//                op1.setSelected(false);
//                op2.setSelected(false);
//                op3.setSelected(false);
//                op4.setSelected(true);
//                op1.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op2.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op3.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
//                op4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selection != null) {
                    // we know an option has been selected
                    if (questionNum == 5) {
                        // we know we're on the last question
                        // so we launch the activity for the results page
                        Toast.makeText(getApplicationContext(), "*launching results* score: " + (score + selection), Toast.LENGTH_SHORT).show();
                    } else {
                        // we're on any other question, so we launch the quiz page again
                        Intent i = new Intent(getApplicationContext(), QuizPage.class);
                        i.putExtra(MainActivity.QUIZ_ID, id);
                        i.putExtra(MainActivity.QUESTION_NUM, questionNum + 1);
                        i.putExtra(MainActivity.SCORE, score + selection);
                        startActivity(i);
                        finish();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please pick an option!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}