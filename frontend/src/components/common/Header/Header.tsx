import React from 'react';
import * as Styled from './Header.styles';

const Header = () => {
  return (
    <Styled.HeaderStyles>
      <Styled.LogoButtonStyles>Moadong</Styled.LogoButtonStyles>
      <Styled.introduceButtonStyles>모아동 소개</Styled.introduceButtonStyles>
    </Styled.HeaderStyles>
  );
};

export default Header;
