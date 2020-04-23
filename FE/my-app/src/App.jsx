import React from 'react';
import Header from './components/Header/Header';
import GlobalStyle from './components/GlobalStyle';
import { lnbList, menuList } from './components/MockData';

function App() {
  return (
    <>
      <GlobalStyle />
      <Header lnbList={lnbList} menuList={menuList} />
    </>
  );
}

export default App;
