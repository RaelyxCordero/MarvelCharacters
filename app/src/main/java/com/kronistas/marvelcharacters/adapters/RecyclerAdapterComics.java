package com.kronistas.marvelcharacters.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kronistas.marvelcharacters.R;
import com.kronistas.marvelcharacters.api.vo.Character;
import com.kronistas.marvelcharacters.api.vo.Comic;
import com.kronistas.marvelcharacters.utils.Commons;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Desarrollo on 28/7/2016.
 */
public class RecyclerAdapterComics extends RecyclerView.Adapter<RecyclerAdapterComics.ViewHolder> {
    ArrayList<Comic> arrayList;
    Context mContext;
    Commons.OnRecyclerItemClickedListener onRecyclerItemClickedListener;

    public RecyclerAdapterComics(ArrayList<Comic> arrayList, Context context,
                                 Commons.OnRecyclerItemClickedListener onRecyclerItemClickedListener) {
        this.arrayList = arrayList;
        this.mContext = context;
        this.onRecyclerItemClickedListener = onRecyclerItemClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child, parent, false);

        ViewHolder viewHolder = new ViewHolder(v, new ViewHolder.ViewHolderClickListener() {
            @Override
            public void onItemClick(View caller, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("name", arrayList.get(position).title);
                if(arrayList.get(position).description != null){
                    bundle.putString("description", arrayList.get(position).description);
                }else {
                    bundle.putString("description", arrayList.get(position).variantDescription);
                }
                onRecyclerItemClickedListener.onRecyclerItemClicked(bundle);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.info.setText(arrayList.get(position).title);
//        holder.cardViewImage.setImageBitmap(arrayList.get(position).thumbnail); /* objeto de tipo ImageInfo*/

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView info;
        CircleImageView cardViewImage;
        public ViewHolderClickListener mListener;


        public ViewHolder(View itemView, ViewHolderClickListener listener) {
            super(itemView);
            mListener = listener;
            info = (TextView)itemView.findViewById(R.id.info_text);
            cardViewImage = (CircleImageView) itemView.findViewById(R.id.card_view_image);
            itemView.setOnClickListener(this);

        }

        public interface ViewHolderClickListener {
            public void onItemClick(View caller, int position);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }
}
