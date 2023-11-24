import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./slice/authSlice";
import userReducer from "./slice/userSlice";
import cartReducer from "./slice/cartSlice";
import snackBarReducer from "./slice/snackBarSlice";
import productReducer from "./slice/productSlice";
import locationReducer from "./slice/locationSlice";
import orderReducer from "./slice/orderSlice";

export default configureStore({
  reducer: {
    auth: authReducer,
    users: userReducer,
    cart: cartReducer,
    snackBar: snackBarReducer,
    products: productReducer,
    location: locationReducer,
    order: orderReducer,
  },
});
