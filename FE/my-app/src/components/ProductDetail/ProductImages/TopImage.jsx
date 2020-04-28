import React from 'react';
import styled from 'styled-components';

const TopImageWrap = styled.div`
  position: relative;
  width: 100%;
  padding-bottom: calc(100% - 2px);
  background-color: var(--gray-3);
  border: 1px solid var(--gray-3);
  line-height: 0;
`;

const Image = styled.img`
  position: absolute;
  width: 100%;
`;

const TopImage = ({ topImage }) => {
  return (
    <TopImageWrap>
      <Image src={topImage} />
    </TopImageWrap>
  );
};

export default TopImage;
