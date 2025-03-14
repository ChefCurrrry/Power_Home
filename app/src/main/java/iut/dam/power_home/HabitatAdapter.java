package iut.dam.power_home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HabitatAdapter extends RecyclerView.Adapter<HabitatAdapter.ViewHolder> {
    private List<Habitat> items;

    public HabitatAdapter(List<Habitat> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habitat_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Habitat habitat = items.get(position);
        holder.nameTV.setText(habitat.user.lastName + " " + habitat.user.firstName);
        holder.floor.setText(String.valueOf(habitat.floor));
        // Récupération du nombre d'équipements
        int equipementCount = habitat.appliances.size();

        // Mettre à jour le texte du nombre d'équipements
        holder.equipementCountTV.setText(equipementCount + (equipementCount > 1 ? " équipements" : " équipement"));

        // Nettoyage des anciennes icônes d'équipement
        holder.equipementContainer.removeAllViews();

        // Ajout dynamique des icônes d'équipements
        for (Appliance iconRes : habitat.appliances) {
            ImageView imageView = new ImageView(holder.itemView.getContext());
            imageView.setImageResource(iconRes.getRef(iconRes.reference));
            imageView.setLayoutParams(new LinearLayout.LayoutParams(50, 50)); // Taille icône
            imageView.setPadding(4, 4, 4, 4);
            holder.equipementContainer.addView(imageView);
        }

        holder.itemView.setOnClickListener(view -> {
            String message = "Propriétaire: " + habitat.user.lastName + " " + habitat.user.firstName;
            Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, floor, equipementCountTV, etageNumber;
        LinearLayout equipementContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.proprio);
            floor = itemView.findViewById(R.id.etageNumber);
            equipementCountTV = itemView.findViewById(R.id.tvEquipementCount);
            equipementContainer = itemView.findViewById(R.id.equipmentContainer);
        }
    }
}
