import React from 'react';
import styled from 'styled-components';
import Product from './Product/Product';
import NavButton from './NavButton';

const SliderWrap = styled.div`
  border: 1px solid blue;
`;

const ProductUl = styled.ul`
  display: flex;
  border: 1px solid green;
`;

const Slider = ({ list }) => {
  const products = list.body.map(productData => <Product key={productData.detail_hash} list={productData} />);

  return (
    <SliderWrap>
      <ProductUl>{products}</ProductUl>
      <NavButton />
    </SliderWrap>
  );
};

export default Slider;
