import React, { useState } from 'react';
import styled from 'styled-components';
import { API_URL, useFetch } from '../../utils/useFetch';
import ProductImages from './ProductImages/ProductImages';
import ProductInfo from './ProductInfo/ProductInfo';
import Selector from './Selector';
import CartButton from './CartButton';
import TotalPrice from './TotalPrice';
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
  padding-top: 2.5rem;
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

const ProductDetail = ({ productType, hash, onClick }) => {
  const [detailData, setDetailData] = useState({ data: [] });
  const isDetailDataLoading = useFetch(API_URL(productType, hash), setDetailData);
  const { title, thumb_images, product_description, point, delivery_info, delivery_fee, prices, originPrice, salePrice } = detailData.data;

  return (
    <>
      <DimmedLayerDiv onClick={() => onClick()} />
      <ProductDetailWrap>
        <CloseButton onClick={() => onClick()}>
          <IconContext.Provider value={{ size: '2rem' }}>
            <IoIosClose />
          </IconContext.Provider>
        </CloseButton>
        <ProductImagesWrap>
          <ProductImages thumbImages={thumb_images} />
        </ProductImagesWrap>
        <ProductInfoWrap>
          <ProductInfo
            title={title}
            description={product_description}
            deliveryInfo={delivery_info}
            deliveryFee={delivery_fee}
            point={point}
            prices={prices}
            originPrice={originPrice}
            salePrice={salePrice}
          />
          <Selector />
          <CartButton />
          <TotalPrice />
        </ProductInfoWrap>
      </ProductDetailWrap>
    </>
  );
};

export default ProductDetail;
