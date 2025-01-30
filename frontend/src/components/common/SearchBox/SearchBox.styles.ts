import styled from 'styled-components';

export const SearchBoxStyles = styled.form`
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

export const SearchInputStyles = styled.input`
  background-color: #eeeeee;
  height: 36px;
  border: none;
  border-radius: 41px;
  outline: none;
  font-size: 14px;

  &::placeholder {
    transition: opacity 0.3s;
  }
  &:focus::placeholder {
    opacity: 0;
  }
`;

export const SearchButton = styled.button`
  padding: 8px 16px;
  margin-left: 300px;
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
