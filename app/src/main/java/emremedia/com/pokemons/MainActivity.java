package emremedia.com.pokemons;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import emremedia.com.pokemons.Model.Pokemon;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecyclerView;
    RecyclerViewAdapter myAdapter;
    Boolean programStart=true;

    List<Pokemon> pokemons;
    //-------------------------------

    String pokemonName;
    String pokemonNumber;
    String pokemonImage;
    String pokemonAciklama;
    String sonrakiUrl = "";

    String uzanti = "/us/pokedex/bulbasaur";
    String url = "https://www.pokemon.com";
    String adress;

    //--------------------------------------
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pokemons=new ArrayList<>();
        new VeriGetir().execute();



    }
    private class VeriGetir extends AsyncTask<Void,Void,Void>{

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (programStart){
                dialog=new ProgressDialog(MainActivity.this);
                dialog.setTitle("POKEMONS");
                dialog.setMessage("Resimler Yükleniyor");
                dialog.setIndeterminate(false);
                dialog.show();
            }

        }

        @Override
        protected Void doInBackground(Void... voids) {
            veriCek();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if (programStart){
                dialog.dismiss();
                myRecyclerView=(RecyclerView)findViewById(R.id.myRecyclerView);
                myAdapter=new RecyclerViewAdapter(getBaseContext(),pokemons);
                myRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                myRecyclerView.setAdapter(myAdapter);
            }

            myAdapter=new RecyclerViewAdapter(getBaseContext(),pokemons);
            myRecyclerView.getAdapter().notifyDataSetChanged();// yeni eklene pokemonları surekli recycler viewde günceleeme yapar

            programStart=false;
            if(pokemons.size()!=806)
                new VeriGetir().execute();

        }
    }

    private void veriCek()
    {
        for (int i = 0; i < 5; i++)
        {
            adress = url + uzanti;

            Document document = null;
            try {
                document = Jsoup.connect(adress).get();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Element imageUrlRow = document.select("div[class=profile-images]").select("img[class=active]").first();
            pokemonImage = imageUrlRow.attr("src").toString();
            pokemonName=imageUrlRow.attr("alt").toString();
            pokemonNumber = pokemonImage.substring(pokemonImage.length() - 7, pokemonImage.length()-4);

            Element rowNextUrl = document.select("div[class=pokedex-pokemon-pagination]").select("a[class=next]").first();
            sonrakiUrl=rowNextUrl.attr("href").toString();

            Element pokeDesc=document.select("p[class=version-y\n" +
                    "                                  active]").first();
            pokemonAciklama=pokeDesc.text();

            pokemons.add(new Pokemon(pokemonName,pokemonNumber,pokemonImage,pokemonAciklama));

            uzanti = sonrakiUrl;
        }
    }
}
