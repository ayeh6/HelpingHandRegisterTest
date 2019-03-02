package com.example.helpinghandregistertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register_helper_activity extends AppCompatActivity {

    private EditText username,fullname,age,gender,city,load,hoursMonth,password;
    private Button register;
    private static String URL_REGIST = "http://76.105.25.203/helping_hand_php/register_helper.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_helper);

        username = findViewById(R.id.usernameInput);
        fullname = findViewById(R.id.fullnameInput);
        age = findViewById(R.id.ageInput);
        gender = findViewById(R.id.genderInput);
        city = findViewById(R.id.cityInput);
        load = findViewById(R.id.loadInput);
        hoursMonth = findViewById(R.id.hoursMonthInput);
        password = findViewById(R.id.passwordInput);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist(){
        final String username = this.username.getText().toString().trim();
        final String fullname = this.fullname.getText().toString().trim();
        final String age = this.age.getText().toString().trim();
        final String gender = this.gender.getText().toString().trim();
        final String city = this.city.getText().toString().trim();
        final String load = this.load.getText().toString().trim();
        final String hoursMonth = this.hoursMonth.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")) {
                                Toast.makeText(register_helper_activity.this,"Register Success!",Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(register_helper_activity.this,"Register Error here!!!" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register_helper_activity.this,"Register Error!!!" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("fullname",fullname);
                params.put("age",age);
                params.put("gender",gender);
                params.put("city",city);
                params.put("load",load);
                params.put("hoursMonth",hoursMonth);
                params.put("totalHours","0");
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
