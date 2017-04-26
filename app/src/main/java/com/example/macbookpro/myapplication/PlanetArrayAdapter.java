package com.example.macbookpro.myapplication;

/**
 * Created by macbookpro on 25/04/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/** Custom adapter for displaying an array of Planet objects. */
public  class PlanetArrayAdapter extends ArrayAdapter<Planet> {

    private LayoutInflater inflater;

    public PlanetArrayAdapter(Context context, ArrayList<Planet> planetList ) {
        super( context, R.layout.layout, R.id.rowTextView, planetList );
        // Cache the LayoutInflate to avoid asking for a new one each time.
        inflater = LayoutInflater.from(context) ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Planet to display
        Planet planet = this.getItem( position );

        // The child views in each row.
        CheckBox checkBox ;
        TextView textView ;

        // Create a new row view
        if ( convertView == null ) {
            convertView = inflater.inflate(R.layout.layout, null);

            // Find the child views.
            textView = (TextView) convertView.findViewById( R.id.rowTextView );
            checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );

            // Optimization: Tag the row with it's child views, so we don't have to
            // call findViewById() later when we reuse the row.
            convertView.setTag( new PlanetViewHolder(textView,checkBox) );

            // If CheckBox is toggled, update the planet it is tagged with.
            checkBox.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Planet planet = (Planet) cb.getTag();
                    planet.setChecked( cb.isChecked() );
                }
            });
        }
        // Reuse existing row view
        else {
            // Because we use a ViewHolder, we avoid having to call findViewById().
            PlanetViewHolder viewHolder = (PlanetViewHolder) convertView.getTag();
            checkBox = viewHolder.getCheckBox() ;
            textView = viewHolder.getTextView() ;
        }

        // Tag the CheckBox with the Planet it is displaying, so that we can
        // access the planet in onClick() when the CheckBox is toggled.
        checkBox.setTag( planet );

        // Display planet data
        checkBox.setChecked( planet.isChecked() );
        textView.setText( planet.getName() );

        return convertView;
    }

}

//    public Object onRetainCustomNonConfigurationInstance () {
//        return planets ;
//    }

