package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove{

    public Swagger(){
        super(Type.FLYING, 0d, 85d);
    }

    @Override
    public String describe(){
        return "применяет Swagger";
    }
    @Override
    public void applySelfEffects(Pokemon pokemon){
        pokemon.confuse();
        pokemon.addEffect(new Effect().chance(1).stat(Stat.ATTACK, +2));
    }

}