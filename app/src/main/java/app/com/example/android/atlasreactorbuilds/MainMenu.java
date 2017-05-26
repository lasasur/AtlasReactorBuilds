package app.com.example.android.atlasreactorbuilds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Button btnChooseFreelancer = (Button)findViewById(R.id.btnChooseFreelancer);
        btnChooseFreelancer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainMenu.this , ChooseFreelancer.class);
                startActivity(intent);
            }
        });
    }
}
