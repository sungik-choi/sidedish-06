import React from 'react';
import styled from 'styled-components';

const ItemLi = styled.li`
  cursor: pointer;
  padding: 0 0.5rem;
  border-right: 1px solid var(--gray-3);
  &:last-child {
    border-right: none;
  }
  &:hover {
    color: var(--green);
  }
`;

const Item = ({ name }) => {
  return <ItemLi>{name}</ItemLi>;
};

export default Item;
