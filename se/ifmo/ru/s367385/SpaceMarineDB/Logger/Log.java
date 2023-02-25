package se.ifmo.ru.s367385.SpaceMarineDB.Logger;

public class Log {
    

    
    /** 
     * @param t
     * @param msg
     */
    public static void log(LogTypes t, String msg){

        if(t == LogTypes.ERROR){
            System.out.println(ConsoleColors.BLACK_BOLD + ConsoleColors.RED_BACKGROUND + " " + msg + " " + ConsoleColors.RESET);
        }
        else if(t == LogTypes.INFO){
            System.out.println(ConsoleColors.BLACK_BOLD + ConsoleColors.YELLOW_BACKGROUND + " " + msg + " " + ConsoleColors.RESET);
        }
        else if(t == LogTypes.SERVER){
            System.out.println(ConsoleColors.WHITE_BOLD + ConsoleColors.CYAN_BACKGROUND + " " + msg + " " + ConsoleColors.RESET);
        }

    }

    
    /** 
     * @param t
     * @param msg
     * @return String
     */
    public static String log2client(LogTypes t, String msg){
        if(t == LogTypes.ERROR){
            return (ConsoleColors.BLACK_BOLD + ConsoleColors.RED_BACKGROUND + " " + msg + " " + ConsoleColors.RESET);
        }
        else if(t == LogTypes.INFO){
            return (ConsoleColors.BLACK_BOLD + ConsoleColors.YELLOW_BACKGROUND + " " + msg + " " + ConsoleColors.RESET);
        }
        else if(t == LogTypes.SERVER){
            return (ConsoleColors.WHITE_BOLD + ConsoleColors.CYAN_BACKGROUND + " " + msg + " " + ConsoleColors.RESET);
        }
        return msg;
    }

}
