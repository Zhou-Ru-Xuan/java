package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    public RoomType(Long roomId) {
        this.roomId = roomId;
    }

    Long roomId;
    String roomName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(roomId, roomType.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
