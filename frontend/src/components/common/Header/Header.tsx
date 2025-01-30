import React from 'react';
import * as Styled from './Header.styles';
import SearchBox from '../SearchBox/SearchBox';

const Header = () => {
  return (
    <Styled.HeaderStyles>
      <Styled.HeaderContainer>
        <Styled.TextCoverStyles>
          <Styled.LogoButtonStyles>Moadong</Styled.LogoButtonStyles>
          <Styled.IntroduceButtonStyles>
            모아동 소개
          </Styled.IntroduceButtonStyles>
          <SearchBox />
        </Styled.TextCoverStyles>
      </Styled.HeaderContainer>
    </Styled.HeaderStyles>
  );
};

export default Header;
