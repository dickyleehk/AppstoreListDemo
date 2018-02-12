package com.dddproduct.appstorelistdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dddproduct.appstorelistdemo.R;
import com.dddproduct.appstorelistdemo.api.gson.Entry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dickyleehk on 12/2/18.
 */

public class MainAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_LOADING = -1;
    private static final int VIEW_TYPE_LIST = 0;
    private static final int VIEW_TYPE_VERTICAL = 1;
    private static final int VIEW_TYPE_HORIZONTAL = 2;


    class VHLoading extends RecyclerView.ViewHolder{
        VHLoading(Context ctxt) {
            super(new ProgressBar(ctxt));
        }
    }

    class VHList extends RecyclerView.ViewHolder{
        MainAdapter mainAdapter;

        VHList(RecyclerView itemView) {
            super(itemView);

            mainAdapter = new MainAdapter(itemView.getContext());
            mainAdapter.isVertical = false;

            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);

            itemView.setLayoutManager(manager);
            itemView.setAdapter(mainAdapter);
        }

        RecyclerView getView(){
            return (RecyclerView) itemView;
        }
    }

    class VHVertical extends RecyclerView.ViewHolder{

        boolean isValid = true;
        TextView tvPosition, tvTitle, tvCategory, tvRating;
        ImageView ivIcon;

        VHVertical(View itemView) {
            super(itemView);

            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
            if(isValid)isValid = tvPosition != null;

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            if(isValid)isValid = tvTitle != null;

            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            if(isValid)isValid = tvCategory != null;

            tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
            if(isValid)isValid = tvRating != null;

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            if(isValid)isValid = ivIcon != null;
        }
    }
    class VHHorizontal extends RecyclerView.ViewHolder{

        boolean isValid = true;
        TextView tvTitle, tvCategory;
        ImageView ivIcon;

        VHHorizontal(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            if(isValid)isValid = tvTitle != null;

            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            if(isValid)isValid = tvCategory != null;

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            if(isValid)isValid = ivIcon != null;
        }
    }

    boolean isVertical = true;

    private Context ctxt;
    List<Object> list;

    public MainAdapter(Context context){
        ctxt = context;
        list = new ArrayList<>();
    }

    public void setList(List list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position) instanceof List)
            return VIEW_TYPE_LIST;

        if(list.get(position) instanceof Entry)
            return isVertical?VIEW_TYPE_VERTICAL:VIEW_TYPE_HORIZONTAL;

        return VIEW_TYPE_LOADING;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_LOADING:
                return new VHLoading(ctxt);
            case VIEW_TYPE_LIST:
                return new VHList(new RecyclerView(ctxt));
            default:
            case VIEW_TYPE_VERTICAL:
                return new VHVertical(LayoutInflater.from(ctxt).inflate(R.layout.view_vertical_app, parent, false));
            case VIEW_TYPE_HORIZONTAL:
                return new VHHorizontal(LayoutInflater.from(ctxt).inflate(R.layout.view_horizontal_app, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_LOADING:
                break;
            case VIEW_TYPE_LIST:
                bindVHList((VHList) holder, position);
                break;
            default:
            case VIEW_TYPE_VERTICAL:
                bindVHVertical((VHVertical) holder, position);
                break;
            case VIEW_TYPE_HORIZONTAL:
                bindVHHorizontal((VHHorizontal) holder, position);
                break;
        }
    }

    private void bindVHList(VHList holder, int position){
        if(list.get(position) instanceof List){
            List<Entry> dataList = new ArrayList<>();
            for(Object item : (List)list.get(position)){
                if(item instanceof Entry)
                    dataList.add((Entry) item);
            }
            holder.mainAdapter.setList(dataList);
        }
    }

    private void bindVHVertical(VHVertical holder, int position){
        if(!holder.isValid || !(list.get(position) instanceof Entry))return;

        Entry app = (Entry) list.get(position);
        holder.tvPosition.setText(Integer.toString(position));
        Picasso.with(ctxt).load(app.getImImage().get(2).getLabel()).into(holder.ivIcon);
        holder.tvTitle.setText(app.getImName().getLabel());
        holder.tvCategory.setText(app.getCategory().getAttributes().getLabel());
        holder.tvRating.setText(app.getImName().getLabel());
    }

    private void bindVHHorizontal(VHHorizontal holder, int position){
        if(!holder.isValid || !(list.get(position) instanceof Entry))return;

        Entry app = (Entry) list.get(position);
        Picasso.with(ctxt).load(app.getImImage().get(2).getLabel()).into(holder.ivIcon);
        holder.tvTitle.setText(app.getImName().getLabel());
        holder.tvCategory.setText(app.getCategory().getAttributes().getLabel());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
