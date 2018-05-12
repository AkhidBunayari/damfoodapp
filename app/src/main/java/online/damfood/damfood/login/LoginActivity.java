package online.damfood.damfood.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import online.damfood.damfood.R;
import online.damfood.damfood.main.MainActivity;
import online.damfood.damfood.model.Logins;
import online.damfood.damfood.network.NetworkService;
import online.damfood.damfood.network.RestApi;
import online.damfood.damfood.penjual.PenjualActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.checked;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNIM, etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViews();

        etNIM = (EditText) (findViewById(R.id.et_nim));
        etPassword = (EditText) (findViewById(R.id.et_password));
        btnLogin = (Button) (findViewById(R.id.btn_login));
        pbLogin = (ProgressBar) findViewById(R.id.pb_login);
        pbLogin.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(this);

        checkValidation();
    }

    private void loginViews() {
        etNIM = (EditText) findViewById(R.id.et_nim);
        etNIM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.hasText(etNIM);
            }
        });
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isNIM(etNIM, false)) ret = false;

        return ret;
    }

    private void userSignIn() {
        String nim = etNIM.getText().toString().trim();
        nim = nim.replace(".","");
        String password = etPassword.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkService.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);


        Call<Logins> call = service.userLogin(nim, password);

        call.enqueue(new Callback<Logins>() {
            @Override
            public void onResponse(Call<Logins> call, Response<Logins> response) {
                if (!response.body().getError()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), PenjualActivity.class));

                } else {
                    Toast.makeText(getApplicationContext(), "NIM dan Password Salah", Toast.LENGTH_LONG).show();
                    btnLogin.setEnabled(true);
                    pbLogin.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Logins> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            userSignIn();
            btnLogin.setEnabled(false);
            pbLogin.setVisibility(View.VISIBLE);
        }
    }
}
