import React from 'react';
import styled from 'styled-components';
import Item from './Item';

function LocalNavBar(props) {
  const lnbList = props.list.map(list => <Item key={list.id} name={list.name} type={list.type} />);

  return (
    <div>
      <ul>{lnbList}</ul>
    </div>
  );
}

export default LocalNavBar;
