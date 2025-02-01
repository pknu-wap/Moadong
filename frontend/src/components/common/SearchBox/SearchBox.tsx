import React from 'react';
import * as Styled from './SearchBox.styles';

const SearchBox = () => {
  return (
    <Styled.SearchBoxStyles>
      <Styled.SearchInputStyles placeholder='어떤 동아리를 찾으세요?' />
      <Styled.SearchButton />
    </Styled.SearchBoxStyles>
  );
};

export default SearchBox;
