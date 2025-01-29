import styled from 'styled-components';

export const HeaderStyles = styled.header`
  position: fixed;
  display: flex;
  left: 0;
  top: 0;
  width: 100%;
  height: 52px;
  z-index: 1;
`;

export const TextCoverStyles = styled.div`
  display: flex;
  width: 365px;
`;

export const LogoButtonStyles = styled.button`
  margin-left: 130px;
  width: 117px;
  height: 41px;
  background-color: transparent;
  border: none;
  font-style: normal;
  font-weight: 400;
  font-size: 26px;
  line-height: 40px;
  letter-spacing: -0.03em;
  color: #000000;
  cursor: pointer;
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
