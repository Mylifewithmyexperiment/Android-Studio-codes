package com.nrxtechnologies.hoto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nrxtechnologies.hoto.Activities.SharedPrefmanager;
import com.nrxtechnologies.hoto.Activities.SiteDetailsActivity;
import com.nrxtechnologies.hoto.Activities.UserDetails;
import com.nrxtechnologies.hoto.utilities.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button login;
    String device="android";
    EditText editText_employeeId,editText_Employee_Password;
    //String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJJRCI6IjViYWNiYjA5NDg0Zjg0NTA4ZjlhMzJjMCIsInVzZXJuYW1lIjoiZGxheG1pbmFyYXlhbmExQGluc3Bpcm9uaW5mb2luYy5jb20iLCJ1c2VyVHlwZSI6InBhdGllbnQifSwiaWF0IjoxNTM4MDQ2NzI5LCJleHAiOjE1Njk1ODI3Mjl9.VH5Oq48NpcdM5snwM6_JM4WjfXzg9BywTiQWFfy0ajY";
String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefmanager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, SiteDetailsActivity.class));
            return;
        }
    editText_employeeId= (EditText) findViewById(R.id.employee_id);
        editText_Employee_Password= (EditText) findViewById(R.id.password);

        login= (Button)findViewById(R.id.login_btn);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userLogin();
        }
    });

    }
    private void userLogin() {
        final ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging in, Please Wait");
        progressDialog.show();
        //first getting the values
        final String username = editText_employeeId.getText().toString();
        final String password = editText_Employee_Password.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editText_employeeId.setError("Please enter your username");
            editText_employeeId.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editText_Employee_Password.setError("Please enter your password");
            editText_Employee_Password.requestFocus();
            return;
        }

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST,APIs.loginApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progress bar functionaity
                            progressDialog.dismiss();
                        try {
                            Log.e("response",response);
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (obj.getBoolean("success")) {
                                JSONObject user = obj.getJSONObject("data");
                                JSONObject userData = user.getJSONObject("userData");

                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                UserDetails userDetails = new UserDetails(
                                        userData.getString("employeeID"),
                                        user.getString("token"));

                                //storing the user in shared preferences
                                SharedPrefmanager.getInstance(getApplicationContext()).userLogin(userDetails);

                                //starting the profile activity
                                finish();
                               startActivity(new Intent(getApplicationContext(), SiteDetailsActivity.class));

                            }
                            else

                            {
                                Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("employeeID", username);
                params.put("password", password);
                params.put("token",token);
                params.put("x-request-fom",device);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
