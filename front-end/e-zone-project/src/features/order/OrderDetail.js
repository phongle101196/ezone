import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { Box, CardMedia, Paper, Typography } from "@mui/material";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import httpClient from "../../api/httpClient";
import CreditCardIcon from "@mui/icons-material/CreditCard";
import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import FavoriteIcon from "@mui/icons-material/Favorite";

function OrderDetail(props) {
  const order = useLocation().state.order;

  const generateStatus = (status) => {
    switch (status) {
      case "PREPARING":
        return { name: "Đang chuẩn bị", color: "#eab676" };
      case "ONSHIPPING":
        return { name: "Đang giao hàng", color: "#76b5c5" };
      case "DONE":
        return { name: "Đã giao hàng", color: "#27C93F" };
      case "FAILED":
        return { name: "Đã hủy", color: "#F30C28" };
      default:
        return null;
    }
  };
  const [wardInfo, setWardInfo] = useState("");
  const generateWardInfo = async () => {
    const res = await httpClient.get(`vn_units/ward/${order?.wardCode}`);

    setWardInfo(res.data);
  };

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  useEffect(() => {
    generateWardInfo();
    // getOrderDetail(order?.id, dispatch);
  }, []);
  return (
    <Box display={"flex"} flexDirection={"column"} gap={3}>
      <Box
        display={"flex"}
        flexDirection={{ xs: "column", sm: "column", md: "row" }}
        gap={3}
      >
        <Paper>
          <Box display={"flex"} gap={1} mx={1} mb={1}>
            <Typography variant="h6" color={"#1e81b0"}>
              {`Chi tiết đơn hàng #${order.id}`}
            </Typography>
            <Typography
              variant="h6"
              color={generateStatus(order?.status).color}
            >
              {` - ${generateStatus(order?.status).name}`}
            </Typography>
          </Box>
          <Box mx={1} pb={1}>
            <Box display={"flex"} gap={1} mb={1}>
              <LocationOnIcon sx={{ color: "#76b5c5" }} />
              <Typography>THÔNG TIN NHẬN HÀNG</Typography>
            </Box>
            <Box>
              <Typography>{`Người nhận: ${order?.userFullName} - ${order?.userPhoneNumber}`}</Typography>
              <Typography>{`Địa chỉ: ${order.orderAddress}, ${wardInfo.fullName}, ${wardInfo.districtFullName}, ${wardInfo.districtProvinceFullName}`}</Typography>
              <Typography>{`Giao lúc:`}</Typography>
              <Typography>{`Ghi chú:`}</Typography>
            </Box>
          </Box>
        </Paper>
        <Paper>
          <Box display={"flex"} gap={1} mx={1} mb={1}>
            <Typography
              variant="h6"
              fontWeight={"lighter"}
            >{`Đặt lúc: ${order.createdDate}`}</Typography>
          </Box>
          <Box mx={1} pb={1}>
            <Box display={"flex"} gap={1} mb={1}>
              <CreditCardIcon sx={{ color: "#76b5c5" }} />
              <Typography>HÌNH THỨC THANH TOÁN</Typography>
            </Box>
            <Box>
              <Typography>Thanh toán khi nhận hàng</Typography>
            </Box>
          </Box>
        </Paper>
      </Box>

      <Box>
        <Paper>
          <Box mx={1} pb={1}>
            <Box display={"flex"} gap={1} mb={1}>
              <ShoppingBagIcon sx={{ color: "#76b5c5" }} />
              <Typography>THÔNG TIN SẢN PHẨM</Typography>
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
                      <Typography>
                        {od.productInventory?.productName}
                      </Typography>
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
          </Box>

          <Box display={"flex"} flexDirection={"column"} gap={1} px={1} pb={1}>
            <Box
              display={"flex"}
              justifyContent={"space-between"}
              borderBottom={"solid 1px #2596be"}
            >
              <Typography>Tổng số sản phẩm:</Typography>
              <Typography color={"#2596be"}>
                {`${order.bill?.amount} Sản phẩm`}
              </Typography>
            </Box>
            <Box
              display={"flex"}
              justifyContent={"space-between"}
              borderBottom={"solid 1px #2596be"}
            >
              <Typography>Giá trị đơn hàng:</Typography>
              <Typography color={"#2596be"}>
                {VND.format(order.bill?.total + order.bill?.discount)}
              </Typography>
            </Box>
            <Box
              display={"flex"}
              justifyContent={"space-between"}
              borderBottom={"solid 1px #2596be"}
            >
              <Typography>Mã giảm giá đã dùng:</Typography>
              <Typography color={"#2596be"}>
                {order.orderVoucher
                  ? `- ${VND.format(order.orderVoucher?.voucherDiscountAmount)}`
                  : "Không sử dụng"}
              </Typography>
            </Box>

            <Box
              display={"flex"}
              justifyContent={"space-between"}
              borderBottom={"solid 1px #2596be"}
            >
              <Typography>Tổng số tiền đã được giảm:</Typography>
              <Typography color={"#2596be"}>
                {VND.format(order.bill?.discount)}
              </Typography>
            </Box>
            <Box display={"flex"} justifyContent={"space-between"}>
              <Typography fontWeight={"bold"}>
                Tổng tiền phải thanh toán:
              </Typography>
              <Typography fontWeight={"bold"} color={"#F30C28"}>
                {VND.format(order.bill?.total)}
              </Typography>
            </Box>
          </Box>
        </Paper>
      </Box>
      <Box display={"flex"} gap={1} alignItems={"center"} alignSelf={"center"}>
        <Typography variant="h6" align="center" color={"#2596be"}>
          Cám ơn quý khách đã mua hàng!
        </Typography>
        <FavoriteIcon sx={{ color: "#2596be" }} />
      </Box>
    </Box>
  );
}

export default OrderDetail;
