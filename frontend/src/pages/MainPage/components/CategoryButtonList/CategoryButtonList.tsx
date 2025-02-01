import React from 'react';
import styled from 'styled-components';
import iconAll from '@/assets/images/icon_all.png';
import iconVolunteer from '@/assets/images/icon_volunteer.png';
import iconReligion from '@/assets/images/icon_religion.png';
import iconHobby from '@/assets/images/icon_hobby.png';
import iconAcademic from '@/assets/images/icon_academic.png';
import iconSport from '@/assets/images/icon_sport.png';
import iconPerformance from '@/assets/images/icon_performance.png';

interface Category {
  id: string;
  name: string;
  icon: string;
}

interface CategoryButtonListProps {
  onCategorySelect: (division: string) => void;
}

const clubCategories: Category[] = [
  { id: 'all', name: '전체', icon: iconAll },
  { id: 'volunteering', name: '봉사', icon: iconVolunteer },
  { id: 'religion', name: '종교', icon: iconReligion },
  { id: 'hobby', name: '취미교양', icon: iconHobby },
  { id: 'study', name: '학술', icon: iconAcademic },
  { id: 'sport', name: '운동', icon: iconSport },
  { id: 'performance', name: '공연', icon: iconPerformance },
];

const CategoryButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: nowrap;

  @media (max-width: 1280px) {
    gap: calc(5vw + 10px);
  }

  @media (max-width: 700px) {
    gap: calc(4vw + 5px);
  }

  @media (max-width: 480px) {
    gap: calc(3vw + 2px);
  }

  @media (max-width: 375px) {
    gap: 8px;
  }
`;

const CategoryButton = styled.button`
  display: flex;
  flex-direction: column;
  align-items: center;
  border: none;
  background: none;
  cursor: pointer;

  img {
    width: 36px;
    height: 36px;
    @media (max-width: 480px) {
      width: 30px;
      height: 30px;
    }
    @media (max-width: 360px) {
      width: 25px;
      height: 25px;
    }
  }

  span {
    font-size: 1rem;
    font-weight: 500;
    margin-top: 11px;
    line-height: 30px;
    white-space: nowrap;

    @media (max-width: 768px) {
      font-size: 14px;
      margin-top: 10px;
    }

    @media (max-width: 480px) {
      font-size: 12px;
      margin-top: 11px;
      line-height: normal;
    }

    @media (max-width: 375px) {
      font-size: 11px;
      margin-top: 8px;
      line-height: normal;
    }
  }
`;

const CategoryButtonList = ({ onCategorySelect }: CategoryButtonListProps) => {
  return (
    <CategoryButtonContainer>
      {clubCategories.map((category) => (
        <CategoryButton
          key={category.id}
          onClick={() => onCategorySelect(category.id)}>
          <img src={category.icon} alt={category.name} />
          <span>{category.name}</span>
        </CategoryButton>
      ))}
    </CategoryButtonContainer>
  );
};

export default CategoryButtonList;
