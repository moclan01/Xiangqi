package AI;

public class AlphaBetaPrunning extends AMoveAlgorithm {

    public AlphaBetaPrunning() {
        super();
    }

    public AlphaBetaPrunning(Node node) {
        super(node);
    }

    @Override
    public int move() {
        return alphaBetaPrunning(node, DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, MIN);
    }

    private int alphaBetaPrunning(Node node, int depth, int alpha, int beta, boolean isMax) {
        if(depth == 0) {
            return Heuristic.compute(node.getBoard());
        } 
        else {
            if(isMax) {
                for (Node neighbor : node.getNeighbors()) {
                    alpha = Math.max(alpha, alphaBetaPrunning(neighbor, depth - 1, alpha, beta, MIN));
                    neighbor.setHeuristicValue(alpha);
                    if(alpha >= beta) break;
                }
                return alpha;
            }
            else {
                for (Node neighbor : node.getNeighbors()) {
                    beta = Math.min(beta, alphaBetaPrunning(neighbor, depth - 1, alpha, beta, MAX));
                    neighbor.setHeuristicValue(beta);
                    if(alpha >= beta) break;
                }
            }
            return beta;
        }
    }
    
}
