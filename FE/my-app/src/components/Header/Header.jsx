import React from 'react';
import styled from 'styled-components';
import LocalNavBar from './LocalNavBar/LocalNavBar';
import Logo from './Logo';
import SearchBar from './SearchBar';
import MenuBar from './MenuBar/MenuBar';

const Header = ({ lnbList, menuList }) => {
  return (
    <HeaderWrap>
      <LocalNavBar list={lnbList} />
      <LogoWrap>
        <Logo />
        <SearchBar />
      </LogoWrap>
      <MenuBar list={menuList} />
    </HeaderWrap>
  );
};

const HeaderWrap = styled.header`
  width: 100vw;
  min-width: var(--width);
`;

const LogoWrap = styled.div`
  display: flex;
  align-items: center;
  width: var(--width);
  margin: 0 auto;
  padding: 1rem 0;
`;

export default Header;
