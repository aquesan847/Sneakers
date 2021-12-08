package es.aqs.di.ad.finalproject.view.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.aqs.di.ad.finalproject.R;

public class SneakerBrandViewHolder extends RecyclerView.ViewHolder {

    private TextView tvVideoGameConsole;

    public SneakerBrandViewHolder(@NonNull View itemView) {
        super(itemView);
        tvVideoGameConsole = itemView.findViewById(R.id.tvSneakersName);
    }

    public TextView getTvVideoGameConsole() {
        return tvVideoGameConsole;
    }
}
