import { openSnackBar } from "../slice/snackBarSlice";

export const openSnackBarRequest = (msg, dispatch) => {
  dispatch(openSnackBar(msg));
};

export const setSnackBarMsg = (msg, severity) => {
  return {
    msg: msg,
    severity: severity,
  };
};
