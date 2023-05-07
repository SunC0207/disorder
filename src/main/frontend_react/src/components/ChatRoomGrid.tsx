import React, { useEffect, useState } from "react";
import apiClient from "../Services/api-client";
import { Text } from "@chakra-ui/react";

interface ChatRoom {
  id: number;
  name: string;
}
interface FetchChatRoomsResponse {
  results: ChatRoom[];
}

const ChatRoomGrid = () => {
  const [chatRooms, setChatRooms] = useState<ChatRoom[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    apiClient
      .get<FetchChatRoomsResponse>("chatRooms")
      .then((response) => setChatRooms(response.data.results))
      .catch((error) => setError(error.message));
  });

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
