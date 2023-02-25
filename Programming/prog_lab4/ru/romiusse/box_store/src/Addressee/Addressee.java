package ru.romiusse.box_store.src.Addressee;

import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Deliver.RoadType;

public class Addressee {

    private String street, house;
    private Person person;
    private RoadType roadType;
    private Box box;

    public Addressee(String street, String house){

        this.street = street;
        this.house = house;

    }

    public void setPerson(String name, int age){
        person = new Person(name, age);
    }

    public void setRoadType(RoadType roadType){
        this.roadType = roadType;
    }

    public void setBox(Box box){
        this.box = box;
    }

    private class Person{

        private String name;
        private int age;


        private Person(String name, int age){

            this.name = name;
            this.age = age;
            
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person name: " + getName() + " age:" + Integer.toString(getAge());
        }

    }

    private interface AddressContainer {
        String getStreet();
        String getHouse();
    }

    public AddressContainer getAddressContainer() {
        class PersonAddressContainer implements AddressContainer {
            final String street = Addressee.this.street, house = Addressee.this.house;

            @Override
            public String getStreet() {
                return this.street;
            }

            @Override
            public String getHouse() {
                return this.house;
            }
        }

        return new PersonAddressContainer();
    }

    @Override
    public String toString() {
    
        return "Stret:" + getAddressContainer().getStreet() + " House: " + getAddressContainer().getHouse() + 
            "\n" + person.toString() + '\n' + "Road type: " + roadType.toString() + '\n' + box.toString();
    }


    
}
