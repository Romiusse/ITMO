package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class ZingZap extends PhysicalMove{

    public ZingZap(){
        super(Type.ELECTRIC, 80d, 100d);
    }

    @Override
    public String describe(){
        return "применяет Zing Zap";
    }
    @Override
    public void applyOppEffects(Pokemon pokemon){
        pokemon.setCondition(new Effect().chance(0.3).condition(Status.PARALYZE));
    }

}
