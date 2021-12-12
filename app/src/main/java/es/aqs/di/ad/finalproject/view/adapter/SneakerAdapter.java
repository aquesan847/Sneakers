package es.aqs.di.ad.finalproject.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import es.aqs.di.ad.finalproject.R;
import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.model.entity.Sneaker_SneakerBrand;
import es.aqs.di.ad.finalproject.view.adapter.viewholder.SneakerViewHolder;

import java.util.List;

public class SneakerAdapter extends RecyclerView.Adapter<SneakerViewHolder> implements View.OnClickListener {

    private List<Sneaker_SneakerBrand> sneakerList;
    private Context context;

    private View.OnClickListener listener;

    public SneakerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SneakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sneakers, parent, false);

        view.setOnClickListener(this);

        return new SneakerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SneakerViewHolder holder, int position) {
        Sneaker_SneakerBrand sneaker_SneakerBrand = sneakerList.get(position);
        Sneaker sneaker = sneaker_SneakerBrand.sneaker;
        SneakerBrand sneakerBrand = sneaker_SneakerBrand.sneakerBrand;
        holder.tvSneakersName.setText(sneaker.name);
        try {
            holder.tvSneakersBrand.setText(sneakerBrand.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        holder.tvSneakersSize.setText(sneaker.size);
        holder.tvSneakersBuyDate.setText(sneaker.buyDate);
        Glide.with(context).load(sneaker.imageUrl).centerCrop()
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .centerCrop()
                .into(holder.imgSneakers);
        //Glide.with(context).load(sneaker.imageUrl).placeholder(R.drawable.loading).into(holder.imgSneakers);
        //Picasso.get().load(sneaker.imageUrl).into(holder.imgSneakers);
    }

    @Override
    public int getItemCount() {
        if (sneakerList == null) {
            return 0;
        }
        return sneakerList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSneakerList(List<Sneaker_SneakerBrand> sneakerList) {
        this.sneakerList = sneakerList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public Sneaker_SneakerBrand getItem(int position) {
        return sneakerList.get(position);
    }
}
