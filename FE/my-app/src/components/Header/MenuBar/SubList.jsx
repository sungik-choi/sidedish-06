import React from 'react';
import styled from 'styled-components';
import _v from '../../Variables';
import Item from './Item';

const SubListUl = styled.ul`
  display: none;
  position: absolute;
  top: 100%;
  left: -1px;
  width: 10rem;
  padding: 1.5rem 0 1.5rem 1rem;
  border: 1px solid ${_v.shadow};
  border-top: none;
  font-weight: 500;
  &:hover {
    display: block;
  }
`;

function SubList(props) {
  const items = props.list.map((itemName, index) => <Item key={index} name={itemName} />);

  return <SubListUl>{items}</SubListUl>;
}

export default SubList;
