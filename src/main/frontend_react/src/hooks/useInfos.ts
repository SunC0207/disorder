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

interface FetchInfosResponse {
  result: Info[];
}

const useInfos = () => {
  const [infos, setInfos] = useState<Info[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    apiInfomation
      .get<FetchInfosResponse>("/get-all", {
        signal: controller.signal,
      })
      .then((reponse) => setInfos(reponse.data.result))
      .catch((error) => {
        if (error instanceof CanceledError) return;
        setError(error.message);
      });
    return () => controller.abort();
  });

  return { infos, error };
};

export default useInfos;
