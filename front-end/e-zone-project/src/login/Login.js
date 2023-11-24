import {
  Box,
  Button,
  InputLabel,
  FormControl,
  InputAdornment,
  OutlinedInput,
  IconButton,
  TextField,
  Typography,
  Card,
} from "@mui/material";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import React, { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { loginUser } from "../redux/request/apiRequest";
import { useDispatch, useSelector } from "react-redux";

import SnackBar from "../components/SnackBar";
import { closeSnackBar, openSnackBar } from "../redux/slice/snackBarSlice";
import { MyButton } from "../components/MyButton";

import "./Login.scss";

function Login(props) {
  const user = localStorage.getItem("currentUser");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleIsLogIn = () => {
    navigate("/");
  };

  const handleLogin = () => {
    const user = {
      username: username,
      password: password,
    };
    if (user.username == "" || user.password == "") {
      dispatch(
        openSnackBar({
          msg: "Vui lòng điền tên tài khoản và mật khẩu!!",
          severity: "warning",
        })
      );
      return;
    } else {
      dispatch(
        openSnackBar({
          msg: "Đang đăng nhập",
          severity: "info",
        })
      );
      loginUser(user, dispatch, navigate);
      dispatch(closeSnackBar());
    }
  };
  useEffect(() => {
    if (user != null) {
      handleIsLogIn();
    }
  }, []);
  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": {
          m: 1,
          width: "40ch",
          marginTop: 10,
          margin: "auto",
          padding: 4,
        },
      }}
      noValidate
      autoComplete="off"
    >
      <Card
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: 3,
          alignItems: "center",
        }}
      >
        <NavLink to="/">
          <Typography variant="h2" color={"#1e81b0"}>
            E-ZONE
          </Typography>
        </NavLink>
        <FormControl fullWidth>
          <TextField
            onChange={(e) => {
              setUsername(e.target.value);
            }}
            id="outlined-basic"
            label="Tên tài khoản"
            variant="outlined"
          />
        </FormControl>
        <FormControl fullWidth variant="outlined" className="password">
          <InputLabel htmlFor="outlined-adornment-password">
            Mật khẩu
          </InputLabel>
          <OutlinedInput
            onChange={(e) => {
              setPassword(e.target.value);
            }}
            id="outlined-adornment-password"
            type={showPassword ? "text" : "password"}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                  edge="end"
                >
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
            label="Password"
          />
        </FormControl>
        <FormControl fullWidth>
          <MyButton size="large" onClick={handleLogin}>
            ĐĂNG NHẬP
          </MyButton>
        </FormControl>
        <Typography>
          Chưa có tài khoản? <NavLink to={"/register"}>Đăng ký ngay!</NavLink>
        </Typography>
      </Card>
      <SnackBar />
    </Box>
  );
}

export default Login;
