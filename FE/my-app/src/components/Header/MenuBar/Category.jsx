import React from 'react';
import styled from 'styled-components';
import SubList from './SubList';

const Category = ({ name, list }) => {
  return (
    <CategoryLi>
      {name}
      <SubListUl>
        <SubList list={list} />
      </SubListUl>
    </CategoryLi>
  );
};

const SubListUl = styled.ul`
  cursor: default;
  z-index: 20;
  display: none;
  position: absolute;
  top: 100%;
  left: -1px;
  width: 10rem;
  padding: 1.5rem 0 1.5rem 1rem;
  border: 1px solid var(--shadow);
  border-top: none;
  font-weight: 500;
  background-color: var(--white);
  &:hover {
    display: block;
  }
`;

const CategoryLi = styled.li`
  cursor: pointer;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  margin-top: 0.25rem;
  padding-bottom: 0.25rem;
  border: 1px solid var(--brown);
  color: var(--white);
  font-size: 0.875rem;
  font-weight: 600;
  &:hover {
    background-color: var(--white);
    color: var(--green);
    border: 1px solid var(--shadow);
    border-bottom: none;
    ${SubListUl} {
      display: block;
    }
  }
  &:last-child {
    ${SubListUl} {
      left: calc(-100% + 1px);
      text-align: right;
      padding: 1.5rem 1rem 1.5rem 0;
    }
  }
`;

export default Category;
