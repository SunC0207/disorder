import { useEffect, useState } from "react";
import { CanceledError } from "axios";
import apiInfomation from "../Services/api-infomation";

export interface Info {
  id: number;
  subject: string;
  content: string;
  image: string;
  date: string;
}

const useInfos = () => {
  const [infos, setInfos] = useState<Info[]>([]);
  const [error, setError] = useState("");
  const [isLoading, setLoading] = useState(false);

  useEffect(() => {
    const controller = new AbortController();
    setLoading(true);
    apiInfomation
      .get("/get-all", {
        signal: controller.signal,
      })
      .then((response) => {
        setInfos(response.data);
        setLoading(false);
      })
      .catch((error) => {
        if (error instanceof CanceledError) return;
        setError(error.message);
        setLoading(false);
      });
    return () => controller.abort();
  }, []);

  return { infos, error, isLoading };
};

export default useInfos;
