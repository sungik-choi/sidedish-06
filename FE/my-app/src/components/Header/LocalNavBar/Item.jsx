import React from 'react';
import styled from 'styled-components';

function Item(props) {
  return (
    <li>
      <a>{props.name}</a>
    </li>
  );
}

export default Item;
