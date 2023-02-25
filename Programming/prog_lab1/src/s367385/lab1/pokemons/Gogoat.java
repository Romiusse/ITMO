package s367385.lab1.pokemons;

import s367385.lab1.moves.AerialAce;

public class Gogoat extends Skiddo{


    public Gogoat(String name, int lvl){
        super(name, lvl);
        this.setStats(123d, 100d, 62d, 97d, 81d, 68d);
        this.addMove(new AerialAce());
    }

}
