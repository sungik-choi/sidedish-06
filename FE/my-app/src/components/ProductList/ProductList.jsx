import React, { useState } from 'react';
import styled from 'styled-components';
import Carousel from './Carousel/Carousel';
import Title from './Title';
import { API_URL, useFetch } from '../Fetch';

const ProductListWrap = styled.div`
  margin: 0 auto;
  padding: 6rem 0 2rem 0;
`;

const ProductList = ({ type, onClick }) => {
  const [list, setList] = useState({ body: [] });
  const isListLoading = useFetch(API_URL(type), setList);

  return (
    <ProductListWrap>
      <Title />
      <Carousel list={list} onClick={onClick} />
    </ProductListWrap>
  );
};

export default ProductList;
