import React from 'react';
import Header from './components/Header/Header';
import { lnbList, categoryList } from './components/MockData';

function App() {
  return <Header lnbList={lnbList} categoryList={categoryList} />;
}

export default App;
