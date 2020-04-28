import React from 'react';
import styled from 'styled-components';

const PriceWrap = styled.div`
  text-align: right;
  margin-top: 2rem;
  font-size: 2.5rem;
  font-weight: 600;
`;

const UnitSpan = styled.span`
  display: inline-block;
  font-size: 1.5rem;
  margin-left: 0.1rem;
  transform: translateY(-0.1rem);
`;

const Price = ({ prices, salePrice }) => {
  return (
    <PriceWrap>
      {prices}
      <UnitSpan>원</UnitSpan>
    </PriceWrap>
  );
};

export default Price;
