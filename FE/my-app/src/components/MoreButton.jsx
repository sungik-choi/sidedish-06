import React from 'react';
import styled from 'styled-components';
import { IoIosArrowForward } from 'react-icons/io';

const MoreBtnWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  padding: 2rem 0;
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

const MoreButton = ({ onClick }) => {
  return (
    <MoreBtnWrap>
      <MoreBtn onClick={onClick}>
        반찬 전체보기
        <IoIosArrowForward />
      </MoreBtn>
    </MoreBtnWrap>
  );
};

export default MoreButton;
