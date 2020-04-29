import React, { useState, useEffect } from 'react';
import TopImage from './TopImage';
import ThumbImages from './ThumbImages';

const ProductImages = ({ thumbImages }) => {
  const [topImage, setTopImage] = useState(null);
  const nonOverlappingThumbImages = new Set(thumbImages);
  const imageMouseOverHandler = e => {
    setTopImage(e.target.src);
    e.target.focus();
  };

  useEffect(() => {
    if (thumbImages) setTopImage(thumbImages[0]);
  }, [thumbImages]);

  return (
    <>
      <TopImage topImage={topImage} />
      <ThumbImages thumbImages={nonOverlappingThumbImages} onMouseOver={imageMouseOverHandler} />
    </>
  );
};

export default ProductImages;
