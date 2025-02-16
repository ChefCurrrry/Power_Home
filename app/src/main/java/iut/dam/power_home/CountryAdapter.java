package iut.dam.power_home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {

    Activity activity;
    int itemResourceId;
    List<Country> items;

    public CountryAdapter(Activity activity, int itemResourceId, List<Country> items) {
        super(activity, itemResourceId, items);

        this.activity = activity;
        this.itemResourceId = itemResourceId;
        this.items = items;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }
    private View createView(int position, View convertView, ViewGroup parent) {
        View layout = convertView;
        if (layout == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            layout = inflater.inflate(itemResourceId, parent, false);
        }

        TextView nameTV = layout.findViewById(R.id.name);
        ImageView flagIV = layout.findViewById(R.id.flag);

        Country country = items.get(position);
        nameTV.setText(country.name);
        flagIV.setImageResource(country.flagResourceId);

        return layout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }
}
