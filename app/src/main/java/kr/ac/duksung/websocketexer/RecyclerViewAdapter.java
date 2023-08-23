package kr.ac.duksung.websocketexer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//for 실시간재고
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=(TextView) itemView.findViewById(R.id.txtName);
            txtPrice=(TextView) itemView.findViewById(R.id.txtPrice);
            txtStock=(TextView) itemView.findViewById(R.id.txtStock);
        }
    }
    private ArrayList<RecyclerViewItem> itemlist = null;
    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> itemlist){
        this.itemlist=itemlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_item,parent,false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerViewItem item = itemlist.get(position);
        holder.txtName.setText(item.getItemName());
        holder.txtPrice.setText(item.getItemPrice());
        holder.txtStock.setText(item.getItemStock());
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }


}
