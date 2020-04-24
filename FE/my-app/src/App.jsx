import React, { useState } from 'react';
import styled from 'styled-components';
import Header from './components/Header/Header';
import ProductList from './components/ProductList/ProductList';
import GlobalStyle from './components/GlobalStyle';
import { API_URL, useFetch } from './components/Fetch';
import { lnbList, menuList } from './components/MockData';

const FooterDiv = styled.div`
  height: 6rem;
`;

const App = () => {
  const [mainList, setMainList] = useState({ body: [] });
  const [soupList, setSoupList] = useState({ body: [] });
  const isMainListLoading = useFetch(API_URL.main(), setMainList);
  const isSoupListLoading = useFetch(API_URL.soup(), setSoupList);

  return (
    <>
      <GlobalStyle />
      <Header lnbList={lnbList} menuList={menuList} />
      <ProductList list={mainList} />
      <ProductList list={soupList} />
      <FooterDiv />
    </>
  );
};

export default App;
