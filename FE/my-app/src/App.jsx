import React, { useState } from 'react';
import Header from './components/Header/Header';
import ProductList from './components/ProductList/ProductList';
import GlobalStyle from './components/GlobalStyle';
import { API_URL, useFetch } from './components/Fetch';
import { lnbList, menuList } from './components/MockData';

const App = () => {
  const [mainList, setMainList] = useState({ body: [] });
  const isMainListLoading = useFetch(API_URL.main(), setMainList);

  if (!isMainListLoading)
    return (
      <>
        <div>Loading...</div>
      </>
    );
  return (
    <>
      <GlobalStyle />
      <Header lnbList={lnbList} menuList={menuList} />
      <ProductList list={mainList} />
    </>
  );
};

export default App;
