package com.example.shachindratripathi.jktshoppingcartapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity  {


    LoginButton fbButton;
    CallbackManager callbackManager;
    TextView textView;
    Menu menuObj = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // displayUserInterface();
      //  menuObj.displayMenu();
        loginMethod();
        loginWithFb();
        FacebookSdk.sdkInitialize(getApplicationContext());

    }
    private void loginMethod(){
        callbackManager = CallbackManager.Factory.create();
        fbButton = (LoginButton)findViewById(R.id.login_button);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void loginWithFb(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               textView.setText("Login Success\n"+loginResult.getAccessToken());
                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
               textView.setText("Login failed");
            }

            @Override
            public void onError(FacebookException error) {
               textView.setText("Error occured while login" + error.getMessage());
            }
        });


    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
/*<ImageView
        android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@mipmap/ic_launcher"
                android:id="@+id/imgIcon"/>

<TextView
         android:layout_width="wrap_content"
                 android:layout_height="wrap_content"
                 android:text="@string/app_name"
                 android:textSize="24dp"
                 android:padding="12dp"
                 android:id="@+id/textTitle"
                 android:layout_gravity="center"
                 android:layout_weight="1"
                 android:layout_marginTop="15dp"
                 android:layout_alignParentTop="true"
                 android:layout_alignParentRight="true"
                 android:layout_alignParentEnd="true" /> */

