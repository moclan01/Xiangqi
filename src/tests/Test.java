package tests;

import AI.Computer;
import constants.Constants;
import models.Board;
import models.Piece;
import models.Point;

public class Test {

    static Board board = Board.getInstance();

    public static void testCloneBoard() throws CloneNotSupportedException {
        Board newBoard = (Board) board.clone();

        System.out.println("----------------- Board Address Location ----------------------");
        System.out.println(board);
        System.out.println(newBoard);
        System.out.println("------------------- List pieces Address Location --------------");
        System.out.println(Integer.toHexString(board.getPieces().hashCode()));
        System.out.println(Integer.toHexString(newBoard.getPieces().hashCode()));
        System.out.println("----------------- Hashmap piece Address Location --------------");
        System.out.println(board.getPieceMap());
        System.out.println(newBoard.getPieceMap());
        System.out.println("----------------- Piece's Point Address Location --------------");
        System.out.println(Integer.toHexString(board.getPieceMap().get(Constants.RED_GENERAL).getPoint().hashCode()));
        System.out.println(Integer.toHexString(newBoard.getPieceMap().get(Constants.RED_GENERAL).getPoint().hashCode()));
    }

    public static void testAdvisor() {
        Piece redAdvisor = board.getPieceByPoint(new Point(9, 3));
        System.out.println(redAdvisor.getAllPossibleMoves());
    }

    public static void testChariot() {
        Piece chariot = board.getPieces().get(16);
        System.out.println(chariot.getAllPossibleMoves());

    }

    public static void testCannon() {
        Piece cannon = board.getPieces().get(25);
        System.out.println(cannon.getAllPossibleMoves());
    }

    public static void testGeneral() {
        Piece general = board.getPieceByPoint(new Point(9, 4));
        System.out.println(board.isInArch(general));
    }

    public static void testElephant(){
        Piece elephant = board.getPieces().get(4);
        System.out.println(elephant.getAllPossibleMoves());
    }

    public static void testIsCheckmateAfterMove() {
        Piece redCannon = board.getPieceByPoint(new Point(7, 7));
        redCannon.setPoint(new Point(3, 8));
    }

    public static void testFutureBoards() {
        Computer computer = new Computer();
        System.out.println(computer.getFutureBoards().size());
    }

    public static void main(String[] args) throws Exception {
        testFutureBoards();
    }
}
