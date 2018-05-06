package emremedia.com.pokemons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import emremedia.com.pokemons.Model.Pokemon;

/**
 * Created by EMRE on 5.05.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private Context myContext;
    private List<Pokemon> pokemons;

    public RecyclerViewAdapter(Context myContext, List<Pokemon> pokemons) {
        this.myContext = myContext;
        this.pokemons = pokemons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(myContext);
        view=layoutInflater.inflate(R.layout.pokemon_kart,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.pokemonName.setText(pokemons.get(position).getPokemonName());
        holder.pokemonNumber.setText(pokemons.get(position).getPokemonNumber());
        holder.pokemonDesc.setText(pokemons.get(position).getPokemonAciklama());
        Picasso.get()
                .load(pokemons.get(position).getPokemonImage())
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.pokeball)
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView pokemonImage;
        TextView pokemonName;
        TextView pokemonNumber;
        TextView pokemonDesc;

        public MyViewHolder(View itemView) {
            super(itemView);

            pokemonImage=(ImageView)itemView.findViewById(R.id.pokemonImage);
            pokemonName=(TextView) itemView.findViewById(R.id.pokemonName);
            pokemonNumber=(TextView)itemView.findViewById(R.id.pokemonNumber);
            pokemonDesc=(TextView)itemView.findViewById(R.id.pokemonDescription);
        }
    }
}
