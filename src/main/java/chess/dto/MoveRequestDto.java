package chess.dto;

public class MoveRequestDto {
    private String roomId;
    private String target;
    private String destination;

    public MoveRequestDto(String roomId, String target, String destination) {
        this.roomId = roomId;
        this.target = target;
        this.destination = destination;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}