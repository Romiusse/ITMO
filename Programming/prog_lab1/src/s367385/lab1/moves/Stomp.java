package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class Stomp extends PhysicalMove{

    public Stomp(){
        super(Type.GRASS, 65d, 100d);
    }

    @Override
    public String describe(){
        return "применяет Stomp";
    }

}