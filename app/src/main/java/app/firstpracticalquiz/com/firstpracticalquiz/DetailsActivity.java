package app.firstpracticalquiz.com.firstpracticalquiz;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView username, email, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar = getActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.daUserNameID);
        email = findViewById(R.id.daEmailID);
        about = findViewById(R.id.daaboutID);

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_key), Context.MODE_PRIVATE);
        String userNameString = sharedPreferences.getString(
                getString(R.string.userNameKey),"");
        String emailString = sharedPreferences.getString(
                getString(R.string.emailKey),"");
        String aboutString = sharedPreferences.getString(
                getString(R.string.aboutKey),"");

        if(!userNameString.isEmpty() && !emailString.isEmpty() && !aboutString.isEmpty())
        {
            username.setText(userNameString);
            email.setText(emailString);
            about.setText(aboutString);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            NavUtils.navigateUpFromSameTask(this);
        return super.onOptionsItemSelected(item);
    }
}
