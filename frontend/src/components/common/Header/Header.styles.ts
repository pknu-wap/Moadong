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

export const introduceButtonStyles = styled.button`
  margin-left: 45px;
  width: 63px;
  height: 43px;
  background: transparent;
  border: none;
  font-weight: 500;
  font-size: 14px;
  line-height: 42px;
  letter-spacing: -0.02em;
  cursor: pointer;
`;

export const searchBoxStyles = styled.form`
  margin-top: 5px;
  margin-left: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 500px;
  height: 36px;
  padding: 20px;
  border: none;
  border-radius: 41px;
  background-color: #eeeeee;
`;

export const searchInputStyles = styled.input`
  background-color: #eeeeee;
  height: 36px;
  border: none;
  border-radius: 41px;
  outline: none;
  font-size: 14px;
`;

export const SearchButton = styled.button`
  padding: 8px 16px;
  margin-left: 8px;
  border: none;
  background-color: blueviolet;
  color: white;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: white;
  }
`;
