import { HStack, Image, Text } from "@chakra-ui/react";
import logo from "../assets/IMG_4848.gif";
import ThemeModeSwitch from "./ThemeModeSwitch";

const NavBar = () => {
  return (
    <HStack justifyContent="space-between" padding="10px">
      <Image src={logo} boxSize="60px" />
      <ThemeModeSwitch />
    </HStack>
  );
};

export default NavBar;
