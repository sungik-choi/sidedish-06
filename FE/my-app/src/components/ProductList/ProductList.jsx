import React from 'react';
import styled from 'styled-components';
import _v from '../Variables';

function ProductList(props) {
  return (
    <div>
      <img src={props.list.image} />
    </div>
  );
}

export default ProductList;
