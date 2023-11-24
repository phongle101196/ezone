import httpClient from "../../api/httpClient";
import {
  getProductFailed,
  getProductStart,
  getProductSuccess,
} from "../slice/productSlice";

export const getProduct = async (categoryId, size, page) => {
  try {
    const res = await httpClient.get(
      `products?categoryId=${categoryId}&page=${page}&size=${size}&sort=id,desc`
    );
    return res.data;
  } catch (error) {}
};

export const getAllSmartPhone = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=1&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/smartphone");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};

export const getAllLaptop = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=2&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/laptop");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};

export const getAllTablet = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=3&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/tablet");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};

export const getAllSmartwatch = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=4&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/smartwatch");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};

export const getAllWatch = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=5&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/watch");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};

export const getAllCableCharger = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=6&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/cable-charger");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};

export const getAllHeadphone = async (info, dispatch, navigate) => {
  dispatch(getProductStart());
  try {
    const res = await httpClient.get(
      `products?categoryId=7&page=${info.page}&size=${info.size}&search=${info.search}&sort=id,desc`
    );
    dispatch(getProductSuccess(res.data));
    navigate("/headphone");
  } catch (error) {
    dispatch(getProductFailed());
    navigate("/err");
  }
};
