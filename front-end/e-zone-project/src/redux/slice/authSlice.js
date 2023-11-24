import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: {
    login: {
      currentUser: null,
      isFetching: false,
      error: false,
    },
    register: {
      isFetching: false,
      error: false,
      success: false,
    },
    isLogin: false,
  },
  reducers: {
    loginStart: (state) => {
      state.login.isFetching = true;
    },
    loginSuccess: (state, action) => {
      state.login.isFetching = false;
      state.login.currentUser = action.payload.user;
      state.login.error = false;
      state.isLogin = true;
    },
    loginFailed: (state) => {
      state.login.isFetching = false;
      state.login.error = true;
      state.isLogin = false;
    },
    registerStart: (state) => {
      state.register.isFetching = true;
    },
    registerSuccess: (state) => {
      state.register.isFetching = false;
      state.register.success = true;
      state.register.error = false;
    },
    registerFailed: (state) => {
      state.register.isFetching = false;
      state.register.error = true;
      state.register.success = false;
    },
    logout: (state) => {
      state.isLogin = false;
      state.login.currentUser = null;
    },
  },
});

export const {
  loginStart,
  loginSuccess,
  loginFailed,
  registerFailed,
  registerStart,
  registerSuccess,
  logout,
} = authSlice.actions;

export default authSlice.reducer;
