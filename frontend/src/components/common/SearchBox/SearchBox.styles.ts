import styled from 'styled-components';

export const SearchBoxStyles = styled.form`
  margin-top: 5px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 300px;
  height: 36px;
  padding: 10px 20px;
  border: none;
  border-radius: 41px;
  background-color: #eeeeee;
`;

export const SearchInputStyles = styled.input`
  background-color: #eeeeee;
  height: 36px;
  border: none;
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
  border: none;
  background-color: transparent;
  font-size: 16px;
  cursor: pointer;
  img {
    width: 16px;
    height: auto;
  }
`;
