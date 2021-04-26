package chess.service;

import chess.dao.RoomDAO;
import chess.domain.ChessGame;
import chess.domain.Rooms;
import chess.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class RoomService {

    private final RoomDAO roomDAO;
    private final Rooms rooms;

    public RoomService(final RoomDAO roomDAO, final Rooms rooms) {
        this.roomDAO = roomDAO;
        this.rooms = rooms;
    }

    public List<RoomDTO> allRooms() {
        return roomDAO.allRooms();
    }

    public void createRoom(final String name) {
        roomDAO.createRoom(name);
    }

    public void changeStatus(final String roomId) {
        roomDAO.changeStatusEndByRoomId(roomId);
    }

    public List<String> allRoomsId() {
        return roomDAO.allRoomIds();
    }

    public void addRoom(final String roomId, final ChessGame chessGame) {
        rooms.addRoom(roomId, chessGame);
    }

    public ChessGame loadGameByRoomId(final String roomId) {
        return rooms.loadGameByRoomId(roomId);
    }
}