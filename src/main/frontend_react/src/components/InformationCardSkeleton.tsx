import { Card, CardBody, Skeleton, SkeletonText } from "@chakra-ui/react";

const InformationCardSkeleton = () => {
  return (
    <Card borderRadius={10} overflow="hidden" padding="10px">
      <Skeleton height="200px" />
      <CardBody>
        <SkeletonText />
      </CardBody>
    </Card>
  );
};

export default InformationCardSkeleton;
