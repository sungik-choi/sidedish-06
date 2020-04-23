import React from 'react';
import styled from 'styled-components';
import _v from '../Variables';
import LocalNavBar from './LocalNavBar/LocalNavBar';
import Logo from './Logo';
import SearchBar from './SearchBar';
import MenuBar from './MenuBar/MenuBar';

const LogoWrapDiv = styled.div`
  display: flex;
  align-items: center;
  width: ${_v.width};
  margin: 0 auto;
  padding: 1rem 0;
`;

function Header(props) {
  return (
    <header>
      <LocalNavBar list={props.lnbList} />
      <LogoWrapDiv>
        <Logo />
        <SearchBar />
      </LogoWrapDiv>
      <MenuBar list={props.menuList} />
    </header>
  );
}

export default Header;
