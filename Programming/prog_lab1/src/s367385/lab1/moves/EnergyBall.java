package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class EnergyBall extends SpecialMove{

    public EnergyBall(){
        super(Type.GRASS, 90d, 100d);
    }

    @Override
    public String describe(){
        return "применяет Energy Ball";
    }
    @Override
    public void applyOppEffects(Pokemon pokemon){
        pokemon.addEffect(new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE, -1));
    }

}