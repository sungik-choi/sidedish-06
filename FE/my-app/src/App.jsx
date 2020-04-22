import React from 'react';
import Header from './components/Header/Header';
import { lnbList, menuList } from './components/MockData';

function App() {
  return <Header lnbList={lnbList} menuList={menuList} />;
}

export default App;
