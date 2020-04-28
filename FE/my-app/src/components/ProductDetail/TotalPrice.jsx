import React from 'react';
import styled from 'styled-components';

const PriceWrap = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: var(--green);
  text-align: right;
  margin: 1.5rem 0;
  font-size: 2.5rem;
  font-weight: 600;
`;

const UnitSpan = styled.span`
  display: inline-block;
  font-size: 1.5rem;
  margin-left: 0.1rem;
  transform: translateY(-0.1rem);
`;

const PriceTextSpan = styled.span`
  color: var(--black);
  font-size: 1rem;
`;

const TOTAL_PRICE_TEXT = '총 상품금액';

const TotalPrice = ({ price }) => {
  return (
    <PriceWrap>
      <PriceTextSpan>{TOTAL_PRICE_TEXT}</PriceTextSpan>
      <span>
        {price}
        <UnitSpan>원</UnitSpan>
      </span>
    </PriceWrap>
  );
};

export default TotalPrice;
