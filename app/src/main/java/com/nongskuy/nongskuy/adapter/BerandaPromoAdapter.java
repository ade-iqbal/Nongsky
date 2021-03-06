package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.Promo;
import java.util.ArrayList;

public class BerandaPromoAdapter extends RecyclerView.Adapter<BerandaPromoAdapter.BerandaPromoViewHolder>{

    private ArrayList<Promo> listPromoBeranda;
    private Context context;
    private boolean isShimmer = true;
    private Integer numberShimmer = 10;
    private OnPromoViewHolderClick promoClickObject;

    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    public BerandaPromoAdapter(ArrayList<Promo> listPromoBeranda) {
        this.listPromoBeranda = listPromoBeranda;
    }

    @NonNull
    @Override
    public BerandaPromoAdapter.BerandaPromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_promo_horizontal, parent, false);
        context = view.getContext();
        return new BerandaPromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaPromoAdapter.BerandaPromoViewHolder holder, int position) {
        if(isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            Promo promo = listPromoBeranda.get(position);

            // format mata uang rupiah
            Helper helper = new Helper();

            holder.imagePromo.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(promo.getGambarMenu()))
                    .apply(new RequestOptions()
                            .override(148, 92))
                    .into(holder.imagePromo);

            holder.textMenuPromo.setBackground(null);
            holder.textMenuPromo.setText(promo.getNamaMenu());

            holder.textTokoPromo.setBackground(null);
            holder.textTokoPromo.setText(promo.getNamaToko());

            holder.textHargaSebelumPromo.setBackground(null);
            holder.textHargaSebelumPromo.setText(helper.mataUangRupiah(promo.getHargaAwal()));
            holder.textHargaSebelumPromo.setPaintFlags(holder.textHargaSebelumPromo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.textHargaSetelahPromo.setBackground(null);
            holder.textHargaSetelahPromo.setText(
                    helper.mataUangRupiah((100 - promo.getPersentase()) * (promo.getHargaAwal()/100))
            );

            holder.keterangan.setText(promo.getJenisPromo() + " " + promo.getPersentase().toString() + "%");

            if(promo.getJenisPromo().equals("diskon")){
                holder.keterangan.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
                holder.keterangan.setBackgroundResource(R.drawable.background_ket_diskon);

            }
            else if(promo.getJenisPromo().equals("cashback")){
                holder.keterangan.setTextColor(Color.WHITE);
                holder.keterangan.setBackgroundResource(R.drawable.background_ket_cashback);
            }
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer || listPromoBeranda.size() > 10 ? 10 : listPromoBeranda.size();
    }

    // onClick item recyclerview
    public interface OnPromoViewHolderClick{
        void onPromoBerandaClick(Promo promo);
    }

    // set objek onClick item recyclerview
    public void setPromoClickObject(OnPromoViewHolderClick promoClickObject){
        this.promoClickObject = promoClickObject;
    }

    public class BerandaPromoViewHolder extends RecyclerView.ViewHolder{

        TextView textMenuPromo, textHargaSebelumPromo, textHargaSetelahPromo, textTokoPromo, keterangan;
        ShapeableImageView imagePromo;
        ShimmerFrameLayout shimmerFrameLayout;

        public BerandaPromoViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerPromoBeranda);
            imagePromo = itemView.findViewById(R.id.imagePromoBeranda);
            textTokoPromo = itemView.findViewById(R.id.textTokoPromoBeranda);
            textMenuPromo = itemView.findViewById(R.id.textMenuPromoBeranda);
            textHargaSebelumPromo = itemView.findViewById(R.id.textHargaAwalPromoBeranda);
            textHargaSetelahPromo = itemView.findViewById(R.id.textHargaSetelahPromoBeranda);
            keterangan = itemView.findViewById(R.id.keteranganPromoBeranda);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isShimmer){
                        Promo promo = listPromoBeranda.get(getAdapterPosition());
                        promoClickObject.onPromoBerandaClick(promo);
                    }
                }
            });
        }
    }
}
