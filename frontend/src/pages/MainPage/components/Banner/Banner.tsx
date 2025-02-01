import React, { useRef, useState, useEffect } from 'react';
import * as Styled from './Banner.styles';
import { BannerProps } from './Banner.styles';

interface BannerComponentProps {
  banners: BannerProps[];
}

const Banner = ({ banners }: BannerComponentProps) => {
  const slideRef = useRef<HTMLDivElement>(null);
  const [currentSlide, setCurrentSlide] = useState(0);
  const IMG_WIDTH = 1180;

  useEffect(() => {
    if (slideRef.current) {
      slideRef.current.style.transform = `translateX(-${currentSlide * IMG_WIDTH}px)`;
    }
  }, [currentSlide]);

  const moveToNextSlide = () => {
    if (currentSlide >= banners.length - 1) return;
    setCurrentSlide((prev) => prev + 1);
  };

  const moveToPrevSlide = () => {
    if (currentSlide <= 0) return;
    setCurrentSlide((prev) => prev - 1);
  };

  return (
    <Styled.BannerContainer>
      <Styled.BannerWrapper>
        <Styled.ButtonContainer>
          <Styled.NextButton onClick={moveToPrevSlide}>
            <img src='/prevButton.png' />
          </Styled.NextButton>
          <Styled.NextButton onClick={moveToNextSlide}>
            <img src='/nextButton.png' />
          </Styled.NextButton>
        </Styled.ButtonContainer>
        <Styled.SlideWrapper ref={slideRef}>
          {banners.map((banner, index) => (
            <Styled.Dash
              key={index}
              style={{
                backgroundImage: `url(${banner.backgroundImage})`,
              }}></Styled.Dash>
          ))}
        </Styled.SlideWrapper>
      </Styled.BannerWrapper>
    </Styled.BannerContainer>
  );
};

export default Banner;
