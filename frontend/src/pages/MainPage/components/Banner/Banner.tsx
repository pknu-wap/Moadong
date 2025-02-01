interface BannerComponentProps {
  banners: BannerProps[];
}
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
