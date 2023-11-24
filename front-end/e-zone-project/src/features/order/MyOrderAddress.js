import { Box, Paper, Typography } from "@mui/material";
import React from "react";

function MyOrderAddress(props) {
  return (
    <Paper>
      <Box
        display={"flex"}
        flexDirection={"column"}
        justifyContent={"start"}
        gap={1}
        px={1}
        pb={1}
      >
        <Typography variant="h6" color={"#1e81b0"}>
          Danh sách địa chỉ nhận hàng
        </Typography>
      </Box>
    </Paper>
  );
}

export default MyOrderAddress;
