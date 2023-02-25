package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class PlayNice extends StatusMove{

    public PlayNice(){
        super(Type.GRASS, 0d, 0d);
    }

    @Override
    public String describe(){
        return "применяет Play Nice";
    }

}
