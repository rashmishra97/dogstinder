package com.example.dogstinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {

    TextInputEditText username, password;

    Button logInBtn;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googleBtn = findViewById(R.id.google_btn);

         username = findViewById(R.id.username);
         password = findViewById(R.id.password);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        logInBtn = findViewById(R.id.loginbtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Please sign in with Google", Toast.LENGTH_SHORT).show();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }


        googleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    signIn();
                }
            });
        }

        void signIn(){
            Intent signInIntent = gsc.getSignInIntent();
            startActivityForResult(signInIntent,1000);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode,Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1000){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                try {
                    task.getResult(ApiException.class);
                    navigateToSecondActivity();
                } catch (ApiException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

        }

        private void navigateToSecondActivity() {
            finish();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }

    }



/*

        google_sign = findViewById(R.id.google_sign);
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, options);

        google_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = googleSignInClient.getSignInIntent();
                startActivityForResult(i, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1234)
        {
            //Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Intent i = new Intent(LoginActivity.this, com.example.dogstinder.HomeActivity.class);
                            startActivity(i);

                        } else {

                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
*/
