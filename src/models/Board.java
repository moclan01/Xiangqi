package models;

import java.util.ArrayList;
import java.util.List;

public class Board {


    public static final int BOARD_ROWS = 10;
    public static final int BOARD_COLS = 9;

    public int[][] board;
    public List<Piece> pieces;
    public static Board instance = null;

    private Board() {
        board = new int[BOARD_ROWS][BOARD_COLS];
        pieces = new ArrayList<Piece>();

        initBlackPiecePosition();
        initRedPiecePosition();
        initBoard();
    }

    public static Board getInstance() {
        if(instance == null)
            instance = new Board();
        return instance;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void initBlackPiecePosition() {

        Piece chariot1 = new Chariot(Color.BLACK);
        Piece chariot2 = new Chariot(Color.BLACK);

        chariot1.setPoint(new Point(0, 0));
        chariot2.setPoint(new Point(0, 8));

        Piece horse1 = new Horse(Color.BLACK);
        Piece horse2 = new Horse(Color.BLACK);

        horse1.setPoint(new Point(0, 1));
        horse2.setPoint(new Point(0, 7));

        Piece elephant1 = new Elephant(Color.BLACK);
        Piece elephant2 = new Elephant(Color.BLACK);

        elephant1.setPoint(new Point(0, 2));
        elephant2.setPoint(new Point(0, 6));

        Piece advisor1 = new Advisor(Color.BLACK);
        Piece advisor2 = new Advisor(Color.BLACK);

        advisor1.setPoint(new Point(0, 3));
        advisor2.setPoint(new Point(0, 5));

        Piece general = new General(Color.BLACK);
        general.setPoint(new Point(0, 4));

        Piece cannon1 = new Canon(Color.BLACK);
        Piece cannon2 = new Canon(Color.BLACK);

        cannon1.setPoint(new Point(2, 1));
        cannon2.setPoint(new Point(2, 7));

        Piece soldier1 = new Soldier(Color.BLACK);
        Piece soldier2 = new Soldier(Color.BLACK);
        Piece soldier3 = new Soldier(Color.BLACK);
        Piece soldier4 = new Soldier(Color.BLACK);
        Piece soldier5 = new Soldier(Color.BLACK);

        soldier1.setPoint(new Point(3, 0));
        soldier2.setPoint(new Point(3, 2));
        soldier3.setPoint(new Point(3, 4));
        soldier4.setPoint(new Point(3, 6));
        soldier5.setPoint(new Point(3, 8));

        pieces.add(chariot1);
        pieces.add(chariot2);
        pieces.add(horse1);
        pieces.add(horse2);
        pieces.add(elephant1);
        pieces.add(elephant2);
        pieces.add(advisor1);
        pieces.add(advisor2);
        pieces.add(general);
        pieces.add(cannon1);
        pieces.add(cannon2);
        pieces.add(soldier1);
        pieces.add(soldier2);
        pieces.add(soldier3);
        pieces.add(soldier4);
        pieces.add(soldier5);
    }

    public void initRedPiecePosition() {
        // Variables are same as initBlack but x value for each Point is 9
        Piece chariot1 = new Chariot(Color.RED);
        Piece chariot2 = new Chariot(Color.RED);

        chariot1.setPoint(new Point(9, 0));
        chariot2.setPoint(new Point(9, 8));

        Piece horse1 = new Horse(Color.RED);
        Piece horse2 = new Horse(Color.RED);

        horse1.setPoint(new Point(9, 1));
        horse2.setPoint(new Point(9, 7));

        Piece elephant1 = new Elephant(Color.RED);
        Piece elephant2 = new Elephant(Color.RED);

        elephant1.setPoint(new Point(9, 2));
        elephant2.setPoint(new Point(9, 6));

        Piece advisor1 = new Advisor(Color.RED);
        Piece advisor2 = new Advisor(Color.RED);

        advisor1.setPoint(new Point(9, 3));
        advisor2.setPoint(new Point(9, 5));

        Piece general = new General(Color.RED);
        general.setPoint(new Point(9, 4));

        Piece cannon1 = new Canon(Color.RED);
        Piece cannon2 = new Canon(Color.RED);

        cannon1.setPoint(new Point(7, 1));
        cannon2.setPoint(new Point(7, 7));

        Piece soldier1 = new Soldier(Color.RED);
        Piece soldier2 = new Soldier(Color.RED);
        Piece soldier3 = new Soldier(Color.RED);
        Piece soldier4 = new Soldier(Color.RED);
        Piece soldier5 = new Soldier(Color.RED);

        soldier1.setPoint(new Point(6, 0));
        soldier2.setPoint(new Point(6, 2));
        soldier3.setPoint(new Point(6, 4));
        soldier4.setPoint(new Point(6, 6));
        soldier5.setPoint(new Point(6, 8));

        pieces.add(chariot1);
        pieces.add(chariot2);
        pieces.add(horse1);
        pieces.add(horse2);
        pieces.add(elephant1);
        pieces.add(elephant2);
        pieces.add(advisor1);
        pieces.add(advisor2);
        pieces.add(general);
        pieces.add(cannon1);
        pieces.add(cannon2);
        pieces.add(soldier1);
        pieces.add(soldier2);
        pieces.add(soldier3);
        pieces.add(soldier4);
        pieces.add(soldier5);

    }

    public void initBoard() {

        for(int i = 0; i < BOARD_ROWS; i++)
            for(int j = 0; j < BOARD_COLS; j++)
                board[i][j] = 0;

        for (Piece piece : pieces) {
            int row = piece.getPoint().getX();
            int col = piece.getPoint().getY();

            board[row][col] = piece.getCode();
        }
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

}
