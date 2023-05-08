import { SimpleGrid, Text } from "@chakra-ui/react";
import InformationCard from "./InformationCard";
import useInfos from "../hooks/useInfos";
import InformationCardSkeleton from "./InformationCardSkeleton";

const InfoGrid = () => {
  const { infos, error, isLoading } = useInfos();
  const skeletons = [1, 2, 3];
  return (
    <>
      {error && <Text>{error}</Text>}

      <SimpleGrid column={1} spacing={10} padding="10px">
        {isLoading &&
          skeletons.map((skeleton) => (
            <InformationCardSkeleton key={skeleton} />
          ))}
        {infos.map((info) => (
          <InformationCard key={info.id} info={info} />
        ))}
      </SimpleGrid>
    </>
  );
};

export default InfoGrid;
