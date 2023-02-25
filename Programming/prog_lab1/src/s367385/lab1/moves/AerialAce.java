package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class AerialAce extends PhysicalMove{

    public AerialAce(){
        super(Type.FLYING, 60d, Double.POSITIVE_INFINITY);
    }

    @Override
    public String describe(){
        return "применяет Aerial Ace";
    }

}