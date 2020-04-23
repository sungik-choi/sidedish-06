import React, { useState } from 'react';
import Header from './components/Header/Header';
import ProductList from './components/ProductList/ProductList';
import GlobalStyle from './components/GlobalStyle';
import { API_URL, useFetch } from './components/Fetch';
import { lnbList, menuList } from './components/MockData';

function App() {
  const [mainList, setMainList] = useState({ body: [] });
  useFetch(API_URL.main(), setMainList);
  const mainProductList = mainList.body.map(productList => <ProductList list={productList} />);

  return (
    <>
      <GlobalStyle />
      <Header lnbList={lnbList} menuList={menuList} />
      {mainProductList}
    </>
  );
}

export default App;
