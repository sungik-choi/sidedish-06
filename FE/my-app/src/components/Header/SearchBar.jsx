import React from 'react';
import styled from 'styled-components';
import { IoIosSearch } from 'react-icons/io';

const submitHandler = e => {
  e.preventDefault();
};

const SearchBar = () => {
  return (
    <SearchBarForm onSubmit={submitHandler}>
      <SearchBarInput type="text" />
      <SearchBarButton>
        <IoIosSearch />
      </SearchBarButton>
    </SearchBarForm>
  );
};

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
    border: 1px solid var(--green);
  }
`;

const SearchBarButton = styled.button`
  cursor: pointer;
  box-sizing: border-box;
  position: absolute;
  right: 0;
  width: 2.5rem;
  height: 2.5rem;
  font-size: 1.5rem;
  color: var(--gray-2);
  background-color: transparent;
  border: none;
  &:hover {
    color: var(--gray-1);
  }
`;

export default SearchBar;
