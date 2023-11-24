import {
  loginFailed,
  loginStart,
  loginSuccess,
  registerStart,
  registerSuccess,
} from "../slice/authSlice";
import httpClient from "../../api/httpClient";
import { openSnackBar } from "../slice/snackBarSlice";
import { setSnackBarMsg } from "./snackBarRequest";

export const loginUser = async (user, dispatch, navigate) => {
  dispatch(loginStart());
  try {
    const res = await httpClient.post("/login", user);
    dispatch(loginSuccess(res.data));
    localStorage.setItem("currentUser", JSON.stringify(res.data));
    dispatch(
      openSnackBar({
        msg: `Xin chào: ${res.data.user.fullName}! Chúc bạn có một ngày vui :))`,
        severity: "success",
      })
    );
    navigate("/");
  } catch (error) {
    dispatch(loginFailed());
    dispatch(
      openSnackBar({
        msg: "Sai tài khoản hoặc mật khẩu!!",
        severity: "error",
      })
    );
  }
};

export const registerUser = async (user, dispatch, navigate) => {
  dispatch(registerStart());
  try {
    await httpClient.post("/users", user);
    dispatch(registerSuccess());
    const msg = setSnackBarMsg(
      "Đăng ký thành công! Đang chuyển đến trang đăng nhập",
      "success"
    );
    dispatch(openSnackBar(msg));
    navigate("/login");
  } catch (error) {
    const msg = setSnackBarMsg("Đăng ký thất bại!!", "error");
    dispatch(openSnackBar(msg));
  }
};
