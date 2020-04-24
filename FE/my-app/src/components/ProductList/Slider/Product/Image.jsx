import React, { useState } from 'react';
import styled from 'styled-components';

const DeliveryTypeSpan = styled.span`
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: var(--white);
`;

const DeliveryTypeWrap = styled.div`
  display: ${props => (props.isMouseOver ? 'flex' : 'none')};
  flex-direction: column;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const ImageWrap = styled.div`
  position: relative;
`;

const ThumbImage = styled.img`
  height: var(--slider-width);
  border-radius: 100%;
  &:hover {
    filter: brightness(50%);
  }
`;

const Image = ({ alt, src, deliveryType }) => {
  const [isMouseOver, setIsMouseOver] = useState(false);
  const deliveryTypeSpans = deliveryType.map(type => <DeliveryTypeSpan>{type}</DeliveryTypeSpan>);

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
