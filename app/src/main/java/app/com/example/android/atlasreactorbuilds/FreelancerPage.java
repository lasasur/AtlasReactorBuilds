package app.com.example.android.atlasreactorbuilds;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FreelancerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_page);
        TextView flName = (TextView)findViewById(R.id.freelancerName);
        ImageView flIcon = (ImageView)findViewById(R.id.freelancerIcon);
        Intent intent = getIntent();
        int icon = intent.getExtras().getInt("flIcon");
        Freelancer freelancer = intent.getParcelableExtra("fl");
        flName.setText(freelancer.getName());
        flIcon.setImageResource(icon);
        setActionBar(freelancer.getName());
    }

    public void setActionBar(String text){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        TextView actBarTx = new TextView(actionBar.getThemedContext());
        actBarTx.setText(text);
        actionBar.setCustomView(actBarTx);
    }
}
