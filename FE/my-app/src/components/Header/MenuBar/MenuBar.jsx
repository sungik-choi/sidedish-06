import React from 'react';
import styled from 'styled-components';
import Category from './Category';

const MenuBar = ({ list }) => {
  const categories = list.map(category => <Category key={category.id} name={category.name} list={category.subList} />);

  return (
    <MenuWrap>
      <MenuNav>
        <CategoryUl>{categories}</CategoryUl>
      </MenuNav>
    </MenuWrap>
  );
};

const MenuWrap = styled.div`
  z-index: 10;
  background-color: var(--brown);
`;

const MenuNav = styled.nav`
  width: var(--width);
  height: 3.25rem;
  margin: 0 auto;
`;

const CategoryUl = styled.ul`
  display: flex;
  justify-content: space-between;
  height: 100%;
`;

export default MenuBar;
