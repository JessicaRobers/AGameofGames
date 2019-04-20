package com.example.android.agameofgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.firebase.ui.auth.AuthUI;
//import com.firebase.ui.auth.IdpResponse;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;

    public static final String QUIZ_ID = "com.example.android.agameofgames.ID";
    public static final String QUESTION_NUM = "com.example.android.agameofgames.NUM";
    public static final String SCORE = "com.example.android.agameofgames.SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String id = "bread";

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), QuizPage.class);
                i.putExtra(QUIZ_ID, "bread");
                i.putExtra(QUESTION_NUM, 1);
                i.putExtra(SCORE, 0);
                startActivity(i);
            }
        });

//        // choosing authentication providers
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build()
//        );
//
//        // creating and launching sign-in intent
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build(),
//                RC_SIGN_IN);


    }


    // adding authentication thing
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//
//            if (resultCode == RESULT_OK) {
//                // successfully signed in
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            } else {
//                // sign in failed
//            }
//        }
//    }
}
