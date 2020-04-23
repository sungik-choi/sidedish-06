import React from 'react';
import styled from 'styled-components';
import _v from '../Variables';
import { IoIosSearch } from 'react-icons/io';

const SearchBarForm = styled.form`
  position: relative;
  padding-left: 3rem;
`;

const SearchBarInput = styled.input`
  width: 14rem;
  height: 2.5rem;
  padding: 0.5rem 2rem 0.5rem 0.5rem;
  font-size: 1rem;
  &:hover {
    border: 1px solid ${_v.green};
  }
`;

const SearchBarButton = styled.button`
  cursor: pointer;
  box-sizing: border-box;
  position: absolute;
  right: 0;
  height: 2.5rem;
  padding-top: 0.3rem;
  appearance: none;
  font-size: 1.5rem;
  color: ${_v.gray2};
  background-color: transparent;
  border: none;
  &:hover {
    color: ${_v.gray1};
  }
`;

function SearchBar() {
  return (
    <SearchBarForm>
      <SearchBarInput type="text" />
      <SearchBarButton>
        <IoIosSearch />
      </SearchBarButton>
    </SearchBarForm>
  );
}

export default SearchBar;
