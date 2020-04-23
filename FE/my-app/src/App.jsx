import React, { useEffect } from 'react';
import Header from './components/Header/Header';
import GlobalStyle from './components/GlobalStyle';
import { lnbList, menuList } from './components/MockData';

function App() {
  useEffect(() => {
    fetch('https://h3rb9c0ugl.execute-api.ap-northeast-2.amazonaws.com/develop/baminchan/main')
      .then(response => response.json())
      .then(json => console.log(json));
  });

  return (
    <>
      <GlobalStyle />
      <Header lnbList={lnbList} menuList={menuList} />
    </>
  );
}

export default App;
