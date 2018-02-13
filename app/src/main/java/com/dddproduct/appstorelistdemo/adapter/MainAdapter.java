package com.dddproduct.appstorelistdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dddproduct.appstorelistdemo.R;
import com.dddproduct.appstorelistdemo.api.RetrofitAPI;
import com.dddproduct.appstorelistdemo.api.gson.AppDetail;
import com.dddproduct.appstorelistdemo.api.gson.Entry;
import com.dddproduct.appstorelistdemo.api.gson.Result;
import com.dddproduct.appstorelistdemo.utils.CircleTransform;
import com.dddproduct.appstorelistdemo.utils.RoundTransform;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dickyleehk on 12/2/18.
 */

public class MainAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_LOADING = -1;
    private static final int VIEW_TYPE_LIST = 0;
    private static final int VIEW_TYPE_VERTICAL = 1;
    private static final int VIEW_TYPE_HORIZONTAL = 2;

    public interface ReachEndListener{
        void onReachEnd(int lastIndex);
    }


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
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);

            isValid = tvPosition != null &&
                    tvTitle != null &&
                    tvCategory != null &&
                    tvRating != null &&
                    ivIcon != null;
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

    private boolean isVertical = true;

    private ReachEndListener listener;

    private Context ctxt;
    private List<Object> dataList, displayList;
    private String keyword;

    public MainAdapter(Context context){
        ctxt = context;
        dataList = new ArrayList<>();
        displayList = new ArrayList<>();
    }

    public void setList(List list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        update();
    }

    //For load more feature only
    public void addToList(List list){
        int startPosition = this.dataList.size() - 1;
        this.dataList.addAll(list);
        notifyItemRangeChanged(startPosition, list.size());
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        if(!TextUtils.isEmpty(keyword))
            keyword = keyword.toLowerCase();

        this.keyword = keyword;
        update();
    }

    public void setListener(ReachEndListener listener) {
        this.listener = listener;
    }

    public void update(){
        if(TextUtils.isEmpty(keyword)){
            displayList = dataList;
        }else{
            displayList = new ArrayList<>();
            for(Object obj:dataList){
                if(obj instanceof Entry){
                    if(verify((Entry)obj)){
                        displayList.add(obj);
                    }
                }else{
                    displayList.add(obj);
                }
            }
        }
        notifyDataSetChanged();
    }

    private boolean verify(Entry entry) {
        return entry.getImName().getLabel().toLowerCase().contains(keyword) ||
                entry.getCategory().getAttributes().getLabel().toLowerCase().contains(keyword) ||
                entry.getImArtist().getLabel().toLowerCase().contains(keyword) ||
                entry.getSummary().getLabel().toLowerCase().contains(keyword);
    }

    @Override
    public int getItemViewType(int position) {
        if(displayList.get(position) instanceof List)
            return VIEW_TYPE_LIST;

        if(displayList.get(position) instanceof Entry)
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

        if(listener != null &&
                TextUtils.isEmpty(keyword) &&
                position == displayList.size()-1){
            listener.onReachEnd(position);
        }
    }

    private void bindVHList(VHList holder, int position){
        if(displayList.get(position) instanceof List){
            List<Entry> dataList = new ArrayList<>();
            for(Object item : (List) displayList.get(position)){
                if(item instanceof Entry)
                    dataList.add((Entry) item);
            }
            holder.mainAdapter.setList(dataList);
            holder.mainAdapter.setKeyword(keyword);
        }
    }

    private void bindVHVertical(final VHVertical holder, int position){
        if(!holder.isValid || !(displayList.get(position) instanceof Entry))return;

        final Entry app = (Entry) displayList.get(position);
        holder.tvPosition.setText(Integer.toString(position));

        Transformation transformation;
        switch (position%2){
            case 0:
                transformation = new CircleTransform();
                break;
            default:
            case 1:
                transformation = new RoundTransform();
                break;
        }

        Picasso.with(ctxt).load(app.getImImage().get(2).getLabel()).transform(transformation).into(holder.ivIcon);
        holder.tvTitle.setText(app.getImName().getLabel());
        holder.tvCategory.setText(app.getCategory().getAttributes().getLabel());

        holder.tvRating.setText(app.getImName().getLabel());
        RetrofitAPI.getInstance().getAppDetail(app.getId().getAttributes().getImId(), new Callback<AppDetail>() {
            @Override
            public void onResponse(Call<AppDetail> call, Response<AppDetail> response) {
                if(response.isSuccessful() &&
                        response.body() != null &&
                        response.body().getResultCount() > 0){
                    Result result = response.body().getResults().get(0);
                    holder.tvRating.setText(result.getAverageUserRatingForCurrentVersion() + " (" + result.getUserRatingCount()+")");
                }else{
                    onFailure(call, new Throwable("API Fail"));
                }
            }

            @Override
            public void onFailure(Call<AppDetail> call, Throwable t) {
                holder.tvRating.setText("-");
            }
        });

    }

    private void bindVHHorizontal(VHHorizontal holder, int position){
        if(!holder.isValid || !(displayList.get(position) instanceof Entry))return;

        final Entry app = (Entry) displayList.get(position);
        Picasso.with(ctxt).load(app.getImImage().get(2).getLabel()).transform(new RoundTransform()).into(holder.ivIcon);
        holder.tvTitle.setText(app.getImName().getLabel());
        holder.tvCategory.setText(app.getCategory().getAttributes().getLabel());

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

}
