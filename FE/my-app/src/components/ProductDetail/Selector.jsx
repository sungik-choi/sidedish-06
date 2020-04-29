import React from 'react';
import styled from 'styled-components';

const Selector = ({ min, max, quantity, onChange }) => {
  return (
    <SelectorWrap>
      {QUANTITY_TEXT}
      <QuantityInput type="number" value={quantity} min={min} max={max} onChange={onChange} />
    </SelectorWrap>
  );
};

const QUANTITY_TEXT = '수량';

const SelectorWrap = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem 0;
  margin-top: 1rem;
  border-top: 1px solid var(--gray-3);
  border-bottom: 1px solid var(--gray-3);
`;

const QuantityInput = styled.input`
  font-size: 1rem;
  width: 3rem;
  height: 2rem;
  text-align: center;
`;

export default Selector;
