import * as React from "react";
import { createBrowserRouter } from "react-router-dom";
import Features from "../features/Features";
import Login from "../login/Login";
import Register from "../register/Register";
import Home from "../features/home/Home";
import Cart from "../features/cart/Cart";
import Product from "../features/product/Product";
import Error from "../features/error/Error";
import Smartphone from "../features/product/Smartphone";
import Laptop from "../features/product/Laptop";
import Tablet from "../features/product/Tablet";
import Smartwatch from "../features/product/Smartwatch";
import Watch from "../features/product/Watch";
import Headphone from "../features/product/Headphone";
import CableCharger from "../features/product/CableCharger";
import Manager from "../features/admin/Manager";
import Order from "../features/order/Order";
import MyOrder from "../features/order/MyOrder";
import MyOrderAddress from "../features/order/MyOrderAddress";
import OrderDetail from "../features/order/OrderDetail";
import OrderManager from "../features/admin/OrderManager";
import ProductManager from "../features/admin/ProductManager";
import StaffManager from "../features/admin/StaffManager";
import NewsManager from "../features/admin/NewsManager";
import Overview from "../features/admin/Overview";
import OrderDetailManager from "../features/admin/OrderDetailManager";
import MyAcc from "../features/myacc/MyAcc";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Features />,
    children: [
      {
        path: "",
        element: <Home />,
      },
      {
        path: "smartphone",
        element: <Smartphone />,
      },
      {
        path: "laptop",
        element: <Laptop />,
      },
      {
        path: "tablet",
        element: <Tablet />,
      },
      {
        path: "smartwatch",
        element: <Smartwatch />,
      },
      {
        path: "watch",
        element: <Watch />,
      },
      {
        path: "cable-charger",
        element: <CableCharger />,
      },
      {
        path: "headphone",
        element: <Headphone />,
      },
      {
        path: "news",
        element: <div>News</div>,
      },
      {
        path: "err",
        element: <Error />,
      },
      {
        path: "account",
        element: <MyAcc />,
      },
      {
        path: "order",
        element: <Order />,
        children: [
          {
            path: "",
            element: <MyOrder />,
          },
          {
            path: "myOrderAddress",
            element: <MyOrderAddress />,
          },
          {
            path: "orderDetail",
            element: <OrderDetail />,
          },
        ],
      },
      {
        path: "cart",
        element: <Cart />,
      },
      {
        path: "product/:id",
        element: <Product />,
      },

      {
        path: "manager",
        element: <Manager />,
        children: [
          {
            path: "",
            element: <Overview />,
          },
          {
            path: "don-hang",
            element: <OrderManager />,
          },
          {
            path: "chi-tiet-don-hang",
            element: <OrderDetailManager />,
          },
          {
            path: "san-pham",
            element: <ProductManager />,
          },
          {
            path: "nhan-vien",
            element: <StaffManager />,
          },
          {
            path: "tin-tuc",
            element: <NewsManager />,
          },
        ],
      },
    ],
  },
  {
    path: "login",
    element: <Login />,
  },
  {
    path: "register",
    element: <Register />,
  },
  {
    path: "*",
    element: <Error />,
  },
]);

export default router;
