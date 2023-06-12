package fia.ues.saluddigital.Autenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import fia.ues.saluddigital.MainActivity;
import fia.ues.saluddigital.R;

public class GoogleSignInModulo extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private GoogleSignInAccount account;
    private SignInButton googleBtn;

    private BeginSignInRequest signUpRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();
        googleBtn = findViewById(R.id.sign_in_button);
        googleBtn.setSize(googleBtn.SIZE_STANDARD);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    void signIn() {
        Intent signInINtent = gsc.getSignInIntent();
        startActivityForResult(signInINtent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToNext();
            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(this, "Algo salió mal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToNext() {
        finish();
        Intent intent = new Intent(GoogleSignInModulo.this, MainActivity.class);
        // Cambiar "MainActivity.class" por la clase del activity que corresponda
        startActivity(intent);
    }

    //Implementar este metodo donde corresponda
    private void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
            public void onComplete(Task<Void> task){
                finish();
                startActivity(new Intent(GoogleSignInModulo.this, MainActivity.class));
                // Cambiar el contexto donde corresponda

            }
        });
    }
}