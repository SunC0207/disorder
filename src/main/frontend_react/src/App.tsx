import { Grid, GridItem, Show } from "@chakra-ui/react";
import NavBar from "./components/NavBar";
import ChatRoomList from "./components/ChatRoomList";
import InfoGrid from "./components/InfoGrid";

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
          <ChatRoomList />
        </GridItem>
      </Show>
      <GridItem area="main">
        <InfoGrid />
      </GridItem>
    </Grid>
  );
}

export default App;
