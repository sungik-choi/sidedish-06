import React from 'react';
import styled from 'styled-components';
import logo from 'assets/logo.svg';

const Logo = () => {
  return (
    <LogoH1>
      {LOGO_TITLE}
      <LogoSvg type="image/svg+xml" data={logo}></LogoSvg>
    </LogoH1>
  );
};

const LOGO_TITLE = '강남구 넘버원 반찬가게 코쿼찬';

const LogoH1 = styled.h1`
  font-size: 0;
`;

const LogoSvg = styled.object`
  width: 12.125rem; /* 194/16 */
  height: 4.25rem; /* 68/16 */
`;

export default Logo;
