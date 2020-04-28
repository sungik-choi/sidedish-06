import React from 'react';
import Title from './Title';
import Details from './Details';
import Price from './Price';

const ProductInfo = ({ title, description, deliveryInfo, deliveryFee, point, prices, originPrice, salePrice }) => {
  return (
    <>
      <Title title={title} description={description} />
      <Details deliveryInfo={deliveryInfo} deliveryFee={deliveryFee} point={point} />
      <Price prices={prices} originPrice={originPrice} salePrice={salePrice} />
    </>
  );
};

export default ProductInfo;
