import styled from 'styled-components';

export const ClubDetailHeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 150px;

  @media (max-width: 500px) {
    & > button { 
      display: none;
    }
    margin-top: 40px;
`;

export const ClubDetailFooterContainer = styled.div`
  display: none;

  @media (max-width: 500px) {
    position: sticky;
    bottom: 0;
    width: 100%;
    z-index: 100;
    padding: 10px 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    background-color: white;
    border-top: 1px solid #cdcdcd;
  }
`;
