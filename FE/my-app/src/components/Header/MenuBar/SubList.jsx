import React from 'react';
import Item from './Item';

const SubList = ({ list }) => {
  const items = list.map((itemName, index) => <Item key={index} name={itemName} />);

  return <>{items}</>;
};

export default SubList;
