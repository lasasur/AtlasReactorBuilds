package app.com.example.android.atlasreactorbuilds;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class FreelancerPage extends AppCompatActivity {

    private List<Freelancer> freelancer;
    //private Freelancer freelancer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_page);
        Intent intent = getIntent();
        freelancer = intent.getParcelableExtra("freelancer");
    }
}
