import { SimpleGrid, Text } from "@chakra-ui/react";
import InformationCard from "./InformationCard";
import useInfos from "../hooks/useInfos";

const InfoGrid = () => {
  const { infos, error } = useInfos();
  return (
    <>
      {error && <Text>{error}</Text>}

      <SimpleGrid column={1} spacing={10} padding="10px">
        {infos.map((info) => (
          <InformationCard key={info.id} info={info} />
        ))}
      </SimpleGrid>
    </>
  );
};

export default InfoGrid;
