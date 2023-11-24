import * as React from "react";
import AppBar from "@mui/material/AppBar";
import "./Manager.scss";
import { Box, Container, Typography } from "@mui/material";
import { NavLink, Outlet } from "react-router-dom";
import { MyButton } from "../../components/MyButton";

function Manager(props) {
  const list = [
    { name: "Tổng quan", path: "" },
    { name: "Đơn hàng", path: "don-hang" },
    { name: "Sản phẩm", path: "san-pham" },
    { name: "Nhân viên", path: "nhan-vien" },
    { name: "Tin tức", path: "tin-tuc" },
  ];

  return (
    <Container>
      <Box
        display={"flex"}
        justifyContent={"space-evenly"}
        mt={3}
        gap={3}
        alignItems={"start"}
      >
        <Box display={"flex"} flexDirection={"column"} gap={1}>
          {list.map((l) => (
            <NavLink to={l.path}>
              <MyButton>{l.name}</MyButton>
            </NavLink>
          ))}
        </Box>
        <Box
          display={"flex"}
          flexDirection={"column"}
          justifyContent={"center"}
        >
          <Typography variant="h4" color={"#1e81b0"} align="center">
            TRANG QUẢN LÝ
          </Typography>
          <Outlet />
        </Box>
      </Box>
    </Container>
  );
}

export default Manager;
