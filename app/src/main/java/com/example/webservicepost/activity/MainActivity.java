package com.example.webservicepost.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webservicepost.R;
import com.example.webservicepost.model.Result;
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

    Result result;

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

    private void addValueToDatabase(final String name,final String surname) {
        Log.i(LOG,"addValueToDatabase method is working");
        result = new Result();
        Call<Result> x = Manager.getInstance().addUser(name,surname);
        x.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.i(LOG,"onResponse method is working");
                if(response.isSuccessful()){
                    Log.i(LOG,"response.isSuccessful() method is working");
                    result = response.body();
                    Toast.makeText(getApplicationContext(), result.getResult(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.i(LOG,t.toString());
            }
        });
    }

    private void clearEdittext(){
        ad.setText("");
        soyad.setText("");
    }
}
