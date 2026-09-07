import styled from 'styled-components';
import { colors } from '@/styles/theme/colors';
import { setTypography, typography } from '@/styles/theme/typography';

export const Dialog = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 322px;
  max-width: calc(100vw - 68px);
  padding: 30px 24px 18px;
  border-radius: 20px;
  background: ${colors.base.white};
`;

export const Title = styled.p`
  width: 100%;
  ${setTypography(typography.title.title5)};
  color: ${colors.base.black};
  text-align: center;
  letter-spacing: -0.4px;
`;

export const Actions = styled.div`
  display: flex;
  gap: 10px;
  width: 100%;
  margin-top: 34px;
`;

export const ActionButton = styled.button<{ $primary?: boolean }>`
  flex: 1;
  padding: 14px 0;
  border: ${({ $primary }) =>
    $primary ? 'none' : `1px solid ${colors.gray[300]}`};
  border-radius: 12px;
  background: ${({ $primary }) =>
    $primary ? colors.primary[900] : colors.base.white};
  ${setTypography(typography.paragraph.p2)};
  color: ${({ $primary }) => ($primary ? colors.base.white : colors.gray[600])};
  letter-spacing: -0.32px;
  cursor: pointer;
`;
