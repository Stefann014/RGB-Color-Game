package com.example.rgbcolorgame.activity.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rgbcolorgame.R;
import com.example.rgbcolorgame.activity.domain.Score;

public class ScoreAdapter extends ListAdapter<Score, ScoreAdapter.RezultatHolder> {

    private OnItemClickListener listener;

    public ScoreAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Score> DIFF_CALLBACK = new DiffUtil.ItemCallback<Score>() {
        @Override
        public boolean areItemsTheSame(Score oldItem, Score newItem) {
            return oldItem.getRezultatID() == newItem.getRezultatID();
        }

        @Override
        public boolean areContentsTheSame(Score oldItem, Score newItem) {
            return oldItem.getRezultat() == (newItem.getRezultat()) &&
                    oldItem.getDatum().equals(newItem.getDatum()) &&
                    oldItem.getIgrac().equals(newItem.getIgrac()) &&
                    oldItem.getVremeSekundi() == newItem.getVremeSekundi();
        }
    };

    @NonNull
    @Override
    public RezultatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_cardview, parent, false);
        return new RezultatHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RezultatHolder holder, int position) {
        Score trenutniScore = getItem(position);
        holder.playerName.setText(" Player: " + " " + trenutniScore.getIgrac() + " ");
        holder.score.setText(" Score: " + trenutniScore.getRezultat() + " ");
        holder.date.setText(" Date: " + datumZaPrikaz(trenutniScore.getDatum()) + " ");
        holder.time.setText(" Time: " + trenutniScore.getVremeSekundi() + " ");
    }

    @NonNull
    private String datumZaPrikaz(String datum) {
        String[] datumi = datum.split("-");
        return datumi[2] + "." + datumi[1] + "." + datumi[0];
    }

    public Score getRezultatAt(int position) {
        return getItem(position);
    }

    class RezultatHolder extends RecyclerView.ViewHolder {
        private TextView playerName;
        private TextView score;
        private TextView date;
        private TextView time;

        public RezultatHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.txtPlayer);
            score = itemView.findViewById(R.id.txtScore);
            date = itemView.findViewById(R.id.txtDate);
            time = itemView.findViewById(R.id.txtTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Score score);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
