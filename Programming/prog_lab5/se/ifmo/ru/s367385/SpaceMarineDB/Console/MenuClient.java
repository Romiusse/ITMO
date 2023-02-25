package se.ifmo.ru.s367385.SpaceMarineDB.Console;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import se.ifmo.ru.s367385.SpaceMarineDB.Logger.ConsoleColors;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.Log;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.LogTypes;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.AstartesCategory;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.Chapter;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.Coordinates;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.MeleeWeapon;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarine;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarineCollection;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.Weapon;


public class MenuClient {

    Scanner sc;
    boolean isRunning;
    MenuServer server;
    SpaceMarineCollection spaceMarineCollection;
    ArrayList<String> history;

    public MenuClient(SpaceMarineCollection spaceMarineCollection){
        sc = new Scanner(System.in);
        server = new MenuServer(spaceMarineCollection);
        history = new ArrayList<String>();
        isRunning = true;
    }
    

    public void run(){
        while(isRunning){
            try {
                System.out.print(ConsoleColors.CYAN_BOLD + ">> ");
                String command = sc.nextLine();
                history.add(command);
                commandParser(command, false);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e){
                Log.log(LogTypes.ERROR, e.getMessage());
            }
        }
    }

    public void help(){
        System.out.println(ConsoleColors.GREEN_BOLD + "help" + ConsoleColors.WHITE_BOLD + " : вывести справку по доступным командам" + "\n\n" +
        ConsoleColors.GREEN_BOLD + "info" + ConsoleColors.WHITE_BOLD + " : вывести в стандартный поток вывода информацию o коллекции (тип, дата инициализации, количество элементов и т.д.)"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "show" + ConsoleColors.WHITE_BOLD + ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "add" + ConsoleColors.WHITE_BOLD + " : добавить новый элемент в коллекцию"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "update " + ConsoleColors.YELLOW_BOLD + "id" + ConsoleColors.WHITE_BOLD + " : обновить значение элемента коллекции, id которого равен заданному"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "remove_by_id " + ConsoleColors.YELLOW_BOLD + "id" + ConsoleColors.WHITE_BOLD + " : удалить элемент из коллекции по ero id"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "clear" + ConsoleColors.WHITE_BOLD + " : очистить коллекцию"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "save" + ConsoleColors.WHITE_BOLD + " : сохранить коллекцию в файл"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "execute_script " + ConsoleColors.YELLOW_BOLD + "file_name" + ConsoleColors.WHITE_BOLD + " : считать и исполнить скрипт из указанного файла. B скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме."+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "exit" + ConsoleColors.WHITE_BOLD + " : завершить программу (без сохранения в файл)"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "remove_greater " + ConsoleColors.YELLOW_BOLD + "id" + ConsoleColors.WHITE_BOLD + " : удалить из коллекции все элементы, превышающие заданный"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "sort" + ConsoleColors.WHITE_BOLD + " : отсортировать коллекцию в естественном порядке"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "history" + ConsoleColors.WHITE_BOLD + " : вывести последние 11 команд (без их аргументов)"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "min_by_category" + ConsoleColors.WHITE_BOLD + " : вывести любой объект из коллекции, значение поля category которого является минимальным"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "group_counting_by_melee_weapon" + ConsoleColors.WHITE_BOLD + " : сгруппировать элементы коллекции по значению поля meleeWeapon, вывести количество элементов в каждой группе"+ "\n\n" +
        ConsoleColors.GREEN_BOLD + "filter_by_health health" + ConsoleColors.WHITE_BOLD + " : вывести элементы, значение поля health которых равно заданному" + ConsoleColors.RESET);
    }

    public void info(){
        System.out.println(server.info());
    }

    public void show(){
        System.out.println(server.show());
    }

    
    /** 
     * @param cmd
     * @throws Exception
     */
    private void commandParser(String cmd, boolean isExecuteScript) throws Exception{
        String[] commands = cmd.split(" ");

        switch (commands[0]) {
            case "help":
                help();
                break;

            case "info":
                info();
                break;

            case "show":
                show();
                break;

            case "add":
                add();
                break;
            
            case "update":
                update(commands[1]);
                break;

            case "remove_by_id":
                removeById(commands[1]);
                break;
            
            case "clear":
                clear();
                break;

            case "save":
                save();
                break;

            case "execute_script":
                if(!isExecuteScript)
                executeScript(commands[1]);
                break;
            
            case "exit":
                exit();
                break;

            case "remove_greater":
                removeGreater(commands[1]);
                break;
            
            case "sort":
                sort();
                break;

            case "history":
                history();
                break;
            
            case "min_by_category":
                minByCategory();
                break;

            case "group_counting_by_melee_Weapon":
                groupCountingByMeleeWeapon();
                break;

            case "filter_by_health":
                filterByHealth(commands[1]);
                break;
        
            default:
                Log.log(LogTypes.INFO, "Комманда " + cmd + " не существует или введена неправильно");
        }
    }

    
    /** 
     * @param checker
     */
    private void correctInput(InputChecker checker){
        while(true){
            try{
                checker.check();
                return;
            } catch(NumberFormatException e){
                Log.log(LogTypes.INFO, "Необходимо ввести число");
            } catch(IllegalArgumentException e){
                Log.log(LogTypes.INFO, "Введенное значение не содержится в списке или оно введено неправильно");
            }
            catch(Exception e){
                Log.log(LogTypes.INFO, e.getMessage());
            }
            System.out.print(ConsoleColors.CYAN_BOLD + ">> ");
        }
    }

    
    /** 
     * @param inp
     * @return boolean
     */
    private boolean isYesInput(String inp){
        inp.toLowerCase();
        if(inp.equals("д") || inp.equals("да") || inp.equals("yes") || inp.equals("y")) return true;
        return false;
    }


    /**
     * @throws Exception
     */
    private void add() throws Exception{

        SpaceMarine spaceMarine = new SpaceMarine();

        spaceMarine.setId(System.nanoTime());

        System.out.print(ConsoleColors.WHITE_BOLD + "Введите имя космического пехотинца" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {spaceMarine.setName(sc.nextLine());});
        

        Coordinates coordinates = new Coordinates();
        System.out.println(ConsoleColors.WHITE_BOLD + "Введите координаты");
        System.out.print(ConsoleColors.WHITE_BOLD + "X" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {coordinates.setX(Integer.parseInt(sc.nextLine()));});

        System.out.print(ConsoleColors.WHITE_BOLD + "Y" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {coordinates.setY(Integer.parseInt(sc.nextLine()));});
        spaceMarine.setCoordinates(coordinates);

        System.out.print(ConsoleColors.WHITE_BOLD + "Введите здоровье пехотинца" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {spaceMarine.setHealth(Integer.parseInt(sc.nextLine()));});
        

        //String isNull;
        System.out.print(ConsoleColors.WHITE_BOLD + "Введите одну из категорий " + ConsoleColors.YELLOW_BOLD + Arrays.toString(AstartesCategory.values()) + ConsoleColors.WHITE_BOLD + " или" + ConsoleColors.YELLOW_BOLD + " null" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {
            String isNull = sc.nextLine(); if(isNull.equals("null")) spaceMarine.setCategory(null); else
            spaceMarine.setCategory(AstartesCategory.valueOf(isNull));
        });
        
        System.out.print(ConsoleColors.WHITE_BOLD + "Введите одно из оружий 1 " + ConsoleColors.YELLOW_BOLD + Arrays.toString(Weapon.values()) + ConsoleColors.WHITE_BOLD + " или" + ConsoleColors.YELLOW_BOLD + " null" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {
        String isNull = sc.nextLine(); if(isNull.equals("null")) spaceMarine.setWeaponType(null); else
        spaceMarine.setWeaponType(Weapon.valueOf(isNull));
        });

        System.out.print(ConsoleColors.WHITE_BOLD + "Введите одно из оружий 2 " + ConsoleColors.YELLOW_BOLD + Arrays.toString(MeleeWeapon.values()) + ConsoleColors.WHITE_BOLD + " или" + ConsoleColors.YELLOW_BOLD + " null" + ConsoleColors.CYAN_BOLD + " >> ");
        correctInput(() -> {
        String isNull = sc.nextLine(); if(isNull.equals("null")) spaceMarine.setMeleeWeapon(null); else
        spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(isNull));
        });

        Chapter chapter = new Chapter();
        System.out.print(ConsoleColors.WHITE_BOLD + "Добавить главу?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        String addChapter = sc.nextLine();

        if(isYesInput(addChapter)){

            System.out.print(ConsoleColors.WHITE_BOLD + "Введите имя главы" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {chapter.setName(sc.nextLine());});

            System.out.print(ConsoleColors.WHITE_BOLD + "Введите количество пехотинцев" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {chapter.setMarinesCount(Long.parseLong(sc.nextLine()));});

            System.out.print(ConsoleColors.WHITE_BOLD + "Введите легион" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {chapter.setParentLegion(sc.nextLine());});

            System.out.print(ConsoleColors.WHITE_BOLD + "Добавить название мира?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            String addWorld = sc.nextLine();

            if(isYesInput(addWorld)){
                System.out.print(ConsoleColors.WHITE_BOLD + "Введите название мира" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {chapter.setWorld(sc.nextLine());});
            }
            else{
                chapter.setWorld(null);
            }
            spaceMarine.setChapter(chapter);
        }
        else{
            spaceMarine.setChapter(null);
        }

        System.out.println(ConsoleColors.WHITE_BOLD + "Проверьте данные:");
        System.out.println(spaceMarine.info());

        System.out.print(ConsoleColors.WHITE_BOLD + "Добавить пехотинца?"+ ConsoleColors.YELLOW_BOLD +" (д/н) " + ConsoleColors.CYAN_BOLD);
        String addMarine = sc.nextLine();
        if(isYesInput(addMarine)){
            System.out.println(server.add(spaceMarine));
        }
    }

    private void update(String id) throws NumberFormatException, Exception{

        SpaceMarine spaceMarine = server.spaceMarineCollection.getObjectById(Long.parseLong(id));
        String needToUpdate;


        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить имя космического пехотинца?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){
            System.out.print(ConsoleColors.WHITE_BOLD + "Введите имя космического пехотинца" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {spaceMarine.setName(sc.nextLine());});
        }
        
        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить координаты?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){
            Coordinates coordinates = spaceMarine.getCoordinates();

            System.out.print(ConsoleColors.WHITE_BOLD + "Изменить координату x?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            needToUpdate = sc.nextLine();
            if(isYesInput(needToUpdate)){
                System.out.print(ConsoleColors.WHITE_BOLD + "X" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {coordinates.setX(Integer.parseInt(sc.nextLine()));});
            }

            System.out.print(ConsoleColors.WHITE_BOLD + "Изменить координату y?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            needToUpdate = sc.nextLine();
            if(isYesInput(needToUpdate)){
                System.out.print(ConsoleColors.WHITE_BOLD + "Y" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {coordinates.setY(Integer.parseInt(sc.nextLine()));});
            }
            spaceMarine.setCoordinates(coordinates);
        }

        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить здоровье пехотинца?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){
            System.out.print(ConsoleColors.WHITE_BOLD + "Введите здоровье пехотинца" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {spaceMarine.setHealth(Integer.parseInt(sc.nextLine()));});
        }
        

        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить категорию?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){
            System.out.print(ConsoleColors.WHITE_BOLD + "Введите одну из категорий " + ConsoleColors.YELLOW_BOLD + Arrays.toString(AstartesCategory.values()) + ConsoleColors.WHITE_BOLD + " или" + ConsoleColors.YELLOW_BOLD + " null" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {
                String isNull = sc.nextLine(); if(isNull.equals("null")) spaceMarine.setCategory(null); else
                spaceMarine.setCategory(AstartesCategory.valueOf(isNull));
            });
        }
        
        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить оружие 1?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){
            System.out.print(ConsoleColors.WHITE_BOLD + "Введите одно из оружий 1 " + ConsoleColors.YELLOW_BOLD + Arrays.toString(Weapon.values()) + ConsoleColors.WHITE_BOLD + " или" + ConsoleColors.YELLOW_BOLD + " null" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {
            String isNull = sc.nextLine(); if(isNull.equals("null")) spaceMarine.setWeaponType(null); else
            spaceMarine.setWeaponType(Weapon.valueOf(isNull));
            });
        }

        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить оружие 2?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){
            System.out.print(ConsoleColors.WHITE_BOLD + "Введите одно из оружий 2 " + ConsoleColors.YELLOW_BOLD + Arrays.toString(MeleeWeapon.values()) + ConsoleColors.WHITE_BOLD + " или" + ConsoleColors.YELLOW_BOLD + " null" + ConsoleColors.CYAN_BOLD + " >> ");
            correctInput(() -> {
            String isNull = sc.nextLine(); if(isNull.equals("null")) spaceMarine.setMeleeWeapon(null); else
            spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(isNull));
            });
        }

        Chapter chapter = spaceMarine.getChapter();
        System.out.print(ConsoleColors.WHITE_BOLD + "Изменить главу?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
        needToUpdate = sc.nextLine();
        if(isYesInput(needToUpdate)){

            System.out.print(ConsoleColors.WHITE_BOLD + "Изменить имя главы?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            needToUpdate = sc.nextLine();
            if(isYesInput(needToUpdate)){
                System.out.print(ConsoleColors.WHITE_BOLD + "Введите имя главы" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {chapter.setName(sc.nextLine());});
            }

            System.out.print(ConsoleColors.WHITE_BOLD + "Изменить количество пехотинцев?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            needToUpdate = sc.nextLine();
            if(isYesInput(needToUpdate)){
                System.out.print(ConsoleColors.WHITE_BOLD + "Введите количество пехотинцев" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {chapter.setMarinesCount(Long.parseLong(sc.nextLine()));});
            }

            System.out.print(ConsoleColors.WHITE_BOLD + "Изменить легион?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            needToUpdate = sc.nextLine();
            if(isYesInput(needToUpdate)){
                System.out.print(ConsoleColors.WHITE_BOLD + "Введите легион" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {chapter.setParentLegion(sc.nextLine());});
            }

            System.out.print(ConsoleColors.WHITE_BOLD + "Изменить название мира?" + ConsoleColors.YELLOW_BOLD + " (д/н) " + ConsoleColors.CYAN_BOLD);
            needToUpdate = sc.nextLine();
            if(isYesInput(needToUpdate)){
                System.out.print(ConsoleColors.WHITE_BOLD + "Введите название мира" + ConsoleColors.CYAN_BOLD + " >> ");
                correctInput(() -> {chapter.setWorld(sc.nextLine());});
            }
            spaceMarine.setChapter(chapter);
        }

        System.out.println(ConsoleColors.WHITE_BOLD + "Проверьте данные:");
        System.out.println(spaceMarine.info());

        System.out.print(ConsoleColors.WHITE_BOLD + "Применить изменения пехотинца?"+ ConsoleColors.YELLOW_BOLD +" (д/н) " + ConsoleColors.CYAN_BOLD);
        String addMarine = sc.nextLine();
        if(isYesInput(addMarine)){
            System.out.println(server.update(spaceMarine));
        }
    }

    private void removeById(String id) throws Exception{
        System.out.println(server.removeById(Long.parseLong(id)));
    }

    private void clear(){
        System.out.println(server.clear());
    }

    private void save(){
        System.out.println(server.save(System.getenv("SPACE_MARINE")));
    }

    private void executeScript(String fileName) throws Exception{
        try{
        sc = new Scanner(new File(fileName));
        while(sc.hasNextLine()){
            commandParser(sc.nextLine(), true);
        }
        Log.log(LogTypes.INFO, "Скрипт успешно выполнился");
        } catch(Exception e){
            throw e;

        } finally{
            sc = new Scanner(System.in); 
        }
    }

    private void exit(){
        isRunning = false;
    }

    private void removeGreater(String id){
        System.out.println(server.removeGreater(Long.parseLong(id)));
    }

    private void sort(){
        System.out.println(server.sort());
    }

    private void history(){
        System.out.println(ConsoleColors.BLUE_BOLD + "История комманд (последние 11):" + ConsoleColors.GREEN_BOLD);
        for(int i = history.size() - 1, j = 0; i >= 0 && j < 11; j++, i--)
            System.out.println(history.get(i));
        System.out.print(ConsoleColors.RESET);
            
    }

    private void minByCategory(){
        System.out.println(server.minByCategory());
    }

    private void groupCountingByMeleeWeapon(){
        System.out.println(server.groupCountingByMeleeWeapon());
    }

    private void filterByHealth(String hp){
        System.out.println(server.filterByHealth(Integer.parseInt(hp)));
    }





}

interface InputChecker{
    void check() throws Exception;
}
