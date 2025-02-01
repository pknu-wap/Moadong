import styled from 'styled-components';

export interface BannerProps {
  backgroundImage?: string;
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

export const BannerItem = styled.div`
  flex: none;
  width: 1180px;
  height: 316px;
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
