import React from 'react';
import styled from 'styled-components';
import LocalNavBar from './LocalNavBar/LocalNavBar';
import Logo from './Logo';
import SearchBar from './SearchBar';
import MenuBar from './MenuBar/MenuBar';

function Header(props) {
  return (
    <header>
      <LocalNavBar list={props.lnbList} />
      <Logo />
      <SearchBar />
      <MenuBar list={props.menuList} />
    </header>
  );
}

export default Header;
