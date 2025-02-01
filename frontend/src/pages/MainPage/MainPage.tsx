import React from 'react';
import Banner from './components/Banner/Banner';

const banners = [
  {
    title: '모아모아 동아리',
    description: '부경대학교의 모든 동아리를 한눈에',
    backgroundImage: '/re.png',
  },
  {
    title: '동아리 소개',
    description: '다양한 동아리를 확인하고 참여하세요.',
    backgroundImage: '/airbnb.webp',
  },
  {
    title: '활동 모아보기',
    description: '동아리 활동 내역을 확인하세요.',
    backgroundImage: '/images/banner3.jpg',
  },
];

const MainPage = () => {
  return (
    <>
      <Banner banners={banners} />
    </>
  );
};

export default MainPage;
