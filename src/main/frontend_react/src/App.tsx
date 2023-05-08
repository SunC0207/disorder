import { Box, Container, Grid, GridItem, Show } from "@chakra-ui/react";
import NavBar from "./components/NavBar";
import ChatRoomList from "./components/ChatRoomList";
import InfoGrid from "./components/InfoGrid";
import SideBarTag from "./components/SideBarTag";

function App() {
  return (
    <Grid
      templateAreas={{
        base: `"nav" "main"`,
        lg: `"nav nav" "aside main"`,
      }}
      templateColumns={{
        base: "1fr",
        lg: "250px 1fr",
      }}
    >
      <GridItem area="nav">
        <NavBar />
      </GridItem>
      <Show above="lg">
        <GridItem area="aside" paddingX={5}>
          <SideBarTag />
        </GridItem>
      </Show>
      <Box h="500px">
        <GridItem area="main">
          <InfoGrid />
        </GridItem>
      </Box>
    </Grid>
  );
}

export default App;
