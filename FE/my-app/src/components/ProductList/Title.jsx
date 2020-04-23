import React from 'react';
import styled from 'styled-components';

const menuType = styled.h3``;
const menuTypeTitle = styled.h2``;

const Title = props => {
  return (
    <>
      <menuType>{props}</menuType>
      <menuTypeTitle>{props}</menuTypeTitle>
    </>
  );
};

export default Title;
