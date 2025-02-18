import React, { useEffect } from 'react';
import Header from '@/components/common/Header/Header';
import BackNavigationBar from '@/pages/ClubDetailPage/components/BackNavigationBar/BackNavigationBar';
import ClubProfile from '@/pages/ClubDetailPage/components/ClubProfile/ClubProfile';
import ClubApplyButton from '@/pages/ClubDetailPage/components/ClubApplyButton/ClubApplyButton';
import InfoTabs from '@/pages/ClubDetailPage/components/InfoTabs/InfoTabs';
import InfoBox from '@/pages/ClubDetailPage/components/InfoBox/InfoBox';
import IntroduceBox from '@/pages/ClubDetailPage/components/IntroduceBox/IntroduceBox';
import DeadlineBadge from '@/pages/ClubDetailPage/components/DeadlineBadge/DeadlineBadge';
import Footer from '@/components/@common/Footer/Footer';
import * as Styled from '@/styles/PageContainer.styles';
import './ClubDetailPage.styles';
import useAutoScroll from '@/hooks/useAutoScroll';
import {
  ClubDetailFooterContainer,
  ClubDetailHeaderContainer,
} from '@/pages/ClubDetailPage/ClubDetailPage.styles';

const ClubDetailPage = () => {
  const { sectionRefs, scrollToSection } = useAutoScroll();
  const [showHeader, setShowHeader] = React.useState(window.innerWidth > 500);

  useEffect(() => {
    const handleResize = () => {
      setShowHeader(window.innerWidth > 500);
    };

    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  return (
    <>
      {showHeader && <Header />}
      <BackNavigationBar />
      <Styled.PageContainer>
        <ClubDetailHeaderContainer>
          <ClubProfile
            name={'WAP'}
            classification={'중동'}
            division={'학술'}
            tags={['프로젝트', '소프트웨어']}
          />
          <ClubApplyButton />
        </ClubDetailHeaderContainer>
        <InfoTabs onTabClick={scrollToSection} />
        <InfoBox sectionRefs={sectionRefs} />
        <IntroduceBox sectionRefs={sectionRefs} />
        <Footer />
      </Styled.PageContainer>
      <ClubDetailFooterContainer>
        <DeadlineBadge />
        <ClubApplyButton />
      </ClubDetailFooterContainer>
    </>
  );
};
export default ClubDetailPage;
