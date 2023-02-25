package s367385.lab1.pokemons;

import s367385.lab1.moves.*;

public class Tsareena extends Steenee{


    public Tsareena(String name, int lvl){
        super(name, lvl);
        this.setStats(72d, 120d, 98d, 50d, 98d, 72d);
        this.addMove(new Stomp());
    }

}
