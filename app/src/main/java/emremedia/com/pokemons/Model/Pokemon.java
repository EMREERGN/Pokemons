package emremedia.com.pokemons.Model;

/**
 * Created by EMRE on 5.05.2018.
 */

public class Pokemon {
    private String pokemonName;
    private String pokemonNumber;
    private String pokemonImage;
    private String pokemonAciklama;

    public Pokemon() {
    }

    public Pokemon(String pokemonName, String pokemonNumber, String pokemonImage, String pokemonAciklama) {
        this.pokemonName = pokemonName;
        this.pokemonNumber = pokemonNumber;
        this.pokemonImage = pokemonImage;
        this.pokemonAciklama = pokemonAciklama;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonNumber() {
        return pokemonNumber;
    }

    public void setPokemonNumber(String pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public String getPokemonImage() {
        return pokemonImage;
    }

    public void setPokemonImage(String pokemonImage) {
        this.pokemonImage = pokemonImage;
    }

    public String getPokemonAciklama() {
        return pokemonAciklama;
    }

    public void setPokemonAciklama(String pokemonAciklama) {
        this.pokemonAciklama = pokemonAciklama;
    }
}
