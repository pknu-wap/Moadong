import React, { useState } from 'react';
import CategoryButtonList from '@/pages/MainPage/components/CategoryButtonList/CategoryButtonList';
import Banner from '@/pages/MainPage/components/Banner/Banner';
import { BannerImageList } from '@/utils/banners';

const MainPage = () => {
  const [selectedCategory, setSelectedCategory] = useState<string>('all');

  const handleCategorySelect = (categoryId: string) => {
    setSelectedCategory(categoryId);
  };

  return (
    <>
      <Banner banners={BannerImageList} />
      <CategoryButtonList onCategorySelect={handleCategorySelect} />
    </>
  );
};

export default MainPage;
