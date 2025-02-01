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
