import React from 'react';
import styled from 'styled-components';
import { IoIosArrowForward } from 'react-icons/io';

const MoreBtnWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  padding-top: 4rem;
`;

const MoreBtn = styled.button`
  cursor: pointer;
  appearance: none;
  display: flex;
  align-items: center;
  justify-content: center;
  width: var(--width);
  height: 3.5rem;
  border: 1px solid var(--gray-5);
  background-color: var(--white);
  font-size: 1rem;
  &:hover {
    border: 1px solid var(--gray-4);
  }
`;

const MORE_BTN_TEXT = '반찬 전체보기';

const MoreButton = ({ onClick }) => {
  return (
    <MoreBtnWrap>
      <MoreBtn onClick={onClick}>
        {MORE_BTN_TEXT}
        <IoIosArrowForward />
      </MoreBtn>
    </MoreBtnWrap>
  );
};

export default MoreButton;
