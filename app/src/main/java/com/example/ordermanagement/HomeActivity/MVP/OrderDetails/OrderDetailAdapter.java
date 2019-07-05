package com.example.ordermanagement.HomeActivity.MVP.OrderDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ordermanagement.HomeActivity.Model.HistoryDetailList;
import com.example.ordermanagement.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>
{
    Context context;
    List<HistoryDetailList> list;
    private OrderDetailAdapter.onNoteClickListener onNoteClickListener;

    public OrderDetailAdapter(Context context, List<HistoryDetailList> list,onNoteClickListener onNoteClickListener) {
        this.context = context;
        this.list = list;
        this.onNoteClickListener=onNoteClickListener;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_cartlist,viewGroup,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder viewHolder, int i) {

        HistoryDetailList cartList=list.get(i);
        viewHolder.name.setText(cartList.getName());
        viewHolder.prod_name.setText(cartList.getProd_name_new());
        viewHolder.nvm.setText("NVM : "+cartList.getNvm());
        viewHolder.cost.setText(cartList.getCost());
        viewHolder.size.setText(cartList.getSize());
        viewHolder.unit.setText(cartList.getUnit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,prod_name,nvm;
        EditText cost,size,unit;
        TextView delete,update;
        onNoteClickListener listener;

        public ViewHolder(@NonNull View itemView , final onNoteClickListener listener) {
            super(itemView);

            name=itemView.findViewById(R.id.cart_final_name);
            prod_name=itemView.findViewById(R.id.cart_final_prod_name);
            nvm=itemView.findViewById(R.id.cart_final_nvm);
            cost=itemView.findViewById(R.id.cart_final_price);
            size=itemView.findViewById(R.id.cart_final_size);
            unit=itemView.findViewById(R.id.cart_final_unit);
            update=itemView.findViewById(R.id.cart_final_update);
            delete=itemView.findViewById(R.id.cart_final_delete);
            this.listener=listener;

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUpdateClick(getAdapterPosition(),cost.getText().toString(),size.getText().toString(),unit.getText().toString());
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {

        }

    }
    public interface onNoteClickListener
    {
        void onUpdateClick(int position,String cost,String size,String unit);
        void onDeleteClick(int position);
    }
}
