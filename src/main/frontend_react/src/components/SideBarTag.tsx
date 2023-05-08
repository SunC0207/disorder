import {
  HStack,
  Tab,
  TabList,
  TabPanel,
  TabPanels,
  Tabs,
  Text,
} from "@chakra-ui/react";
import ChatRoomList from "./ChatRoomList";
import { ChatIcon, LockIcon } from "@chakra-ui/icons";

const SideBarTag = () => {
  return (
    <Tabs variant="enclosed">
      <TabList>
        <Tab>
          <HStack>
            <ChatIcon />
            <Text>公共</Text>
          </HStack>
        </Tab>
        <Tab>
          <HStack>
            <LockIcon />
            <Text>私人</Text>
          </HStack>
        </Tab>
      </TabList>
      <TabPanels>
        <TabPanel>
          <ChatRoomList />
        </TabPanel>
        <TabPanel></TabPanel>
      </TabPanels>
    </Tabs>
  );
};

export default SideBarTag;
