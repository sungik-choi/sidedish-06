import React, { useState } from 'react';
import styled from 'styled-components';

const DeliveryTypeSpan = styled.span`
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: var(--white);
`;

const DeliveryTypeWrap = styled.div`
  pointer-events: none;
  display: ${props => (props.isMouseOver ? 'flex' : 'none')};
  flex-direction: column;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  span:first-child {
    padding-bottom: 0.5rem;
    border-bottom: 1px solid rgba(255, 255, 255, 0.4);
  }
`;

const ImageWrap = styled.div`
  position: relative;
`;

const ThumbImage = styled.img`
  width: var(--slider-width);
  height: var(--slider-width);
  background-color: var(--gray-3);
  border-radius: 100%;
  &:hover {
    filter: brightness(50%);
  }
`;

const ThumbPlaceholderDiv = styled.div`
  width: var(--slider-width);
  height: var(--slider-width);
  background-color: var(--black);
  border-radius: 100%;
`;

const Image = ({ alt, src, deliveryType }) => {
  const [isMouseOver, setIsMouseOver] = useState(false);
  const deliveryTypeSpans = deliveryType.map((type, index) => <DeliveryTypeSpan key={index}>{type}</DeliveryTypeSpan>);

  const mouseEnterHandler = () => {
    setIsMouseOver(true);
  };

  const mouseLeaveHandler = () => {
    setIsMouseOver(false);
  };

  return (
    <ImageWrap>
      <ThumbImage alt={alt} src={src} onMouseEnter={mouseEnterHandler} onMouseLeave={mouseLeaveHandler} />
      <DeliveryTypeWrap isMouseOver={isMouseOver}>{deliveryTypeSpans}</DeliveryTypeWrap>
    </ImageWrap>
  );
};

export default Image;
