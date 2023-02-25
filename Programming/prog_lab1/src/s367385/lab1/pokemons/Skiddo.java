package s367385.lab1.pokemons;
import ru.ifmo.se.pokemon.*;
import s367385.lab1.moves.Bulldoze;
import s367385.lab1.moves.TakeDown;
import s367385.lab1.moves.WildCharge;

public class Skiddo extends Pokemon{


    public Skiddo(String name, int lvl){
        super(name, lvl);
        this.setType(Type.GRASS);
        this.setStats(66d, 65d, 48d, 62d, 57d, 52d);
        this.setMove(new Bulldoze(), new TakeDown(),
            new WildCharge());
    }

}