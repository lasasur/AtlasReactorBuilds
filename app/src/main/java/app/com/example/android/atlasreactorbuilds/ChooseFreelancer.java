package app.com.example.android.atlasreactorbuilds;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChooseFreelancer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_freelancer);

        //lista dos icones dos herois
        final int[] icons = new int[]{R.drawable.blackburn_pt , R.drawable.celeste_pt , R.drawable.elle_pt,
                R.drawable.gremolitions_pt, R.drawable.grey_pt, R.drawable.juno_pt, R.drawable.lockwood_pt,
                R.drawable.nix_pt, R.drawable.oz_pt, R.drawable.pup_pt, R.drawable.zuki_pt,
                R.drawable.asana_pt, R.drawable.garrison_pt, R.drawable.rampart_pt, R.drawable.rask_pt,
                R.drawable.titus_pt, R.drawable.aurora_pt, R.drawable.dr_finn_pt, R.drawable.helio_pt,
                R.drawable.khita_pt, R.drawable.meridian_pt, R.drawable.orion_pt, R.drawable.quark_pt,
                R.drawable.su_ren_pt, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy,};

        GridView gv = (GridView)findViewById(R.id.gridView);
        gv.setAdapter(new GridViewAdapter(this, icons));

        gv.setOnItemClickListener(new GridView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //ao clicar no icone de um freelancer, pegar os dados dele do arquivo
                //freelancers.xml e abrir a activity ChooseFreelancer passando esses dados
                if(icons[position] != R.drawable.dummy){
                    String flName = getResources().getResourceEntryName(icons[position]);
                    flName = flName.substring(0 , flName.length()-3);
                    //TODO remover a linha abaixo assim que terminado de testar
                    Toast.makeText(getBaseContext(), flName , Toast.LENGTH_SHORT).show();
                    XmlPullParserHandler parser = new XmlPullParserHandler();

                    ArrayList<Freelancer> fl = null;
                    //Freelancer fl = null;
                    try{
                        //fl = parser.getFreelancerByName(getAssets().open("freelancers.xml") , flName);
                        fl = parser.parse(getAssets().open("freelancers.xml"));
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(ChooseFreelancer.this , FreelancerPage.class);
                    intent.putExtra("com.exemple.android.AtlasReactorBuilds" , fl);

                }
            }
        });
    }
}
