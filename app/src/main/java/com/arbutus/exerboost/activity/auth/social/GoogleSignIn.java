package com.arbutus.exerboost.activity.auth.social;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.auth.login.LoginActivity;
import com.arbutus.exerboost.activity.auth.login.LoginRepository;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.AppConstants;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class GoogleSignIn {

    private Context context;
    private LoginRepository repository;

    public GoogleSignIn(Context context , LoginRepository repository) {
        this.context = context;
        this.repository = repository;
    }

    public void signIn() {

        System.out.println("=========== web client id  =============");

        GoogleSignInAccount account = com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(context);
        if (account == null) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.web_client_id))
                .requestEmail()
                .build();

        System.out.println("=========== web client id  ============= "+context.getString(R.string.web_client_id));
        GoogleSignInClient googleSignInClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(context, googleSignInOptions);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        ((Activity)context).startActivityForResult(signInIntent, AppConstants.GOOGLE_REQ_CODE);
        } else
            Toast.makeText(context, "Already exist", Toast.LENGTH_SHORT).show();
    }

    public void activityResult(int requestCode, int resultCode, Intent data, int RESULT_OK) {

        if (requestCode == AppConstants.GOOGLE_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                ((LoginActivity)context).progressDialog = AppBoilerPlateCode.setProgressDialog(context);
                Task<GoogleSignInAccount> task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        }
    }

    private  void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String socialId = account.getId();

            SocialSignInModel model = new SocialSignInModel(socialId,"Google");
            repository.loginUserWithGoogle(model);

            /*
            ====== User credentials from google ========

            String username = account.getDisplayName();
            String email = account.getEmail();
            String socialId = account.getId();
            String token = account.getIdToken();
            Uri profileImage = account.getPhotoUrl();
            */

        } catch (ApiException e) {
            ((LoginActivity)context).progressDialog.dismiss();
            e.printStackTrace();
        }
    }
}
