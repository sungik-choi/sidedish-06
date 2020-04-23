import React from 'react';
import styled from 'styled-components';

const ItemLi = styled.li`
  cursor: pointer;
  padding: 0.5rem 0;
  &:hover {
    color: var(--green);
    font-weight: 600;
    text-decoration: underline;
  }
`;

const Item = ({ name }) => {
  return <ItemLi>{name}</ItemLi>;
};

export default Item;
