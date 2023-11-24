import React from "react";
import MessageIcon from "@mui/icons-material/Message";
import { Box, IconButton } from "@mui/material";
import "./ChatBox.scss";

function ChatBox(props) {
  return (
    <Box m={3}>
      <IconButton className="chat-box">
        <MessageIcon variant="outline" sx={{ fontSize: "35px" }} />
      </IconButton>
    </Box>
  );
}

export default ChatBox;
