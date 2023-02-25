package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class TakeDown extends PhysicalMove{

    public TakeDown(){
        super(Type.NORMAL, 90d, 85d);
    }

    @Override
    public String describe(){
        return "применяет Take Down";
    }
    @Override
    public void applySelfDamage(Pokemon pokemon, final double n){
        double fn = (0.4 * pokemon.getLevel() + 2.0) * 90d / 150.0;
        fn *= 0.25;
        pokemon.setMod(Stat.HP, (int)Math.round(fn));
    }

}
