import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useTheme } from 'styled-components';
import BackChevronIcon from '@/assets/images/icons/back_chevron_icon.svg?react';
import NotificationIcon from '@/assets/images/icons/notification_icon.svg?react';
import Spinner from '@/components/common/Spinner/Spinner';
import Toast from '@/components/common/Toast/Toast';
import { PAGE_NAME, USER_EVENT } from '@/constants/eventName';
import useMixpanelTrack from '@/hooks/Mixpanel/useMixpanelTrack';
import { useScrollTrigger } from '@/hooks/Scroll/useScrollTrigger';
import useOpenAppFromKakao from '@/hooks/useOpenAppFromKakao';
import isInAppWebView from '@/utils/isInAppWebView';
import isKakaoTalkBrowser from '@/utils/isKakaoTalkBrowser';
import {
  requestNavigateBack,
  requestOpenAppSettings,
  requestSubscribeToggle,
  type AppToWebMessage,
} from '@/utils/webviewBridge';
import * as Styled from './ClubDetailTopBar.styles';
import UnsubscribeConfirmModal from './UnsubscribeConfirmModal';

export const SUBSCRIBED_TOAST_MESSAGE = '구독이 완료되었어요';
export const PERMISSION_TOAST_MESSAGE = '알림 권한을 켜 주세요';

interface TabItem {
  key: string;
  label: string;
}

interface ClubDetailTopBarProps {
  clubId: string;
  clubName: string;
  tabs?: TabItem[];
  activeTab?: string;
  onTabClick?: (tabKey: string) => void;
  initialIsSubscribed?: boolean;
  showTabs?: boolean;
}

const ClubDetailTopBar = ({
  clubId,
  clubName,
  tabs,
  activeTab,
  onTabClick,
  initialIsSubscribed = false,
  showTabs = false,
}: ClubDetailTopBarProps) => {
  const navigate = useNavigate();
  const theme = useTheme();
  const isInApp = isInAppWebView();
  const isKakao = !isInApp && isKakaoTalkBrowser();
  const { openApp, isLoading } = useOpenAppFromKakao();
  const trackEvent = useMixpanelTrack();
  const [isNotificationActive, setIsNotificationActive] =
    useState(initialIsSubscribed);
  const [isUnsubscribeModalOpen, setIsUnsubscribeModalOpen] = useState(false);
  const [toastMessage, setToastMessage] = useState<string | null>(null);

  useEffect(() => {
    const handleMessage = (event: MessageEvent) => {
      let data: AppToWebMessage;
      try {
        data =
          typeof event.data === 'string' ? JSON.parse(event.data) : event.data;
      } catch {
        return;
      }
      if (data.type === 'SUBSCRIBE_STATE') {
        setIsNotificationActive(
          data.payload.subscribedClubIds.includes(clubId),
        );
      } else if (
        data.type === 'SUBSCRIBE_RESULT' &&
        data.payload.clubId === clubId
      ) {
        const { subscribed, needsPermission } = data.payload;
        setIsNotificationActive(subscribed);
        // 앱이 실제로 처리한 결과에만 반응한다. 권한이 막혀 있으면 "완료"라고 말하지 않는다.
        if (needsPermission) {
          setToastMessage(PERMISSION_TOAST_MESSAGE);
        } else if (subscribed) {
          setToastMessage(SUBSCRIBED_TOAST_MESSAGE);
        }
      }
    };
    window.addEventListener('message', handleMessage);
    return () => window.removeEventListener('message', handleMessage);
  }, [clubId]);

  const { isTriggered: isHeaderVisible } = useScrollTrigger({
    threshold: 0,
    direction: 'down',
  });

  const handleBackClick = () => {
    trackEvent(USER_EVENT.BACK_BUTTON_CLICKED);
    const handled = requestNavigateBack();
    if (!handled) {
      // 히스토리 스택이 있으면 뒤로가기, 없으면(직접 진입 등) 메인으로 이동
      if (window.history.state && window.history.state.idx > 0) {
        navigate(-1);
      } else {
        navigate('/', { replace: true });
      }
    }
  };

  const requestToggle = () => {
    requestSubscribeToggle(clubId);
    trackEvent(USER_EVENT.WEBVIEW_SUBSCRIBE_TOGGLED, {
      club_id: clubId,
      subscribed: !isNotificationActive,
      source: PAGE_NAME.CLUB_DETAIL,
    });
  };

  // 구독 해제는 되돌리기 번거로우니 한 번 더 묻고, 구독은 바로 보낸다.
  const handleNotificationClick = () => {
    if (isNotificationActive) {
      setIsUnsubscribeModalOpen(true);
      return;
    }
    requestToggle();
  };

  const handleUnsubscribeConfirm = () => {
    setIsUnsubscribeModalOpen(false);
    requestToggle();
  };

  // 권한 안내 토스트만 탭할 수 있다. 완료 토스트는 이어지는 동작이 없다.
  const handlePermissionToastClick = () => {
    setToastMessage(null);
    requestOpenAppSettings();
  };

  return (
    <>
      <Styled.TopBarWrapper $isVisible={isHeaderVisible || showTabs}>
        <Styled.TopBarContent $isVisible={isHeaderVisible}>
          <Styled.IconButtonWrapper>
            <Styled.IconButton
              $isVisible={isHeaderVisible}
              onClick={handleBackClick}
              aria-label='뒤로가기'
            >
              <BackChevronIcon width={48} height={48} />
            </Styled.IconButton>
          </Styled.IconButtonWrapper>
          <Styled.ClubName $isVisible={isHeaderVisible}>
            {clubName}
          </Styled.ClubName>
          {isInApp ? (
            <Styled.IconButtonWrapper>
              <Styled.NotificationButton
                $isVisible={isHeaderVisible}
                $isActive={isNotificationActive}
                onClick={handleNotificationClick}
                aria-label='알림 설정'
              >
                <NotificationIcon
                  width={21}
                  height={21}
                  fill={
                    isNotificationActive
                      ? theme.colors.primary[900]
                      : theme.colors.gray[500]
                  }
                />
              </Styled.NotificationButton>
            </Styled.IconButtonWrapper>
          ) : isKakao ? (
            <>
              {isLoading && (
                <Styled.LoadingOverlay>
                  <Spinner height='auto' />
                </Styled.LoadingOverlay>
              )}
              <Styled.AppOpenButton onClick={() => openApp()}>
                앱열기
              </Styled.AppOpenButton>
            </>
          ) : (
            <Styled.Placeholder />
          )}
        </Styled.TopBarContent>
        {tabs && showTabs && (
          <Styled.TabBar>
            {tabs.map((tab) => (
              <Styled.TabButton
                key={tab.key}
                $active={activeTab === tab.key}
                onClick={() => onTabClick?.(tab.key)}
              >
                {tab.label}
              </Styled.TabButton>
            ))}
          </Styled.TabBar>
        )}
      </Styled.TopBarWrapper>
      <UnsubscribeConfirmModal
        isOpen={isUnsubscribeModalOpen}
        onClose={() => setIsUnsubscribeModalOpen(false)}
        onConfirm={handleUnsubscribeConfirm}
      />
      <Toast
        isOpen={toastMessage !== null}
        onClose={() => setToastMessage(null)}
        message={toastMessage ?? ''}
        onClick={
          toastMessage === PERMISSION_TOAST_MESSAGE
            ? handlePermissionToastClick
            : undefined
        }
      />
    </>
  );
};

export default ClubDetailTopBar;
