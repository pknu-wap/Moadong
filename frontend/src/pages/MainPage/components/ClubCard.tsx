import React from 'react';
import ClubTag from '../../../components/ClubTag/ClubTag';
import ClubLogo from '../../../components/ClubLogo/ClubLogo';
import ClubStateBox from '../../../components/ClubStateBox/ClubStateBox';
import * as Styled from './ClubCard.styles';

interface ClubCardProps {
  club: {
    id: string;
    name: string;
    logo: string;
    tags: string[];
    state: string;
    division: string;
    classification: string;
    description: string;
  };
}

const ClubCard = ({ club }: ClubCardProps) => {
  return (
    <Styled.CardContainer state={club.state}>
      <Styled.CardHeader>
        <Styled.ClubProfile>
          <ClubLogo imageSrc={club.logo} />
          <Styled.ClubName>{club.name}</Styled.ClubName>
        </Styled.ClubProfile>
        <ClubStateBox state={club.state} />
      </Styled.CardHeader>
      <Styled.Description>{club.description}</Styled.Description>
      <Styled.TagsContainer>
        <ClubTag type={club.division} />
        <ClubTag type={club.classification} />
        {club.tags.map((tag) => (
          <ClubTag key={tag} type={'자유'}>
            {tag}
          </ClubTag>
        ))}
      </Styled.TagsContainer>
    </Styled.CardContainer>
  );
};

export default ClubCard;
