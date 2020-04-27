import React from 'react';
import styled from 'styled-components';

const TitleH2 = styled.h2`
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 1rem;
`;

const DescB = styled.b`
  color: var(--gray-2);
`;

const Title = ({ title, description }) => {
  return (
    <>
      <TitleH2>[집밥의 완성] 맛있는 요리 500g</TitleH2>
      <DescB>{description}</DescB>
    </>
  );
};

export default Title;
