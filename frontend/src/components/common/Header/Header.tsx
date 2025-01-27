import React from 'react';
import * as Styled from './Header.styles';

const Header = () => {
  return (
    <Styled.HeaderStyles>
      <Styled.TextCoverStyles>
        <Styled.LogoButtonStyles>Moadong</Styled.LogoButtonStyles>
        <Styled.introduceButtonStyles>모아동 소개</Styled.introduceButtonStyles>
      </Styled.TextCoverStyles>
      <Styled.searchBoxStyles>
        <Styled.searchInputStyles placeholder='어떤 동아리를 찾으세요?' />
        <Styled.SearchButton />
      </Styled.searchBoxStyles>
    </Styled.HeaderStyles>
  );
};

export default Header;
