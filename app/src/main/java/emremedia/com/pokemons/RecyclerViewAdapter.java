package emremedia.com.pokemons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Pokemon currentPokemon=pokemons.get(position);

        String pokemonNumber=currentPokemon.getPokemonNumber();
        holder.pokemonName.setText(pokemonNumber+" "+currentPokemon.getPokemonName());
        holder.pokemonDesc.setText(currentPokemon.getPokemonAciklama());


        if (currentPokemon.getPokemonType().contains("Fire")){
            holder.pokemonImage.setBackground(ContextCompat.getDrawable(myContext,R.drawable.ic_pok_red));
        }

        else if (currentPokemon.getPokemonType().contains("Grass")){
            holder.pokemonImage.setBackground(ContextCompat.getDrawable(myContext,R.drawable.ic_pok_green));
        }

        else if (currentPokemon.getPokemonType().contains("Water")){
            holder.pokemonImage.setBackground(ContextCompat.getDrawable(myContext,R.drawable.ic_pok_blue));
            holder.pokemonName.setTextColor(ContextCompat.getColor(myContext,android.R.color.white));
        }

        else if (currentPokemon.getPokemonType().contains("Electric")){
            holder.pokemonImage.setBackground(ContextCompat.getDrawable(myContext,R.drawable.ic_pok_yellow));
        }

        else if (currentPokemon.getPokemonType().contains("Poison")
                || currentPokemon.getPokemonType().contains("Psychic"))
        {
            holder.pokemonImage.setBackground(ContextCompat.getDrawable(myContext,R.drawable.ic_pok_purple));
            holder.pokemonName.setTextColor(ContextCompat.getColor(myContext,android.R.color.white));
        }



        else {
            holder.pokemonImage.setBackgroundColor(ContextCompat.getColor(myContext,android.R.color.white));
            holder.pokemonName.setTextColor(ContextCompat.getColor(myContext,android.R.color.black));
        }

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

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView pokemonImage;
        TextView pokemonName;
        TextView pokemonDesc;

        MyViewHolder(View itemView) {
            super(itemView);

            pokemonImage= itemView.findViewById(R.id.pokemonImage);
            pokemonName= itemView.findViewById(R.id.pokemonName);
            pokemonDesc= itemView.findViewById(R.id.pokemonDescription);
        }
    }
}
