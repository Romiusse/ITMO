package s367385.lab1;

import ru.ifmo.se.pokemon.*;
import s367385.lab1.pokemons.*;

//Variant: 3117101


public class Main {
    public static void main(String []args) {
        Battle b = new Battle();
        Mimikyu p1 = new Mimikyu("Чужой1", 10);
        Skiddo p2 = new Skiddo("Чужой2", 10);
        Gogoat p3 = new Gogoat("Чужой3", 10);
        Bounsweet p4 = new Bounsweet("Чужой4", 10);
        Steenee p5 = new Steenee("Чужой5", 10);
        Tsareena p6 = new Tsareena("Чужой6", 10);


        b.addAlly(p1);
        b.addAlly(p3);
        b.addAlly(p5);
        b.addFoe(p2);
        b.addFoe(p4);
        b.addFoe(p6);
        b.go();
     }
}
