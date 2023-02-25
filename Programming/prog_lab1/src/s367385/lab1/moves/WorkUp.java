package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class WorkUp extends StatusMove{

    public WorkUp(){
        super(Type.NORMAL, 0d, 0d);
    }

    @Override
    public String describe(){
        return "применяет Work Up";
    }
    @Override
    public void applySelfEffects(Pokemon pokemon){
        pokemon.addEffect(new Effect().chance(1).stat(Stat.ATTACK, +1));
        pokemon.addEffect(new Effect().chance(1).stat(Stat.SPECIAL_ATTACK, +1));
    }


}