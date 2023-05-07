import { Badge } from "@chakra-ui/react";

interface Props {
  date: string;
}

const InfoDate = ({ date }: Props) => {
  return (
    <Badge fontSize="14px" paddingX={2} borderRadius={1}>
      {date}
    </Badge>
  );
};

export default InfoDate;
