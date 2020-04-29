import React from 'react';
import styled from 'styled-components';

const Title = ({ menuType, menuTypeTitle }) => {
  return (
    <TitleWrap>
      <MenuType>{menuType}</MenuType>
      <MenuTypeTitle>{menuTypeTitle}</MenuTypeTitle>
    </TitleWrap>
  );
};

const TitleWrap = styled.div`
  text-align: center;
  margin-bottom: 2.5rem;
`;

const MenuType = styled.h3`
  margin-bottom: 0.5rem;
  font-size: 1.25rem;
  color: var(--gray-4);
`;

const MenuTypeTitle = styled.h2`
  font-size: 2.125rem;
  font-weight: 300;
`;

export default Title;
