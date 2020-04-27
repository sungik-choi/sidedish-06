import React from 'react';
import styled from 'styled-components';
import Title from './Title';
import Image from './Image';
import Price from './Price';
import Badge from './Badge';

const ProductDiv = styled.div`
  cursor: pointer;
  width: var(--slider-width);
`;

const ProductInfoDl = styled.dl`
  text-align: center;
  padding: 1rem 0.625rem 0.75rem 0.625rem;
`;

const Product = ({ list: { detail_hash, alt, badge, delivery_type, description, image, n_price, s_price, title }, onClick }) => {
  return (
    <ProductDiv onClick={() => onClick(detail_hash)}>
      <Image alt={alt} src={image} deliveryType={delivery_type} />
      <ProductInfoDl>
        <Title title={title} desc={description} />
        <Price originPrice={n_price} salePrice={s_price} />
      </ProductInfoDl>
      <Badge list={badge} />
    </ProductDiv>
  );
};

export default Product;
