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

    private void addValueToDatabase(final String isim,final String soyisim) {
        Log.i(LOG,"addValueToDatabase method is working");
        Call<Result> x = Manager.getInstance().addUser(isim,soyisim);
        x.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()){
                    result = response.body();
                    Toast.makeText(getApplicationContext(), result.getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void clearEdittext(){
        ad.setText("");
        soyad.setText("");
    }
}
