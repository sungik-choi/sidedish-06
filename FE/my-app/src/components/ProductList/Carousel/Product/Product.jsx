import React from 'react';
import styled from 'styled-components';
import Title from './Title';
import Image from './Image';
import Price from './Price';
import Badge from './Badge';
import { UNIT } from 'constants/constants';

const Product = ({ list, onClick }) => {
  const { hash, alt, badge, delivery_type, description, image, originPrice, salePrice, title } = list;
  const salePriceExceptUnit = salePrice.replace(UNIT, '');

  return (
    <ProductWrap onClick={() => onClick(hash)}>
      <Image alt={alt} src={image} deliveryType={delivery_type} />
      <ProductInfoDl>
        <Title title={title} desc={description} />
        <Price originPrice={originPrice} salePrice={salePriceExceptUnit} />
      </ProductInfoDl>
      <Badge list={badge} />
    </ProductWrap>
  );
};

const ProductWrap = styled.div`
  cursor: pointer;
  width: var(--slider-width);
`;

const ProductInfoDl = styled.dl`
  text-align: center;
  padding: 1rem 0.625rem 0.75rem 0.625rem;
`;

export default Product;
