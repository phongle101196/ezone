import { createSlice } from "@reduxjs/toolkit";

const productSlice = createSlice({
  name: "product",
  initialState: {
    getProduct: {
      products: null,
      isFetching: false,
      error: false,
    },
    getProductInvent: {
      productInvents: null,
      isFetching: false,
      error: false,
    },
    getInventSale: {
      inventSales: null,
      isFetching: false,
      error: false,
    },
  },
  reducers: {
    getProductStart: (state) => {
      state.getProduct.isFetching = true;
    },
    getProductSuccess: (state, action) => {
      state.getProduct.isFetching = false;
      state.getProduct.products = action.payload;
      state.getProduct.error = false;
    },
    getProductFailed: (state) => {
      state.getProduct.isFetching = false;
      state.getProduct.error = true;
    },

    getProductInventStart: (state) => {
      state.getProductInvent.isFetching = true;
    },
    getProductInventSuccess: (state, action) => {
      state.getProductInvent.isFetching = false;
      state.getProductInvent.productInvents = action.payload;
      state.getProductInvent.error = false;
    },
    getProductInventFailed: (state) => {
      state.getProductInvent.isFetching = false;
      state.getProductInvent.error = true;
    },

    getInventSaleStart: (state) => {
      state.getInventSale.isFetching = true;
    },
    getInventSaleSuccess: (state, action) => {
      state.getInventSale.isFetching = false;
      state.getInventSale.inventSales = action.payload;
      state.getInventSale.error = false;
    },
    getInventSaleFailed: (state) => {
      state.getInventSale.isFetching = false;
      state.getInventSale.error = true;
    },
  },
});

export const {
  getProductStart,
  getProductSuccess,
  getProductFailed,
  getProductInventStart,
  getProductInventSuccess,
  getProductInventFailed,
  getInventSaleStart,
  getInventSaleSuccess,
  getInventSaleFailed,
} = productSlice.actions;

export default productSlice.reducer;
