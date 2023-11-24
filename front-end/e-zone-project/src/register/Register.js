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
  MenuItem,
  Select,
} from "@mui/material";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import React, { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { registerUser } from "../redux/request/apiRequest";
import { useDispatch } from "react-redux";
import SnackBar from "../components/SnackBar";
import { setSnackBarMsg } from "../redux/request/snackBarRequest";
import { openSnackBar } from "../redux/slice/snackBarSlice";
import { MyButton } from "../components/MyButton";

import "./Register.scss";

function Register(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [password2, setPassword2] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [fullName, setFullName] = useState("");
  const [gender, setGender] = useState("");
  const [address, setAddress] = useState("");

  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const [showPassword2, setShowPassword2] = React.useState(false);

  const handleClickShowPassword2 = () => setShowPassword2((show) => !show);

  const handleMouseDownPassword2 = (event) => {
    event.preventDefault();
  };

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleRegister = (e) => {
    const user = {
      username: username,
      password: password,
      password2: password2,
      email: email,
      phoneNumber: phoneNumber,
      fullName: fullName,
      gender: gender,
      address: address,
    };
    if (
      username == "" ||
      password == "" ||
      password2 == "" ||
      email == "" ||
      fullName == ""
    ) {
      const msg = setSnackBarMsg(
        "Vui lòng điền đủ những thông tin bắt buộc.",
        "warning"
      );
      dispatch(openSnackBar(msg));
      return;
    }
    if (password != password2) {
      const msg = setSnackBarMsg("Mật khẩu không khớp", "warning");
      dispatch(openSnackBar(msg));
      return;
    }
    registerUser(user, dispatch, navigate);
  };
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
        className="reg-form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: 3,
          alignItems: "center",
        }}
      >
        <NavLink to={"/"}>
          <Typography variant="h2" color={"#1e81b0"}>
            E-ZONE
          </Typography>
        </NavLink>

        <Typography variant="h5" color={"#1e81b0"}>
          Đăng ký tài khoản
        </Typography>

        <FormControl fullWidth>
          <TextField
            onChange={(e) => {
              setUsername(e.target.value);
            }}
            id="outlined-basic"
            label="Nhập tên tài khoản"
            variant="outlined"
          />
        </FormControl>

        <FormControl fullWidth variant="outlined">
          <InputLabel htmlFor="outlined-adornment-password">
            Nhập mật khẩu
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
            label="Nhập mật khẩu"
          />
        </FormControl>

        <FormControl fullWidth variant="outlined">
          <InputLabel htmlFor="outlined-adornment-password2">
            Nhập lại mật khẩu
          </InputLabel>
          <OutlinedInput
            onChange={(e) => {
              setPassword2(e.target.value);
            }}
            id="outlined-adornment-password2"
            type={showPassword2 ? "text" : "password"}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword2}
                  onMouseDown={handleMouseDownPassword2}
                  edge="end"
                >
                  {showPassword2 ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
            label="Nhập lại mật khẩu"
          />
        </FormControl>
        <FormControl fullWidth>
          <TextField
            onChange={(e) => {
              setEmail(e.target.value);
            }}
            id="outlined-basic"
            label="Nhập email"
            variant="outlined"
          />
        </FormControl>
        <FormControl fullWidth>
          <TextField
            onChange={(e) => {
              setPhoneNumber(e.target.value);
            }}
            id="outlined-basic"
            label="Nhập số điện thoại"
            variant="outlined"
          />
        </FormControl>
        <FormControl fullWidth>
          <TextField
            onChange={(e) => {
              setFullName(e.target.value);
            }}
            id="outlined-basic"
            label="Nhập họ tên"
            variant="outlined"
          />
        </FormControl>
        <FormControl fullWidth>
          <TextField
            onChange={(e) => {
              setAddress(e.target.value);
            }}
            id="outlined-basic"
            label="Nhập địa chỉ"
            variant="outlined"
          />
        </FormControl>
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Chọn giới tính</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            label="Chọn giới tính"
            onChange={(e) => {
              setGender(e.target.value);
            }}
          >
            <MenuItem value={"MALE"}>Nam</MenuItem>
            <MenuItem value={"FEMALE"}>Nữ</MenuItem>
            <MenuItem value={"OTHER"}>Khác</MenuItem>
          </Select>
        </FormControl>

        <FormControl fullWidth>
          <MyButton size="large" onClick={handleRegister}>
            ĐĂNG KÝ
          </MyButton>
        </FormControl>

        <Typography>
          Đã có tài khoản? <NavLink to={"/login"}>Đăng nhập!</NavLink>
        </Typography>
      </Card>
      <SnackBar />
    </Box>
  );
}

export default Register;
