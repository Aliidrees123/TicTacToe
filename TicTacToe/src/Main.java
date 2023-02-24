import java.util.*;

public class Main {

    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    //This contains all positions the player places an 'X'
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
    //This contains all positions the CPU places an 'O'

    public static void main(String[] args) {

        char[][] board = {{' ', '|', ' ', '|',' '},
                          {'-', '+', '-', '+','-'},
                          {' ', '|', ' ', '|',' '},
                          {'-', '+', '-', '+','-'},
                          {' ', '|', ' ', '|',' '}};
        //2D array containing the layout of the board
        printBoard(board);
        //The board is printed to the console

        while(true) {
            Scanner s = new Scanner(System.in);
            //Scanner is used to read input from the user

            System.out.println("Choose your placement 1 - 9 ");
            //The user is asked to select where to place 'X'
            int placement = s.nextInt();
            //Scanner is used to read the input and applies the value of the input to the int 'placement'

            while(playerPos.contains(placement) || cpuPos.contains(placement) || placement <= 0 || placement > 9) {
                System.out.println("Incorrect position, try again!");
                placement= s.nextInt();
            }
            //This while loop checks to make sure the user input is valid

            playerPos.add(placement);
            //The value os the input is added to the array list mentioned at the start of the code
            placePiece(board, placement, "player");
            //A method is called to place an 'X' where the user input stated
            printBoard(board);
            //The board is printed with the 'X' now in position

            winner();
            if(winner().length() > 0) {
                System.out.println(winner());
                break;
            }
            //This calls a method to check if there is a winner,
            //if the String returned by the winner method is not null, we print it

            Random ran = new Random();
            int ranPlace = ran.nextInt(9) + 1;
            //Here we create a new object called ran, this will randomly select a number between 1-9
            while(playerPos.contains(ranPlace) || cpuPos.contains(ranPlace)) {
                ranPlace = ran.nextInt(9) + 1;
            }

            //A check is run to ensure the number is valid, if it is not, another number 1-9 is randomly generated
            cpuPos.add(ranPlace);
            //The number is added to the second array list mentioned at the start of the code
            placePiece(board, ranPlace, "CPU");
            //A method is called to place an 'O' where the random number stated
            printBoard(board);
            //The board is printed

            winner();
            if(winner().length() > 0) {
                System.out.println(winner());
            }
            //This calls a method to check if there is a winner,
            //if the String returned by the winner method is not null, we print it
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for(char[] row : board) {
            for(char x : row) {
                System.out.print(x);
            }
            System.out.println();
        }
    }
    //This method uses a nested for loop to iterate through the 2D array, printing a row, then a new line,
    //then the next row until the entire array is printed, resulting in the board being displayed in the console

    public static void placePiece(char[][] board, int position, String user) {
        char symbol = ' ';
        //An empty char is created and labelled 'symbol'
        if(user.equals("player")) {
            symbol = 'X';
        //If the third input states player, then an 'X' will be placed
        } else if(user.equals("CPU")) {
            symbol = 'O';
        //If the third input states CPU, then an 'X' will be placed
        }

        switch (position) {
            case 1: board[0][0] = symbol;
                break;
            case 2: board[0][2] = symbol;
                break;
            case 3: board[0][4] = symbol;
                break;
            case 4: board[2][0] = symbol;
                break;
            case 5: board[2][2] = symbol;
                break;
            case 6: board[2][4] = symbol;
                break;
            case 7: board[4][0] = symbol;
                break;
            case 8: board[4][2] = symbol;
                break;
            case 9: board[4][4] = symbol;
                break;
            default:
                break;
            //Here we input the 'X' or 'O' in certain indices of the 2D array, depending on user input
            //Switch is used as it is more efficient than else if statements
        }
    }

    public static String winner() {
        //This method checks if there is a winner and returns a String
        List top = Arrays.asList(1, 2, 3);
        List midR = Arrays.asList(4, 5, 6);
        List bottom = Arrays.asList(7, 8, 9);
        List left = Arrays.asList(1, 4, 7);
        List midC = Arrays.asList(2, 5, 8);
        List right = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(3, 5, 7);
        //Above we define a number of lists that contain winning positions
        List<List> winCon = new ArrayList<List>();
        winCon.add(top);
        winCon.add(midR);
        winCon.add(bottom);
        winCon.add(left);
        winCon.add(midC);
        winCon.add(right);
        winCon.add(diag1);
        winCon.add(diag2);
        //These are then added to the collection called winCon
        for(List w : winCon) {
            if (playerPos.containsAll(w)) {
                return "You won!";
            } else if (cpuPos.containsAll(w)) {
                return "You lost!";
            }
        //This for each loop iterates through the collection and checks if either the CPU or player position array lists
        //contain all the numbers for any one of the winning conditions, and then returns a string
        }
                if(playerPos.size() + cpuPos.size() == 9) {
                return "It's a draw!";
        //If there is no winner and the board is filled, we return this String instead
        }
        return "";
        //This returns an empty String
    }
}