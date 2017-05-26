package app.com.example.android.atlasreactorbuilds;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Fábio on 22/05/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int[] lista;

    public GridViewAdapter(Context ct , int[] lst){
        this.context = ct;
        this.lista = lst;
    }

    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public Object getItem(int position) {
        return lista[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView iv = new ImageView(context);
        iv.setImageResource(lista[position]);
        //Faz com que a imagem se ajuste ao tamanho do view sempre mantendo o ratio da msm
        iv.setAdjustViewBounds(true);

        return iv;
    }
}
