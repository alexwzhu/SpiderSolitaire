import java.util.Scanner;
public class Driver
{

    public static void main(String[] args){
        Board board = new Board(8,4);
        board.printBoard();
        int i = 5;
        while(!board.isEmpty()){
            Scanner id = new Scanner(System.in);
            String run = id.nextLine();
            if(run.equals("draw")){
                board.drawCards();
                System.out.println();
                board.printBoard();
            }else if(run.length() == 10 && run.substring(0,4).equals("move")){
                board.makeMove(convert(run.substring(5,6)),convert(run.substring(7,8)),convert(run.substring(9,10)));
                System.out.println();
                board.printBoard();
            }else if(run.length() == 7 && run.substring(0,5).equals("clear")){
                board.clear(convert(run.substring(6,7)));
                System.out.println();
                board.printBoard();
            }else if(run.equals("restart")){
                Board board2 = new Board(8,4);
                board2.printBoard();
            }else if(run.equals("quit")){
                System.out.println("GoodBye!");
                break;
                
            }else if(run.equals("save")){
                board.save();
            }else if(run.equals("load")){
                board.load();
                board.printBoard();
            }else{
                System.out.println("Invalid Move");
            }
        }
        if(board.isEmpty()){
            System.out.println("You win");
        }
    }

    public static int convert(String symbol){
        if(symbol.equals("A")){
            return 1;
        }else if(symbol.equals("2")){
            return 2;
        }else if(symbol.equals("3")){
            return 3;
        }else if(symbol.equals("4")){
            return 4;
        }else if(symbol.equals("5")){
            return 5;
        }else if(symbol.equals("6")){
            return 6;
        }else if(symbol.equals("7")){
            return 7;
        }else if(symbol.equals("8")){
            return 8;
        }else if(symbol.equals("9")){
            return 9;
        }else if(symbol.equals("T")){
            return 10;
        }else if(symbol.equals("J")){
            return 11;
        }else if(symbol.equals("Q")){
            return 12;
        }else if(symbol.equals("K")){
            return 13;
        }else{
            return 0;
        }

    }
}
