package wooteco.chess.domain.piece;

import wooteco.chess.domain.board.Board;
import wooteco.chess.domain.piece.position.Direction;
import wooteco.chess.domain.piece.position.InvalidPositionException;
import wooteco.chess.domain.piece.position.Position;
import wooteco.chess.domain.piece.team.Team;

public class Bishop extends Piece {
	private static final String SYMBOL = "b";
	private static final double SCORE = 3;

	public Bishop(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected void validateDirection(Direction direction) {
		boolean isWrongDirection = !Direction.isDiagonalDirection(direction);
		if (isWrongDirection) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
		}
	}

	@Override
	protected void validateStepSize(Position sourcePosition, Position targetPosition) {
	}

	@Override
	protected void validateRoute(Direction direction, Position targetPosition, Board board) {
		boolean hasPiece = direction.hasPieceInRoute(this.position, targetPosition, board);
		if (hasPiece) {
			throw new InvalidPositionException(InvalidPositionException.HAS_PIECE_IN_ROUTE);
		}
	}

	@Override
	protected String getRawSymbol() {
		return SYMBOL;
	}

	@Override
	public double getScore() {
		return SCORE;
	}
}