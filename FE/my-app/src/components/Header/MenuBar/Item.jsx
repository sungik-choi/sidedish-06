import React from 'react';
import styled from 'styled-components';

function Item(props) {
  return (
    <li>
      {console.log(props)}
      <a>{props.name}</a>
    </li>
  );
}

export default Item;
