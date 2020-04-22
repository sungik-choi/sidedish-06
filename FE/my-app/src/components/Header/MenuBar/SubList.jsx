import React from 'react';
import styled from 'styled-components';
import Item from './Item';

function SubList(props) {
  const items = props.list.map((itemName, index) => <Item key={index} name={itemName} />);

  return <ul>{items}</ul>;
}

export default SubList;
