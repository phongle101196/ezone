import React from "react";
import { Outlet } from "react-router-dom";
import HeaderWraper from "./header/HeaderWraper";
import FooterWraper from "./footer/FooterWraper";
import { Container } from "@mui/material";
import SnackBar from "../components/SnackBar";
import Chatbox from "../features/chatbox/ChatBox";

function Features(props) {
  return (
    <div>
      <HeaderWraper />
      <Container maxWidth>
        <Outlet />
      </Container>
      <Chatbox />
      <FooterWraper />
      <SnackBar />
    </div>
  );
}

export default Features;
