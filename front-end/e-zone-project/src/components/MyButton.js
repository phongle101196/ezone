import { Button, styled } from "@mui/material";
import { purple } from "@mui/material/colors";

export const MyButton = styled(Button)(({ theme }) => ({
  color: theme.palette.getContrastText(purple[500]),
  backgroundColor: "#1e81b0",
  "&:hover": {
    backgroundColor: "#76b5c5",
  },
}));
