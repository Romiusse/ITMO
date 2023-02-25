package s367385.lab1.pokemons;
import ru.ifmo.se.pokemon.*;
import s367385.lab1.moves.*;

public class Bounsweet extends Pokemon{


    public Bounsweet(String name, int lvl){
        super(name, lvl);
        this.setType(Type.GRASS);
        this.setStats(42d, 30d, 38d, 30d, 38d, 32d);
        this.setMove(new Swagger(), new EnergyBall());
    }

    public Bounsweet(){
        this("12", 11);
    }


}
