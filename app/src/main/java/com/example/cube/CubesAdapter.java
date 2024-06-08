package com.example.cube;

import static com.example.cube.MainActivity.fragmentManager;
import static com.example.cube.MainActivity.isAdmin;

import android.graphics.Color;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CubesAdapter extends RecyclerView.Adapter<CubesAdapter.MyViewHolder> {
    private ArrayList<Animation> items;
    boolean isEnable=false;
    ActionMode adapterMode;
    ArrayList<Animation> selectItems = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCubeName;
        Button playButton;
        CheckBox checkBox;
        public MyViewHolder(View view) {
            super(view);
            tvCubeName = view.findViewById(R.id.cubeName);
            playButton = view.findViewById(R.id.playButton);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

    public CubesAdapter(ArrayList<Animation> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CubesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cube_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CubesAdapter.MyViewHolder holder, int position) {
        holder.tvCubeName.setText(items.get(position).getName());
        if(!items.get(position).isDisplaying) holder.playButton.setBackground(ContextCompat.getDrawable(holder.playButton.getContext(), R.drawable.ic_play));
        else holder.playButton.setBackground(ContextCompat.getDrawable(holder.playButton.getContext(), R.drawable.ic_pause));
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!items.get(holder.getAdapterPosition()).isDisplaying) {
                    holder.playButton.setBackground(ContextCompat.getDrawable(holder.playButton.getContext(), R.drawable.ic_pause));
                    items.get(holder.getAdapterPosition()).setDisplaying(true);
                    Log.d("POSITION", holder.getAdapterPosition()+"");
                    Log.i("size of imgs", items.get(holder.getAdapterPosition()).getImages().size() + "");
                    Log.i("size of imgs", items.get(holder.getAdapterPosition()).getName() + "");

                    for(int i = 0; i < items.get(holder.getAdapterPosition()).getImages().size(); i++){
                        CreateCubeFragment.sendToCube(items.get(holder.getAdapterPosition()).getImages().get(i).getLeds_status(), CreateCubeFragment.urlForLeds, holder.playButton.getContext());
                    }
                    for(int i = 0; i < items.size(); i++){
                        if (i != holder.getAdapterPosition()) {
                            items.get(i).setDisplaying(false);
                        }
                        notifyItemChanged(i);
                    }
                }
                else {
                    holder.playButton.setBackground(ContextCompat.getDrawable(holder.playButton.getContext(), R.drawable.ic_play));
                    items.get(holder.getAdapterPosition()).setDisplaying(false);
                    String nuls = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                    CreateCubeFragment.sendToCube(nuls, CreateCubeFragment.urlForLeds, holder.playButton.getContext());


                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isAdmin){

                    if (!isEnable)
                    {
                        ActionMode.Callback callback=new ActionMode.Callback() {
                            @Override
                            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                                MenuInflater menuInflater= mode.getMenuInflater();
                                menuInflater.inflate(R.menu.action_bar_menu,menu);
                                adapterMode = mode;
                                return true;
                            }

                            @Override
                            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                                isEnable = true;
                                clickItem(holder);
                                return true;
                            }

                            @Override
                            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                                int id = item.getItemId();
                                if(id == R.id.menuDelete){
                                    for(Animation anim: selectItems)
                                    {
                                        MainActivity.DBHelper.deleteCube(anim);
                                        items.remove(anim);
                                    }
                                    mode.finish();
                                }
                                if(id == R.id.menuCreate){
                                    MainActivity.bottomNavigationView.setSelectedItemId(R.id.create);
                                    Fragment fragmenCreateCube = new CreateCubeFragment(selectItems.get(0));
                                    MainActivity.fragmentTransaction = fragmentManager.beginTransaction();
                                    MainActivity.fragmentTransaction.replace(R.id.main_view, fragmenCreateCube);
                                    MainActivity.fragmentTransaction.commit();
                                    mode.finish();
                                }
                                return true;
                            }

                            @Override
                            public void onDestroyActionMode(ActionMode mode) {
                                isEnable = false;
                                selectItems.clear();
                                holder.checkBox.setVisibility(View.GONE);
                                notifyDataSetChanged();
                            }

                        };
                        ((AppCompatActivity) v.getContext()).startActionMode(callback);
                    }
                    else {
                        clickItem(holder);
                    }
                }
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnable)
                {
                    clickItem(holder);
                }
            }
        });



    }
    public void clickItem(MyViewHolder holder){
        Animation anim = items.get(holder.getAdapterPosition());
        if(holder.checkBox.getVisibility() == View.GONE || !holder.checkBox.isChecked())
        {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(true);
            selectItems.add(anim);
        }
        else if(holder.checkBox.isChecked()){
            selectItems.remove(anim);
        }
        else {
            holder.checkBox.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            selectItems.remove(anim);

        }
        if(selectItems.isEmpty()) adapterMode.finish();
        Log.i("SIZE", selectItems.size()+"");

    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
