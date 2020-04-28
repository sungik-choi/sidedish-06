import React from 'react';
import styled from 'styled-components';

const CartBtn = styled.button`
  cursor: pointer;
  width: 100%;
  height: 4rem;
  background-color: var(--green);
  color: var(--white);
  text-align: center;
  font-size: 1.5rem;
  font-weight: 600;
`;

const BUTTON_TEXT = '장바구니 담기';

const CartButton = ({ onClick }) => {
  return <CartBtn onClick={() => onClick()}>{BUTTON_TEXT}</CartBtn>;
};

export default CartButton;
