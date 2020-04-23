import { createGlobalStyle } from 'styled-components';
import _v from './Variables';
import reset from 'styled-reset';

const GlobalStyle = createGlobalStyle`
  ${reset};

  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  
  * {
    box-sizing: border-box;
  }

  a {
    cursor: pointer;
  }

  ul, li {
    color: ${_v.gray2};
  }
`;

export default GlobalStyle;
