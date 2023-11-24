import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import httpClient from "../../api/httpClient";
import {
  Box,
  Button,
  Card,
  CardContent,
  Grid,
  ImageList,
  ImageListItem,
  Typography,
} from "@mui/material";
import ShoppingCartCheckoutIcon from "@mui/icons-material/ShoppingCartCheckout";
import { useDispatch, useSelector } from "react-redux";
import {
  addToCart,
  getCart,
  refreshCart,
} from "../../redux/request/apiCartRequest";
import SnackBar from "../../components/SnackBar";
import { openSnackBarRequest } from "../../redux/request/snackBarRequest";
import { MyButton } from "../../components/MyButton";

function Product(props) {
  let { id } = useParams();

  const dispatch = useDispatch();

  const [product, setProduct] = useState(null);
  const [imgList, setImgList] = useState([]);
  const [inventory, setInventory] = useState(null);

  const handleAddCart = async (inv) => {
    await addToCart(
      {
        productInventory: inv,
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

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  useEffect(() => {
    const getProduct = async () => {
      const product = await httpClient.get(`/products/${id}`);
      setProduct(product.data);
      const imgList = [];
      imgList.push(
        product.data.image1,
        product.data.image2,
        product.data.image3,
        product.data.image4,
        product.data.image5
      );
      setImgList(imgList);
    };

    const getInvent = async () => {
      const invent = await httpClient.get(
        `/productInventories?productId=${id}`
      );
      console.log(invent);
      setInventory(invent.data.content);
    };
    getProduct();
    getInvent();
  }, []);

  return (
    <Box display={"flex"} flexDirection={"column"}>
      <Box mt={5}>
        <Typography variant="h4" color={"#1e81b0"} align="center">
          {product ? product.name : null}
        </Typography>
      </Box>

      <Box display={"flex"} flexDirection={"column"} gap={3}>
        <Box display={"flex"}>
          {
            <ImageList variant="masonry" cols={3} gap={8}>
              {imgList.map((item) => (
                <ImageListItem key={item}>
                  <img
                    srcSet={`${item}?w=248&fit=crop&auto=format&dpr=2 2x`}
                    src={`${item}?w=248&fit=crop&auto=format`}
                    alt={item}
                    loading="lazy"
                  />
                </ImageListItem>
              ))}
            </ImageList>
          }
        </Box>

        <Box display={"flex"} flexDirection={"column"}>
          <Typography variant="body1" color={"#1e81b0"} align="center">
            Chọn phiên bản
          </Typography>
          <Box
            sx={{
              display: "flex",
              flexDirection: "row",
              justifyContent: "center",
            }}
          >
            {inventory?.map((invent) => (
              <Card>
                <CardContent sx={{ display: "flex", flexDirection: "column" }}>
                  <Typography variant="body1" color={"#1e81b0"} align="center">
                    Màu sắc
                  </Typography>
                  <Typography variant="h6" align="center">
                    {invent.colorName}
                  </Typography>

                  <Typography variant="h6" color="#1e81b0">
                    {VND.format(
                      invent.productInventorySale
                        ? invent.productInventorySale.salePrice
                        : invent.price
                    )}
                  </Typography>
                </CardContent>
                <MyButton
                  onClick={() => {
                    handleAddCart(invent);
                  }}
                  variant="contained"
                  startIcon={<ShoppingCartCheckoutIcon />}
                >
                  Thêm vào giỏ
                </MyButton>
              </Card>
            ))}
          </Box>
        </Box>
      </Box>

      <SnackBar />
    </Box>
  );
}

export default Product;
