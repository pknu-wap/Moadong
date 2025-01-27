import styled from 'styled-components';

const CardContainer = styled.div<{ state: string }>`
  display: flex;
  flex-direction: column;
  border-radius: 14px;
  padding: 20px;
  background-color: #fff;
  width: 370px;
  height: 170px;
  box-shadow: ${({ state }) =>
    state === '모집중'
      ? '0 0 14px rgba(0, 166, 255, 0.28)'
      : '0 0 14px rgba(0, 0, 0, 0.12)'};
`;

const CardHeader = styled.div`
  display: flex;
  justify-content: space-between;
`;

const ClubProfile = styled.div`
  display: flex;
  align-items: center;
  gap: 18px;
`;

const ClubName = styled.h2`
  font-size: 1.125rem;
  font-weight: bold;
`;

const TagsContainer = styled.div`
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
`;

const Description = styled.p`
  font-size: 0.875rem;
  margin: 17px 3px 35px 5px;
`;

export {
  CardContainer,
  CardHeader,
  ClubProfile,
  ClubName,
  TagsContainer,
  Description,
};
