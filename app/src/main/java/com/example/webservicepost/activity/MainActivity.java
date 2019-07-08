package com.example.webservicepost.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webservicepost.R;
import com.example.webservicepost.model.User;
import com.example.webservicepost.retrofit.Manager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getName();

    @BindView(R.id.ad)
    EditText ad;

    @BindView(R.id.soyad)
    EditText soyad;

    @BindView(R.id.btn)
    Button btn;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        add();
    }

    private void add() {
        Log.i(LOG,"add method is working");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isim = ad.getText().toString();
                String soyisim = soyad.getText().toString();
                if(!isim.equals("") && !soyisim.equals("")) {
                    addValueToDatabase(isim, soyisim);
                    clearEdittext();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "boş değer girmeyin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addValueToDatabase(String isim, String soyisim) {
        Log.i(LOG,"addValueToDatabase method is working");
        Call<User> x = Manager.getInstance().addUser(isim,soyisim);
        x.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                    Log.i(LOG,"addValueToDatabase | user :  " + user.getName() + " " + user.getSurname());
                    Toast.makeText(getApplicationContext(), user.getName() + " " + user.getSurname(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void clearEdittext(){
        ad.setText("");
        soyad.setText("");
    }
}
