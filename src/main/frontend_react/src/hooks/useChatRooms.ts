import { useEffect, useState } from "react";
import apiClient from "../Services/api-client";
import { CanceledError } from "axios";

interface ChatRoom {
  id: number;
  name: string;
}
interface FetchChatRoomsResponse {
  results: ChatRoom[];
}

const useChatRooms = () => {
  const [chatRooms, setChatRooms] = useState<ChatRoom[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    apiClient
      .get<FetchChatRoomsResponse>("apiclient之後接的網址", {
        signal: controller.signal,
      })
      .then((response) => setChatRooms(response.data.results))
      .catch((error) => {
        if (error instanceof CanceledError) return;
        setError(error.message);
      });
    return () => controller.abort();
  }, []);

  return { chatRooms, error };
};

export default useChatRooms;
