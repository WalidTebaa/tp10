package ma.projet.restclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ma.projet.restclient.R;
import ma.projet.restclient.entities.Compte;

import java.util.ArrayList;
import java.util.List;

public class CompteAdapter extends RecyclerView.Adapter<CompteAdapter.AccountViewHolder> {
    public interface OnDeleteClickListener {
        void onDeleteClick(Compte compte);
    }
    public interface OnUpdateClickListener {
        void onUpdateClick(Compte compte);
    }

    private List<Compte> accountList;
    private OnDeleteClickListener onDeleteClickListener;
    private OnUpdateClickListener onUpdateClickListener;

    public CompteAdapter(OnDeleteClickListener onDeleteClickListener, OnUpdateClickListener onUpdateClickListener) {
        this.accountList = new ArrayList<>();
        this.onDeleteClickListener = onDeleteClickListener;
        this.onUpdateClickListener = onUpdateClickListener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_compte, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Compte compte = accountList.get(position);
        holder.bind(compte);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public void updateData(List<Compte> newComptes) {
        this.accountList.clear();
        if (newComptes != null) {
            this.accountList.addAll(newComptes);
        }
        notifyDataSetChanged();
    }

    class AccountViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvSolde, tvType, tvDate;
        private View btnDelete, btnUpdate;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvSolde = itemView.findViewById(R.id.tvSolde);
            tvType = itemView.findViewById(R.id.tvType);
            tvDate = itemView.findViewById(R.id.tvDate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnEdit);
        }

        public void bind(Compte compte) {
            tvId.setText("Identifiant: " + compte.getId());
            tvSolde.setText(String.format("%.2f DH", compte.getSolde()));
            tvType.setText(compte.getType());
            tvDate.setText(compte.getDateCreation());

            btnDelete.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(compte);
                }
            });
            btnUpdate.setOnClickListener(v -> {
                if (onUpdateClickListener != null) {
                    onUpdateClickListener.onUpdateClick(compte);
                }
            });
        }
    }
}