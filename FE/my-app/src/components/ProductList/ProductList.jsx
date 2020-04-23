import React from 'react';
import styled from 'styled-components';
import Slider from './Slider/Slider';
import Title from './Title';

const ProductList = ({ list }) => {
  return (
    <div>
      <img src={list.image} />
    </div>
  );
};

export default ProductList;
