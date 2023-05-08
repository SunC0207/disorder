import { useEffect, useState } from "react";
import { CanceledError } from "axios";
import apiChatRoom from "../Services/api-chatRoom";

interface ChatRoom {
  id: number;
  name: string;
}

const useChatRooms = () => {
  const [chatRooms, setChatRooms] = useState<ChatRoom[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    apiChatRoom
      .get("/get-all", {
        signal: controller.signal,
      })
      .then((response) => setChatRooms(response.data))
      .catch((error) => {
        if (error instanceof CanceledError) return;
        setError(error.message);
      });
    return () => controller.abort();
  }, []);

  return { chatRooms, error };
};

export default useChatRooms;
