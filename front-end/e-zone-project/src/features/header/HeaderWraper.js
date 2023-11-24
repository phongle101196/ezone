import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Avatar from "@mui/material/Avatar";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import { NavLink, useNavigate } from "react-router-dom";
import AccountCircle from "@mui/icons-material/AccountCircle";
import { useDispatch, useSelector } from "react-redux";
import LoginIcon from "@mui/icons-material/Login";
import { styled, alpha } from "@mui/material/styles";
import InputBase from "@mui/material/InputBase";
import SearchIcon from "@mui/icons-material/Search";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import NewspaperIcon from "@mui/icons-material/Newspaper";
import RedditIcon from "@mui/icons-material/Reddit";

import "./HeaderWraper.scss";
import { logout } from "../../redux/slice/authSlice";
import { Badge } from "@mui/material";
import { getCart } from "../../redux/request/apiCartRequest";

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(3),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("md")]: {
      width: "20ch",
    },
  },
}));

function HeaderWraper(props) {
  const user = JSON.parse(localStorage.getItem("currentUser"))?.user;

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const pages = [
    { name: "ĐIỆN THOẠI", path: "/smartphone" },
    { name: "LAPTOP", path: "/laptop" },
    { name: "MÁY TÍNH BẢNG", path: "/tablet" },
    { name: "SMARTWATCH", path: "/smartwatch" },
    { name: "ĐỒNG HỒ", path: "/watch" },
    { name: "TAI NGHE", path: "/headphone" },
    { name: "CÁP, SẠC", path: "/cable-charger" },
  ];
  const settings = [
    // { name: `${user.fullName}`, path: "/account" },
    { name: "Đơn hàng", path: "/order" },
  ];

  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem("currentUser");
    navigate("/");
  };

  const handleGoLoginPage = () => {
    navigate("/login");
  };

  const cart = useSelector((state) => state.cart.getCart.cart);

  const getCartInfo = async () => {
    await getCart(dispatch);
  };

  React.useEffect(() => {
    getCartInfo();
  }, []);

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" style={{ background: "#1e81b0" }}>
        <Toolbar>
          <Box sx={{ display: "flex" }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>

            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block" },
              }}
            >
              {pages.map((page, index) => (
                <NavLink key={index} to={page.path}>
                  <MenuItem
                    onClick={() => {
                      handleCloseNavMenu();
                    }}
                  >
                    <Typography textAlign="center">{page.name}</Typography>
                  </MenuItem>
                </NavLink>
              ))}
            </Menu>
          </Box>
          <NavLink to={""}>
            <RedditIcon sx={{ display: "flex", mr: 1, fontSize: 45 }} />
          </NavLink>
          <Typography
            variant="h5"
            noWrap
            sx={{
              mr: 2,
              display: { xs: "none", sm: "flex" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            E-ZONE
          </Typography>
          <Search
            sx={{
              display: "flex",
            }}
          >
            <SearchIconWrapper>
              <SearchIcon />
            </SearchIconWrapper>
            <StyledInputBase
              placeholder="Tìm kiếm..."
              inputProps={{ "aria-label": "search" }}
            />
          </Search>

          <NavLink to={"/news"}>
            <Tooltip title={"Trang tin tức"}>
              <IconButton sx={{ p: 0, mr: 2 }}>
                <NewspaperIcon sx={{ fontSize: "35px" }} />
              </IconButton>
            </Tooltip>
          </NavLink>

          <NavLink to={"/cart"}>
            <Tooltip title={"Giỏ hàng"} sx={{ mr: 2 }}>
              <Badge badgeContent={cart?.length} color="warning">
                <LocalMallIcon sx={{ fontSize: "35px" }} />
              </Badge>
            </Tooltip>
          </NavLink>

          {user ? (
            <Box sx={{ flexGrow: 0 }}>
              <Tooltip title={`Xin chào: ${user?.fullName}!`}>
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                  <Avatar
                    alt={user?.fullName}
                    src={user.avatar ? user?.avatar : <AccountCircle />}
                  />
                </IconButton>
              </Tooltip>
              <Menu
                sx={{ mt: "45px" }}
                id="menu-appbar"
                anchorEl={anchorElUser}
                anchorOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}
              >
                {user?.role != "MEMBER" ? (
                  <NavLink to={"/manager"}>
                    <MenuItem onClick={handleCloseUserMenu}>
                      <Typography textAlign="center">Trang quản lý*</Typography>
                    </MenuItem>
                  </NavLink>
                ) : null}
                <NavLink to={"/account"}>
                  <MenuItem onClick={handleCloseUserMenu}>
                    <Typography textAlign="center">Tài khoản</Typography>
                  </MenuItem>
                </NavLink>
                {settings.map((setting) => (
                  <NavLink key={setting} to={setting.path}>
                    <MenuItem onClick={handleCloseUserMenu}>
                      <Typography textAlign="center">{setting.name}</Typography>
                    </MenuItem>
                  </NavLink>
                ))}
                <MenuItem
                  onClick={() => {
                    handleLogout();
                    handleCloseUserMenu();
                  }}
                  sx={{ ":hover": { color: "#1e81b0" } }}
                >
                  Đăng xuất
                </MenuItem>
              </Menu>
            </Box>
          ) : (
            <Tooltip title="Đăng nhập" onClick={handleGoLoginPage}>
              <IconButton aria-label="Đăng nhập" sx={{ p: 0 }}>
                <LoginIcon sx={{ fontSize: 35 }} />
              </IconButton>
            </Tooltip>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default HeaderWraper;
