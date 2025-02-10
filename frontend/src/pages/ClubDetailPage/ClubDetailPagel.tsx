import React from 'react';
import Header from '@/components/common/Header/Header';
import InfoBox from '@/pages/ClubDetailPage/components/InfoBox/InfoBox';
import IntroduceBox from '@/pages/ClubDetailPage/components/IntroduceBox/IntroduceBox';
import * as Styled from '../MainPage/MainPage.styles';

const ClubDetailPage = () => {
  return (
    <>
      <Header />
      <Styled.PageContainer>
        <InfoBox />
        <IntroduceBox />
      </Styled.PageContainer>
    </>
  );
};

export default ClubDetailPage;
