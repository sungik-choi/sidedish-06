import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { API_URL, useFetch } from '../../utils/useFetch';
import ProductImages from './ProductImages/ProductImages';
import ProductInfo from './ProductInfo/ProductInfo';
import Selector from './Selector';
import CartButton from './CartButton';
import TotalPrice from './TotalPrice';
import Placeholder from '../Placeholder';
import { fadeIn } from '../../utils/animation.js';
import { IconContext } from 'react-icons';
import { IoIosClose } from 'react-icons/io';

const DimmedLayerDiv = styled.div`
  z-index: 50;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: var(--black);
  opacity: 0.5;
  animation: ${fadeIn(0.5)} 0.3s;
`;

const ProductDetailWrap = styled.div`
  z-index: 100;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  width: var(--width);
  height: 45rem;
  padding: 2rem;
  padding-top: 3rem;
  background-color: var(--white);
  animation: ${fadeIn(1)} 0.5s;
`;

const ProductImagesWrap = styled.div`
  width: 40%;
`;

const ProductInfoWrap = styled.div`
  display: flex;
  flex-direction: column;
  width: calc(60% - 2rem);
  margin-left: 2rem;
`;

const CloseButton = styled.button`
  cursor: pointer;
  position: absolute;
  top: 0;
  right: 0;
  padding: 1rem;
  background: none;
`;

const PlaceholderWrap = styled.div`
  z-index: 100;
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--white);
  width: 100%;
  height: 100%;
`;

const ProductDetail = ({ productType, hash, onClick }) => {
  const [detailData, setDetailData] = useState({ data: [] });
  const isDetailDataLoading = useFetch(API_URL(productType, hash), setDetailData);
  const { title, thumb_images, description, point, delivery_info, delivery_fee, salePrice } = detailData.data;

  const MAX_QUANTITY = 99;
  const MIN_QUANTITY = 1;
  const UNIT = '원';

  const salePriceNum = salePrice.replace(UNIT, '');
  const [quantity, setQuantity] = useState(MIN_QUANTITY);
  const [totalPrice, setTotalPrice] = useState(quantity * salePriceNum);

  const priceChangeHandler = e => {
    if (e.target.value > MAX_QUANTITY) e.target.value = MIN_QUANTITY;
    setQuantity(e.target.value);
  };

  useEffect(() => setTotalPrice(quantity * salePriceNum), [quantity]);

  return (
    <>
      <DimmedLayerDiv onClick={() => onClick()} />
      <ProductDetailWrap>
        <CloseButton onClick={() => onClick()}>
          <IconContext.Provider value={{ size: '2rem' }}>
            <IoIosClose />
          </IconContext.Provider>
        </CloseButton>
        {!isDetailDataLoading && (
          <PlaceholderWrap>
            <Placeholder />
          </PlaceholderWrap>
        )}
        <ProductImagesWrap>
          <ProductImages thumbImages={thumb_images} />
        </ProductImagesWrap>
        <ProductInfoWrap>
          <ProductInfo title={title} description={description} deliveryInfo={delivery_info} deliveryFee={delivery_fee} point={point} salePrice={salePriceNum} />
          <Selector quantity={quantity} min={MIN_QUANTITY} max={MAX_QUANTITY} onChange={priceChangeHandler} />
          <TotalPrice price={totalPrice} />
          <CartButton onClick={onClick} />
        </ProductInfoWrap>
      </ProductDetailWrap>
    </>
  );
};

export default ProductDetail;
