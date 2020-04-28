import React from 'react';
import styled from 'styled-components';

const ThumbImages = ({ thumbImages, onMouseOver }) => {
  const images = [...thumbImages].map((imageSrc, index) => (
    <ImageWrap key={index}>
      <Image key={index} src={imageSrc} onMouseOver={onMouseOver} tabIndex="-1" />
    </ImageWrap>
  ));

  return <ThumbImagesWrap>{images}</ThumbImagesWrap>;
};

const Image = styled.img`
  position: absolute;
  width: 100%;
  border: 1px solid var(--gray-3);
  &:focus {
    border: 1px solid var(--green);
    outline: none;
  }
`;

const ImageWrap = styled.div`
  position: relative;
  width: calc(25% - 0.375rem); /* 1.5 / 4 */
  padding-bottom: calc(25% - 0.375rem);
  line-height: 0;
  margin-right: 0.5rem;
  background-color: var(--gray-3);
`;

const ThumbImagesWrap = styled.div`
  position: relative;
  display: flex;
  margin-top: 0.5rem;
  line-height: 0;
  ${ImageWrap}:last-child {
    margin-right: 0;
  }
`;

export default ThumbImages;
