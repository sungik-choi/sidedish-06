import React from 'react';
import styled from 'styled-components';
import _v from '../../Variables';

const ItemLi = styled.li`
  padding: 0 0.5rem;
  border-right: 1px solid ${_v.gray3};
  &:last-child {
    border-right: none;
  }
  &:hover {
    color: ${_v.green};
  }
`;

function Item(props) {
  return (
    <ItemLi>
      <a>{props.name}</a>
    </ItemLi>
  );
}

export default Item;
