import { keyframes } from 'styled-components';

export const fadeIn = num => keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: num;
  }
`;
