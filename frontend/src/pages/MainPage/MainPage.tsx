import React, { useState, useEffect } from 'react';
import CategoryButtonList from '@/pages/MainPage/components/CategoryButtonList/CategoryButtonList';
import ClubCard from '@/pages/MainPage/components/ClubCard/ClubCard';
import StatusRadioButton from '@/pages/MainPage/components/StatusRadioButton/StatusRadioButton';
import { mockClubs } from '@/apis/mockClubs';
import * as Styled from './MainPage.styles';

const MainPage = () => {
  const [isFilterActive, setIsFilterActive] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState('전체');
  const [filteredClubs, setFilteredClubs] = useState(mockClubs);

  const handleFilterChange = (status: boolean) => {
    setIsFilterActive(status);
  };

  const handleCategorySelect = (category: string) => {
    setSelectedCategory(category);
  };

  useEffect(() => {
    let filtered = mockClubs;

    if (isFilterActive) {
      filtered = filtered.filter(
        (club) => club.state === '모집중' || club.state === '모집예정',
      );
    }

    if (selectedCategory !== '전체') {
      filtered = filtered.filter(
        (club) => club.classification === selectedCategory,
      );
    }

    setFilteredClubs(filtered);
  }, [isFilterActive, selectedCategory]);

  return (
    <Styled.PageContainer>
      <CategoryButtonList onCategorySelect={handleCategorySelect} />
      <Styled.FilterWrapper>
        <StatusRadioButton onChange={handleFilterChange} />
      </Styled.FilterWrapper>

      <Styled.ContentWrapper>
        <Styled.CardList>
          {filteredClubs.length > 0 ? (
            filteredClubs.map((club) => <ClubCard key={club.id} club={club} />)
          ) : (
            <p>조건에 맞는 동아리가 없어요</p>
          )}
        </Styled.CardList>
      </Styled.ContentWrapper>
    </Styled.PageContainer>
  );
};

export default MainPage;
