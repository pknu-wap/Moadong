import { useId } from 'react';
import Modal from '@/components/common/Modal/Modal';
import * as Styled from './UnsubscribeConfirmModal.styles';

interface UnsubscribeConfirmModalProps {
  isOpen: boolean;
  onClose: () => void;
  onConfirm: () => void;
}

/** 구독 중인 동아리의 종을 눌렀을 때 실수로 끊지 않도록 한 번 더 묻는다 */
const UnsubscribeConfirmModal = ({
  isOpen,
  onClose,
  onConfirm,
}: UnsubscribeConfirmModalProps) => {
  const titleId = useId();

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <Styled.Dialog role='dialog' aria-modal='true' aria-labelledby={titleId}>
        <Styled.Title id={titleId}>정말 구독을 취소하시겠어요?</Styled.Title>
        <Styled.Actions>
          <Styled.ActionButton type='button' onClick={onClose}>
            아니요
          </Styled.ActionButton>
          <Styled.ActionButton type='button' $primary onClick={onConfirm}>
            네
          </Styled.ActionButton>
        </Styled.Actions>
      </Styled.Dialog>
    </Modal>
  );
};

export default UnsubscribeConfirmModal;
