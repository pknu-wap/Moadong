import styled from 'styled-components';

export interface BannerProps {
  backgroundImage?: string;
  title?: string;
  description?: string;
}

export const BannerContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 80px;
  position: relative;
`;

export const BannerWrapper = styled.div<BannerProps>`
  position: relative;
  width: 1180px;
  height: 316px;
  border-radius: 20px;
  overflow: hidden;
  background-color: #ffddad;
  ${({ backgroundImage }) =>
    backgroundImage &&
    `
    background-image : url(${backgroundImage});
    background-size: cover;
    background-position: center;
    `}
`;

export const SlideWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 100%;
  transition: all 0.5s ease-in-out;
`;

export const Dash = styled.div`
  flex: none;
  width: 1180px;
  height: 316px;
`;

export const BannerTextContainer = styled.div`
  margin-left: 59px;
  margin-top: 180px;
  display: flex;
  flex-direction: column;
  gap: 5px;
`;

export const BannerTitle = styled.h1`
  font-size: 48px;
  font-weight: bold;
  color: #121212;
  letter-spacing: -0.02em;
`;

export const BannerDescription = styled.p`
  font-size: 27px;
  font-weight: 400;
  color: #000000;
  letter-spacing: -0.02em;
`;

export const ButtonContainer = styled.div`
  position: absolute;
  width: 100%;
  top: 50%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  transform: translateY(-50%);
  z-index: 10;
`;

export const NextButton = styled.button`
  background-color: transparent;
  color: black;
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
`;
