import React from 'react';
import styled from 'styled-components';
import _v from '../../Variables';
import SubList from './SubList';

const CategoryLi = styled.li`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  margin-top: 0.25rem;
  border: 1px solid ${_v.brown};
  color: ${_v.white};
  font-size: 0.875rem;
  font-weight: 600;
  &:hover {
    background-color: ${_v.white};
    color: ${_v.green};
    border: 1px solid ${_v.shadow};
    border-bottom: none;
    ul {
      display: block;
    }
  }
  &:last-child {
    ul {
      left: -100%;
      text-align: right;
      padding: 1.5rem 1rem 1.5rem 0;
    }
  }
`;

function Category(props) {
  return (
    <CategoryLi>
      <a>{props.name}</a>
      <SubList list={props.list} />
    </CategoryLi>
  );
}

export default Category;
