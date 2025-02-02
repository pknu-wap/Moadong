import React, { useState } from 'react';
import * as Styled from './StatusRadioButton.styles';

interface StatusRadioButtonProps {
  onChange: (selectedStatus: boolean) => void;
}

const StatusRadioButton = ({ onChange }: StatusRadioButtonProps) => {
  const [isActive, setIsActive] = useState(false);

  const handleToggle = () => {
    setIsActive((prev) => {
      const newStatus = !prev;
      onChange(newStatus);
      return newStatus;
    });
  };

  return (
    <Styled.RadioLabel>
      <Styled.RadioInput
        type='checkbox'
        checked={isActive}
        onChange={handleToggle}
      />
      <Styled.CustomRadio isActive={isActive} />
      <Styled.RadioText isActive={isActive}>
        모집중/모집예정 보기
      </Styled.RadioText>
    </Styled.RadioLabel>
  );
};

export default StatusRadioButton;
