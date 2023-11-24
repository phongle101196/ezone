import { Box, Container } from "@mui/material";
import React, { useEffect, useState } from "react";
import CategoryBox from "../../components/CategoryBox";
import { getProduct } from "../../redux/request/apiProductRequest";

function Home(props) {
  let [smartphoneItem, setSmartphoneItem] = useState(null);
  let [laptopItem, setLaptopItem] = useState(null);
  let [smartwatchItem, setSmartwatchItem] = useState(null);
  let [tabletItem, setTabletItem] = useState(null);
  let [relaItem, setRelaItem] = useState(null);

  useEffect(() => {
    const fetchData1 = async () => {
      const data = await getProduct(1, 5, 0);
      setSmartphoneItem(data?.content);
    };

    const fetchData2 = async () => {
      const data = await getProduct(2, 5, 0);
      setLaptopItem(data?.content);
    };

    const fetchData3 = async () => {
      const data = await getProduct(3, 5, 0);
      setTabletItem(data?.content);
    };

    const fetchData4 = async () => {
      const data = await getProduct(4, 5, 0);
      setSmartwatchItem(data?.content);
    };

    const fetchData5 = async () => {
      const data = await getProduct(1, 5, 1);
      setRelaItem(data?.content);
    };

    // call the function
    fetchData1();
    fetchData2();
    fetchData3();
    fetchData4();
    fetchData5();
    // make sure to catch any error
    //   .catch(console.error);
  }, []);
  // console.log(smartphoneItem);
  return (
    <Box sx={{ display: "flex", flexDirection: "column" }}>
      {/* <CategoryBox title="ĐANG GIẢM GIÁ"  /> */}

      <CategoryBox
        title="ĐIỆN THOẠI THÔNG MINH"
        path={"/smartphone"}
        items={smartphoneItem}
      />
      <CategoryBox title="LAPTOP" path={"/laptop"} items={laptopItem} />
      <CategoryBox
        title="ĐỒNG HỒ THÔNG MINH"
        path={"/smartwatch"}
        items={smartwatchItem}
      />
      <CategoryBox title="MÁY TÍNH BẢNG" path={"/tablet"} items={tabletItem} />
      <CategoryBox title="GỢI Ý" path={"/watch"} items={relaItem} />
    </Box>
  );
}

export default Home;
