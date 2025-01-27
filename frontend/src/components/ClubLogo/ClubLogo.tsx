import React from 'react';
import styled, { css } from 'styled-components';

const logoSizes = {
  small: { imageSize: '45px' },
  large: { imageSize: '100px' },
};

type LogoSizeKeys = keyof typeof logoSizes;

interface ClubProfileProps {
  imageSrc?: string;
  logoSize?: LogoSizeKeys;
}

const StyledClubLogo = styled.div<{ size: LogoSizeKeys; imageSrc?: string }>`
  ${({ size, imageSrc }) => css`
    width: ${logoSizes[size].imageSize};
    height: ${logoSizes[size].imageSize};
    background-color: #efefef;
    border-radius: 10px;
    background-size: cover;
    background-position: center;
    background-image: ${imageSrc ? `url(${imageSrc})` : 'none'};
  `}
`;

const ClubLogo = ({ imageSrc, logoSize = 'small' }: ClubProfileProps) => {
  return <StyledClubLogo imageSrc={imageSrc} size={logoSize} />;
};

export default ClubLogo;
