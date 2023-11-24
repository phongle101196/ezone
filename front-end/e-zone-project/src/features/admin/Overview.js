import { Box, Paper, Typography } from "@mui/material";
import React from "react";

function Overview(props) {
  return (
    <Paper>
      <Box mx={1}>
        <Typography variant="h6" color={"#1e81b0"}>
          TỔNG QUAN
        </Typography>
      </Box>
    </Paper>
  );
}

export default Overview;
