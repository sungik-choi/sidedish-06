import React, { useState } from 'react';
import styled from 'styled-components';
import { API_URL, useFetch } from '../../utils/useFetch';
import ProductImages from './ProductImages/ProductImages';
import ProductInfo from './ProductInfo/ProductInfo';
import Selector from './Selector';
import CartButton from './CartButton';
import TotalPrice from './TotalPrice';

const DimmedLayerDiv = styled.div`
  z-index: 50;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
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

const ProductDetail = ({ productType, hash, onClick }) => {
  const [detailData, setDetailData] = useState({ data: [] });
  const isDetailDataLoading = useFetch(API_URL(productType, hash), setDetailData);
  const { title, top_image, thumb_images, description, point, delivery_info, delivery_fee, originPrice, salePrice } = detailData;

  return (
    <>
      <DimmedLayerDiv onClick={() => onClick()} />
      <ProductDetailWrap>
        <ProductImages topImage={top_image} thumbImage={thumb_images} />
        <ProductInfo title={title} description={description} deliveryInfo={delivery_info} deliveryFee={delivery_fee} point={point} />
        <Selector />
        <CartButton />
        <TotalPrice originPrice={originPrice} salePrice={salePrice} />
      </ProductDetailWrap>
    </>
  );
};

export default ProductDetail;
