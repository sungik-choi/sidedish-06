import React, { useState } from 'react';
import styled from 'styled-components';
import { API_URL, useFetch } from '../../utils/useFetch';
import Carousel from './Carousel/Carousel';
import Title from './Title';

const ProductListWrap = styled.div`
  margin: 0 auto;
  padding-top: 6rem;
`;

const ProductListPlaceholder = styled.div`
  height: 33.75rem; /* 540 / 16 */
`;

const ProductList = ({ productType, onClick }) => {
  const [list, setList] = useState({ body: [] });
  const isListLoading = useFetch(API_URL(productType), setList);

  return (
    <>
      {!isListLoading && <ProductListPlaceholder />}
      <ProductListWrap>
        <Title />
        <Carousel list={list} onClick={onClick} />
      </ProductListWrap>
    </>
  );
};

export default ProductList;
