import React from 'react';
import styled from 'styled-components';

const Title = ({ title, desc }) => {
  return (
    <>
      <TitleDt>{title}</TitleDt>
      <DescDd>{desc}</DescDd>
    </>
  );
};

const TitleDt = styled.dt`
  padding-bottom: 0.5rem;
  color: var(--black);
  font-weight: 600;
`;

const DescDd = styled.dd`
  margin-bottom: 0.25rem;
  color: var(--gray-2);
  font-size: 0.8125rem;
`;

export default Title;
