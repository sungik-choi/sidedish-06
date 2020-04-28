import React, { useState, useEffect } from 'react';
import TopImage from './TopImage';
import ThumbImages from './ThumbImages';

const ProductImages = ({ thumbImages = [] }) => {
  const [topImage, setTopImage] = useState(null);
  const imageMouseOverHandler = e => {
    setTopImage(e.target.src);
    e.target.focus();
  };

  useEffect(() => setTopImage(thumbImages[0]), [thumbImages]);

  return (
    <>
      <TopImage topImage={topImage} />
      <ThumbImages thumbImages={thumbImages} onMouseOver={imageMouseOverHandler} />
    </>
  );
};

export default ProductImages;
