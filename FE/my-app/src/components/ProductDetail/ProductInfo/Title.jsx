import React from 'react';
import styled from 'styled-components';

const Title = ({ title, description }) => {
  return (
    <>
      <TitleH2>{title}</TitleH2>
      <DescB>{description}</DescB>
    </>
  );
};

const TitleH2 = styled.h2`
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 0.75rem;
  line-height: 1.3;
`;

const DescB = styled.b`
  color: var(--gray-2);
`;

export default Title;
