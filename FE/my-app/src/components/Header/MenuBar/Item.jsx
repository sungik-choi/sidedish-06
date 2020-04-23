import React from 'react';
import styled from 'styled-components';

const ItemLi = styled.li`
  background-color: blue;
`;

function Item(props) {
  return (
    <ItemLi>
      <a>{props.name}</a>
    </ItemLi>
  );
}

export default Item;
