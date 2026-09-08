import PromotionCardView from '@/components/promotion/PromotionCardView/PromotionCardView';
import { USER_EVENT } from '@/constants/eventName';
import useMixpanelTrack from '@/hooks/Mixpanel/useMixpanelTrack';
import useNavigator from '@/hooks/useNavigator';
import { PromotionArticle } from '@/types/promotion';
import * as Styled from './PromotionCard.styles';

interface PromotionCardProps {
  article: PromotionArticle;
}

const PromotionCard = ({ article }: PromotionCardProps) => {
  const trackEvent = useMixpanelTrack();
  const handleLink = useNavigator();

  const handleCardClick = () => {
    trackEvent(USER_EVENT.PROMOTION_CARD_CLICKED, {
      promotionId: article.id,
    });

    handleLink(`/promotions/${article.id}`);
  };

  return (
    <Styled.Clickable onClick={handleCardClick}>
      <PromotionCardView article={article} />
    </Styled.Clickable>
  );
};

export default PromotionCard;
