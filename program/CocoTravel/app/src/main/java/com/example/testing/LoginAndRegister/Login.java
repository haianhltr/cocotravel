package com.example.testing.LoginAndRegister;




import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testing.LoginAndRegister.GetDetails.DashboardActivity;
import com.example.testing.R;
import com.example.testing.globaluser;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    private static final String TAG = "PhotoGalleryFragment";
    EditText Email, Password;
    Button LogIn ;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "http://192.168.64.2/LoginRegister/UserLogin.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        LogIn = (Button)findViewById(R.id.Login);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {

                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
    public void CheckEditTextIsEmptyOrNot(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Login.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                Log.i(TAG, "Received JSON: " + httpResponseMsg);
                progressDialog.dismiss();




                String[] parts = httpResponseMsg.split("-");
                String part1 = parts[0]; // 004
                String part2 = parts[1];
                Toast.makeText(Login.this,part1 ,Toast.LENGTH_LONG).show();
                if(part1.equalsIgnoreCase("Data Matched")){
                    globaluser.a = part2;
                    finish();

                    Intent intent = new Intent(Login.this, DashboardActivity.class);

                    intent.putExtra(UserEmail,email);

                    startActivity(intent);

                }
                else{

                    Toast.makeText(Login.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }

}