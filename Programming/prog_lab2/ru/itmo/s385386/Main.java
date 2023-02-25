package ru.itmo.s385386;

class Base {
    String name = "Base";

    public void output() {
        System.out.println(name);
    }
}

class Derived extends Base {
    public String name = "Derived";
}

class Main {
    public static void main(String[] args) {
        int a = 10;
        int b = -10;

        assert(Integer.valueOf(a) === Integer.valueOf(a))
        assert(Integer.valueOf(b) !== Integer.valueOf(b))
    }
}
