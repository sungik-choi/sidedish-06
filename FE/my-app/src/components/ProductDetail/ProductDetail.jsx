import React, { useState } from 'react';
import styled from 'styled-components';
import ProductImages from './ProductImages/ProductImages';
import ProductInfo from './ProductInfo/ProductInfo';
import Selector from './Selector';
import CartButton from './CartButton';
import TotalPrice from './TotalPrice';
import { API_URL, useFetch } from '../Fetch';

const DimmedLayerDiv = styled.div`
  z-index: 50;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  overflow-y: hidden;
  background-color: var(--black);
  opacity: 0.5;
`;

const ProductDetailWrap = styled.div`
  z-index: 100;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: var(--width);
  height: 10rem;
  padding: 2rem;
  background-color: var(--white);
`;

const ProductDetail = ({ type, hash }) => {
  const [detailData, setDetailData] = useState({ data: [] });
  const isDetailDataLoading = useFetch(API_URL(type, hash), setDetailData);
  console.log(detailData);
  return (
    <>
      <DimmedLayerDiv />
      <ProductDetailWrap>
        <ProductImages />
        <ProductInfo />
        <Selector />
        <CartButton />
        <TotalPrice />
      </ProductDetailWrap>
    </>
  );
};

export default ProductDetail;
