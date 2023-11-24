import {
  AppBar,
  Box,
  Button,
  CardMedia,
  Grid,
  IconButton,
  Paper,
  Toolbar,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { getAllOrder } from "../../redux/request/apiOrderRequest";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

function OrderManager(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const orderList = useSelector((state) => state.order.orderList);

  const pageArr = [];
  for (let index = 1; index <= orderList?.totalPages; index++) {
    pageArr.push(index);
  }

  const [currentPage, setCurentPage] = useState(1);

  const handlePage = async (p) => {
    await getAllOrder(p - 1, dispatch);
    setCurentPage(p);
  };

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

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  useEffect(() => {
    getAllOrder(0, dispatch);
  }, []);

  return (
    <Paper>
      <Box mx={1} display={"flex"} flexDirection={"column"} gap={3}>
        <Typography variant="h6" color={"#1e81b0"} align="center">
          ĐƠN HÀNG
        </Typography>
        {orderList.content?.map((o) => (
          <Box
            display={"flex"}
            flexDirection={"column"}
            borderBottom={"solid #1e81b0"}
            pb={1}
          >
            <Box display={"flex"} justifyContent={"space-between"} gap={1}>
              <Typography>{`Đơn hàng: #${o?.id}`}</Typography>
              <Typography
                fontWeight={"bold"}
                color={generateStatus(o?.status).color}
              >
                {generateStatus(o?.status).name}
              </Typography>
            </Box>

            <Box
              display={"flex"}
              gap={3}
              justifyContent={"space-between"}
              alignItems={"start"}
            >
              <Box display={"flex"} flexDirection={"column"} gap={1}>
                {o.orderDetails?.map((od) => (
                  <Box display={"flex"} justifyContent={"start"} mt={1} gap={3}>
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
                    </Box>
                  </Box>
                ))}
              </Box>
              <Box
                display={"flex"}
                flexDirection={"column"}
                justifyContent={"end"}
                py={1}
              >
                <Box>
                  <Typography>{`Tổng tiền: ${VND.format(
                    o.bill?.total
                  )}`}</Typography>
                </Box>
                <Button
                  size="small"
                  variant="outlined"
                  sx={{ color: "#1e81b0" }}
                  onClick={() => {
                    navigate("/manager/chi-tiet-don-hang", {
                      state: { order: o },
                    });
                  }}
                >
                  Xử lý
                </Button>
              </Box>
            </Box>
          </Box>
        ))}

        <Box m={1} alignItems={"center"}>
          <Box display={"flex"} justifyContent={"center"} color={"#1e81b0"}>
            <Typography variant="h6">Trang</Typography>
          </Box>
          <Box display={"flex"} justifyContent={"center"}>
            {pageArr.map((p) => (
              <Button
                className={`${p === currentPage ? "actived paging" : "paging"}`}
                key={p}
                onClick={(e) => {
                  handlePage(p);
                }}
              >
                {p}
              </Button>
            ))}
          </Box>
        </Box>
      </Box>
    </Paper>
  );
}

export default OrderManager;
