import java.util.*;

public class TicTacToe {

    public static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    public static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        int theoreticalPos;
        int chosenPos;
        String result;
        printGameBoard(gameBoard);

        while(true){
            do {
                System.out.println("Select a position: ");
                chosenPos = scan.nextInt();
            } while (checkPositionValidation(chosenPos));
            placePiece(gameBoard, "player", chosenPos);

            if(checkTie(gameBoard)) {
                System.out.println("Smells like true draw here!");
                break;
            }
            result = checkWinner();
            if(!result.equals("")){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            do {
                chosenPos = random.nextInt(9) + 1;
            } while (checkPositionValidation(chosenPos));
            placePiece(gameBoard, "computer", chosenPos);

            printGameBoard(gameBoard);

             result = checkWinner();
             if(!result.equals("")){
                 System.out.println(result);
                 break;
             }
        }
    }

    public static  boolean checkTie(char[][] gameBoard) {
        for(char[] row : gameBoard){
            for(char el : row){
                if(el == ' ') return false;
            }
        }
        return true;
    }

    public static String checkWinner() {
        String winner = "";
        List<Integer> topRow = Arrays.asList(1, 2 ,3);
        List<Integer> midRow = Arrays.asList(4, 5 , 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5 ,8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> crossLeft = Arrays.asList(1, 5, 9);
        List<Integer> crossRight = Arrays.asList(3, 5 , 7);

        ArrayList<List> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(crossLeft);
        winningConditions.add(crossRight);

        for(List l : winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congratulations! You won the game!";
            }
            if(computerPositions.containsAll(l)){
                return "I'm sorry, but you lost to computer:(";
            }
        }
        return "";
    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char row[] : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

     public static void placePiece(char[][] gameBoard, String playerName, int position) {
        char symbol = ' ';
        if(playerName.equals("computer")) {
            symbol = '0';
            computerPositions.add(position);
        } else if(playerName.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else {
            System.out.println("WRONG PLAYER");
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static boolean checkPositionValidation(int position) {
        if(playerPositions.contains(position) || computerPositions.contains(position)){
            return true;
        }
        return false;
    }
}