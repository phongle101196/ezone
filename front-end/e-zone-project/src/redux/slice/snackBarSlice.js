import { createSlice } from "@reduxjs/toolkit";

const snackBarSlice = createSlice({
  name: "snackBar",
  initialState: { openSnackBar: false, severity: "", msg: "" },
  reducers: {
    openSnackBar: (state, action) => {
      state.openSnackBar = true;
      state.msg = action.payload.msg;
      state.severity = action.payload.severity;
    },
    closeSnackBar: (state) => {
      state.openSnackBar = false;
    },
  },
});

export const { openSnackBar, closeSnackBar } = snackBarSlice.actions;

export default snackBarSlice.reducer;
