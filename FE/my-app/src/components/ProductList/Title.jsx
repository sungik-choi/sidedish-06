import React from 'react';
import styled from 'styled-components';
import _v from '../Variables';

const menuTypeH3 = styled.h3``;
const menuTypeTitleH2 = styled.h2``;

function Title(props) {
  return (
  <menuTypeH3>{props}</menuTypeH3>
  <menuTypeTitleH2>{props}</menuTypeTitleH2>
  );
}

export default Title;
