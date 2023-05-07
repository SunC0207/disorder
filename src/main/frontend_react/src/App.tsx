import { Grid, GridItem, Show } from "@chakra-ui/react";
import NavBar from "./components/NavBar";
import ChatRoomGrid from "./components/ChatRoomGrid";

function App() {
  return (
    <Grid
      templateAreas={{
        base: `"nav" "main"`,
        lg: `"nav nav" "aside main"`,
      }}
    >
      <GridItem area="nav">
        <NavBar />
      </GridItem>
      <Show above="lg">
        <GridItem area="aside">
          <ChatRoomGrid />
        </GridItem>
      </Show>
      <GridItem area="main"></GridItem>
    </Grid>
  );
}

export default App;
