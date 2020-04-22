import React from 'react';
import styled from 'styled-components';
import SubList from './SubList';

function Category(props) {
  return (
    <li>
      <a>{props.name}</a>
      <SubList list={props.list} />
    </li>
  );
}

export default Category;
