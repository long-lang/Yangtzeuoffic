package com.sweethearts.presenter;

import android.app.Activity;

import com.sweethearts.contract.LoginContract;
import com.sweethearts.model.LoginModel;


public class LoginPresenter implements LoginContract.LoginPresenter {  private LoginModel model;
    private LoginContract.LoginView view;
    private Activity activity;

    public LoginPresenter(Activity activity, LoginContract.LoginView view) {
        this.view = view;
        this.activity = activity;
        model = new LoginModel();
    }
    public void loadLoginEvent() {
        model.loadLoginEvent(activity, view);
    }
}
