import React from 'react';
import styled from 'styled-components';
import Slider from 'react-slick';
import Product from './Product/Product';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const ArrowWrap = styled.div`
  cursor: pointer;
  transform: ${props => (props.arrowType === 'prev' ? 'rotate(180deg)' : 'none')};
  top: 5.25rem;
  left: ${props => (props.arrowType === 'next' ? 'auto' : '-5rem')};
  right: ${props => (props.arrowType === 'prev' ? 'auto' : '-5rem')};
  width: 2.1875rem /* 35/16 */;
  height: 4rem;
  &::before {
    content: '';
  }
`;

const ArrowSvg = styled.object`
  pointer-events: none;
  fill: var(--gray-3);
`;

const NextArrow = ({ className, onClick }) => {
  return (
    <ArrowWrap arrowType="next" className={className} onClick={onClick}>
      <ArrowSvg type="image/svg+xml" data="images/arrow.svg" />
    </ArrowWrap>
  );
};

const PrevArrow = ({ className, onClick }) => {
  return (
    <ArrowWrap arrowType="prev" className={className} onClick={onClick}>
      <ArrowSvg type="image/svg+xml" data="images/arrow.svg" />
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

const Carousel = ({ list }) => {
  const products = list.body.map(productData => <Product hash={productData.detail_hash} key={productData.detail_hash} list={productData} />);

  const setting = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 4,
    nextArrow: <NextArrow />,
    prevArrow: <PrevArrow />,
  };

  return (
    <CarouselWrap>
      <Slider {...setting}>{products}</Slider>
    </CarouselWrap>
  );
};

export default Carousel;
