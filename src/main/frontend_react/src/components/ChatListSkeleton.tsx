import { HStack, ListItem, Skeleton, SkeletonText } from "@chakra-ui/react";

const ChatListSkeleton = () => {
  return (
    <ListItem key="0" paddingY="5px">
      <HStack>
        <Skeleton borderRadius={8} boxSize="50px" />
        <SkeletonText fontSize="lg" />
      </HStack>
    </ListItem>
  );
};

export default ChatListSkeleton;
