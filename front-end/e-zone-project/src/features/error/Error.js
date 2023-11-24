import { Box, Typography } from "@mui/material";
import { red } from "@mui/material/colors";
import React from "react";
import { NavLink } from "react-router-dom";

function Error(props) {
  return (
    <Box>
      <Typography
        variant="h5"
        color={"#e28743"}
        gutterBottom
        align="center"
        mt={5}
      >
        Đã có lỗi xảy ra!!
      </Typography>
      <NavLink to={"/"}>
        <Typography
          variant="h6"
          color={"#1e81b0"}
          gutterBottom
          align="center"
          mt={5}
        >
          {`->Trở về trang chủ <-`}
        </Typography>
      </NavLink>
    </Box>
  );
}

export default Error;
