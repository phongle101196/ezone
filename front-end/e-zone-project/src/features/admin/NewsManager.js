import { Box, Paper, Typography } from "@mui/material";
import React from "react";

function NewsManager(props) {
  return (
    <Paper>
      <Box mx={1}>
        <Typography variant="h6" color={"#1e81b0"}>
          TIN TỨC
        </Typography>
      </Box>
    </Paper>
  );
}

export default NewsManager;
