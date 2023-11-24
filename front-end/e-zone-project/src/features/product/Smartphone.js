import React, { useEffect, useState } from "react";
import { getAllSmartPhone } from "../../redux/request/apiProductRequest";
import {
  Box,
  Button,
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Grid,
  Pagination,
  Typography,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import {
  addToCart,
  getCart,
  refreshCart,
} from "../../redux/request/apiCartRequest";
import { openSnackBarRequest } from "../../redux/request/snackBarRequest";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";
import "./Smartphone.scss";

function Smartphone(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const items = useSelector((state) => state.products.getProduct?.products);

  const pageArr = [];
  for (let index = 1; index <= items?.totalPages; index++) {
    pageArr.push(index);
  }

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  const handleClick = (id) => {
    navigate(`/product/${id}`);
  };

  const getData = async (p) => {
    await getAllSmartPhone(
      { page: p, size: 10, search: "" },
      dispatch,
      navigate
    );
  };

  const handleAddCart = async (inv) => {
    await addToCart(
      {
        productInventory: inv.productInventories[0],
        quantity: 1,
      },
      dispatch
    );

    await getCart(dispatch);
    openSnackBarRequest(
      { msg: "Đã thêm vào giỏ hàng!!", severity: "success" },
      dispatch
    );
  };

  //make current page button differrent
  const [currentPage, setCurentPage] = useState(1);
  const handlePage = async (p) => {
    await getData(p - 1);
    setCurentPage(p);
  };

  useEffect(() => {
    getAllSmartPhone({ page: 0, size: 10, search: "" }, dispatch, navigate);
  }, []);

  return (
    <Card
      sx={{
        mt: 2,
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
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
          ĐIỆN THOẠI THÔNG MINH
        </Typography>

        <Grid
          container
          direction="row"
          justifyContent="flex-start"
          columns={10}
          rowSpacing={3}
        >
          {items?.content.map((item) => (
            <Grid key={item} item xs={10} sm={5} md={3} lg={2}>
              <Card
                sx={{
                  width: "240px",
                  margin: "auto",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <CardActionArea
                  onClick={() => {
                    handleClick(item.id);
                  }}
                >
                  <CardMedia
                    component="img"
                    height="200"
                    image={item.image1}
                    alt={item.name}
                    sx={{
                      objectFit: "contain",
                    }}
                  />
                  <CardContent
                    sx={{
                      display: "flex",
                      flexDirection: "column",
                      justifyContent: "end",
                    }}
                  >
                    <Typography
                      gutterBottom
                      variant="body1"
                      component="div"
                      flexWrap={1}
                    >
                      {item.name}
                    </Typography>
                    <Typography variant="h6" color="#F30C28">
                      {VND.format(item.productInventories[0].price)}
                    </Typography>
                  </CardContent>
                </CardActionArea>

                <Button
                  startIcon={<AddShoppingCartIcon sx={{ color: "#1e81b0" }} />}
                  sx={{ color: "#1e81b0" }}
                  onClick={() => {
                    handleAddCart(item);
                  }}
                >
                  Thêm vào giỏ
                </Button>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Box>

      <Box m={5} alignItems={"center"}>
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
    </Card>
  );
}

export default Smartphone;
