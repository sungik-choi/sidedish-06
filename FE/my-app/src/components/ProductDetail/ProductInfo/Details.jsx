import React from 'react';
import styled from 'styled-components';

const Details = ({ deliveryInfo, deliveryFee, point }) => {
  return (
    <DetailsWrap>
      <DetailsUl>
        <DetailsLi>
          <CategoryB>{CATEGORY_TEXT.point}</CategoryB>
          <span>{point}</span>
        </DetailsLi>
        <DetailsLi>
          <CategoryB>{CATEGORY_TEXT.deliveryInfo}</CategoryB>
          {deliveryInfo}
        </DetailsLi>
        <DetailsLi>
          <CategoryB>{CATEGORY_TEXT.deliveryFee}</CategoryB>
          {deliveryFee}
        </DetailsLi>
      </DetailsUl>
    </DetailsWrap>
  );
};

const CATEGORY_TEXT = {
  point: '적립금',
  deliveryInfo: '배송정보',
  deliveryFee: '배송비',
};

const DetailsWrap = styled.div`
  margin-top: 2rem;
`;

const CategoryB = styled.b`
  flex: 0 0 10rem;
  font-weight: 600;
  color: var(--gray-2);
`;

const DetailsLi = styled.li`
  display: flex;
  color: var(--black);
  line-height: 1.4;
`;

const DetailsUl = styled.ul`
  display: flex;
  flex-direction: column;
  ${DetailsLi} + ${DetailsLi} {
    margin-top: 1rem;
  }
`;

export default Details;
