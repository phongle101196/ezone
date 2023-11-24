import { Box, Grid, IconButton, Paper, Typography } from "@mui/material";
import React from "react";
import FacebookIcon from "@mui/icons-material/Facebook";
import YouTubeIcon from "@mui/icons-material/YouTube";
import TwitterIcon from "@mui/icons-material/Twitter";

function FooterWraper(props) {
  return (
    <Box bgcolor={"#76b5c5"} display={"flex"} flexDirection={"column"}>
      <Typography variant="h6" align="center" color={"white"}>
        MOCK 2307 - NHÓM 2
      </Typography>

      <Box display={"flex"} justifyContent={"center"}>
        <IconButton>
          <FacebookIcon />
        </IconButton>
        <IconButton>
          <YouTubeIcon />
        </IconButton>
        <IconButton>
          <TwitterIcon />
        </IconButton>
      </Box>
    </Box>
  );
}

export default FooterWraper;
