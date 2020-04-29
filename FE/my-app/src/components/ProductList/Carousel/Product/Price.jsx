import React from 'react';
import styled from 'styled-components';
import { UNIT } from 'constants/constants';

const Price = ({ originPrice, salePrice }) => {
  return (
    <PriceDd>
      <SalePriceP>
        {salePrice}
        <UnitSpan>{UNIT}</UnitSpan>
      </SalePriceP>
      {originPrice && <OriginPriceP>{originPrice}</OriginPriceP>}
    </PriceDd>
  );
};

const PriceDd = styled.dd`
  display: flex;
  align-items: flex-end;
  justify-content: center;
  margin-top: 1rem;
`;

const OriginPriceP = styled.p`
  font-size: 0.8125rem;
  color: var(--gray-4);
  text-decoration: line-through;
`;

const SalePriceP = styled.p`
  color: var(--green);
  font-size: 1.625rem;
  font-weight: bold;
  margin-right: 0.5rem;
`;

const UnitSpan = styled.span`
  display: inline-block;
  font-size: 1rem;
  margin-left: 0.1rem;
  transform: translateY(-0.1rem);
`;

export default Price;
