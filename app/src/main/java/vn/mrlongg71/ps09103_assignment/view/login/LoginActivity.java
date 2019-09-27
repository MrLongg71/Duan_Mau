package vn.mrlongg71.ps09103_assignment.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.library.Dialog;
import vn.mrlongg71.ps09103_assignment.presenter.login.PresenterLogin;
import vn.mrlongg71.ps09103_assignment.view.activity.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IViewLogin {

    private CallbackManager callbackManager;
    private Button btnLogin;
    private EditText edtEmail,edtPass;
    private TextView txtForgotPassword;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        txtForgotPassword = findViewById(R.id.txtForgotpassword);
        progressDialog = new ProgressDialog(LoginActivity.this);
        edtEmail.setText("abc@gmail.com");
        edtPass.setText("123456");
        initEvent();
    }

    private void initEvent() {
        btnLogin.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String email = edtEmail.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                doLoginUser(email,password);
                break;
            case R.id.txtForgotpassword:
                initForgotpassword();
                break;
        }
    }

    private void initForgotpassword() {
        android.app.Dialog dialogForgotpassword = new android.app.Dialog(this);
        dialogForgotpassword.setContentView(R.layout.custom_dialog_getpass);
        dialogForgotpassword.getWindow().setBackgroundDrawableResource(R.color.float_transparent);
        dialogForgotpassword.show();

    }

    private void doLoginUser(String email,String password){
        if(initCheckVailied(email,password)){
            Dialog.DialogLoading(progressDialog,true);
            PresenterLogin presenterLogin = new PresenterLogin(LoginActivity.this);
            presenterLogin.getEmailandPass(email,password);
        }
    }
    private boolean initCheckVailied(String email,String password) {
        boolean check = false;
        if(email.length() > 0 || password.length() >0){
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                check = true;
            }else {
                check = false;
                edtEmail.setError(getString(R.string.email_invalid));
            }
            if(password.length() < 6){
                check = false;
                edtPass.setError(getString(R.string.weak_password));
            }

        }else {
            check = false;
            edtEmail.setError(getString(R.string.youhavenotinput));
            edtPass.setError(getString(R.string.youhavenotinput));
        }
        return check;
    }

    @Override
    public void onSuccess() {
        Dialog.DialogLoading(progressDialog,false);
        Toasty.success(LoginActivity.this, getString(R.string.success), Toast.LENGTH_SHORT, true).show();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    @Override
    public void onFailed() {
        Dialog.DialogLoading(progressDialog,false);
        Toasty.error(LoginActivity.this,getString(R.string.failure),Toasty.LENGTH_LONG,true).show();
    }
}
