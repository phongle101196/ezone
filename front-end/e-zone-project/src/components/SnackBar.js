import { Alert, Snackbar } from "@mui/material";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { closeSnackBar } from "../redux/slice/snackBarSlice";
import "./SnackBar.scss";

function SnackBar(props) {
  const snackBar = useSelector((state) => state.snackBar);
  const dispatch = useDispatch();
  const handleSnackClose = () => {
    dispatch(closeSnackBar());
  };
  return (
    <Snackbar
      open={snackBar.openSnackBar}
      autoHideDuration={1500}
      onClose={handleSnackClose}
    >
      <Alert className="snack-bar" severity={snackBar.severity}>
        {snackBar.msg}
      </Alert>
    </Snackbar>
  );
}

export default SnackBar;
