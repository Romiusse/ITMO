package s367385.lab1.pokemons;

import s367385.lab1.moves.PlayNice;

public class Steenee extends Bounsweet{


    public Steenee(String name, int lvl){
        super(name, lvl);
        this.setStats(52d, 40d, 48d, 40d, 48d, 62d);
        this.addMove(new PlayNice());
    }

}
