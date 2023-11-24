import { createSlice } from "@reduxjs/toolkit";

const orderSlice = createSlice({
  name: "order",
  initialState: {
    orderList: [],
    order: null,
  },
  reducers: {
    getOrderListSuccess: (state, action) => {
      state.orderList = action.payload;
    },
    getOrderSuccess: (state, action) => {
      state.order = action.payload;
    },
  },
});

export const { getOrderListSuccess, getOrderSuccess } = orderSlice.actions;

export default orderSlice.reducer;
