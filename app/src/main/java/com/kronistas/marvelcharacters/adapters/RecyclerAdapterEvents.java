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
import com.kronistas.marvelcharacters.api.vo.Event;
import com.kronistas.marvelcharacters.utils.Commons;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Desarrollo on 28/7/2016.
 */
public class RecyclerAdapterEvents extends RecyclerView.Adapter<RecyclerAdapterEvents.ViewHolder> {
    ArrayList<Event> arrayList;
    Context mContext;
    Commons.OnRecyclerItemClickedListener onRecyclerItemClickedListener;

    public RecyclerAdapterEvents(ArrayList<Event> arrayList, Context context,
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
                bundle.putString("description", arrayList.get(position).description);
                onRecyclerItemClickedListener.onRecyclerItemClicked(bundle);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.info.setText(arrayList.get(position).title);

//        String path = arrayList.get(position).thumbnail.path + arrayList.get(position).thumbnail.extension;

        /** EL SERVICIO NO TRAE BIEN LAS IMAGENES, POR ESO NO SE VEN Y DEJO LA QUE TENGO POR DEFECTO*/

//        Drawable thumbnail = Utils.loadImageFromWebOperations(path, arrayList.get(position).name);
//        holder.cardViewImage.setImageDrawable(thumbnail); /* objeto de tipo ImageInfo*/

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
            /*Bundle arguments = new Bundle();
            arguments.putString("description", description);
            arguments.putString("name", info.getText().toString());

            DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(arguments);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(detailDialogFragment, detailDialogFragment.getTag());
            ft.commitAllowingStateLoss();*/


        }
    }
}
