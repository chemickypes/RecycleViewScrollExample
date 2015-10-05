package com.baraccasoftware.recycleviewscrollexample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by angelomoroni on 05/10/15.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
        mDataset.add(0,DataObject.header());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {

        RecyclerView.ViewHolder dataObjectHolder;
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false);

            dataObjectHolder = new DataObjectHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.spessore, parent, false);
            dataObjectHolder = new HeaderHolder(view);
        }

        return dataObjectHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position>0) {
            DataObjectHolder h = (DataObjectHolder)holder;
            h.label.setText(mDataset.get(position).getmText1());
            h.dateTime.setText(mDataset.get(position).getmText2());
        }
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size() +1;
    }



    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private DataObject getItem(int position) {
        return mDataset.get(position-1);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
