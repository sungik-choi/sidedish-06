import { css, createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const variables = css`
  :root {
    --width: 61.25rem; /* 980 / 16 */
    --slider-width: 13.4375rem; /* 215 / 16 */
    --white: #fff;
    --black: #000;
    --shadow: rgba(0, 0, 0, 0.6);
    --gray-1: #333;
    --gray-2: #666;
    --gray-3: #e9e9e9;
    --gray-4: #999;
    --green: #1fcbc7;
    --brown: #483f35;
    --beige: #c6a8a5;
  }
`;

const customReset = css`
  *,
  html,
  body {
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';
    box-sizing: border-box;
  }

  ul,
  li {
    color: var(--gray-2);
  }

  dt,
  dd {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
`;

const GlobalStyle = createGlobalStyle`
  ${reset};
  ${variables};
  ${customReset}; 
`;

export default GlobalStyle;
