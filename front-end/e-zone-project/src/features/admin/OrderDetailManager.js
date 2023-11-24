import {
  Box,
  CardMedia,
  FormControl,
  InputLabel,
  MenuItem,
  Paper,
  Select,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { NavLink, useLocation, useNavigate } from "react-router-dom";
import DocumentScannerIcon from "@mui/icons-material/DocumentScanner";
import PermContactCalendarIcon from "@mui/icons-material/PermContactCalendar";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import httpClient from "../../api/httpClient";
import { MyButton } from "../../components/MyButton";
import {
  openSnackBarRequest,
  setSnackBarMsg,
} from "../../redux/request/snackBarRequest";
import { useDispatch } from "react-redux";

function OrderDetailManager(props) {
  const order = useLocation().state.order;
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  const [wardInfo, setWardInfo] = useState("");
  const generateWardInfo = async () => {
    const res = await httpClient.get(`vn_units/ward/${order?.wardCode}`);

    setWardInfo(res.data);
  };

  const [status, setStatus] = useState("");

  const handleStatus = (status) => {
    setStatus(status);
  };

  const handleOrderStatus = async () => {
    if (status != "") {
      try {
        const form = {
          orderId: order.id,
          status: status,
        };
        await httpClient.put("orders/update", form);
        openSnackBarRequest(
          setSnackBarMsg("Cập nhật thành công", "success"),
          dispatch
        );
        navigate("/manager/don-hang");
      } catch (error) {
        openSnackBarRequest(setSnackBarMsg("Đã xảy ra lỗi", "error"), dispatch);
      }
    } else {
      openSnackBarRequest(
        setSnackBarMsg("Vui lòng chọn thao tác", "warning"),
        dispatch
      );
    }
  };

  useEffect(() => {
    generateWardInfo();
  }, []);
  return (
    <Box display={"flex"} flexDirection={"column"} gap={3}>
      <Paper>
        <Box mx={1} pb={1}>
          <Box display={"flex"} gap={1} mb={3} justifyContent={"space-between"}>
            <Box display={"flex"} gap={1}>
              <DocumentScannerIcon sx={{ color: "#1e81b0" }} />
              <Typography color={"#1e81b0"}>THÔNG TIN ĐƠN HÀNG</Typography>
            </Box>
            <Box>
              <NavLink to={"/manager/don-hang"}>
                <Box display={"flex"}>
                  <ArrowBackIosNewIcon sx={{ color: "#1e81b0" }} />
                  <Typography color={"#1e81b0"}>Quay lại</Typography>
                </Box>
              </NavLink>
            </Box>
          </Box>
          <Box>
            {order.orderDetails?.map((od) => (
              <Box
                key={od}
                display={"flex"}
                justifyContent="space-between"
                borderBottom={"solid #1e81b0"}
                mb={1}
                pb={1}
                gap={3}
              >
                <Box display={"flex"} gap={3}>
                  <Box>
                    <CardMedia
                      component="img"
                      height="50px"
                      image={od.productInventory?.productImage1}
                      alt={od.productInventory?.productName}
                      sx={{ objectFit: "contain" }}
                    />
                  </Box>
                  <Box>
                    <Typography>{od.productInventory?.productName}</Typography>
                    <Typography>
                      Màu: {od.productInventory?.colorName}
                    </Typography>
                  </Box>
                </Box>

                <Box>
                  <Typography>{`Số lượng: ${od.quantity}`}</Typography>
                </Box>
              </Box>
            ))}
          </Box>
          <Box display={"flex"} flexDirection={"column"} gap={1} px={1} pb={1}>
            <Box display={"flex"} justifyContent={"space-between"}>
              <Typography>Tổng số sản phẩm:</Typography>
              <Typography color={"#2596be"}>
                {`${order.bill?.amount} Sản phẩm`}
              </Typography>
            </Box>

            <Box display={"flex"} justifyContent={"space-between"}>
              <Typography fontWeight={"bold"}>Tổng tiền đơn hàng:</Typography>
              <Typography fontWeight={"bold"} color={"#F30C28"}>
                {VND.format(order.bill?.total)}
              </Typography>
            </Box>
            <Box display={"flex"} justifyContent={"space-between"}>
              <Typography fontWeight={"bold"}>Hình thức thanh toán:</Typography>
              <Typography fontWeight={"bold"} color={"#2596be"}>
                Thanh toán khi nhận hàng
              </Typography>
            </Box>
            <Box display={"flex"} justifyContent={"space-between"}>
              <Typography>Đã tạo lúc:</Typography>
              <Typography color={"#2596be"}>{order.createdDate}</Typography>
            </Box>
          </Box>
        </Box>
      </Paper>

      <Paper>
        <Box mx={1} pb={1}>
          <Box display={"flex"} gap={1} mb={3}>
            <PermContactCalendarIcon sx={{ color: "#1e81b0" }} />
            <Typography color={"#1e81b0"}>THÔNG TIN KHÁCH HÀNG</Typography>
          </Box>
          <Typography>{`Tên người nhận: ${order.userFullName}`}</Typography>
          <Typography>{`Số điện thoại: ${
            order.userPhoneNumber ? order.userPhoneNumber : "Chưa có"
          }`}</Typography>
          <Typography>{`Địa chỉ người nhận: ${order.orderAddress}, ${wardInfo.fullName}, ${wardInfo.districtFullName}, ${wardInfo.districtProvinceFullName}`}</Typography>
        </Box>
      </Paper>

      <Box display={"flex"} gap={3}>
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Thao tác</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            label="Thao tác"
            onChange={(e) => {
              handleStatus(e.target.value);
            }}
          >
            <MenuItem value={"ONSHIPPING"}>Đã gửi hàng</MenuItem>
            <MenuItem value={"FAILED"}>Hủy đơn</MenuItem>
          </Select>
        </FormControl>
        <MyButton fullWidth onClick={handleOrderStatus}>
          <Typography>Cập nhật</Typography>
        </MyButton>
      </Box>
    </Box>
  );
}

export default OrderDetailManager;
