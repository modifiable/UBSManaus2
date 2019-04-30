package example.android.ubsmanaus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import example.android.ubsmanaus.Model.Cidade;
import example.android.ubsmanaus.R;


public class Adapter extends ArrayAdapter<Cidade> {

    public Adapter(Context context, List<Cidade> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cidade cidade = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cidade, parent, false);
        }

        TextView tvNome = (TextView) convertView.findViewById(R.id.nome);
        TextView tvCapital = (TextView) convertView.findViewById(R.id.bairro);

        tvNome.setText(cidade.name);
        tvCapital.setText(cidade.capital);

        return convertView;
    }

}
