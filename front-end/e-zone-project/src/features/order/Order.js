import React, { useEffect, useState } from "react";
import "./Order.scss";
import { Box, Container, Typography } from "@mui/material";
import { Outlet, useNavigate } from "react-router-dom";
import { MyButton } from "../../components/MyButton";
import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import AddHomeIcon from "@mui/icons-material/AddHome";

function Order(props) {
  const user = JSON.parse(localStorage.getItem("currentUser"))?.user;
  const [gender, setGender] = useState("");
  const generateGender = () => {
    switch (user?.gender) {
      case "MALE":
        return "Anh";
      case "FEMALE":
        return "Chị";
      default:
        return "";
    }
  };
  const navigate = useNavigate();

  useEffect(() => {
    setGender(generateGender());
  }, []);
  return (
    <Container>
      <Box
        display={"flex"}
        flexDirection={{ xs: "column", sm: "column", md: "row" }}
        justifyContent={"center"}
        alignItems={{ xs: "center", sm: "center", md: "start" }}
        gap={3}
        mt={3}
      >
        <Box
          display={"flex"}
          // flexDirection={{ sm: "row", md: "column" }}
          flexDirection={"column"}
          justifyContent={"center"}
          gap={1}
        >
          <Box display={"flex"} gap={1} justifyContent={"center"}>
            <Typography variant="h6" fontWeight={"lighter"} color={"#1e81b0"}>
              {gender}
            </Typography>
            <Typography variant="h6" color={"#1e81b0"}>
              {user.fullName}
            </Typography>
          </Box>
          <Box
            display={"flex"}
            flexDirection={{ xs: "row", sm: "row", md: "column" }}
            gap={1}
          >
            <MyButton
              onClick={() => {
                navigate("");
              }}
              startIcon={<ShoppingBagIcon />}
              sx={{
                display: "flex",
                justifyContent: "start",
                alignItems: "center",
              }}
            >
              Đơn hàng đã mua
            </MyButton>
            <MyButton
              onClick={() => {
                navigate("myOrderAddress");
              }}
              startIcon={<AddHomeIcon />}
              sx={{
                display: "flex",
                justifyContent: "start",
                alignItems: "center",
              }}
            >
              Sổ địa chỉ
            </MyButton>
          </Box>
        </Box>

        <Outlet />
      </Box>
    </Container>
  );
}

export default Order;
