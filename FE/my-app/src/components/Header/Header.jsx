import React from 'react';
import styled from 'styled-components';
import MenuBar from './MenuBar/MenuBar';
import categoryList from '../MockData';

function Header(props) {
  return (
    <header>
      <MenuBar category={categoryList} />
    </header>
  );
}

export default Header;
