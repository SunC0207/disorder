import { HStack, Switch, Text, useColorMode } from "@chakra-ui/react";

const ThemeModeSwitch = () => {
  const { toggleColorMode, colorMode } = useColorMode();

  return (
    <HStack>
      <Switch
        colorScheme="blue"
        isChecked={colorMode === "dark"}
        onChange={toggleColorMode}
      />
      <Text>黑夜模式</Text>
    </HStack>
  );
};

export default ThemeModeSwitch;
