import {
  Card,
  CardBody,
  CardHeader,
  HStack,
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
    <Card borderRadius={10} overflow="hidden">
      <Image src={info.image} />
      <HStack justifyContent="space-between">
        <CardHeader>{info.subject}</CardHeader>
        <InfoDate date={info.date} />
      </HStack>

      <CardBody>
        <Text>{info.content}</Text>
      </CardBody>
    </Card>
  );
};
export default InformationCard;
