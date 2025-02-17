package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import AI.AlphaBetaPrunning;
import AI.Computer;
import AI.Minimax;
import Constants.Constants;
import enums.EColor;
import enums.EPlayer;
import models.Board;
import models.Piece;
import models.Point;
import utils.Benchmark;
import views.BoardComponent;

public class BoardComponentController extends MouseAdapter {

    private BoardComponent boardComponent;
    private boolean isSelected;
    private EColor turn;
    private EPlayer player;

    public BoardComponentController(BoardComponent boardComponent) {
        this.boardComponent = boardComponent;
        this.isSelected = false;
        this.turn = EColor.RED;
        this.player = EPlayer.COMPUTER;
    }

    private Point calculatePoint(MouseEvent e) {
        // In UI, X and Y will be swapped compared to X and Y in the model, so using new
        // Point(y, x)
        int x = (int) Math.round((e.getPoint().getX() - BoardComponent.FIRST_POINT_X) / BoardComponent.CELL_COL);
        int y = (int) Math.round((e.getPoint().getY() - BoardComponent.FIRST_POINT_Y) / BoardComponent.CELL_ROW);
        Point point = new Point(y, x);
        return point;
    }

    private boolean isValidTurn(Piece piece) {
        return piece.getColor().equals(turn);
    }

    private void playerVersusHandler(MouseEvent e) {
        Board board = Board.getInstance();

        if (!isSelected) {
            Point point = calculatePoint(e);
            if (!board.isEmptyPosition(point)) {
                Piece piece = board.getPieceByPoint(point);
                if (isValidTurn(piece)) {
                    boardComponent.setSelectedPiece(piece);
                    isSelected = true;
                }
            }

        } else {
            Point point = calculatePoint(e);
            Piece piece = boardComponent.getSelectedPiece();

            if (!point.equals(piece.getPoint())) {
                boolean moved = piece.move(board, point);
                if (moved) {
                    changeTurn();
                    updateComponent();
                    checkAlert();
                } else {
                    Piece otherPiece = board.getPieceByPoint(point);
                    if (otherPiece != null && isValidTurn(otherPiece)) {
                        boardComponent.setSelectedPiece(otherPiece);
                        repaintComponent();
                        return;
                    }
                }
            }

            boardComponent.setSelectedPiece(null);
            isSelected = false;
        }
        repaintComponent();
    }

    private void changeTurn() {
        turn = turn.equals(EColor.RED) ? EColor.BLACK : EColor.RED;
    }

    private void computerVersusHandler(MouseEvent e) {
        Board board = Board.getInstance();

        if (!isSelected) {
            Point point = calculatePoint(e);
            if (!board.isEmptyPosition(point)) {
                Piece piece = board.getPieceByPoint(point);
                if (isValidTurn(piece)) {
                    boardComponent.setSelectedPiece(piece);
                    isSelected = true;
                }
            }

        } else {
            Point point = calculatePoint(e);
            Piece piece = boardComponent.getSelectedPiece();

            if (!point.equals(piece.getPoint())) {
                boolean moved = piece.move(board, point);
                if (moved) {

                    changeTurn();
                    updateComponent();
                    repaintComponent();
                    checkAlert();

                    Computer computer = new Computer(board);
                    computer.setMoveAlgorithm(new Minimax());

                    Benchmark.startBenchmark();
                    computer.move();
                    boardComponent.setBoard(Board.getInstance());
                    changeTurn();
                    Benchmark.endBenchmark();

                    System.out.println(Benchmark.getBenchmarkResult());

                    updateComponent();
                    checkAlert();
                } else {
                    Piece otherPiece = board.getPieceByPoint(point);
                    if (otherPiece != null && isValidTurn(otherPiece)) {
                        boardComponent.setSelectedPiece(otherPiece);
                        repaintComponent();
                        return;
                    }
                }
            }

            boardComponent.setSelectedPiece(null);
            isSelected = false;
        }
        repaintComponent();
    }

    public void checkAlert() {
        Board board = Board.getInstance();
        if (board.isOver(turn)) {
            // because turn was swapped in line 60 so the value of victory is swapped to
            String victory = turn.equals(EColor.RED) ? "Đen" : "Đỏ";
            boardComponent.notifyMessage(victory + " Thắng ");
        } else if (board.isCheckmate(turn))
            boardComponent.notifyMessage(Constants.CHECKMATE_MESSAGE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.player.equals(EPlayer.PLAYER))
            playerVersusHandler(e);
        else
            computerVersusHandler(e);
    }

    private void updateComponent() {
        boardComponent.setSelectedPiece(null);
        boardComponent.revalidate();
        boardComponent.repaint();
    }

    private void repaintComponent() {
        boardComponent.revalidate();
        boardComponent.repaint();
    }

}
