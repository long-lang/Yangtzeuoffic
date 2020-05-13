package com.sweethearts.contract;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

public interface LoginContract {
    interface LoginModel {
        void loadLoginEvent(final Activity activity, final LoginContract.LoginView view);
        void doLogin(final Activity activity,final String userName,final String password);
        void login(final Activity activity,final String userName,final String login_encode_pass);

    }

    public interface LoginView{
        EditText getUserName_ET();
        EditText getPassword_ET();
        Button getLogin_btn();
    }

    interface LoginPresenter {
        void loadLoginEvent();
    }
}
