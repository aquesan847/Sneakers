package es.aqs.di.ad.finalproject.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.aqs.di.ad.finalproject.R;

public class SneakerViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgSneakers;
    public TextView tvSneakersName, tvSneakersBrand,
            tvSneakersSize, tvSneakersBuyDate;

    public SneakerViewHolder(@NonNull View itemView) {
        super(itemView);
        imgSneakers = itemView.findViewById(R.id.imgSneakers);
        tvSneakersName = itemView.findViewById(R.id.tvSneakersName);
        tvSneakersBrand = itemView.findViewById(R.id.tvSneakersBrand);
        tvSneakersSize = itemView.findViewById(R.id.tvSneakersSize);
        tvSneakersBuyDate = itemView.findViewById(R.id.tvSneakersBuyDate);
    }
}
