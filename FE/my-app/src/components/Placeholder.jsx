import React from 'react';
import styled from 'styled-components';
import spinner from 'assets/spinner.svg';

const Placeholder = () => {
  return <PlaceholderSvg type="image/svg+xml" data={spinner}></PlaceholderSvg>;
};

const PlaceholderSvg = styled.object`
  width: 6rem;
`;

export default Placeholder;
