import React from 'react';
import styled from 'styled-components';

const TitleWrap = styled.div`
  text-align: center;
  margin-bottom: 2rem;
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

const Title = ({ menuType, menuTypeTitle }) => {
  return (
    <TitleWrap>
      <MenuType>배민찬 추천</MenuType>
      <MenuTypeTitle>고르고 골라 배민찬이 추천합니다</MenuTypeTitle>
    </TitleWrap>
  );
};

export default Title;
