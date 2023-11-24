import {
  Box,
  Button,
  Card,
  CardActions,
  CardMedia,
  Checkbox,
  Container,
  FormControl,
  FormControlLabel,
  InputLabel,
  MenuItem,
  Paper,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { MyButton } from "../../components/MyButton";
import "./Cart.scss";
import {
  addVoucher,
  clearCart,
  clearVoucher,
  deleteFromCart,
  getCart,
  getCartTotal,
  updateCart,
} from "../../redux/request/apiCartRequest";
import { useDispatch, useSelector } from "react-redux";
import SnackBar from "../../components/SnackBar";
import {
  getDataDistrictByProvinceCode,
  getDataProvince,
  getDataWardByDistrictCode,
  resetDataWard,
} from "../../redux/request/apiLocationRequest";
import { NavLink, useNavigate } from "react-router-dom";
import httpClient from "../../api/httpClient";
import {
  openSnackBarRequest,
  setSnackBarMsg,
} from "../../redux/request/snackBarRequest";
import HomeIcon from "@mui/icons-material/Home";
import ShoppingCartCheckoutIcon from "@mui/icons-material/ShoppingCartCheckout";

function Cart(props) {
  const cartInfo = useSelector((state) => state.cart.getCart);
  const provinces = useSelector((state) => state.location?.provinces);
  let districts = useSelector((state) => state.location?.districts);
  let wards = useSelector((state) => state.location?.wards);

  //
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const VND = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  //
  let [voucherCode, setVoucherCode] = useState("");

  let [isVoucher, setIsVoucher] = useState(false);

  let checkVoucher = async () => {
    const productInventoryIds = [];
    cartInfo.cart.forEach((c) => {
      productInventoryIds.push(c.productInventory?.id);
    });

    const form = {
      code: voucherCode,
      productInventoryIds: productInventoryIds,
    };
    try {
      const res = await httpClient.post("orderVouchers/check", form);

      if (res.data) {
        setIsVoucher(true);
        addVoucher(voucherCode, dispatch);
      } else {
        openSnackBarRequest(
          setSnackBarMsg("Mã giảm giá sai hoặc không thể áp dụng!!", "error"),
          dispatch
        );
      }
    } catch (error) {
      openSnackBarRequest(
        setSnackBarMsg("Lỗi hệ thống, không thể kiểm tra...", "warning"),
        dispatch
      );
    }
  };

  let [wardCode, setWardCode] = useState("");
  let [orderAddress, setOrderAddress] = useState("");
  let [isConfirm, setIsConfirm] = useState(false);

  const handleConfirm = () => {
    setIsConfirm(!isConfirm);
  };

  const handleCreateOrder = async () => {
    //kiểm tra chính sách
    if (!isConfirm) {
      openSnackBarRequest(
        setSnackBarMsg("Vui lòng đồng ý chính sách của E-zone!", "warning"),
        dispatch
      );
      return;
    }
    //kiểm tra địa chỉ
    if (wardCode == "" || orderAddress == "") {
      openSnackBarRequest(
        setSnackBarMsg("Vui lòng điền đầy đủ thông tin người nhận!", "warning"),
        dispatch
      );
      return;
    }

    //tạo order
    const orderInfo = {
      userId: JSON.parse(localStorage.getItem("currentUser")).user.id,
      wardCode: wardCode,
      orderAddress: orderAddress,
      status: "PREPARING",
    };

    try {
      const order = await httpClient.post("orders", orderInfo);
      const orderId = await order.data.id;

      //tiếp tục tạo orderDetail
      cartInfo.cart.forEach(async (c) => {
        try {
          await httpClient.post("orderDetails", {
            orderId: order.data.id,
            productInventoryId: c.productInventory.id,
            quantity: c.quantity,
          });
        } catch (error) {
          //Nếu lỗi xảy ra
          //hiển thị lỗi cho người dùng, xóa đơn hàng vừa tạo và return
          try {
            await httpClient.delete(`orders/${order.data.id}`);

            openSnackBarRequest(
              setSnackBarMsg("Đã có lỗi xảy ra!", "error"),
              dispatch
            );
          } catch (error) {
            //Lỗi này sẽ hiện khi không xóa được đơn hàng, kỹ thuật viên sẽ xử lý sau
            openSnackBarRequest(
              setSnackBarMsg("Lỗi hệ thống!", "error"),
              dispatch
            );
          }
          return;
        }
      });

      //nếu có voucher thì tạo thêm orderVoucher
      if (isVoucher == true) {
        try {
          const form = {
            orderId: order.data?.id,
            code: voucherCode,
          };
          console.log(form);
          await httpClient.post("orderVouchers", form);
        } catch (error) {
          //Nếu lỗi khi thêm voucher thì cũng xóa đơn hàng luôn và return
          try {
            await httpClient.delete(`orders/${order.data.id}`);

            openSnackBarRequest(
              setSnackBarMsg("Đã có lỗi xảy ra!", "error"),
              dispatch
            );
          } catch (error) {
            //Lỗi này sẽ hiện khi không xóa được đơn hàng, kỹ thuật viên sẽ xử lý sau
            openSnackBarRequest(
              setSnackBarMsg("Lỗi hệ thống!", "error"),
              dispatch
            );
          }
          return;
        }
      }

      //Kiểm tra user đã là customer hay chưa, nếu chưa thì tạo customer
      try {
        const checkCustomer = await httpClient.get(
          `customers/user/${
            JSON.parse(localStorage.getItem("currentUser")).user.id
          }`
        );
        //Chỗ này vẫn thông báo tạo đơn thành công, vì không ảnh hưởng đến người mua
        openSnackBarRequest(
          setSnackBarMsg(
            "Đơn hàng đã được tạo! Cám ơn bạn đã đặt hàng <3",
            "success"
          ),
          dispatch
        );
      } catch (error) {
        try {
          await httpClient.post("customers", {
            userId: JSON.parse(localStorage.getItem("currentUser")).user.id,
          });
        } catch (error) {
          //
        }
      }

      //Tạo bill
      try {
        await httpClient.post("bills", {
          orderId: order.data?.id,
          userId: 1,
          discount: 0,
        });
      } catch (error) {}
    } catch (error) {
      //Thông báo nếu có lỗi
      openSnackBarRequest(
        setSnackBarMsg("Đã có lỗi xảy ra, không thể tạo đơn hàng!!", "error"),
        dispatch
      );
    }

    openSnackBarRequest(
      setSnackBarMsg(
        "Đơn hàng đã được tạo! Cám ơn bạn đã đặt hàng <3",
        "success"
      ),
      dispatch
    );

    //sau khi đã tạo đơn thành công, xóa hết sản phẩm trong giỏ và chuyển đến tran đơn hàng
    clearCart(dispatch);
    navigate("/order");

    // console.log("isConfirm: ", isConfirm);
    // console.log(
    //   "userId: ",
    //   JSON.parse(localStorage.getItem("currentUser")).user.id
    // );
    // console.log("wardCode: ", wardCode);
    // console.log("orderAddress: ", orderAddress);
    // console.log("isVoucher ", isVoucher);
    // console.log("voucherCode ", voucherCode);
  };

  //
  const handleUpdateCart = async (inventId, newQuantity) => {
    const info = {
      inventId: inventId,
      newQuantity: newQuantity,
    };

    await updateCart(info, dispatch);
    await getCartTotal(dispatch);
  };

  const getDistrict = async (provinceCode) => {
    await getDataDistrictByProvinceCode(provinceCode, dispatch);
    resetDataWard(dispatch);
  };

  const getWard = async (districtCode) => {
    await getDataWardByDistrictCode(districtCode, dispatch);
  };

  const handleDeleteFromCart = async (inventId) => {
    await deleteFromCart(inventId, dispatch);
    await getCart(dispatch);
    await getCartTotal(dispatch);
    setVoucherCode("");
    setIsVoucher(false);
  };

  useEffect(() => {
    getCart(dispatch);
    getCartTotal(dispatch);
    getDataProvince(dispatch);
  }, []);

  return (
    <Container
      sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}
    >
      <Typography
        variant="h4"
        color="#1e81b0"
        align="center"
        gutterBottom
        mt={5}
      >
        Giỏ hàng của bạn
      </Typography>

      {cartInfo.cart[0] ? (
        <Box>
          <Paper sx={{ px: 3, py: 1 }}>
            {cartInfo.cart?.map((c) => (
              <Box
                key={c}
                display={"flex"}
                justifyContent="space-between"
                borderBottom={"solid #1e81b0"}
                mb={1}
                gap={3}
              >
                <Box display={"flex"} gap={3}>
                  <Card>
                    <CardMedia
                      component="img"
                      height="100px"
                      image={c.productInventory?.productImage1}
                      alt={c.productInventory?.productName}
                      sx={{ objectFit: "contain" }}
                    />

                    <CardActions
                      sx={{ display: "flex", justifyContent: "center" }}
                    >
                      <Button
                        size="small"
                        onClick={() => {
                          handleDeleteFromCart(c.productInventory?.id);
                        }}
                      >
                        Xóa
                      </Button>
                    </CardActions>
                  </Card>

                  <Box>
                    <Typography fontWeight={"bold"}>
                      {c.productInventory?.productName}
                    </Typography>
                    <Typography>
                      Màu: {c.productInventory?.colorName}
                    </Typography>
                  </Box>
                </Box>

                <Box>
                  {c.productInventory?.productInventorySale ? (
                    <Box>
                      <Typography color={"#F30C28"}>
                        {VND.format(
                          c.productInventory.productInventorySale?.salePrice
                        )}
                      </Typography>

                      <Typography sx={{ textDecoration: "line-through" }}>
                        {VND.format(c.productInventory?.price)}
                      </Typography>
                    </Box>
                  ) : (
                    <Typography align="right" color={"#F30C28"}>
                      {VND.format(c.productInventory?.price)}
                    </Typography>
                  )}

                  <TextField
                    id="filled-quantity"
                    label="Số lượng"
                    type="number"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    size="small"
                    value={c.quantity}
                    sx={{ width: "100px", mt: 1 }}
                    onChange={(e) => {
                      handleUpdateCart(c.productInventory?.id, e.target.value);
                    }}
                  />
                </Box>
              </Box>
            ))}

            <Box display={"flex"} justifyContent={"space-between"} mt={1}>
              <Typography>{`Tạm tính (${cartInfo.cart?.length} sản phẩm):`}</Typography>
              <Typography>{VND.format(cartInfo?.cartTotal)}</Typography>
            </Box>
          </Paper>

          <Box mt={3}>
            <Paper sx={{ px: 3, py: 1 }}>
              <Typography gutterBottom>THÔNG TIN NGƯỜI NHẬN</Typography>

              <Box display={"flex"} justifyContent={"space-between"} mt={2}>
                <FormControl sx={{ width: "240px" }} size="small">
                  <InputLabel>Tỉnh/Thành phố</InputLabel>
                  <Select
                    label="Tỉnh/Thành phố"
                    onChange={(e) => {
                      getDistrict(e.target.value);
                      setWardCode("");
                    }}
                  >
                    {provinces?.map((p) => (
                      <MenuItem value={p.code}>{p.fullName}</MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControl sx={{ width: "240px" }} size="small">
                  <InputLabel>Quận/Huyện</InputLabel>
                  <Select
                    label="Quận/Huyện"
                    onChange={(e) => {
                      getWard(e.target.value);
                      setWardCode("");
                    }}
                  >
                    {districts?.map((d) => (
                      <MenuItem value={d.code}>{d.fullName}</MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Box>

              <Box display={"flex"} justifyContent={"space-between"} mt={1}>
                <FormControl sx={{ width: "240px" }} size="small">
                  <InputLabel>Xã/Phường/Thị trấn</InputLabel>
                  <Select
                    label="Xã/Phường/Thị trấn"
                    onChange={(e) => {
                      setWardCode(e.target.value);
                    }}
                  >
                    {wards?.map((w) => (
                      <MenuItem value={w.code}>{w.fullName}</MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <FormControl sx={{ width: "240px" }}>
                  <TextField
                    size="small"
                    onChange={(e) => {
                      setOrderAddress(e.target.value);
                    }}
                    id="outlined-basic"
                    label="Địa chỉ, Số điện thoại"
                    variant="outlined"
                  />
                </FormControl>
              </Box>
            </Paper>
          </Box>

          <Box mt={3}>
            <Paper sx={{ px: 3, py: 1 }}>
              <Box display={"flex"} justifyContent={"space-between"}>
                <FormControl sx={{ width: "320px" }}>
                  <TextField
                    value={voucherCode}
                    onChange={(e) => {
                      setIsVoucher(false);
                      clearVoucher(dispatch);
                      setVoucherCode(e.target.value);
                    }}
                    id="outlined-basic"
                    label="Sử dụng mã giảm giá"
                    variant="outlined"
                    size="small"
                  />
                </FormControl>

                <Button size="small" variant="outlined" onClick={checkVoucher}>
                  Áp dụng
                </Button>
              </Box>
            </Paper>
          </Box>

          <Box display={"flex"} justifyContent={"space-between"} mt={3}>
            <Typography fontWeight={"bold"}>TỔNG TIỀN:</Typography>
            <Typography fontWeight={"bold"} color={"#F30C28"}>
              {VND.format(
                cartInfo.voucher
                  ? cartInfo?.cartTotal - cartInfo.voucher?.discountAmount
                  : cartInfo?.cartTotal
              )}
            </Typography>
          </Box>

          <Box mt={3}>
            <FormControlLabel
              value={isConfirm}
              onChange={handleConfirm}
              className="checkbox"
              required
              control={<Checkbox />}
              label="Tôi đồng ý với Chính sách xử lý dữ liệu cá nhân của E-zone"
            />
          </Box>
          <FormControl fullWidth>
            <MyButton
              size="large"
              onClick={() => {
                handleCreateOrder();
              }}
            >
              Đặt hàng
            </MyButton>
          </FormControl>
        </Box>
      ) : (
        <Box>
          <Paper sx={{ px: 3, py: 1 }}>
            <Box display={"flex"} flexDirection={"column"} gap={1}>
              <Typography
                variant="h6"
                align="center"
                color={"#76b5c5"}
              >{`Chưa có sản phẩm nào trong giỏ hàng!`}</Typography>
              <Box
                display={"flex"}
                flexDirection={{ xs: "column", sm: "row" }}
                justifyContent={"space-between"}
                alignItems={"center"}
                gap={1}
              >
                <NavLink to={"/"}>
                  <MyButton startIcon={<HomeIcon />}>Trang chủ</MyButton>
                </NavLink>
                <NavLink to={"/order"}>
                  <MyButton startIcon={<ShoppingCartCheckoutIcon />}>
                    Đơn hàng của tôi
                  </MyButton>
                </NavLink>
              </Box>
            </Box>
          </Paper>
        </Box>
      )}

      <SnackBar />
    </Container>
  );
}

export default Cart;
