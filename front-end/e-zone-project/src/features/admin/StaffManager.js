import { Box, Paper, Typography } from "@mui/material";
import React from "react";

function StaffManager(props) {
  return (
    <Paper>
      <Box mx={1}>
        <Typography variant="h6" color={"#1e81b0"}>
          NHÂN VIÊN
        </Typography>
      </Box>
    </Paper>
  );
}

export default StaffManager;
