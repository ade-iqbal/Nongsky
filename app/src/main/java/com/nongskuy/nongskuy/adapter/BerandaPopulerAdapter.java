package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Nongskuy;
import java.util.ArrayList;

public class BerandaPopulerAdapter extends RecyclerView.Adapter<BerandaPopulerAdapter.BerandaPopulerViewHolder> {

    private ArrayList<Nongskuy> listPopulerBeranda;
    private Context context;
    private boolean isShimmer = true;
    private OnPopulerViewHolderClick populerClickObject;

    // set shimmer
    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    // constructor
    public BerandaPopulerAdapter(ArrayList<Nongskuy> listPopulerBeranda) {
        this.listPopulerBeranda = listPopulerBeranda;
    }

    @NonNull
    @Override
    public BerandaPopulerAdapter.BerandaPopulerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_populer_horizontal, parent, false);
        context = view.getContext();
        return new BerandaPopulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaPopulerAdapter.BerandaPopulerViewHolder holder, int position) {
        if(isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);
            Nongskuy nongskuy = listPopulerBeranda.get(position);

            holder.imageTokoPopuler.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(nongskuy.getGambarToko()))
                    .apply(new RequestOptions()
                            .override(148, 92))
                    .into(holder.imageTokoPopuler);

            holder.textNamaTokoPopuler.setBackground(null);
            holder.textNamaTokoPopuler.setText(nongskuy.getNamaToko());

            holder.textAlamatTokoPopuler.setBackground(null);
            holder.textAlamatTokoPopuler.setText(nongskuy.getAlamatToko());

            holder.textTipeTongkrongan.setBackground(null);
            holder.textTipeTongkrongan.setText(nongskuy.getTipeToko());

            holder.textJarakTongkrongan.setBackground(null);
            holder.textJarakTongkrongan.setText(nongskuy.getJarakToko() + " Km");

            holder.ratingPopuler.setBackground(null);
            holder.ratingPopuler.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(R.drawable.ic_star), null, null, null
            );
            holder.ratingPopuler.setText(nongskuy.getRatingToko());
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer || listPopulerBeranda.size() > 10 ? 10 : listPopulerBeranda.size();
    }

    // onClick item recyclerview
    public interface OnPopulerViewHolderClick{
        void onPopulerBerandaClick(Nongskuy nongskuy);
    }

    // set objek onClick item recyclerview
    public void setPopulerClickObject(OnPopulerViewHolderClick populerClickObject){
        this.populerClickObject = populerClickObject;
    }

    // view holder
    public class BerandaPopulerViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaTokoPopuler, textAlamatTokoPopuler, textTipeTongkrongan,
                textJarakTongkrongan, ratingPopuler;
        ShapeableImageView imageTokoPopuler;
        ShimmerFrameLayout shimmerFrameLayout;

        public BerandaPopulerViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerTokoPopulerBeranda);
            imageTokoPopuler = itemView.findViewById(R.id.imageTokoPopulerBeranda);
            textNamaTokoPopuler = itemView.findViewById(R.id.textNamaTokoPopulerBeranda);
            textAlamatTokoPopuler = itemView.findViewById(R.id.textAlamatTokoPopulerBeranda);
            textTipeTongkrongan = itemView.findViewById(R.id.textTipeTokoPopulerBeranda);
            textJarakTongkrongan = itemView.findViewById(R.id.textJarakPopulerBeranda);
            ratingPopuler = itemView.findViewById(R.id.ratingPopulerBeranda);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isShimmer){
                        Nongskuy nongskuy = listPopulerBeranda.get(getAdapterPosition());
                        populerClickObject.onPopulerBerandaClick(nongskuy);
                    }
                }
            });
        }
    }
}
