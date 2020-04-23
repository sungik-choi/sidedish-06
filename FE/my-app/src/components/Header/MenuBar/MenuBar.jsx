import React from 'react';
import styled from 'styled-components';
import _v from '../../Variables';
import Category from './Category';

const MenuDiv = styled.div`
  background-color: ${_v.brown};
`;

const MenuNav = styled.nav`
  width: ${_v.width};
  height: 3rem;
  margin: 0 auto;
`;

const CategoryUl = styled.ul`
  display: flex;
  justify-content: space-between;
  height: 100%;
`;

function MenuBar(props) {
  const category = props.list.map(category => <Category key={category.id} name={category.name} list={category.subList} />);

  return (
    <MenuDiv>
      <MenuNav>
        <CategoryUl>{category}</CategoryUl>
      </MenuNav>
    </MenuDiv>
  );
}

export default MenuBar;
