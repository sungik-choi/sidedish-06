import React from 'react';
import styled from 'styled-components';
import _v from '../../Variables';
import Item from './Item';

const LocalNavBarWrapDiv = styled.div`
  border-bottom: 1px solid ${_v.gray3};
`;

const LocalNavBarUl = styled.ul`
  display: flex;
  justify-content: flex-end;
  width: ${_v.width};
  padding: 0.5rem 0;
  margin: 0 auto;
  font-size: 0.75rem;
`;

function LocalNavBar(props) {
  const items = props.list.map(list => <Item key={list.id} name={list.name} type={list.type} />);

  return (
    <LocalNavBarWrapDiv>
      <LocalNavBarUl>{items}</LocalNavBarUl>
    </LocalNavBarWrapDiv>
  );
}

export default LocalNavBar;
