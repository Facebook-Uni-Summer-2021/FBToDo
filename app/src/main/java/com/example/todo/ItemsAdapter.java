package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Create new adapter ([plural of items involved]Adapter)
 * Designed for displaying data from model into row (item) in recyclerview
 */

//Step 3. Extend class as RecyclerView.Adapter<Class.ViewHolderClass>
//Step 4. Correct error when extending
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    //Handler onlongclicklistener removal of items from list
    public interface OnLongClickListener {
        /**
         * Identify location of click to specify item to remove
         * @param position Index of item to remove
         */
        void onItemLongClicked (int position);
    }

    public interface OnClickListener {
        /**
         * Identify location of click to specify item to update
         * @param position Index of item to update
         */
        void onItemClicked (int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    //Step 5. Create constructor (use "right click -> generate")
    //For constructor of adapter, including parameter as list of items, etc
    //(note that parameter is in function, argument is what goes in when called)
    public ItemsAdapter (List<String> items,
                         OnLongClickListener longClickListener,
                         OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    //Step 6. Create new view and wrap in view holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //User layout inflator to inflate view
        View view = LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_1,//Provided by Android, can be custom XML layout resource
                        parent,//Root, or parent
                        false);//Cant understand his words
        //Wrap inside ViewHolder and return
        return new ViewHolder(view);
    }

    @Override
    //Step 7. Bind data to view holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab item at position
        String item = items.get(position);
        //Bind item to specific ViewHolder
        //Step 8. Call "bind" method from ViewHolder, them create new method
        holder.bind(item);
    }

    @Override
    //Step 10. Define amount of items to by displayed, consistent with intended
    //data
    public int getItemCount() {
        return items.size();
    }

    //Step 1. Container to represent individual item in list of n items
    //Step 2. Correct error when extending
    class ViewHolder extends RecyclerView.ViewHolder {
        //Define widget objects
        TextView tvItem;

        //Step 9. Update view inside ViewHolder with data
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Declare widget variables in class, initialize in constructor here
            tvItem = itemView.findViewById(android.R.id.text1);
        }


        public void bind(String item) {
            //Requires information from xml used for inflating view, which
            //were already declared in step 9
            tvItem.setText(item);

            //Create handler for removing items
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notify listener which position was pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

            //Create handler for updating items
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
