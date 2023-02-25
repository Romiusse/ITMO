package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove{

    public Thunderbolt(){
        super(Type.ELECTRIC, 90d, 100d);
    }

    @Override
    public String describe(){
        return "применяет Thunderbolt";
    }
    @Override
    public void applyOppEffects(Pokemon pokemon){
        pokemon.setCondition(new Effect().chance(0.1).condition(Status.PARALYZE));
    }

}