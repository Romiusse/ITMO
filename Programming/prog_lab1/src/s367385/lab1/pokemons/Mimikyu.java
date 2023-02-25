package s367385.lab1.pokemons;
import ru.ifmo.se.pokemon.*;
import s367385.lab1.moves.PinMissile;
import s367385.lab1.moves.Thunderbolt;
import s367385.lab1.moves.WorkUp;
import s367385.lab1.moves.ZingZap;

public class Mimikyu extends Pokemon{

    public Mimikyu(String name, int lvl){
        super(name, lvl);
        this.setType(Type.GHOST, Type.FAIRY);
        this.setStats(55d, 90d, 80d, 50d, 105d, 96d);
        this.setMove(new Thunderbolt(), new PinMissile(),
            new ZingZap(), new WorkUp());
    }

}
