import styled from 'styled-components';

export const IntroduceBoxWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 34px;
  width: 100%;
  height: 100%;
  border-radius: 18px;
  border: 1px solid #dcdcdc;
  padding: 30px;
  gap: 30px;

  @media (max-width: 500px) {
    margin-top: 0;
    width: 100%;
    border: none;
    border-radius: 0;
  }
`;

export const IntroduceTitle = styled.p`
  font-size: 20px;
  font-weight: 400;
  letter-spacing: -0.02px;
`;

export const IntroduceContentBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 15px;

  ol {
    padding-left: 20px;
  }
`;
