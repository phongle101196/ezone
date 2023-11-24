import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
  name: "user",
  initialState: {
    users: {
      allUsers: null,
      isFetching: false,
      error: false,
    },
  },
  reducers: {
    getUserStart: (state) => {
      state.users.isFetching = true;
    },
    getUserSuccess: (state, action) => {
      state.users.isFetching = false;
      state.users.allUsers = action.payload.content;
      state.users.error = false;
    },
    getUserFailed: (state) => {
      state.login.isFetching = false;
      state.login.error = true;
    },
  },
});

export const { getUserStart, getUserSuccess, getUserFailed } =
  userSlice.actions;

export default userSlice.reducer;
