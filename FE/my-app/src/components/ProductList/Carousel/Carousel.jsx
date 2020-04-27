import React from 'react';
import styled from 'styled-components';
import Slider from 'react-slick';
import Product from './Product/Product';
import arrow from './arrow.svg';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const ArrowSvg = styled.object`
  pointer-events: none;
  opacity: 0.3;
`;

const ArrowWrap = styled.div`
  cursor: pointer;
  transform: ${props => (props.arrowType === 'prev' ? 'rotate(180deg) translateY(50%)' : 'translateY(-50%)')};
  top: 6.71875rem; /* 107.5/16 */
  left: ${props => (props.arrowType === 'next' ? 'auto' : '-5rem')};
  right: ${props => (props.arrowType === 'prev' ? 'auto' : '-5rem')};
  width: 2.1875rem; /* 35/16 */
  height: 4rem;
  &::before {
    content: '';
  }
  &:hover {
    ${ArrowSvg} {
      opacity: 0.8;
    }
  }
`;

const Arrow = ({ arrowType, className, onClick }) => {
  return (
    <ArrowWrap arrowType={arrowType} className={className} onClick={onClick}>
      <ArrowSvg type="image/svg+xml" data={arrow} />
    </ArrowWrap>
  );
};

const CarouselWrap = styled.div`
  width: var(--width);
  margin: 0 auto;
  div {
    margin: 0 auto;
  }
`;

const Carousel = ({ list, onClick }) => {
  const products = list.body.map(productData => <Product key={productData.detail_hash} list={productData} onClick={onClick} />);

  const setting = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 4,
    nextArrow: <Arrow arrowType="next" />,
    prevArrow: <Arrow arrowType="prev" />,
  };

  return (
    <CarouselWrap>
      <Slider {...setting}>{products}</Slider>
    </CarouselWrap>
  );
};

export default Carousel;
