package fia.ues.saluddigital.PageLoop;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import java.util.List;

public class VievPageAdapter extends RecyclerView.Adapter<ViewPagerAdapter> {
    private List<Integer> image;

    public VievPageAdapter(List<Integer> image) {
        this.image = image;
    }

    public List<Integer> getImage() {
        return image;
    }

    public void setImage(List<Integer> image) {
        this.image = image;
    }

    @NonNull
    @Override
    public ViewPagerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
