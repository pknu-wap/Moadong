import React from 'react';
import * as Styled from './Header.styles';
import SearchBox from '@/components/common/SearchBox/SearchBox';
import MainIcon from '@/assets/images/mainIcon.png';

const Header = () => {
  return (
    <Styled.HeaderStyles>
      <Styled.HeaderContainer>
        <Styled.TextCoverStyles>
          <Styled.LogoButtonStyles>
            <img src={MainIcon} />
          </Styled.LogoButtonStyles>
          <Styled.IntroduceButtonStyles>
            모아동 소개
          </Styled.IntroduceButtonStyles>
        </Styled.TextCoverStyles>
        <SearchBox />
      </Styled.HeaderContainer>
    </Styled.HeaderStyles>
  );
};

export default Header;
