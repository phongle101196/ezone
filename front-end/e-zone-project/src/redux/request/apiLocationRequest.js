import httpClient from "../../api/httpClient";
import {
  getDistrict,
  getProvince,
  getWard,
  resetWard,
} from "../slice/locationSlice";

export const getDataWardByDistrictCode = async (districtCode, dispatch) => {
  try {
    const res = await httpClient.get(`vn_units/wards/${districtCode}`);
    dispatch(getWard(res.data));
  } catch (error) {}
};

export const getDataDistrictByProvinceCode = async (provinceCode, dispatch) => {
  try {
    const res = await httpClient.get(`vn_units/districts/${provinceCode}`);
    dispatch(getDistrict(res.data));
  } catch (error) {}
};

export const getDataProvince = async (dispatch) => {
  try {
    const res = await httpClient.get("vn_units/provinces");
    dispatch(getProvince(res.data));
  } catch (error) {}
};

export const resetDataWard = (dispatch) => {
  dispatch(resetWard(dispatch));
};
