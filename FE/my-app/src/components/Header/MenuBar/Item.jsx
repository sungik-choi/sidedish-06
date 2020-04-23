import React from 'react';
import styled from 'styled-components';
import _v from '../../Variables';

const ItemLi = styled.li`
  padding: 0.5rem 0;
  &:hover {
    color: ${_v.green};
    font-weight: 600;
    text-decoration: underline;
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
