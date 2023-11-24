import React from "react";
import { Box, Card, Typography } from "@mui/material";
import { MyButton } from "../../components/MyButton";
import EditIcon from "@mui/icons-material/Edit";

function MyAcc(props) {
  const user = JSON.parse(localStorage.getItem("currentUser"))?.user;

  return (
    <Card
      sx={{
        mt: 2,
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        pb: 3,
      }}
    >
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 3,
        }}
      >
        <Typography variant="h4" color={"#1e81b0"} align="center">
          TÀI KHOẢN CỦA TÔI
        </Typography>

        <Box>
          <Typography>{`Avatar: ${user?.avatar}`}</Typography>
          <Typography>{`Username: ${user?.username}`}</Typography>
          <Typography>{`Tên đầy đủ: ${user?.fullName}`}</Typography>
          <Typography>{`Email: ${user?.email}`}</Typography>
          <Typography>{`Số điện thoại: ${user?.phoneNumber}`}</Typography>
          <Typography>{`Địa chỉ: ${user?.address}`}</Typography>
          <Typography>{`Giới tính: ${user?.gender}`}</Typography>
          <Typography>{`Chức vụ: ${user?.role}`}</Typography>
          <Typography>{`Ngày tạo: ${user?.createdDate}`}</Typography>
        </Box>
        <MyButton startIcon={<EditIcon />}>Chỉnh sửa thông tin</MyButton>
      </Box>
    </Card>
  );
}

export default MyAcc;
