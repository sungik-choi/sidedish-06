import React from 'react';
import styled from 'styled-components';
import LocalNavBar from './LocalNavBar/LocalNavBar';
import Logo from './Logo';
import SearchBar from './SearchBar';
import MenuBar from './MenuBar/MenuBar';

const LogoWrap = styled.div`
  display: flex;
  align-items: center;
  width: var(--width);
  margin: 0 auto;
  padding: 1rem 0;
`;

const Header = ({ lnbList, menuList }) => {
  return (
    <header>
      <LocalNavBar list={lnbList} />
      <LogoWrap>
        <Logo />
        <SearchBar />
      </LogoWrap>
      <MenuBar list={menuList} />
    </header>
  );
};

export default Header;
