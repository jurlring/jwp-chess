package chess.entity;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardEntity {

    private final String name;
    private final String positionColumnValue;
    private final int positionRowValue;
    private final String pieceName;
    private final String pieceTeamValue;

    public BoardEntity(final String name,
                       final String positionColumnValue,
                       final int positionRowValue,
                       final String pieceName,
                       final String pieceTeamValue) {
        this.name = name;
        this.positionColumnValue = positionColumnValue;
        this.positionRowValue = positionRowValue;
        this.pieceName = pieceName;
        this.pieceTeamValue = pieceTeamValue;
    }

    public BoardEntity(final String name,
                       final char columnValue,
                       final int rowValue,
                       final Map<Position, Piece> currentBoard) {
        this.name = name;
        positionColumnValue = String.valueOf(columnValue);
        positionRowValue = rowValue;
        Piece piece = currentBoard.get(Position.of(columnValue, positionRowValue));
        pieceName = piece.getName();
        pieceTeamValue = piece.getTeam().getValue();
    }

    public static List<BoardEntity> generateBoardEntities(final String name, final Map<Position, Piece> board) {
        List<BoardEntity> boardEntities = new ArrayList<>();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            BoardEntity boardEntity = new BoardEntity(name,
                    String.valueOf(position.getColumn().getValue()),
                    position.getRow().getValue(),
                    piece.getName(),
                    piece.getTeam().getValue()
            );
            boardEntities.add(boardEntity);
        }
        return boardEntities;
    }

    public String getName() {
        return name;
    }

    public String getPositionColumnValue() {
        return positionColumnValue;
    }

    public int getPositionRowValue() {
        return positionRowValue;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPieceTeamValue() {
        return pieceTeamValue;
    }
}
