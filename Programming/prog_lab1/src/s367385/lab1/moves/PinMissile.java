package s367385.lab1.moves;
import ru.ifmo.se.pokemon.*;

public class PinMissile extends PhysicalMove{

    public PinMissile(){
        super(Type.BUG, 25d, 95d);
    }

    @Override
    public String describe(){
        return "применяет Pin Missile";
    }
    @Override
    public void applyOppDamage(Pokemon pokemon, double damage){
        double r = Math.random();
        if(r <= (3f/8f)){
             damage += (0.4 * pokemon.getLevel() + 2.0) * 50 / 150.0;
             r = Math.random();
             if(r <= (3f/8f)){
                damage += (0.4 * pokemon.getLevel() + 2.0) * 75 / 150.0;
                r = Math.random();
                if(r <= (1f/8f)){
                   damage += (0.4 * pokemon.getLevel() + 2.0) * 100 / 150.0;
                   r = Math.random();
                   if(r <= (1f/8f)){
                      damage += (0.4 * pokemon.getLevel() + 2.0) * 125 / 150.0;
                   }
                }
             }
        }
        pokemon.setMod(Stat.HP, (int)Math.round(damage));

    }

}
