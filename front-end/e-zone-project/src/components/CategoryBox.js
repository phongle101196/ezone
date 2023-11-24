import {
  Box,
  Button,
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  FormControl,
  Grid,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { Await, useNavigate } from "react-router-dom";
import { MyButton } from "../components/MyButton";
import { useDispatch, useSelector } from "react-redux";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";
import { addToCart, getCart } from "../redux/request/apiCartRequest";
import { openSnackBarRequest } from "../redux/request/snackBarRequest";

function CategoryBox(props) {
  let { title, items, path } = props;
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const handleClick = (id) => {
    navigate(`/product/${id}`);
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

  const handleClickMore = () => {
    navigate(path);
  };

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  return (
    <Card sx={{ mt: 5, padding: 2 }}>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 3,
        }}
      >
        <Typography variant="h4" color="#1e81b0" align="center">
          {title}
        </Typography>

        <Grid
          container
          direction="row"
          justifyContent="flex-start"
          columns={10}
          rowSpacing={3}
        >
          {items?.map((item) => (
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
      <Box display={"flex"} mt={3} justifyContent={"center"}>
        <MyButton
          onClick={() => {
            handleClickMore();
          }}
        >
          Xem thêm
        </MyButton>
      </Box>
    </Card>
  );
}

export default CategoryBox;
