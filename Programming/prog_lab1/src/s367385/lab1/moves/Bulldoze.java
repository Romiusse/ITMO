package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class Bulldoze extends PhysicalMove{

    public Bulldoze(){
        super(Type.GROUND, 60d, 100d);
    }

    @Override
    public String describe(){
        return "применяет Bulldoze";
    }
    @Override
    public void applySelfEffects(Pokemon pokemon){
        pokemon.addEffect(new Effect().chance(1).stat(Stat.SPEED, -1));
    }

}
