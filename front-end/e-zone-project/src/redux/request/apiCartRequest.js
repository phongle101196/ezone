import httpClient from "../../api/httpClient";
import {
  addToCartFailed,
  addToCartStart,
  addToCartSuccess,
  addVoucherSuccess,
  clearCartSuccess,
  clearVoucherSuccess,
  deleteFromCartStart,
  deleteFromCartSuccess,
  getCartFailed,
  getCartStart,
  getCartSuccess,
  getCartTotalSuccess,
} from "../slice/cartSlice";
import { openSnackBar } from "../slice/snackBarSlice";
import { setSnackBarMsg } from "./snackBarRequest";

//Ở đây phải dùng async await vì thông tin cart lưu tại localStorage, cũng giống như ở database
export const getCart = async (dispatch) => {
  dispatch(getCartStart());
  try {
    //thay bằng api nếu cart lưu ở db
    const cart = [];
    const cartInfo = await JSON.parse(localStorage.getItem("cart"));
    cart.push(...cartInfo);
    dispatch(getCartSuccess(cart));
  } catch (error) {
    dispatch(getCartFailed());
  }
};

// info param có dạng {Object productInventory, int quantity}

export const addToCart = async (info, dispatch) => {
  dispatch(addToCartStart());
  try {
    //Nếu dùng api chỉ cần call api add là được
    //Ở đây dùng localStorge nên phải lấy ra dữ liệu cart, thêm invent và quantity,
    //rồi đẩy trở lại localStorge
    const cart = [];
    const cartInfo = await JSON.parse(localStorage.getItem("cart"));
    if (cartInfo) {
      cart.push(...cartInfo);
    }

    //Kiểm tra trong giỏ đã có mặt hàng muốn thêm hay chưa
    const isAlready = cart.find(
      (cart) => cart.productInventory.id === info.productInventory.id
    );
    //Nếu chưa có thì thêm vào rồi lưu trở lại localStorage
    if (!isAlready) {
      cart.push(info);
      localStorage.setItem("cart", JSON.stringify(cart));
      dispatch(addToCartSuccess("Đã thêm vào giỏ hàng"));
    }
    //Nếu đã có trong giỏ
    //Tìm vị trí của invent đó trong giỏ, rồi cộng thêm quantity và lưu lại vào localStorage
    else {
      const cartIndex = cart.findIndex(
        (cart) => cart.productInventory.id == info.productInventory.id
      );
      cart[cartIndex].quantity = cart[cartIndex].quantity + info.quantity;
      localStorage.setItem("cart", JSON.stringify(cart));
      dispatch(addToCartSuccess("Đã thêm vào giỏ hàng"));
    }
  } catch (error) {
    //Thay msg bằng err msg nếu call api
    dispatch(addToCartFailed("Lỗi!!"));
  }
};

export const deleteFromCart = async (inventId, dispatch) => {
  dispatch(deleteFromCartStart());
  try {
    //Nếu dùng api chỉ cần call api delete là được
    //Ở đây dùng localStorge nên phải lấy ra dữ liệu cart, xóa invent tương ứng,
    //rồi đẩy trở lại localStorge
    const cart = [];
    const cartInfo = await JSON.parse(localStorage.getItem("cart"));
    cart.push(...cartInfo);
    //Tìm vị trí của card có inventId tương ứng, sau đó xóa đi
    const cartIndex = cart.findIndex(
      (cart) => cart.productInventory.id == inventId
    );
    cart.splice(cartIndex, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
    dispatch(deleteFromCartSuccess("Đã xóa khỏi giỏ hàng"));
  } catch (error) {
    dispatch(addToCartFailed("Lỗi!!"));
  }
};

export const getCartTotal = async (dispatch) => {
  const cart = [];
  const cartInfo = await JSON.parse(localStorage.getItem("cart"));
  if (cartInfo != null) {
    cart.push(...cartInfo);
  }

  let total = 0;

  cart.forEach((c) => {
    if (c.productInventory.productInventorySale != null) {
      const inventTotal =
        c.quantity * c.productInventory.productInventorySale.salePrice;
      total = total + inventTotal;
    } else {
      let inventTotal = c.quantity * c.productInventory.price;
      total = total + inventTotal;
    }
  });

  dispatch(getCartTotalSuccess(total));
};

//info ở đây sẽ có dạng: {int inventId, int newQuantity }
export const updateCart = async (info, dispatch) => {
  //front-end làm công việc của back-end @@!

  //Kiểm tra xem newQuantity >= 1 thì mới cập nhật
  if (info.newQuantity >= 1) {
    try {
      const cart = [];
      const cartInfo = await JSON.parse(localStorage.getItem("cart"));
      cart.push(...cartInfo);

      //Lấy ra invent tương ứng trong Cart, rồi cập nhật quantity
      const cartIndex = cart.findIndex(
        (cart) => cart.productInventory.id == info.inventId
      );

      cart[cartIndex].quantity = info.newQuantity;

      //Lưu trở lại localStorage
      localStorage.setItem("cart", JSON.stringify(cart));

      //Cập nhật lại state:
      dispatch(getCartSuccess(cart));
    } catch (error) {
      console.log(error);
    }
  } else {
    //chỗ này để hiển thị thông tin lên snackBar cho người dùng biết
    dispatch(
      openSnackBar({
        msg: "Không thể cập nhật số lượng < 1, vui lòng ấn nút XÓA nếu muốn bỏ sản phẩm khỏi giỏ hàng!",
        severity: "warning",
      })
    );
  }
};

export const addVoucher = async (code, dispatch) => {
  try {
    const voucher = await httpClient.get(`vouchers/code/${code}`);
    dispatch(addVoucherSuccess(voucher.data));
    dispatch(
      openSnackBar(setSnackBarMsg("Đã áp dụng mã giảm giá!", "success"))
    );
  } catch (error) {}
};

export const clearVoucher = (dispatch) => {
  dispatch(clearVoucherSuccess());
};

export const clearCart = async (dispatch) => {
  const cart = [];
  //Nếu call api mới cần async await
  await localStorage.setItem("cart", JSON.stringify(cart));
  dispatch(clearCartSuccess());
};
