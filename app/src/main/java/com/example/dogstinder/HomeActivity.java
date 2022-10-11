package com.example.dogstinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recycler;
    Toolbar toolbar;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         recycler = findViewById(R.id.recycler);

        name = findViewById(R.id.name);
        signOutBtn = findViewById(R.id.signout);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


        inIt();
    }

    private void inIt() {

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
        }


        Call<DataModel> call = Retrofit_client_call.getApi().get_image();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {


            if(response.isSuccessful())
            {
                DataModel dataModel = response.body();
                //System.out.println("???????? "+ dataModel.getMessage() +"========================== "+ response.code()+" +++++++++++++ "+ response.message());
                //Toast.makeText(HomeActivity.this, "response code is "+ response.code(), Toast.LENGTH_SHORT).show();


                Log.d("SSS", "DAT HAIN "+ dataModel.getMessage());

               if(dataModel.getStatus().equals("success"))
               {
                   Toast.makeText(HomeActivity.this, "Message is "+response.isSuccessful()+" "+ dataModel.getMessage() + " ===== "+ HomeActivity.this, Toast.LENGTH_LONG).show();
                   System.out.println("============================== "+ dataModel.getMessage());
                    MyAdapter adapter = new MyAdapter(dataModel.getMessage(), HomeActivity.this);
                    recycler.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    recycler.setAdapter(adapter);
               }
            }

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage() , Toast.LENGTH_LONG).show();
                System.out.println("===================== "+ t.getMessage()+" CAUSE============ "+ t.getCause());
            }
        });

    }

    private void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });
    }
}