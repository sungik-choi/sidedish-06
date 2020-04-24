import React from 'react';
import styled from 'styled-components';
import Carousel from './Carousel/Carousel';
import Title from './Title';

const ProductListWrap = styled.div`
  margin: 0 auto;
  padding: 6rem 0;
`;

const ProductList = ({ list }) => {
  return (
    <ProductListWrap>
      <Title />
      <Carousel list={list} />
    </ProductListWrap>
  );
};

export default ProductList;
