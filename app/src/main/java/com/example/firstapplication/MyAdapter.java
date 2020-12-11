package com.example.firstapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<Item> data;

    public MyAdapter(ArrayList<Item> _data){
        this.data=_data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.cost.setText(String.valueOf(data.get(position).getCost()));
        holder.quantity.setText(String.valueOf(data.get(position).getQuantity()));
        holder.description.setText(data.get(position).getDescription());

        Boolean frozen = data.get(position).getFrozen();
        if (frozen){
            holder.frozen.setText("Yes");
        }
        else {
            holder.frozen.setText("No");
        }
        holder.location.setText(data.get(position).getLocation());
        holder.counter.setText("Item: "+(position+1));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView quantity;
        TextView cost;
        TextView description;
        TextView frozen;
        TextView location;
        TextView counter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_itemname);
            quantity= itemView.findViewById(R.id.card_quantity);
            cost = itemView.findViewById(R.id.card_cost);
            description = itemView.findViewById(R.id.card_description);
            frozen = itemView.findViewById(R.id.card_frozen);
            location = itemView.findViewById(R.id.card_location);
            counter=itemView.findViewById(R.id.item_counter);
        }
    }
}
