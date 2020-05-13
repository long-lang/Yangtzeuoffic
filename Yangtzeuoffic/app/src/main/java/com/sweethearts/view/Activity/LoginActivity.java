package com.sweethearts.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sweethearts.R;
import com.sweethearts.contract.LoginContract;
import com.sweethearts.presenter.LoginPresenter;

public class LoginActivity extends FragmentActivity implements LoginContract.LoginView {

    private EditText userName_ET;
    private EditText password_ET;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setEvents();
    }

    public EditText getUserName_ET() {
        return userName_ET;
    }

    public EditText getPassword_ET() {
        return password_ET;
    }

    public Button getLogin_btn() {
        return login_btn;
    }

    public void init() {
        userName_ET = findViewById(R.id.username);
        password_ET = findViewById(R.id.password);
        login_btn =  findViewById(R.id.login);
    }


    public void setEvents(){
        LoginPresenter loginPresenter = new LoginPresenter(this, this);
        loginPresenter.loadLoginEvent();
    }
}
