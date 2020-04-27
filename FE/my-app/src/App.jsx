import React, { useState } from 'react';
import styled from 'styled-components';
import Header from './components/Header/Header';
import ProductList from './components/ProductList/ProductList';
import ProductDetail from './components/ProductDetail/ProductDetail';
import MoreButton from './components/MoreButton';
import GlobalStyle from './components/GlobalStyle';
import { lnbList, menuList } from './components/MockData';

const FooterDiv = styled.div`
  height: 6rem;
`;

const App = () => {
  const [isMoreBtnClicked, setIsMoreBtnClicked] = useState(false);
  const [targetProductHash, setTargetProductHash] = useState(null);
  const moreBtnClickHandler = () => setIsMoreBtnClicked(true);
  const productClickHandler = hash => setTargetProductHash(hash);

  return (
    <>
      <GlobalStyle />
      {targetProductHash && <ProductDetail type={'detail'} hash={targetProductHash} />}
      <Header lnbList={lnbList} menuList={menuList} />
      <ProductList type={'side'} onClick={productClickHandler} />
      <ProductList type={'main'} onClick={productClickHandler} />
      {isMoreBtnClicked ? <ProductList type={'soup'} onClick={productClickHandler} /> : <MoreButton onClick={moreBtnClickHandler} />}
      <FooterDiv />
    </>
  );
};

export default App;
