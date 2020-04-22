import React from 'react';
import styled from 'styled-components';
import Category from './Category';

function MenuBar(props) {
  const category = props.list.map(category => <Category key={category.id} name={category.name} list={category.subList} />);

  return (
    <nav>
      <ul>{category}</ul>
    </nav>
  );
}

export default MenuBar;
