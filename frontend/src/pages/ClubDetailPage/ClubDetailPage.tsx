import React, { useEffect } from 'react';
import Header from '@/components/common/Header/Header';
import InfoTabs from '@/pages/ClubDetailPage/components/InfoTabs/InfoTabs';
import InfoBox from '@/pages/ClubDetailPage/components/InfoBox/InfoBox';
import IntroduceBox from '@/pages/ClubDetailPage/components/IntroduceBox/IntroduceBox';
import Footer from '@/components/@common/Footer/Footer';
import * as Styled from '@/styles/PageContainer.styles';
import useAutoScroll from '@/hooks/useAutoScroll';

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
      <InfoTabs onTabClick={scrollToSection} />
      <Styled.PageContainer>
        <InfoBox sectionRefs={sectionRefs} />
        <IntroduceBox sectionRefs={sectionRefs} />
      </Styled.PageContainer>
      <Footer />
    </>
  );
};
export default ClubDetailPage;
