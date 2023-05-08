import { useEffect, useState } from "react";
import { CanceledError } from "axios";
import apiChatRoom from "../Services/api-chatRoom";

interface ChatRoom {
  id: number;
  name: string;
  image: string;
}

const useChatRooms = () => {
  const [chatRooms, setChatRooms] = useState<ChatRoom[]>([]);
  const [error, setError] = useState("");
  const [isLoading, setLoading] = useState(false);

  useEffect(() => {
    const controller = new AbortController();
    setLoading(true);
    apiChatRoom
      .get("/get-all", {
        signal: controller.signal,
      })
      .then((response) => {
        setChatRooms(response.data);
        setLoading(false);
      })
      .catch((error) => {
        if (error instanceof CanceledError) return;
        setError(error.message);
        setLoading(false);
      });
    return () => controller.abort();
  }, []);

  return { chatRooms, error, isLoading };
};

export default useChatRooms;
