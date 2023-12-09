package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomGood {
    Long realRoomId;
    String realRoomName;
    List<LogicRoom> logicRoomRelations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomGood roomGood = (RoomGood) o;
        return Objects.equals(realRoomId, roomGood.realRoomId) && Objects.equals(logicRoomRelations, roomGood.logicRoomRelations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realRoomId, logicRoomRelations);
    }
}
