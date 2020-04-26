import React, { useState } from 'react';
import styled from 'styled-components';
import Header from './components/Header/Header';
import ProductList from './components/ProductList/ProductList';
import MoreButton from './components/MoreButton';
import GlobalStyle from './components/GlobalStyle';
import { API_URL, useFetch } from './components/Fetch';
import { lnbList, menuList } from './components/MockData';

const FooterDiv = styled.div`
  height: 6rem;
`;

const App = () => {
  const [mainList, setMainList] = useState({ body: [] });
  const [sideList, setSideList] = useState({ body: [] });
  const [soupList, setSoupList] = useState({ body: [] });
  const [isMoreBtnClicked, setIsMoreBtnClicked] = useState(false);
  const isSideListLoading = useFetch(API_URL.side(), setSideList);
  const isMainListLoading = useFetch(API_URL.main(), setMainList);
  const isSoupListLoading = useFetch(API_URL.soup(), setSoupList);

  const moreBtnClickHandler = () => {
    setIsMoreBtnClicked(true);
    console.log(isMoreBtnClicked);
  };

  return (
    <>
      <GlobalStyle />
      <Header lnbList={lnbList} menuList={menuList} />
      <ProductList list={sideList} />
      <ProductList list={mainList} />
      {isMoreBtnClicked ? <ProductList list={soupList} /> : <MoreButton isClicked={isMoreBtnClicked} onClick={moreBtnClickHandler} />}
      <FooterDiv />
    </>
  );
};

export default App;
