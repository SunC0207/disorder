import {
  Card,
  CardBody,
  CardHeader,
  HStack,
  Heading,
  Image,
  Text,
} from "@chakra-ui/react";
import { Info } from "../hooks/useInfos";
import InfoDate from "./InfoDate";

interface Props {
  info: Info;
}

const InformationCard = ({ info }: Props) => {
  return (
    <Card borderRadius={10} overflow="hidden" padding="10px">
      <Image src={info.image} />
      <CardBody>
        <HStack justifyContent="space-between">
          <Heading>{info.subject}</Heading>
          <InfoDate date={info.date} />
        </HStack>
        <Text>{info.content}</Text>
      </CardBody>
    </Card>
  );
};
export default InformationCard;
