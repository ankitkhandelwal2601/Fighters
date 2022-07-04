package com.example.freedomfighter.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freedomfighter.R;
import com.example.freedomfighter.activity.DescriptionActivity;
import com.example.freedomfighter.modal.BioModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<BioModal> bioModalArrayList;
    private Context context;


    public CustomAdapter(ArrayList<BioModal> bioModalArrayList, Context context) {
        this.bioModalArrayList = bioModalArrayList;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView bioImg;
        private final CardView llayoutList;
        private ArrayList<BioModal> bioModalArrayList;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            bioImg = (ImageView) view.findViewById(R.id.bioImg);
            llayoutList = (CardView) view.findViewById(R.id.llayoutList);


        }

        public TextView getTextView() {

            return textView;
        }
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder viewHolder, int position) {

        BioModal modal = bioModalArrayList.get(position);
        //viewHolder.bioImg.setImageResource(modal.getBioImage());
        viewHolder.textView.setText(modal.getBioName());
        Picasso.get().load(modal.getBioImage()).into(viewHolder.bioImg);

        viewHolder.llayoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Click on"+" "+ modal.getBioName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DescriptionActivity.class);
                i.putExtra("name",modal.getBioName());
                i.putExtra("description",modal.getDescription());
                i.putExtra("birth",modal.getBioBirth());
                i.putExtra("image",modal.getBioImage());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return bioModalArrayList.size();
    }

}
