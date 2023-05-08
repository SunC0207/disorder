import { MoonIcon, SunIcon } from "@chakra-ui/icons";
import { HStack, Switch, useColorMode } from "@chakra-ui/react";

const ThemeModeSwitch = () => {
  const { toggleColorMode, colorMode } = useColorMode();

  return (
    <HStack>
      <SunIcon />
      <Switch
        colorScheme="blue"
        isChecked={colorMode === "dark"}
        onChange={toggleColorMode}
      />
      <MoonIcon />
    </HStack>
  );
};

export default ThemeModeSwitch;
