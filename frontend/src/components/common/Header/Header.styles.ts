import styled from 'styled-components';

export const HeaderStyles = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  height: 52px;
  padding: 0 40px;
  max-width: 1180px;
  margin: 0 auto;
  z-index: 1;
  background-color: white;

  @media (max-width: 500px) {
    padding: 0 20px;
  }

  @media (max-width: 375px) {
    padding: 0 10px;
  }
`;

export const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  gap: 80px;
`;

export const TextCoverStyles = styled.div`
  display: flex;
  width: 365px;
  white-space: nowrap;
`;

export const LogoButtonStyles = styled.button`
  width: 152px;
  height: 43px;
  background-color: transparent;
  border: none;
  font-weight: 400;
  font-size: 26px;
  line-height: 40px;
  letter-spacing: -0.03em;
  color: #000000;
  cursor: pointer;

  img {
    width: 152px;
    height: auto;
    object-fit: cover;
  }
`;

export const IntroduceButtonStyles = styled.a`
  margin-left: 45px;
  width: 63px;
  height: 43px;
  font-weight: 500;
  font-size: 14px;
  line-height: 42px;
  letter-spacing: -0.02em;
  cursor: pointer;
`;
