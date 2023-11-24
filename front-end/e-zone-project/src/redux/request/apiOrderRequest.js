import httpClient from "../../api/httpClient";
import { getOrderListSuccess, getOrderSuccess } from "../slice/orderSlice";

export const getMyOrder = async (userId, page, dispatch) => {
  try {
    const res = await httpClient.get(
      `orders?userId=${userId}&page=${page}&sort=createdDate,desc`
    );
    dispatch(getOrderListSuccess(res.data));
  } catch (error) {}
};

export const getOrderDetail = async (orderId, dispatch) => {
  try {
    const res = await httpClient.get(`orders/${orderId}`);
    dispatch(getOrderSuccess(res.data));
  } catch (error) {}
};

export const getAllOrder = async (page, dispatch) => {
  try {
    const res = await httpClient.get(
      `orders?page=${page}&sort=status,asc&sort=createdDate,desc`
    );
    dispatch(getOrderListSuccess(res.data));
  } catch (error) {}
};
