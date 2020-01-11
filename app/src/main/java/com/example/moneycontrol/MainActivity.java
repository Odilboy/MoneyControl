package com.example.moneycontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.BroadcastReceivers.NotificationReceiver;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button register;
    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.register);
        logIn = findViewById(R.id.login);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 18);
        calendar.set(Calendar.SECOND, 02);

        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

    }
    public void onRegisterClick (View view) {
      // if (!userName.getText().toString().equals("") && !email.getText().toString().equals("")
        //       && !password.getText().toString().equals("") && !confirmPassword.getText().toString().equals("")
          //     && isEmailValid(email.getText().toString())
            //   && password.getText().toString().equals(confirmPassword.getText().toString())) {

           Intent i = new Intent(getBaseContext(), MoneyControl.class);
           startActivity(i);
          // setContentView(R.layout.money_control);


       //}
       //else {
         //  Toast.makeText(this, "Not filled", Toast.LENGTH_SHORT).show();
      // }

    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
