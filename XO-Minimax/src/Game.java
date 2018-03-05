import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private static final int BOARD_SIZE =3;
	private static final String COMPUTER = "X";
	private static final String USER = "O";
	private static final String EMPTY = "-";
	
	private Index[][] board;
	private List<Index> remainingCells;
	private List<Index> minimaxValues;
	private Random random;
	private Scanner scanner;

	public static void main(String[] args){
		new Game();
	}
	
	private Game(){
		random = new Random();
		scanner = new Scanner(System.in);
		remainingCells = new ArrayList<>();
		minimaxValues = new ArrayList<>();
		board = new Index[BOARD_SIZE][BOARD_SIZE];
		
		for(int i=0; i<BOARD_SIZE; i++){
			for(int j=0; j<BOARD_SIZE; j++){
				board[i][j] = new Index(i, j, EMPTY);
			}
		}
		
		System.out.println("Who wants to start ? Print 1 for Computer and 2 for User");
		
		int start = scanner.nextInt();
		if (start == 1){
			move(random.nextInt(BOARD_SIZE), random.nextInt(BOARD_SIZE), COMPUTER);
			printBoard();
		}
		play();
		getStatus();
	}

	private void move(int x, int y, String player) {
		board[x][y] = new Index(x, y,player);
	}

	private void play() {
		while(isRunning()){
			System.out.println("User's Turn ! Please input the x and y:");
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			move(x, y, USER);
			printBoard();
			
			if(!isRunning())
				break;
			
			System.out.println("Computer's Turn !");
			callMinimax(0, 	COMPUTER);
			Index move = getMaxMove();
			move(move.getRow(), move.getColumn(), COMPUTER);
			printBoard();
		}
	}

	private Index getMaxMove() {
		int max = -999;
		int best = -999;
		
		for(int i=0; i<minimaxValues.size(); i++){
			if(max < minimaxValues.get(i).getCost()){
				max = minimaxValues.get(i).getCost();
				best = i;
			}
		}
		
		return minimaxValues.get(best);
	}

	private void printBoard() {
		System.out.println();
		
		for(int i=0; i<BOARD_SIZE; i++){
			for(int j=0; j<BOARD_SIZE; j++){
				System.out.print(board[i][j] + " ");
			}
			
			System.out.println();
		}
	}

	private boolean isRunning() {
		if(isWinning(COMPUTER)) return false;
		if(isWinning(USER)) return false;
		
		if(getRemainingCells().isEmpty()) return false;
	
		return true;
	}

	private boolean isWinning(String player) {
		if(board[0][0].getValue().equals(player) && board[1][1].getValue().equals(player) && board[2][2].getValue().equals(player))
			return true;
		
		if(board[0][2].getValue().equals(player) && board[1][1].getValue().equals(player) && board[2][0].getValue().equals(player))
			return true;
		
		for(int i=0; i<BOARD_SIZE; i++){
			if(board[i][0].getValue().equals(player) && board[i][1].getValue().equals(player) && board[i][2].getValue().equals(player))
				return true;
			
			if(board[0][i].getValue().equals(player) && board[1][i].getValue().equals(player) && board[2][i].getValue().equals(player))
				return true;
		}
		
		return false;
	}

	private List<Index> getRemainingCells() {
		remainingCells = new ArrayList<>();
		
		for(int i=0; i<BOARD_SIZE; i++)
			for(int j=0; j<BOARD_SIZE; j++)
				if(board[i][j].getValue().equals(EMPTY))
					remainingCells.add(new Index(i,j,EMPTY));
		
		return remainingCells;
	}

	private void getStatus() {
		if(isWinning(COMPUTER))
			System.out.println("Computer has won !");
		else if(isWinning(USER))
			System.out.println("User has won !");
		else
			System.out.println("DRAW !!");
	}
	
    public int returnMin(List<Integer> list) {
    	
        int min = 9999;
        int index = 9999;
        
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(List<Integer> list) {
        int max = -9999;
        int index = -9999;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        
        return list.get(index);
    }
    
	private void callMinimax(int depth, String player) {
		minimaxValues.clear();
        minimax(depth, player);		
	}
    
	private int minimax(int depth, String player){
        if (isWinning(COMPUTER)) return +10;
        if (isWinning(USER)) return -10;

        List<Index> remainingCells = getRemainingCells();
        
        if (remainingCells.isEmpty()) return 0; 

        List<Integer> scores = new ArrayList<>(); 

        for (int i = 0; i < remainingCells.size(); i++) {
            
        	Index target = remainingCells.get(i);  

            if (player == COMPUTER) { 
                move(target.getRow(), target.getColumn(), COMPUTER); 
                int currentScore = minimax(depth + 1, USER);
                scores.add(currentScore);

                if (depth == 0) {
                	target.setCost(currentScore);
                	minimaxValues.add(target);
                }    	
                
            } else if (player == USER) {
                move(target.getRow(), target.getColumn(), USER); 
                scores.add(minimax(depth + 1, COMPUTER));
            }
            
            board[target.getRow()][target.getColumn()] = new Index(target.getRow(), target.getColumn(),EMPTY); 
        }
        
        if( player == COMPUTER ){
        	return returnMax(scores);
        }
        
        return returnMin(scores);
	}
}
