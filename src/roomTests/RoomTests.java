package roomTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import triviaMaze.Room;

public class RoomTests {
	
	private final Room room = new Room(null);
	
	@Test
	void NullRoomtest() {
		Room nullRoom = Room.nullRoom();
		assertEquals(nullRoom.getQuestion(), null);
		assertEquals(nullRoom.getBot(), "# # #");
		assertEquals(nullRoom.getMid(), "# # #");
		assertEquals(nullRoom.getTop(), "# # #");
		assertEquals(nullRoom.getStatus(), -1);
	}
	
	@Test
	void getTopTest() {
		assertEquals(room.getTop(), "- - -");
	}
	
	@Test
	void getMidTest() {
		assertEquals(room.getMid(), "|   |");
	}
	
	@Test
	void getBotTest() {
		assertEquals(room.getBot(), "- - -");
	}
	
	@Test
	void setStatusTest() {
		room.setStatus(1);
		assertEquals(room.getStatus(),1);
	}
	
	@Test
	void setStatus_ThrowsIllegalArgumentExceptionTest() {
		 IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            room.setStatus(2);
		 });
		 assertEquals("Status is invalid", exception.getMessage());
	}
	
	
}
