package models;

import java.util.ArrayList;
import java.util.List;

public class Elephant extends Piece {

    public Elephant(Color color) {
        super(color);
        this.code = 4;
        if (color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "V";
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        Board board = Board.getInstance();

        Point elephantLeftTop;
        Point elephantRightTop;
        Point elephantLeftBottom;
        Point elephantRightBottom;

        if (this.getColor().equals(Color.RED)) {
            elephantLeftTop = new Point(x - 2, y - 2);
            elephantRightTop = new Point(x - 2, y + 2);
            elephantLeftBottom = new Point(x + 2, y - 2);
            elephantRightBottom = new Point(x + 2, y + 2);
        } else {
            elephantLeftTop = new Point(x + 2, y - 2);
            elephantRightTop = new Point(x + 2, y + 2);
            elephantLeftBottom = new Point(x - 2, y - 2);
            elephantRightBottom = new Point(x - 2, y + 2);
        }

            // check left top
            if (((x - 2) >= (board.BOARD_ROWS / 2) || (x+2) <= board.BOARD_ROWS / 2)
                    && (y - 2) >= 0) {
                // elephantLeftTop = new Point(x - 2, y - 2);
                if (board.isEmptyPosition(elephantLeftTop) || board.isOpponentPiece(this, elephantLeftTop)) {
                    result.add(elephantLeftTop);
                }
            }

            // check right top
            if (((x - 2) >= (board.BOARD_ROWS / 2) || (x+2) <= board.BOARD_ROWS / 2)
                    && (y + 2) < board.BOARD_COLS) {
                // elephantRightTop = new Point(x - 2, y + 2);
                if (board.isEmptyPosition(elephantRightTop) || board.isOpponentPiece(this, elephantRightTop)) {
                    result.add(elephantRightTop);
                }
            }

            // check bottom left
            if (((x + 2) < board.BOARD_ROWS || (x-2) >=0) 
                    && (y - 2) >= 0) {
                // elephantLeftBottom = new Point(x + 2, y - 2);
                if (board.isEmptyPosition(elephantLeftBottom) || board.isOpponentPiece(this, elephantLeftBottom)) {
                    result.add(elephantLeftBottom);
                }
            }

            // check bottom right
            if (((x + 2) < board.BOARD_ROWS || (x-2) >= 0) 
                    && (y + 2) < board.BOARD_COLS) {
                // elephantRightBottom = new Point(x + 2, y + 2);
                if (board.isEmptyPosition(elephantRightBottom)
                        || board.isOpponentPiece(this, elephantRightBottom)) {
                    result.add(elephantRightBottom);
                }
            }

        return result;
    }

}
