import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToe {
  
  static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
  static ArrayList<Integer> cpuPosition = new ArrayList<Integer>(); 
  
  public static void main(String[] args) {
    ArrayList<Integer> playedPos = new ArrayList<>(5); // maximum number of times played is 5 in the 
                                                       // event of a tie
    ArrayList<Integer> compPos = new ArrayList<>(5);
    char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '}, 
                          {'-', '+', '-', '+', '-'}, 
                          {' ', '|', ' ', '|', ' '}};

    System.out.println("Welcome to Tic Tac Toe!");
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out
          .println("Enter your placement (1-9) for your move on the board or enter 0 to exit: ");
      int playerPos = sc.nextInt();
      if (playerPos == 0) {
        System.out.println("Goodbye!");
        break;
      }

      while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)) {
        System.out.println("Position Taken! Please pick a valid position");
        playerPos = sc.nextInt();
      }
      playedPos.add(playerPos);
      
      fill(playerPos, gameBoard, "player");

      String result = checkWinner(); //in case of tie, checks winner here too
      if (result.length() > 0) {
        System.out.println(result);
        break;
      }

     // Random rand = new Random();
     // int cpuPos = randomize(); // chooses random position from 1-9
      int cpuPos = playCPU(playedPos, compPos);

      while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) { /*if the move picked
                                                                                  by the cpu is 
                                                                                  already played it 
                                                                                  randomizes to pick 
                                                                                  another move */
        cpuPos = playCPU(playedPos, compPos);
      }
      fill(cpuPos, gameBoard, "cpu");
      printBoard(gameBoard);

      result = checkWinner();

      if (result.length() > 0) {
        System.out.println(result);
        break;
      }
    }
  }
 
  public static int randomize() {
    Random rand = new Random();
    int pos = rand.nextInt(9) + 1;
    return pos;
  }
  
  public static int playCPU(ArrayList<Integer> playedPos, ArrayList<Integer> compPos) {
    if((!playedPos.contains(5)) && (!compPos.contains(5))) {
      return 5; // plays the center of the board as long as it hasn't been played
    }
    if()
    return 0;
  }
  
  public static String checkWinner() {

    // horizontal winners
    List topRow = Arrays.asList(1, 2, 3);
    List midRow = Arrays.asList(4, 5, 6);
    List botRow = Arrays.asList(7, 8, 9);

    // vertical winners
    List topCol = Arrays.asList(1, 4, 7);
    List midCol = Arrays.asList(2, 5, 8);
    List botCol = Arrays.asList(3, 6, 9);

    // diagonal winners
    List diag1 = Arrays.asList(1, 5, 9);
    List diag2 = Arrays.asList(3, 5, 7);

    List<List> winner = new ArrayList<List>();

    winner.add(topRow);
    winner.add(midRow);
    winner.add(botRow);
    winner.add(topCol);
    winner.add(midCol);
    winner.add(botCol);
    winner.add(diag1);
    winner.add(diag2);

    for (List l : winner) {
      if (playerPosition.containsAll(l)) {
        return "Player Wins!";
      } else if (cpuPosition.containsAll(l)) {
        return "CPU Wins!";
      } else if ((playerPosition.size() + cpuPosition.size()) == 9) {
        return "Tie!";
      }
    }
    return "";


  }
  
  public static void printBoard(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  public static void fill(int pos, char[][] board, String user) {
    char symbol = ' ';
    if (user.equals("player")) {
      symbol = 'X';
      playerPosition.add(pos);
    } else if (user.equals("cpu")) {
      symbol = 'O';
      cpuPosition.add(pos);
    }

    switch (pos) {
      case 1:
        board[0][0] = symbol;
        break;

      case 2:
        board[0][2] = symbol;
        break;
      case 3:
        board[0][4] = symbol;
        break;
      case 4:
        board[2][0] = symbol;
        break;
      case 5:
        board[2][2] = symbol;
        break;
      case 6:
        board[2][4] = symbol;
        break;
      case 7:
        board[4][0] = symbol;
        break;
      case 8:
        board[4][2] = symbol;
        break;
      case 9:
        board[4][4] = symbol;
        break;
      default:
        break;
    }
  }

}
