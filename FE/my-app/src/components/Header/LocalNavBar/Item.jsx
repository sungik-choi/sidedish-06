import React from 'react';
import styled from 'styled-components';
import { OAUTH_LINK } from 'utils/useFetch';

const Item = ({ name, type }) => {
  if (type === LOGIN)
    return (
      <ItemLi>
        <LoginAnchor href={OAUTH_LINK}>{name}</LoginAnchor>
      </ItemLi>
    );
  return <ItemLi>{name}</ItemLi>;
};

const LOGIN = 'login';

const ItemLi = styled.li`
  cursor: pointer;
  padding: 0 0.5rem;
  border-right: 1px solid var(--gray-3);
  &:last-child {
    border-right: none;
  }
  &:hover {
    color: var(--green);
  }
`;

const LoginAnchor = styled.a`
  font-weight: 600;
  color: var(--green);
`;

export default Item;
