import { createSlice } from "@reduxjs/toolkit";

const locationSlice = createSlice({
  name: "location",
  initialState: {
    wards: null,
    districts: null,
    provinces: null,
  },
  reducers: {
    getWard: (state, action) => {
      state.wards = action.payload;
    },
    getDistrict: (state, action) => {
      state.districts = action.payload;
    },
    getProvince: (state, action) => {
      state.provinces = action.payload;
    },
    resetWard: (state) => {
      state.wards = null;
    },
  },
});

export const { getWard, getDistrict, getProvince, resetWard } =
  locationSlice.actions;

export default locationSlice.reducer;
