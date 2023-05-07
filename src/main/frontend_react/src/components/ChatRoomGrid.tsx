import { Text } from "@chakra-ui/react";
import useChatRooms from "../hooks/useChatRooms";

const ChatRoomGrid = () => {
  const { chatRooms, error } = useChatRooms();

  return (
    <>
      {error && <Text>{error}</Text>}
      <ul>
        {chatRooms.map((chatroom) => (
          <li key={chatroom.id}>{chatroom.name}</li>
        ))}
      </ul>
    </>
  );
};

export default ChatRoomGrid;
