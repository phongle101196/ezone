import { createSlice } from "@reduxjs/toolkit";

const cartSlice = createSlice({
  name: "cart",
  initialState: {
    getCart: {
      cart: [],
      cartTotal: 0,
      voucher: null,
      isFetching: false,
      error: false,
    },
    addToCart: {
      isFetching: false,
      error: false,
      success: false,
    },
    deleteFromCart: {
      isFetching: false,
      error: false,
      success: false,
    },
    msg: "",
  },
  reducers: {
    getCartStart: (state) => {
      state.getCart.isFetching = true;
    },
    getCartSuccess: (state, action) => {
      state.getCart.isFetching = false;
      state.getCart.cart = action.payload;
      state.getCart.error = false;
    },
    getCartFailed: (state) => {
      state.getCart.isFetching = false;
      state.getCart.error = true;
    },
    addToCartStart: (state) => {
      state.addToCart.isFetching = true;
    },
    addToCartSuccess: (state, action) => {
      state.addToCart.isFetching = false;
      state.addToCart.success = true;
      state.addToCart.error = false;
      state.msg = action.payload;
    },
    addToCartFailed: (state, action) => {
      state.addToCart.isFetching = false;
      state.addToCart.error = true;
      state.addToCart.success = false;
      state.msg = action.payload;
    },
    deleteFromCartStart: (state) => {
      state.deleteFromCart.isFetching = true;
    },
    deleteFromCartSuccess: (state) => {
      state.deleteFromCart.isFetching = false;
      state.deleteFromCart.success = true;
      state.deleteFromCart.error = false;
    },
    deleteFromCartFailed: (state) => {
      state.deleteFromCart.isFetching = false;
      state.deleteFromCart.error = true;
      state.deleteFromCart.success = false;
    },

    getCartTotalSuccess: (state, action) => {
      state.getCart.cartTotal = action.payload;
    },
    addVoucherSuccess: (state, action) => {
      state.getCart.voucher = action.payload;
    },
    clearVoucherSuccess: (state) => {
      state.getCart.voucher = null;
    },
    clearCartSuccess: (state) => {
      state.getCart.cart = [];
    },
  },
});

export const {
  getCartStart,
  getCartSuccess,
  getCartFailed,
  addToCartStart,
  addToCartSuccess,
  addToCartFailed,
  deleteFromCartStart,
  deleteFromCartSuccess,
  deleteFromCartFailed,
  getCartTotalSuccess,
  addVoucherSuccess,
  clearVoucherSuccess,
  clearCartSuccess,
} = cartSlice.actions;

export default cartSlice.reducer;
