package vn.mrlongg71.ps09103_assignment.model.login;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vn.mrlongg71.ps09103_assignment.presenter.login.PresenterLogin;

public class ModelLogin {
    public void loginWithEmail(final String email, String pass, final PresenterLogin presenterLogin){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    presenterLogin.loginStatus(true);
                }else{
                    presenterLogin.loginStatus(false);
                }
            }
        });

    }
}
