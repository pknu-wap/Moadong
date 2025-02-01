import React, { useState } from 'react';
import CategoryButtonList from '@/pages/MainPage/components/CategoryButtonList/CategoryButtonList';

const MainPage = () => {
  const [selectedCategory, setSelectedCategory] = useState<string>('all');

  const handleCategorySelect = (categoryId: string) => {
    setSelectedCategory(categoryId);
  };

  return (
    <>
      <CategoryButtonList onCategorySelect={handleCategorySelect} />
    </>
  );
};

export default MainPage;
