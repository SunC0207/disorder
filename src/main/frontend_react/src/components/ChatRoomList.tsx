import {
  Button,
  HStack,
  Image,
  List,
  ListItem,
  Spinner,
  Text,
} from "@chakra-ui/react";
import useChatRooms from "../hooks/useChatRooms";
import ChatListSkeleton from "./ChatListSkeleton";

const ChatRoomList = () => {
  const { chatRooms, error, isLoading } = useChatRooms();
  const skeletons = [1, 2, 3];
  return (
    <>
      {error && <Text>{error}</Text>}
      <List>
        <ListItem key="0" paddingY="5px">
          <HStack>
            <Image
              src="https://cdn4.iconfinder.com/data/icons/seo-and-digital-marketing-1-2/128/2-512.png"
              borderRadius={8}
              boxSize="50px"
            />
            <Text fontSize="lg">News</Text>
          </HStack>
        </ListItem>

        {isLoading &&
          skeletons.map((skeleton) => <ChatListSkeleton key={skeleton} />)}

        {chatRooms.map((chatroom) => (
          <ListItem key={chatroom.id} paddingY="5px">
            <HStack>
              <Image src={chatroom.image} borderRadius={8} boxSize="50px" />
              <Text fontSize="lg">{chatroom.name}</Text>
            </HStack>
          </ListItem>
        ))}
      </List>
    </>
  );
};

export default ChatRoomList;
