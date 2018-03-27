package app.firstpracticalquiz.com.firstpracticalquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, email, about;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.inputUsernameID);
        email = findViewById(R.id.inputEmailID);
        about = findViewById(R.id.inputAboutID);
        nextButton = findViewById(R.id.buttonNextID);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString(getString(R.string.userNameKey)) != null)
                username.setText(savedInstanceState.getString(getString(R.string.userNameKey)));
            if (savedInstanceState.getString(getString(R.string.emailKey)) != null)
                email.setText(savedInstanceState.getString(getString(R.string.emailKey)));
            if (savedInstanceState.getString(getString(R.string.aboutKey)) != null)
                about.setText(savedInstanceState.getString(getString(R.string.aboutKey)));
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("Value cannot be empty");
                    username.requestFocus();
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Value cannot be empty");
                    email.requestFocus();
                } else if (about.getText().toString().isEmpty()) {
                    about.setError("Value cannot be empty");
                    about.requestFocus();
                } else {
                    String userNameString = username.getText().toString();
                    String emailString = email.getText().toString();
                    String aboutString = about.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(
                            getString(R.string.preference_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.userNameKey), userNameString);
                    editor.putString(getString(R.string.emailKey), emailString);
                    editor.putString(getString(R.string.aboutKey), aboutString);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Details saved", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    email.setText("");
                    about.setText("");
                    username.requestFocus();
                }

            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(getString(R.string.userNameKey), username.getText().toString());
        outState.putString(getString(R.string.emailKey), email.getText().toString());
        outState.putString(getString(R.string.aboutKey), about.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profileID)
            startActivity(new Intent(this, DetailsActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
