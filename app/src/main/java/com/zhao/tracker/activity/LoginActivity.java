package com.zhao.tracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.zhao.tracker.R;
import com.zhao.tracker.presenter.LoginPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {


    @InjectView(R.id.et_key)
    EditText etKey;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.start();
    }

    public EditText getEtKey() {
        return etKey;
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }
}
