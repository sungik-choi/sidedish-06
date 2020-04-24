import React from 'react';
import styled from 'styled-components';
import Slider from './Slider/Slider';
import Title from './Title';

const ProductListWrap = styled.div`
  border: 1px solid red;
  margin: 0 auto;
  padding: 8rem 0;
`;

const ProductList = ({ list }) => {
  return (
    <ProductListWrap>
      <Title />
      <Slider list={list} />
    </ProductListWrap>
  );
};

export default ProductList;
