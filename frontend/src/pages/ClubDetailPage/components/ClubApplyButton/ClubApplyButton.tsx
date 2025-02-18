import React from 'react';
import styled from 'styled-components';

interface ButtonProps {
  onClick?: () => void;
}

const Button = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: transform 0.2s ease-in-out;

  background-color: #3a3a3a;
  color: white;
  font-weight: bold;

  width: 148px;
  height: 44px;
  font-size: 1.25rem;

  &:hover {
    background-color: #555;
    transform: scale(1.03);
  }

  @media (max-width: 500px) {
    width: 256px;
    height: 44px;
    font-size: 1rem;
  }
`;

const ClubApplyButton = ({ onClick }: ButtonProps) => {
  return <Button onClick={onClick}>지원하기</Button>;
};

export default ClubApplyButton;
